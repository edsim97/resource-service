package pro.entera.resource_service.configurations;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import pro.entera.resource_service.configurations.scheduling.SchedulerConfigurationPropertiesImpl;
import pro.entera.resource_service.configurations.security.JwtConfigPropertiesImpl;

/**
 * Конфиг приложения.
 */
@Configuration
@EnableConfigurationProperties({
    SchedulerConfigurationPropertiesImpl.class,
    JwtConfigPropertiesImpl.class,
    BankUrlConfigImpl.class
})
public class ApplicationConfig {
}
