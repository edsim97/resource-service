package pro.entera.resource_service.services;

import pro.entera.resource_service.dtos.UnitDto;
import pro.entera.resource_service.models.Unit;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Сервис для работы с единицами измерения.
 */
public interface UnitService {
    //region Public

    /**
     * Возвращает все единицы измерения.
     *
     * @return Список единиц измерения.
     */
    Flux<Unit> findAll();

    /**
     * Выполняет поиск единицы измерения по ее коду ОКЕИ.
     *
     * @param unitCode Код единицы измерения в ОКЕИ.
     *
     * @return Единица измерения.
     */
    Mono<Unit> findByOkeiCode(String unitCode);

    /**
     * Выполняет поиск единицы измерения по ее краткому наименованию.
     *
     * @param name Краткое наименование единицы измерения.
     *
     * @return Единица измерения.
     */
    Mono<Unit> findByName(String name);

    /**
     * Возвращает единицу измерения для указанного кода или краткого наименования.
     * Сперва попытается найти единицу измерения по коду, если ничего не нашлось то по краткому наименованию и синониму.
     *
     * @param unitCode Код единицы измерения в ОКЕИ.
     * @param unitName Краткое наименование единицы измерения.
     *
     * @return Единица измерения.
     */
    default Mono<UnitDto> findUnit(String unitCode, String unitName) {

        return Mono.justOrEmpty(unitCode)
            .flatMap(this::findByOkeiCode)
            .switchIfEmpty(
                Mono.justOrEmpty(unitName).flatMap(this::findByNameOrSynonym)
            )
            .map(UnitDto::from);
    }

    /**
     * Выполняет поиск единицы измерения по строке.
     * Сначала ищет по краткому наименованию, если не находит, то ищет по синонимам.
     *
     * @param unitName Строковое значение единицы измерения для поиска.
     *
     * @return Единица измерения.
     */
    default Mono<Unit> findByNameOrSynonym(String unitName) {

        return this.findByName(unitName)
            .switchIfEmpty(
                this.findAll()
                    .filter((Unit unit) -> unit.isSynonymous(unitName))
                    .next()
            );
    }

    //endregion
}
