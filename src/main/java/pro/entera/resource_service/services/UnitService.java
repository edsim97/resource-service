package pro.entera.resource_service.services;

import pro.entera.resource_service.dtos.UnitDto;
import pro.entera.resource_service.models.Unit;

import java.util.List;
import java.util.Optional;

public interface UnitService {
    //region Public

    List<Unit> findAll();

    /**
     * Возвращает эталонную единицу измерения из кеша по ее коду ОКЕИ.
     *
     * @param unitCode Код единицы измерения в ОКЕИ.
     *
     * @return Единица измерения.
     */
    Unit findByOkeiCode(String unitCode);

    Unit findByName(String name);

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
    default UnitDto findUnit(String unitCode, String unitName) {

        return Optional.ofNullable(unitCode)
            .map(this::findByOkeiCode)
            .or(() -> Optional.ofNullable(unitName)
                .map(this::findByNameOrSynonym)
            )
            .map(UnitDto::from)
            .orElse(null);
    }

    /**
     * Возвращает эталонную единицу измерения из кеша.
     * Сначала ищет по названию, если не находит, то ищет по синонимам.
     *
     * @param unitName Строковое значение единицы измерения для поиска.
     *
     * @return Единица измерения.
     */
    default Unit findByNameOrSynonym(String unitName) {

        return Optional.ofNullable(this.findByName(unitName))
            .or(() -> this.findAll().stream()
                .filter((Unit unit) -> unit.isSynonymous(unitName))
                .findFirst()
            )
            .orElse(null);
    }

    //endregion
}
