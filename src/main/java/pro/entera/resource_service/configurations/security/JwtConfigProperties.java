package pro.entera.resource_service.configurations.security;

public interface JwtConfigProperties {
    //region Public

    String getHashSecret();

    String getEncryptSecret();

    String getEnteraTokenHeader();

    String getServerToServerSubject();

    //endregion
}
