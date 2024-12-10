package pro.entera.resource_service.configurations.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@AllArgsConstructor
@Getter
@ConfigurationProperties(prefix = "jwt")
public class JwtConfigPropertiesImpl implements JwtConfigProperties {
    //region Fields

    private final String hashSecret;

    private final String encryptSecret;

    private final String enteraTokenHeader;

    private final String serverToServerSubject;

    //endregion
}
