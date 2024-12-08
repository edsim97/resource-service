package pro.entera.resource_service.aop;

import lombok.NonNull;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Aspect
@Component
public class CacheAspect {
    //region Fields

    private final Map<String, Cache<Object>> cache = new HashMap<>();

    //endregion
    //region Public

    @Around(value = "@annotation(pro.entera.resource_service.aop.Cache)")
    public Object cacheMethod(ProceedingJoinPoint joinPoint, Cacheable cacheable) throws Throwable {

        final String cacheKey = this.generateKey(joinPoint, cacheable);

        final AtomicReference<Object> valueReference = Optional.ofNullable(this.cache.get(cacheKey))
            .map(Cache::getValue)
            .orElse(null);

        final Object result;

        if (valueReference == null) {

            result = joinPoint.proceed(joinPoint.getArgs());
            this.cache.put(cacheKey, new Cache<>(result, cacheable.ttl()));
        } else {

            result = valueReference.get();
        }

        return result;
    }

    public void clearCache(@NonNull String cacheNamePrefix) {

        this.cache.replaceAll((key, value) -> key.startsWith(cacheNamePrefix) ? null : value);
    }

    //endregion
    //region Private

    private String generateKey(ProceedingJoinPoint joinPoint, Cacheable cacheable) {

        final String prefix = cacheable.namePrefix().isEmpty() ? "" : cacheable.namePrefix() + "-";
        final String signature = joinPoint.getSignature().toLongString();
        final String args = String.join("-", Arrays.toString(joinPoint.getArgs()));

        return prefix + signature + "-" + args;
    }

    //endregion
}
