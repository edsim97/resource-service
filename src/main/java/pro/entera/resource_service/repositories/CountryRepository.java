package pro.entera.resource_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.entera.resource_service.models.Country;

import java.util.Optional;

/**
 * <p>Репозиторий для доступа к модели {@link Country}.</p>
 */
@Repository
public interface CountryRepository extends JpaRepository<Country, String> {
    //region Public

    /**
     * <p>Возвращает страну в классификаторе ОКСМ по коду ОКСМ.</p>
     *
     * @param code Код ОКСМ.
     *
     * @return Страна в классификаторе ОКСМ.
     */
    Optional<Country> findByCode(String code);

    //endregion
}
