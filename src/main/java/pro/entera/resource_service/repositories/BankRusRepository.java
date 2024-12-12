package pro.entera.resource_service.repositories;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import pro.entera.resource_service.models.BankRus;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.UUID;

/**
 * Репозиторий для доступа к модели {@link BankRus}.
 */
public interface BankRusRepository extends R2dbcRepository<BankRus, UUID> {
    //region Public

    /**
     * Выполняет поиск банка по заданному БИК'у.
     *
     * @param bic БИК банка.
     *
     * @return Российский банк.
     */
    Mono<BankRus> findByBic(String bic);

    /**
     * Выполняет поиск банков без даты ануллирования, БИК которых есть в указанном списке.
     *
     * @param bic Список БИК-ов.
     *
     * @return Российский банк.
     */
    Flux<BankRus> findByBicNotInAndAnnullingDateIsNull(Collection<String> bic);

    //endregion
}
