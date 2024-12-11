package pro.entera.resource_service.configurations.scheduling;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pro.entera.resource_service.services.BankService;

import java.time.Duration;

@AllArgsConstructor
@Component
public class UpdateBanksRusScheduledTask implements ScheduledTask {
    //region Fields

    private SchedulerConfigurationProperties properties;

    private BankService bankService;

    //endregion
    //region Public

    @Override
    public boolean isEnabled() {

        return this.properties.isUpdateBanksRusScheduleEnabled();
    }

    @Override
    public Duration getDelay() {

        return this.properties.getUpdateBanksRusScheduleDelay();
    }

    @Override
    public void run() {

        this.bankService.fetchAndUpdateRus();
    }
}
