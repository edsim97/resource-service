package pro.entera.resource_service.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import pro.entera.resource_service.models.Currency;

/**
 * DTO валюты.
 */
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Builder
public class CurrencyDto {
    //region Fields

    /**
     * Трехзначный (альфа-3) буквенный код валюты, состоящий из латинского алфавита.
     */
    @EqualsAndHashCode.Include
    private final String alpha3Code;

    /**
     * Трехзначный цифровой код ISO-4217.
     */
    private final String numCode;

    /**
     * Наименование валюты на русском языке.
     */
    private final String nameRus;

    /**
     * Наименование валюты на английском языке.
     */
    private final String nameEng;

    //endregion
    //region Static factories

    /**
     * Статическая фабрика DTO валюты.
     *
     * @param currency Модель валюты.
     *
     * @return DTO валюты.
     */
    public static CurrencyDto from(Currency currency) {

        return new CurrencyDto(
            currency.getAlpha3Code(),
            currency.getNumCode(),
            currency.getNameRus(),
            currency.getNameEng()
        );
    }

    //endregion
}
