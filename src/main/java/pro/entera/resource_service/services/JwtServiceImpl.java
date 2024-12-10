package pro.entera.resource_service.services;

import com.nimbusds.jose.EncryptionMethod;
import com.nimbusds.jose.JWEAlgorithm;
import com.nimbusds.jose.JWSAlgorithm;
import lombok.AllArgsConstructor;
import org.pac4j.core.profile.jwt.JwtClaims;
import org.pac4j.jwt.config.encryption.EncryptionConfiguration;
import org.pac4j.jwt.config.encryption.SecretEncryptionConfiguration;
import org.pac4j.jwt.config.signature.SecretSignatureConfiguration;
import org.pac4j.jwt.config.signature.SignatureConfiguration;
import org.pac4j.jwt.credentials.authenticator.JwtAuthenticator;
import org.springframework.stereotype.Service;
import pro.entera.resource_service.configurations.security.JwtConfigProperties;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@Service
public class JwtServiceImpl implements JwtService {

    private final JwtConfigProperties jwtConfig;

    @Override
    public String extractUserName(String token) {

        final var payload = extractPayload(token);

        return Optional.ofNullable(payload)
            .map(p -> p.get(JwtClaims.SUBJECT))
            .filter(String.class::isInstance)
            .map(String.class::cast)
            .orElse(null);
    }

    @Override
    public boolean isExpiredToken(String token) {

        final var payload = extractPayload(token);

        return Optional.ofNullable(payload)
            .map(p -> p.get(JwtClaims.EXPIRATION_TIME))
            .filter(String.class::isInstance)
            .map(String.class::cast)
            .map(JwtServiceImpl::parseDate)
            .map(new Date()::before)
            .orElse(false);
    }

    //endregion
    //region Private static

    /**
     * <p>Возвращает конфигурацию для подписи JWT-токена.</p>
     *
     * @param secretBytes Секретный ключ для подписи JWT-токена.
     *
     * @return Конфигурация для подписи JWT-токена.
     */
    private static SecretSignatureConfiguration getSecretSignatureConfiguration(byte[] secretBytes) {

        return new SecretSignatureConfiguration(secretBytes, JWSAlgorithm.HS512);
    }

    /**
     * <p>Возвращает конфигурацию для шифрования/дешифрации payload-части JWT-токена.</p>
     *
     * @param secretBytes Секретный ключ для шифрования/дешифрации.
     *
     * @return Конфигурация для шифрования/дешифрации payload-части JWT-токена.
     */
    private static SecretEncryptionConfiguration getSecretEncryptionConfiguration(byte[] secretBytes) {

        return new SecretEncryptionConfiguration(secretBytes, JWEAlgorithm.DIR, EncryptionMethod.A256GCM);
    }

    private static Date parseDate(String stringDate) {

        SimpleDateFormat simpleDateFormat = getSimpleDateFormat();
        try {

            return simpleDateFormat.parse(stringDate);
        } catch (ParseException e) {

            return null;
        }
    }

    private static SimpleDateFormat getSimpleDateFormat() {

        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssz");
    }

    //endregion
    //region Private

    /**
     * Извлечение всех данных из токена
     *
     * @param token токен
     * @return данные
     */
    private Map<String, Object> extractPayload(String token) {

        // Конфигурация для проверки подписи токена.
        byte[] hashSecretBytes = Base64.getDecoder().decode(jwtConfig.getHashSecret());
        SignatureConfiguration signatureConfiguration = getSecretSignatureConfiguration(hashSecretBytes);

        // Конфигурация для дешифрации payload-части токена.
        byte[] encryptSecretBytes = Base64.getDecoder().decode(jwtConfig.getEncryptSecret());
        EncryptionConfiguration secretEncryptionConfiguration = getSecretEncryptionConfiguration(encryptSecretBytes);

        // Дешифратор JWT-токена.
        final JwtAuthenticator jwtAuthenticator = new JwtAuthenticator(
            signatureConfiguration,
            secretEncryptionConfiguration
        );

        try {

            return jwtAuthenticator.validateTokenAndGetClaims(token);
        } catch (RuntimeException e) {

            throw new IllegalStateException("Error occurred while trying to parse JWT-token", e);
        }
    }

    //endregion
}
