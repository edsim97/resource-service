package pro.entera.resource_service.dtos;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import pro.entera.resource_service.models.Unit;

/**
 * DTO единицы измерения.
 */
@AllArgsConstructor
@EqualsAndHashCode
@Getter
public class UnitDto {
    //region Fields

    /**
     * Код единицы измерения по классификатору ОКЕИ.
     */
    private String code;

    /**
     * Полное название единицы измерения.
     */
    private String fullName;

    /**
     * Краткое название единицы измерения.
     */
    private String name;

    //endregion
    //region Static factories

    /**
     * Статическая фабрика DTO единицы измерения.
     *
     * @param unit Модель единицы измерения.
     *
     * @return DTO единицы измерения.
     */
    public static UnitDto from(Unit unit) {

        return new UnitDto(
            unit.getCode(),
            unit.getFullName(),
            unit.getName()
        );
    }

    //endregion
}
