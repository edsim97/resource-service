package pro.entera.resource_service.repositories;

import org.hibernate.NonUniqueResultException;
import org.springframework.data.jpa.repository.JpaRepository;
import pro.entera.resource_service.models.BankRus;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * <p>Интерфейс репозитория для доступа к модели {@link BankRus}.</p>
 */
public interface BankRusRepository extends JpaRepository<BankRus, UUID> {
    //region Public

    /**
     * <p>Выполняет поиск банка по заданному БИК'у.</p>
     *
     * @param bic БИК банка.
     *
     * @return Эталонный банк.
     *
     * @throws NonUniqueResultException Если по БИК'у найдено болеее одного банка.
     */
    Optional<BankRus> findByBic(String bic);

    /**
     * <p>Поиск не аннулированных банков в текущей базе.</p>
     *
     * <p>Не аннулированным считаеются те банки у которых нет даты аннулирования.</p>
     *
     * @return Список не аннулированных банков.
     */
    List<BankRus> findByAnnullingDateIsNull();

    //endregion
}
