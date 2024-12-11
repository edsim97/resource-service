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

@RequiredArgsConstructor
@Configuration
public class Pac4jConfig {

    private final JwtConfigProperties jwtConfigProperties;

    @Bean
    public Config config() {

        final JwtAuthenticator authenticator = getJwtAuthenticator();
        final HeaderClient jwtClient = new HeaderClient(jwtConfigProperties.getEnteraTokenHeader(), authenticator);
        final Clients clients = new Clients(jwtClient);

        return new Config(clients);
    }

    private JwtAuthenticator getJwtAuthenticator() {

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
}
