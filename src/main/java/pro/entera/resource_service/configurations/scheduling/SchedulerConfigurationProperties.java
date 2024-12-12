package pro.entera.resource_service.configurations.scheduling;

import java.time.Duration;

/**
 * Настройки запланированных задач.
 */
public interface SchedulerConfigurationProperties {
    //region Public

    /**
     * Включена ли задача обновления российских банков?
     *
     * @return Да/нет.
     */
    boolean isUpdateBanksRusScheduleEnabled();

    /**
     * Возвращает задержку между запусками задачи обновления российских банков.
     *
     * @return Задерка.
     */
    Duration getUpdateBanksRusScheduleDelay();

    /**
     * Включена ли задача обновления казахских банков?
     *
     * @return Да/нет.
     */
    boolean isUpdateBanksKazScheduleEnabled();

    /**
     * Возвращает задержку между запусками задачи обновления казахских банков.
     *
     * @return Задерка.
     */
    Duration getUpdateBanksKazScheduleDelay();

    //endregion
}
