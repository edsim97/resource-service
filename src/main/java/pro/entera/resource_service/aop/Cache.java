package pro.entera.resource_service.aop;

import lombok.Getter;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

@Getter
public class Cache<T> {
    //region Constants

    private static final ScheduledExecutorService CACHE_CLEANER =
        Executors.newSingleThreadScheduledExecutor(Thread::new);

    //endregion
    //region Fields

    private AtomicReference<T> value;

    //endregion
    //region Ctor

    public Cache(T value, long ttl) {

        this.value = new AtomicReference<>(value);

        if (ttl > 0) {

            CACHE_CLEANER.schedule(this::clearCache, ttl, TimeUnit.SECONDS);
        }
    }

    //endregion
    //region Private

    private void clearCache() {

        this.value = null;
    }

    //endregion
}
