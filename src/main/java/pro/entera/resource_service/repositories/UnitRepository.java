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

    /**
     * Выполняет поиск единицы измерения по её краткому наименованию.
     *
     * @param name Краткое наименование единицы измерения.
     *
     * @return Единица измерения.
     */
    Mono<Unit> findByName(String name);

    /**
     * Выполняет поиск единицы измерения по её коду в ОКЕИ.
     *
     * @param code Код единицы измерения в ОКЕИ.
     *
     * @return Единица измерения.
     */
    Mono<Unit> findByCode(String code);

    //endregion
}
