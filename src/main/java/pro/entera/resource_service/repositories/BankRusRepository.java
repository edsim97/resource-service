package pro.entera.resource_service.repositories;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import pro.entera.resource_service.models.BankRus;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

/**
 * <p>Интерфейс репозитория для доступа к модели {@link BankRus}.</p>
 */
public interface BankRusRepository extends R2dbcRepository<BankRus, UUID> {
    //region Public

    /**
     * <p>Выполняет поиск банка по заданному БИК'у.</p>
     *
     * @param bic БИК банка.
     *
     * @return Эталонный банк.
     */
    Mono<BankRus> findByBic(String bic);

    Flux<BankRus> findByBicIn(List<String> bic);

    Flux<BankRus> findByBicNotInAndAnnullingDateIsNull(Collection<String> bic);

    /**
     * <p>Поиск не аннулированных банков в текущей базе.</p>
     *
     * <p>Не аннулированным считаеются те банки у которых нет даты аннулирования.</p>
     *
     * @return Список не аннулированных банков.
     */
    Flux<BankRus> findByAnnullingDateIsNull();

    //endregion
}
