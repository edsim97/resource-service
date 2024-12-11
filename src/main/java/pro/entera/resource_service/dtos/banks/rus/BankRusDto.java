package pro.entera.resource_service.dtos.banks.rus;

import lombok.NonNull;

/**
 * @param bic            <p>БИК банка.</p>
 * @param name           <p>Наименование банка.</p>
 * @param zip            <p>Почтовый индекс расположения банка.</p>
 * @param settlementType <p>Тип населённого пункта, в котором расположен банк.</p>
 * @param settlementName <p>Наименование населённого пункта, в котором расположен банк.</p>
 * @param address        <p>Адрес банка в населённом пункте, в котором расположен банк.</p>
 * @param account        <p>Корреспондентский счёт банка.</p>
 */
public record BankRusDto(
    String bic,
    String name,
    String zip,
    String settlementType,
    String settlementName,
    String address,
    String account
) {
    //region Static factories

    /**
     * <p>Статическая фабрика для получения сущности банка из записи БИК.</p>
     *
     * @param entry Запись БИК.
     *
     * @return DTO представление банка созданное из записи БИК.
     */
    public static BankRusDto fromEntry(@NonNull BICDirectoryEntry entry) {

        String account = null;

        if (!entry.getAccounts().isEmpty()) {
            account = entry.getAccounts().get(0).getAccount();
        }

        return new BankRusDto(
            entry.getBic(),
            entry.getParticipantInfo().getNameP(),
            entry.getParticipantInfo().getInd(),
            entry.getParticipantInfo().getTnp(),
            entry.getParticipantInfo().getNnp(),
            entry.getParticipantInfo().getAdr(),
            account
        );
    }

    //endregion
}
