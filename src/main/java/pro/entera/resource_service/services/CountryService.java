package pro.entera.resource_service.services;

import pro.entera.resource_service.dtos.CountryDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Сервис для работы со странами.
 */
public interface CountryService {
    //region public

    /**
     * Выполняет поиск страны по буквенному трёхзначному коду.
     *
     * @param alpha3Code Буквенный трёхзначный код.
     *
     * @return Страна.
     */
    Mono<CountryDto> findByAlpha3Code(String alpha3Code);

    /**
     * Выполняет поиск страны по её коду ОКСМ.
     *
     * @param code Код ОКСМ.
     *
     * @return Страна.
     */
    Mono<CountryDto> findByOksmCode(String code);

    /**
     * Возвращает все страны.
     *
     * @return Список стран.
     */
    Flux<CountryDto> findAll();

    //endregion
}
