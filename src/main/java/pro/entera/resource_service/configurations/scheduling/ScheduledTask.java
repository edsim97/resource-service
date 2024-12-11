package pro.entera.resource_service.configurations.scheduling;

import java.time.Duration;

public interface ScheduledTask extends Runnable {

    boolean isEnabled();

    Duration getDelay();
}
