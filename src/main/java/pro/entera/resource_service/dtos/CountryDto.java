package pro.entera.resource_service.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import pro.entera.resource_service.models.Country;

@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Builder
public class CountryDto {
    //region Fields

    /**
     * <p>Трехзначный (альфа-3) буквенный код страны, состоящий из букв латинского алфавита.</p>
     */
    @EqualsAndHashCode.Include
    private final String alpha3Code;

    /**
     * <p>Двухзначный (альфа-2) буквенный код страны, состоящий из букв латинского алфавита.</p>
     */
    private final String alpha2Code;

    /**
     * <p>Трехзначный цифровой код ОКСМ.</p>
     */
    private final String code;

    /**
     * <p>Краткое наименование страны.</p>
     */
    private final String shortName;

    /**
     * <p>Полное наименование страны.</p>
     */
    private final String fullName;

    /**
     * <p>Английское название страны.</p>
     */
    private final String nameEng;

    //endregion
    //region Static factories

    public static CountryDto from(Country country) {

        return new CountryDto(
            country.getAlpha3Code(),
            country.getAlpha2Code(),
            country.getCode(),
            country.getShortName(),
            country.getFullName(),
            country.getNameEng()
        );
    }

    //endregion
}
