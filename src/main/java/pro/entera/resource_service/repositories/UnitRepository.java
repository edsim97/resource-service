package pro.entera.resource_service.repositories;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import pro.entera.resource_service.models.Unit;
import reactor.core.publisher.Mono;

/**
 * Репозиторий для доступа к модели {@link Unit}.
 */
@Repository
public interface UnitRepository extends ReactiveCrudRepository<Unit, String> {
    //region Public

    Mono<Unit> findByName(String name);

    /**
     * Возвращает страну в классификаторе ОКСМ по коду ОКСМ.
     *
     * @param code Код ОКСМ.
     *
     * @return Страна в классификаторе ОКСМ.
     */
    Mono<Unit> findByCode(String code);

    //endregion
}
