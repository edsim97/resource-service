package pro.entera.resource_service.services;

import pro.entera.resource_service.dtos.UnitDto;
import pro.entera.resource_service.models.Unit;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UnitService {
    //region Public

    Flux<Unit> findAll();

    /**
     * Возвращает эталонную единицу измерения из кеша по ее коду ОКЕИ.
     *
     * @param unitCode Код единицы измерения в ОКЕИ.
     * @return Единица измерения.
     */
    Mono<Unit> findByOkeiCode(String unitCode);

    Mono<Unit> findByName(String name);

    /**
     * Возвращает эталонную единицу измерения для указанного кода единицы измерения или синонима ед. изм.
     * Сначала попытается найти единицу измерения по предоставленному коду единицы измерения, если не получится
     * то по синониму.
     *
     * @param unitCode Код единицы измерения в ОКЕИ.
     * @param unitName Наименование единицы измерения.
     *
     * @return Эталонная единица измерения из справочника.
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
     * Возвращает эталонную единицу измерения из кеша.
     * Сначала ищет по названию, если не находит, то ищет по синонимам.
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
