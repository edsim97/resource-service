package pro.entera.resource_service.repositories;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import pro.entera.resource_service.models.BankKaz;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.UUID;

/**
 * Репозиторий для доступа к модели {@link BankKaz}.
 */
public interface BankKazRepository extends ReactiveCrudRepository<BankKaz, UUID> {
    //region Public

    /**
     * Выполняет поиск банка Казахстана по заданному РНН-у.
     *
     * @param rnn РНН банка Казахстана.
     *
     * @return Банк Казахстана.
     */
    Mono<BankKaz> findByRnn(String rnn);

    /**
     * Выполняет поиск банков без даты ануллирования, РНН которых есть в указанном списке.
     *
     * @param rnn Список РНН-ов.
     *
     * @return Банк Казахстана.
     */
    Flux<BankKaz> findByRnnNotInAndAnnullingDateIsNull(Collection<String> rnn);

    //endregion
}
