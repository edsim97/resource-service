package pro.entera.resource_service.repositories;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import pro.entera.resource_service.models.BankKaz;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

/**
 * Репозиторий для доступа к модели {@link BankKaz}.
 */
public interface BankKazRepository extends ReactiveCrudRepository<BankKaz, UUID> {
    //region Public

    /**
     * Выполняет поиск банка Казахстана по заданному РНН'у.</p>
     *
     * @param rnn РНН банка Казахстана.
     *
     * @return Эталонный банк Казахстана.
     */
    Mono<BankKaz> findByRnn(String rnn);

    Flux<BankKaz> findByRnnNotInAndAnnullingDateIsNull(Collection<String> bic);

    //endregion
}
