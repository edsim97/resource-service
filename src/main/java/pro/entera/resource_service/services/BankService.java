package pro.entera.resource_service.services;

import pro.entera.resource_service.dtos.BankDto;
import pro.entera.resource_service.models.BankKaz;
import pro.entera.resource_service.models.BankRus;
import reactor.core.publisher.Flux;

public interface BankService {
    //region Public

    /**
     * Возвращает все российские банки.
     *
     * @return Список всех российских банков.
     */
    Flux<BankRus> findAllRus();

    /**
     * Возвращает все казахские банки.
     *
     * @return Список всех казахских банков.
     */
    Flux<BankKaz> findAllKaz();

    /**
     * Выполняет поиск банков, которые подходят под поисковой запрос, и возвращает список подошедших банков.
     * Совпадение ищется в названии банка, БИК'е банка, корреспондентском счёте банка.
     * Если поисковой запрос не задан или короче 3 символов, то будет возвращён пустой список.
     * Казахские банки конвертируются в российские.
     *
     * @param searchString Поисковой запрос.
     * @param countryCode  Код страны.
     * @return Список банков, которые подошли под поисковой запрос.
     */
    Flux<BankDto> find(String searchString, String countryCode);
    
    //endregion
}
