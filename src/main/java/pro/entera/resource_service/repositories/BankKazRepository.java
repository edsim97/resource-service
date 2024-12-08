package pro.entera.resource_service.repositories;

import org.hibernate.NonUniqueResultException;
import org.springframework.data.jpa.repository.JpaRepository;
import pro.entera.resource_service.models.BankKaz;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Репозиторий для доступа к модели {@link BankKaz}.
 */
public interface BankKazRepository extends JpaRepository<BankKaz, UUID> {
    //region Public

    /**
     * Выполняет поиск банка Казахстана по заданному РНН'у.</p>
     *
     * @param rnn РНН банка Казахстана.
     *
     * @return Эталонный банк Казахстана.
     *
     * @throws NonUniqueResultException Если по РНН'у найдено болеее одного банка Казахстана.
     */
    Optional<BankKaz> findByRnn(String rnn);

    /**
     * Выполняет поиск банка Казахстана по заданному БИК'у.</p>
     *
     * @param bic БИК банка Казахстана.
     *
     * @return Эталонный банк Казахстана.
     *
     * @throws NonUniqueResultException Если по БИК'у найдено болеее одного банка Казахстана.
     */
    Optional<BankKaz> findByBic(String bic);

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
