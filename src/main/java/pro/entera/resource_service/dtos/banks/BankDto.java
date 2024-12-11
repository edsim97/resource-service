package pro.entera.resource_service.dtos.banks;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import pro.entera.resource_service.models.BankKaz;
import pro.entera.resource_service.models.BankRus;

@AllArgsConstructor
@EqualsAndHashCode
@Getter
public class BankDto {
    //region Fields

    private final String bic;

    private final String name;

    private final String zip;

    private final String settlementName;

    private final String address;

    //endregion
    //region Static factories

    public static BankDto from(BankRus bankRus) {

        return new BankDto(
            bankRus.getBic(),
            bankRus.getName(),
            bankRus.getZip(),
            bankRus.getSettlementName(),
            bankRus.getAddress()
        );
    }

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
