package pro.entera.resource_service.dtos.banks.kaz;

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
     * <p>Статическая фабрика для получения сущности банка из xml.</p>
     *
     * @param row xml строка Банка КЗ.
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
