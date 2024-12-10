package pro.entera.resource_service.repositories;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import pro.entera.resource_service.models.Country;
import reactor.core.publisher.Mono;

/**
 * Репозиторий для доступа к модели {@link Country}.
 */
@Repository
public interface CountryRepository extends ReactiveCrudRepository<Country, String> {
    //region Public

    /**
     * Возвращает страну в классификаторе ОКСМ по коду ОКСМ.
     *
     * @param code Код ОКСМ.
     *
     * @return Страна в классификаторе ОКСМ.
     */
    Mono<Country> findByCode(String code);

    //endregion
}
