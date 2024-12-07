package pro.entera.resource_service.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import pro.entera.resource_service.models.Currency;

@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Builder
public class CurrencyDto {
    //region Fields

    /**
     * <p>Трехзначный (альфа-3) буквенный код валюты, состоящий из латинского алфавита.</p>
     */
    @EqualsAndHashCode.Include
    private final String alpha3Code;

    /**
     * <p>Трехзначный цифровой код ISO-4217.</p>
     */
    private final String numCode;

    /**
     * <p>Наименование валюты на русском языке.</p>
     */
    private final String nameRus;

    /**
     * <p>Наименование валюты на английском языке.</p>
     */
    private final String nameEng;

    //endregion
    //region Static factories

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
