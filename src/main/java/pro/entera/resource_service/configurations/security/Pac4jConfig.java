package pro.entera.resource_service.configurations.security;

import lombok.RequiredArgsConstructor;
import org.pac4j.core.client.Clients;
import org.pac4j.core.config.Config;
import org.pac4j.http.client.direct.HeaderClient;
import org.pac4j.jwt.config.encryption.SecretEncryptionConfiguration;
import org.pac4j.jwt.config.signature.SecretSignatureConfiguration;
import org.pac4j.jwt.credentials.authenticator.JwtAuthenticator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Base64;

/**
 * Класс конфигурации библиотеки pac4j.
 */
@RequiredArgsConstructor
@Configuration
public class Pac4jConfig {
    //region Fields

    private final JwtConfigProperties jwtConfigProperties;

    //endregion
    //region Public

    /**
     * Создаёт конфиг библиотеки pac4j.
     * В конфиг добавляется клиент для чтения хедеров запроса, проверяющий наличие хедера с токеном и валидность токена.
     *
     * @return Созданный конфиг.
     */
    @Bean
    public Config config() {

        final JwtAuthenticator authenticator = createJwtAuthenticator();
        final HeaderClient jwtClient = new HeaderClient(jwtConfigProperties.getEnteraTokenHeader(), authenticator);
        final Clients clients = new Clients(jwtClient);

        return new Config(clients);
    }

    /**
     * Создаёт JWT аутентификатор.
     *
     * @return JWT аутентификатор.
     */
    private JwtAuthenticator createJwtAuthenticator() {

        final SecretSignatureConfiguration signatureConfiguration = new SecretSignatureConfiguration(
            Base64.getDecoder().decode(jwtConfigProperties.getHashSecret())
        );
        final SecretEncryptionConfiguration encryptionConfiguration = new SecretEncryptionConfiguration(
            Base64.getDecoder().decode(jwtConfigProperties.getEncryptSecret())
        );
        JwtAuthenticator authenticator = new JwtAuthenticator();
        authenticator.addSignatureConfiguration(signatureConfiguration);
        authenticator.addEncryptionConfiguration(encryptionConfiguration);

        return authenticator;
    }

    //endregion
}
