package pro.entera.resource_service.configurations.scheduling;

import java.time.Duration;

public interface SchedulerConfigurationProperties {
    //region Public

    boolean isUpdateBanksRusScheduleEnabled();

    Duration getUpdateBanksRusScheduleDelay();

    boolean isUpdateBanksKazScheduleEnabled();

    Duration getUpdateBanksKazScheduleDelay();

    //endregion
}
