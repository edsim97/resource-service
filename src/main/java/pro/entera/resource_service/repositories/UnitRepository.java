package pro.entera.resource_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.entera.resource_service.models.Unit;

import java.util.Optional;

/**
 * Репозиторий для доступа к модели {@link Unit}.
 */
@Repository
public interface UnitRepository extends JpaRepository<Unit, String> {
    //region Public

    Optional<Unit> findByName(String name);

    /**
     * Возвращает страну в классификаторе ОКСМ по коду ОКСМ.
     *
     * @param code Код ОКСМ.
     *
     * @return Страна в классификаторе ОКСМ.
     */
    Optional<Unit> findByCode(String code);

    //endregion
}
