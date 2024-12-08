package pro.entera.resource_service.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pro.entera.resource_service.aop.Cacheable;
import pro.entera.resource_service.converters.BankKazConverter;
import pro.entera.resource_service.models.BankKaz;
import pro.entera.resource_service.models.BankRus;
import pro.entera.resource_service.repositories.BankKazRepository;
import pro.entera.resource_service.repositories.BankRusRepository;

import java.util.Comparator;
import java.util.List;
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

    private final BankKazConverter bankKazConverter;

    //endregion
    //region Public

    @Cacheable
    @Override
    public List<BankRus> findAllRus() {

        return this.bankRusRepository.findAll();
    }

    @Cacheable
    @Override
    public List<BankKaz> findAllKaz() {

        return this.bankKazRepository.findAll();
    }

    @Override
    public List<BankRus> find(String searchString, String countryCode) {

        final List<BankRus> banks;

        if (searchString == null || searchString.length() < MIN_SEARCH_LENGTH) {

            banks = List.of();
        } else if (KAZ_COUNTRY_CODE.equals(countryCode)) {

            banks = this.findBankKazlist(searchString)
                .stream()
                .map(this.bankKazConverter::toBankRus)
                .toList();
        } else {

            banks = this.findBankRuslist(searchString);
        }

        return banks;
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

    private List<BankRus> findBankRuslist(String searchString) {

        Predicate<BankRus> searchPredicate = createStringFieldSearchPredicate(BankRus::getName, searchString)
            .or((createStringFieldSearchPredicate(BankRus::getBic, searchString)))
            .or(createStringFieldSearchPredicate(BankRus::getAccount, searchString));

        return this.findAllRus()
            .stream()
            .filter(searchPredicate)
            .sorted(Comparator.comparing(BankRus::getBic))
            .limit(MAX_SEARCH_RESULT_LENGTH)
            .toList();
    }

    private List<BankKaz> findBankKazlist(String searchString) {

        Predicate<BankKaz> searchPredicate = createStringFieldSearchPredicate(BankKaz::getName, searchString)
            .or((createStringFieldSearchPredicate(BankKaz::getBic, searchString)))
            .or(createStringFieldSearchPredicate(BankKaz::getRnn, searchString));

        return this.findAllKaz()
            .stream()
            .filter(searchPredicate)
            .sorted(Comparator.comparing(BankKaz::getBic))
            .limit(MAX_SEARCH_RESULT_LENGTH)
            .toList();
    }

    //endregion
}
