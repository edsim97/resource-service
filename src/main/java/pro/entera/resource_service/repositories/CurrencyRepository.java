package pro.entera.resource_service.repositories;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import pro.entera.resource_service.models.Currency;

/**
 * Репозиторий для доступа к модели {@link Currency}.
 */
@Repository
public interface CurrencyRepository extends ReactiveCrudRepository<Currency, String> { }
