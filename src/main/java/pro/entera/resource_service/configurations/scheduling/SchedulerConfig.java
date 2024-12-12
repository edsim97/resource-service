package pro.entera.resource_service.configurations.scheduling;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.Set;

/**
 * Конфиг для запланированных задач.
 */
@AllArgsConstructor
@Configuration
@EnableScheduling
public class SchedulerConfig implements SchedulingConfigurer {
    //region Fields

    /**
     * Набор запланированных задач.
     */
    private final Set<ScheduledTask> tasks;

    //endregion
    //region Public

    @Override
    public void configureTasks(@NonNull ScheduledTaskRegistrar taskRegistrar) {

        for (ScheduledTask task : tasks) {

            if (task.isEnabled()) {

                taskRegistrar.addFixedDelayTask(task, task.getDelay());
            }
        }
    }

    //endregion
}
