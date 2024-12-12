package pro.entera.resource_service.dtos.banks;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import pro.entera.resource_service.models.BankKaz;
import pro.entera.resource_service.models.BankRus;

/**
 * DTO банка.
 */
@AllArgsConstructor
@EqualsAndHashCode
@Getter
public class BankDto {
    //region Fields

    /**
     * БИК.
     */
    private final String bic;

    /**
     * Название.
     */
    private final String name;

    /**
     * Почтовый индекс.
     */
    private final String zip;

    /**
     * Название поселения в котором находится банк.
     */
    private final String settlementName;

    /**
     * Адрес банка.
     */
    private final String address;

    //endregion
    //region Static factories

    /**
     * Статическая фабрика DTO банка из российского банка.
     *
     * @param bankRus Российский банк.
     *
     * @return Банк.
     */
    public static BankDto from(BankRus bankRus) {

        return new BankDto(
            bankRus.getBic(),
            bankRus.getName(),
            bankRus.getZip(),
            bankRus.getSettlementName(),
            bankRus.getAddress()
        );
    }

    /**
     * Статическая фабрика DTO банка из казахского банка.
     *
     * @param bankKaz Казахский банк.
     *
     * @return Банк.
     */
    public static BankDto from(BankKaz bankKaz) {

        return new BankDto(
            bankKaz.getBic(),
            bankKaz.getName(),
            bankKaz.getZip(),
            bankKaz.getCity(),
            bankKaz.getFullAddress()
        );
    }

    //endregion
}
