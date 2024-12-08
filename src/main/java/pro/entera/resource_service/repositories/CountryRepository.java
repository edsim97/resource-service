package pro.entera.resource_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.entera.resource_service.models.Country;

import java.util.Optional;

/**
 * Репозиторий для доступа к модели {@link Country}.
 */
@Repository
public interface CountryRepository extends JpaRepository<Country, String> {
    //region Public

    /**
     * Возвращает страну в классификаторе ОКСМ по коду ОКСМ.
     *
     * @param code Код ОКСМ.
     *
     * @return Страна в классификаторе ОКСМ.
     */
    Optional<Country> findByCode(String code);

    //endregion
}
