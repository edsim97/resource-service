package pro.entera.resource_service.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;

import java.time.Instant;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class BankRus {
    //region Fields

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
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
    //region Public

    /**
     * Возвращает полный адрес банка.
     *
     * @return Строка, представляющая полный адрес банка с индексом.
     */
    public String getFullAddress() {

        String addressWithComma = Optional.ofNullable(this.getAddress())
            .map((String a) -> ", " + a)
            .orElse(null);

        return Stream.of(this.getZip(), this.getSettlementType(), this.getSettlementName(), addressWithComma)
            .filter(Objects::nonNull)
            .collect(Collectors.joining(" "));
    }

    //endregion
}
