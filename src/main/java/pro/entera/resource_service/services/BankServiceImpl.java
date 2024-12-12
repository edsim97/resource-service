package pro.entera.resource_service.services;

import lombok.AllArgsConstructor;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import pro.entera.resource_service.configurations.BankUrlConfig;
import pro.entera.resource_service.dtos.banks.BankDto;
import pro.entera.resource_service.dtos.banks.kaz.BankKazDto;
import pro.entera.resource_service.dtos.banks.kaz.KazBankXmlRoot;
import pro.entera.resource_service.dtos.banks.rus.BankRusDto;
import pro.entera.resource_service.dtos.banks.rus.ED807;
import pro.entera.resource_service.models.BankKaz;
import pro.entera.resource_service.models.BankRus;
import pro.entera.resource_service.repositories.BankKazRepository;
import pro.entera.resource_service.repositories.BankRusRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@AllArgsConstructor
@Service
public class BankServiceImpl implements BankService {
    //region Constants

    /**
     * Трёхбуквенный код страны для Казахстана.
     */
    private static final String KAZ_COUNTRY_CODE = "KAZ";

    /**
     * Минимальная длина поисковой строки.
     */
    private static final int MIN_SEARCH_LENGTH = 3;

    /**
     * Максимальная длина списка результатов поиска.
     */
    private static final int MAX_SEARCH_RESULT_LENGTH = 20;

    //endregion
    //region Fields

    private final BankKazRepository bankKazRepository;

    private final BankRusRepository bankRusRepository;

    private final BankUrlConfig bankUrlConfig;

    //endregion
    //region Public

    @Override
    public Flux<BankRus> findAllRus() {

        return this.bankRusRepository.findAll();
    }

    @Override
    public Flux<BankKaz> findAllKaz() {

        return this.bankKazRepository.findAll();
    }

    @Override
    public Flux<BankDto> find(String searchString, String countryCode) {

        return Flux.just(searchString, countryCode)
            .filter(search -> search != null && search.length() > MIN_SEARCH_LENGTH)
            .flatMap(search -> {
                if (KAZ_COUNTRY_CODE.equals(countryCode)) {

                    return this.findBankKazList(search).map(BankDto::from);
                } else {

                    return this.findBankRusList(search).map(BankDto::from);
                }
            })
            .switchIfEmpty(Flux.error(() -> new IllegalArgumentException(
                "Search parameter must be at least %s characters long".formatted(MIN_SEARCH_LENGTH)
            )));
    }

    @Override
    public void fetchAndUpdateRus() {

        WebClient.builder()
            .build().get()
            .uri(this.bankUrlConfig.getRus())
            .accept(MediaType.APPLICATION_OCTET_STREAM)
            .retrieve()
            .bodyToMono(DataBuffer.class)
            .flatMap(
                zip -> Mono.fromCallable(() -> this.processRusZipContent(zip))
                    .onErrorMap(e -> new RuntimeException("Error processing ZIP content", e))
            )
            .map(ED807::getBicDirectoryEntry)
            .map(entries -> entries.stream().map(BankRusDto::fromEntry).toList())
            .map(this::updateRusBanks)
            .subscribe();
    }

    @Override
    public void fetchAndUpdateKaz() {

        WebClient.builder()
            .build().get()
            .uri(this.bankUrlConfig.getKaz())
            .accept(MediaType.APPLICATION_OCTET_STREAM)
            .retrieve()
            .bodyToMono(DataBuffer.class)
            .flatMap(
                xml -> Mono.fromCallable(() -> this.processKazXml(xml))
                    .onErrorMap(e -> new RuntimeException("Error processing XML content", e))
            )
            .map(KazBankXmlRoot::getKazBankXmlRowList)
            .map(banks -> banks.stream().map(BankKazDto::fromEntry).toList())
            .map(this::updateKazBanks)
            .subscribe();
    }

    //endregion
    //region Private static

    /**
     * Создаёт предикат для поиска по строковому полю.
     *
     * @param fieldGetter Функция для получения значения поля строкового типа.
     * @param search Поисковой запрос.
     *
     * @return Предикат для поиска по строковому полю.
     */
    private static <T> Predicate<T> createStringFieldSearchPredicate(Function<T, String> fieldGetter, String search) {

        return (T object) -> Optional.ofNullable(fieldGetter.apply(object))
            .orElse("")
            .toLowerCase(Locale.ROOT)
            .contains(search.toLowerCase(Locale.ROOT));
    }

    /**
     * Обновляет российский банк из его DTO.
     *
     * @param bank Банк.
     * @param dto DTO банка.
     *
     * @return Обновлённый банк.
     */
    private static BankRus updateFromDto(BankRus bank, BankRusDto dto) {

        return bank.toBuilder()
            .bic(dto.bic())
            .name(dto.name())
            .zip(dto.zip())
            .settlementType(dto.settlementType())
            .settlementName(dto.settlementName())
            .address(dto.address())
            .account(dto.account())
            .build();
    }

    /**
     * Обновляет казахский банк из его DTO.
     *
     * @param bank Банк.
     * @param dto DTO банка.
     *
     * @return Обновлённый банк.
     */
    private static BankKaz updateFromDto(BankKaz bank, BankKazDto dto) {

        return bank.toBuilder()
            .bic(dto.bic())
            .bin(dto.bin())
            .category(dto.category())
            .city(dto.city())
            .country(dto.country())
            .dsc(dto.dsc())
            .house(dto.house())
            .kato(dto.kato())
            .name(dto.name())
            .zip(dto.zip())
            .rnn(dto.rnn())
            .street(dto.street())
            .build();
    }

    //endregion
    //region Private

