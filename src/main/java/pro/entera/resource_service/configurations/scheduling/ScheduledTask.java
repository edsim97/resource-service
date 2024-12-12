package pro.entera.resource_service.configurations.scheduling;

import java.time.Duration;

/**
 * Запланированая повторяющаяся задача.
 */
public interface ScheduledTask extends Runnable {
    //region Public

    /**
     * Задача включена?
     *
     * @return Да/нет.
     */
    boolean isEnabled();

    /**
     * Возвращает задержку после окончания выполнения задачи, перед следующим запуском.
     *
     * @return Задержка.
     */
    Duration getDelay();

    //endregion
}
