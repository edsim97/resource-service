package pro.entera.resource_service.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@Table(name = "bank_rus")
public class BankRus {
    //region Fields

    @Id
    @EqualsAndHashCode.Include
    private final UUID id;

    /**
     * БИК банка.
     */
    private final String bic;

    /**
     * Наименование банка.
     */
    private final String name;

    /**
     * Почтовый индекс расположения банка.
     */
    private final String zip;

    /**
     * Тип населённого пункта, в котором расположен банк.
     */
    private final String settlementType;

    /**
     * Наименование населённого пункта, в котором расположен банк.
     */
    private final String settlementName;

    /**
     * Адрес банка в населённом пункте, в котором расположен банк.
     */
    private final String address;

    /**
     * Корреспондентский счёт банка.
     */
    private final String account;

    /**
     * Время создания сущности.
     */
    @CreatedDate
    private final Instant createdDate;

    /**
     * Время аннулирования банка. Тот момент времени, когда при обновлении банк пропал из списка актуальных.
     */
    private final Instant annullingDate;

    //endregion
}
