package pro.entera.resource_service.configurations;

/**
 * Конфиг с URL-ами для загрузки данных банков.
 */
public interface BankUrlConfig {
    //region Public

    /**
     * Возвращает URL для загрузки российских банков.
     *
     * @return URL.
     */
    String getRus();

    /**
     * Возвращает URL для загрузки казахских банков.
     *
     * @return URL.
     */
    String getKaz();

    //endregion
}
