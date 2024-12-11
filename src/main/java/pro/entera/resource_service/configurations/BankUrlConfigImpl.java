package pro.entera.resource_service.configurations;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@AllArgsConstructor
@Getter
@ConfigurationProperties(prefix = "banks.fetch-url")
public class BankUrlConfigImpl implements BankUrlConfig {
    //region Fields

    private final String rus;

    private final String kaz;

    //endregion
}
