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
     * Трехзначный (альфа-3) буквенный код страны, состоящий из букв латинского алфавита.
     */
    @EqualsAndHashCode.Include
    private final String alpha3Code;

    /**
     * Двухзначный (альфа-2) буквенный код страны, состоящий из букв латинского алфавита.
     */
    private final String alpha2Code;

    /**
     * Трехзначный цифровой код ОКСМ.
     */
    private final String code;

    /**
     * Краткое наименование страны.
     */
    private final String shortName;

    /**
     * Полное наименование страны.
     */
    private final String fullName;

    /**
     * Английское название страны.
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
