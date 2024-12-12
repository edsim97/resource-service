package pro.entera.resource_service.services;


import pro.entera.resource_service.dtos.CurrencyDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Сервис для работы с валютами.
 */
public interface CurrencyService {
    //region public

    /**
     * Выполняет поиск валюты по буквенному трёхзначному коду.
     *
     * @param alpha3Code Буквенный трёхзначный код.
     *
     * @return Валюта.
     */
    Mono<CurrencyDto> findByAlpha3Code(String alpha3Code);

    /**
     * Возвращает все валюты.
     *
     * @return Список валют.
     */
    Flux<CurrencyDto> findAll();

    //endregion
}
