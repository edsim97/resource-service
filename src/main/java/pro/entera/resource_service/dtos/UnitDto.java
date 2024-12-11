package pro.entera.resource_service.dtos;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import pro.entera.resource_service.models.Unit;

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

    public static UnitDto from(Unit unit) {

        return new UnitDto(
            unit.getCode(),
            unit.getFullName(),
            unit.getName()
        );
    }

    //endregion
}
