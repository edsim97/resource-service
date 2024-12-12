package pro.entera.resource_service.dtos.banks.rus;

import lombok.NonNull;

/**
 * DTO российского банка.
 *
 * @param bic БИК банка.
 * @param name Наименование банка.
 * @param zip Почтовый индекс расположения банка.
 * @param settlementType Тип населённого пункта, в котором расположен банк.
 * @param settlementName Наименование населённого пункта, в котором расположен банк.
 * @param address Адрес банка в населённом пункте, в котором расположен банк.
 * @param account Корреспондентский счёт банка.
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
     * Статическая фабрика для получения сущности банка из записи БИК.
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
