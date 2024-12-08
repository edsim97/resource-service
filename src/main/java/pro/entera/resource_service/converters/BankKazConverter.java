package pro.entera.resource_service.converters;

import org.springframework.stereotype.Component;
import pro.entera.resource_service.models.BankKaz;
import pro.entera.resource_service.models.BankRus;

@Component
public class BankKazConverter {
    //region Public

    public BankRus toBankRus(BankKaz bankKaz) {

        return BankRus.builder()
            .bic(bankKaz.getBic())
            .name(bankKaz.getName())
            .zip(bankKaz.getZip())
            .settlementName(bankKaz.getCity())
            .address(bankKaz.getFullAddress())
            .build();
    }

    //endregion
}
