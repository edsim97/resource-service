package pro.entera.resource_service.configurations.security;

/**
 * Конфиг с настройками JWT.
 */
public interface JwtConfigProperties {
    //region Public

    /**
     * Возвращает секрет для формирования хэша.
     *
     * @return Секрет для формирования хэша.
     */
    String getHashSecret();

    /**
     * Возвращает секрет для кодирования.
     *
     * @return Секрет для кодирования.
     */
    String getEncryptSecret();

    /**
     * Возвращает имя хедера, в котором лежит токен.
     *
     * @return Имя хедера с токеном.
     */
    String getEnteraTokenHeader();

    //endregion
}