    /**
     * Выполняет поиск российских банков по строке поиска.
     * Банки ищутся по названию, БИК-у и кор. счёту.
     *
     * @param searchString Строка поиска.
     *
     * @return Список российских банков.
     */
    private Flux<BankRus> findBankRusList(String searchString) {

        Predicate<BankRus> searchPredicate = createStringFieldSearchPredicate(BankRus::getName, searchString)
            .or((createStringFieldSearchPredicate(BankRus::getBic, searchString)))
            .or(createStringFieldSearchPredicate(BankRus::getAccount, searchString));

        return this.findAllRus()
            .filter(searchPredicate)
            .sort(Comparator.comparing(BankRus::getBic))
            .limitRate(MAX_SEARCH_RESULT_LENGTH);
    }

    /**
     * Выполняет поиск казахских банков по строке поиска.
     * Банки ищутся по названию, БИК-у и РНН-у.
     *
     * @param searchString Строка поиска.
     *
     * @return Список казахских банков.
     */
    private Flux<BankKaz> findBankKazList(String searchString) {

        Predicate<BankKaz> searchPredicate = createStringFieldSearchPredicate(BankKaz::getName, searchString)
            .or((createStringFieldSearchPredicate(BankKaz::getBic, searchString)))
            .or(createStringFieldSearchPredicate(BankKaz::getRnn, searchString));

        return this.findAllKaz()
            .filter(searchPredicate)
            .sort(Comparator.comparing(BankKaz::getBic))
            .limitRate(MAX_SEARCH_RESULT_LENGTH);
    }

    /**
     * Обновляет российские банки указанными в DTO данными.
     * Выставляет значение в annullingDate банкам, которых нет в списке.
     * Создаёт банки, которые есть в списке, но которых нет в БД.
     * Обновляет банки, которые есть и в списке, и в БД.
     *
     * @param banks Список DTO банков.
     *
     * @return Флакс с обновлёнными банками.
     */
    private Flux<BankRus> updateRusBanks(List<BankRusDto> banks) {

        Set<String> fetchedBicSet = banks.stream().map(BankRusDto::bic).collect(Collectors.toSet());
        this.bankRusRepository.findByBicNotInAndAnnullingDateIsNull(fetchedBicSet)
            .map(dbBank -> dbBank.toBuilder().annullingDate(Instant.now()).build())
            .flatMap(bankRusRepository::save)
            .subscribe();

        return Flux.fromIterable(banks)
            .flatMap(
                bank -> bankRusRepository.findByBic(bank.bic())
                    .flatMap(existingBank -> bankRusRepository.save(updateFromDto(existingBank, bank)))
                    .switchIfEmpty(
                        bankRusRepository.save(updateFromDto(BankRus.builder().id(UUID.randomUUID()).build(), bank))
                    )
            );
    }

    /**
     * Обновляет казахские банки указанными в DTO данными.
     * Выставляет значение в annullingDate банкам, которых нет в списке.
     * Создаёт банки, которые есть в списке, но которых нет в БД.
     * Обновляет банки, которые есть и в списке, и в БД.
     *
     * @param banks Список DTO банков.
     *
     * @return Флакс с обновлёнными банками.
     */
    private Flux<BankKaz> updateKazBanks(List<BankKazDto> banks) {

        Set<String> fetchedRnnSet = banks.stream().map(BankKazDto::rnn).collect(Collectors.toSet());
        this.bankKazRepository.findByRnnNotInAndAnnullingDateIsNull(fetchedRnnSet)
            .map(dbBank -> dbBank.toBuilder().annullingDate(Instant.now()).build())
            .flatMap(bankKazRepository::save)
            .subscribe();

        return Flux.fromIterable(banks)
            .flatMap(
                bank -> bankKazRepository.findByRnn(bank.rnn())
                    .flatMap(existingBank -> bankKazRepository.save(updateFromDto(existingBank, bank)))
                    .switchIfEmpty(
                        bankKazRepository.save(BankKaz.fromDTO(bank))
                    )
            );
    }

    /**
     * Обрабатывает ZIP-архив, содержащий XML файл с данными всех банков в России.
     *
     * @param zip ZIP-архив.
     *
     * @return Список банков по модели справочника БИК (ED807).
     */
    private ED807 processRusZipContent(DataBuffer zip) {

        byte[] bytes = new byte[zip.readableByteCount()];
        zip.read(bytes);
        DataBufferUtils.release(zip);

        try (ZipInputStream zis = new ZipInputStream(new ByteArrayInputStream(bytes))) {

            ZipEntry entry = zis.getNextEntry();
            if (entry == null) {
                throw new IOException("No entry found in ZIP");
            }

            JAXBContext jaxbContext = JAXBContext.newInstance(ED807.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            return (ED807) jaxbUnmarshaller.unmarshal(zis);

        } catch (IOException | JAXBException e) {

            throw new RuntimeException("Failed to process ZIP content", e);
        }
    }

    /**
     * Обрабатывает XML-файл, содержащий список всех банков в Казахстане.
     *
     * @param xml XML-файл со списком банков в Казахстане.
     *
     * @return Список банков в Казахстане.
     */
    private KazBankXmlRoot processKazXml(DataBuffer xml) {

        byte[] bytes = new byte[xml.readableByteCount()];
        xml.read(bytes);
        DataBufferUtils.release(xml);

        try (InputStream bais = new ByteArrayInputStream(bytes)) {

            JAXBContext jaxbContext = JAXBContext.newInstance(KazBankXmlRoot.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            return (KazBankXmlRoot) jaxbUnmarshaller.unmarshal(bais);

        } catch (IOException | JAXBException e) {

            throw new RuntimeException("Failed to process XML content", e);
        }
    }

    //endregion
}
