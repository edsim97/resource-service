package pro.entera.resource_service.dtos.banks.kaz;

/**
 * DTO казахского банка.
 *
 * @param bic БИК банка.
 * @param bin БИН банка.
 * @param category Категория банка.
 * @param city Город в котором находится банк.
 * @param country Страна в которой находится банк.
 * @param dsc Фактический адрес банка.
 * @param house Номер дома банка.
 * @param id ID банка.
 * @param kato Номер региона Казахстана, в котором находится банк.
 * @param name Название банка.
 * @param zip Почтовый индекс банка.
 * @param rnn РНН банка.
 * @param street Улица на которой находися банк.
 */
public record BankKazDto(
    String bic,
    String bin,
    String category,
    String city,
    String country,
    String dsc,
    String house,
    String id,
    String kato,
    String name,
    String zip,
    String rnn,
    String street
) {
    //region Static factories

    /**
     * Статическая фабрика для получения сущности банка из xml.
     *
     * @param row xml строка казахского банка.
     *
     * @return DTO представление банка созданное из xml.
     */
    public static BankKazDto fromEntry(KazBankXmlRow row) {

        return new BankKazDto(
            row.getBin(),
            row.getBic(),
            row.getCategory(),
            row.getCity(),
            row.getCountry(),
            row.getDsc(),
            row.getHouse(),
            row.getId(),
            row.getKato(),
            row.getName(),
            row.getPostidx(),
            row.getRnn(),
            row.getStreet()
        );
    }

    //endregion
}
