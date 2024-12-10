package pro.entera.resource_service.repositories;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import pro.entera.resource_service.models.BankKaz;
import reactor.core.publisher.Mono;

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

    /**
     * Выполняет поиск банка Казахстана по заданному БИК'у.</p>
     *
     * @param bic БИК банка Казахстана.
     *
     * @return Эталонный банк Казахстана.
     */
    Mono<BankKaz> findByBic(String bic);

    /**
     * Поиск не аннулированных банков Казахстана в текущей базе.</p>
     *
     * Не аннулированным считаеются те банки Казахстана у которых нет даты аннулирования.
     *
     * @return Список не аннулированных банков Казахстана.
     */
    List<BankKaz> findByAnnullingDateIsNull();

    //endregion
}
