package pro.entera.resource_service.configurations.scheduling;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

@AllArgsConstructor
@Getter
@ConfigurationProperties(prefix = "scheduler")
public class SchedulerConfigurationPropertiesImpl implements SchedulerConfigurationProperties {
    //region Fields

    private final boolean updateBanksRusScheduleEnabled;

    private final Duration updateBanksRusScheduleDelay;

    private final boolean updateBanksKazScheduleEnabled;

    private final Duration updateBanksKazScheduleDelay;

    //endregion
}
