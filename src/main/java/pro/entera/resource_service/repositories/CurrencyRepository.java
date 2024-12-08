package pro.entera.resource_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.entera.resource_service.models.Currency;

/**
 * Репозиторий для доступа к модели {@link Currency}.
 */
@Repository
public interface CurrencyRepository extends JpaRepository<Currency, String> { }
