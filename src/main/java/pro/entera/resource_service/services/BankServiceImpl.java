package pro.entera.resource_service.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pro.entera.resource_service.dtos.BankDto;
import pro.entera.resource_service.models.BankKaz;
import pro.entera.resource_service.models.BankRus;
import pro.entera.resource_service.repositories.BankKazRepository;
import pro.entera.resource_service.repositories.BankRusRepository;
import reactor.core.publisher.Flux;

import java.util.Comparator;
import java.util.Locale;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

@AllArgsConstructor
@Service
public class BankServiceImpl implements BankService {
    //region Constants

    private static final String KAZ_COUNTRY_CODE = "KAZ";

    private static final int MIN_SEARCH_LENGTH = 3;

    private static final int MAX_SEARCH_RESULT_LENGTH = 20;

    //endregion
    //region Fields

    private final BankKazRepository bankKazRepository;

    private final BankRusRepository bankRusRepository;

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

    //endregion
    //region Private

    private Flux<BankRus> findBankRusList(String searchString) {

        Predicate<BankRus> searchPredicate = createStringFieldSearchPredicate(BankRus::getName, searchString)
            .or((createStringFieldSearchPredicate(BankRus::getBic, searchString)))
            .or(createStringFieldSearchPredicate(BankRus::getAccount, searchString));

        return this.findAllRus()
            .filter(searchPredicate)
            .sort(Comparator.comparing(BankRus::getBic))
            .limitRate(MAX_SEARCH_RESULT_LENGTH);
    }

    private Flux<BankKaz> findBankKazList(String searchString) {

        Predicate<BankKaz> searchPredicate = createStringFieldSearchPredicate(BankKaz::getName, searchString)
            .or((createStringFieldSearchPredicate(BankKaz::getBic, searchString)))
            .or(createStringFieldSearchPredicate(BankKaz::getRnn, searchString));

        return this.findAllKaz()
            .filter(searchPredicate)
            .sort(Comparator.comparing(BankKaz::getBic))
            .limitRate(MAX_SEARCH_RESULT_LENGTH);
    }

    //endregion
}
