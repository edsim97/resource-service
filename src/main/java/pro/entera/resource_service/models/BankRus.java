package pro.entera.resource_service.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;
import pro.entera.resource_service.dtos.banks.rus.BankRusDto;

import java.time.Instant;
import java.util.UUID;

/**
 * Банк в России.
 */
@AllArgsConstructor(onConstructor_ = @PersistenceCreator)
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder(toBuilder = true)
@Table(name = "bank_rus")
public class BankRus implements Persistable<UUID> {
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
    //region Static fabric

    /**
     * Статическая фабрика для получения сущности российского банка из его DTO.
     *
     * @param dto DTO российского банка.
     *
     * @return Модель российского банка.
     */
    public static BankRus fromDto(@NonNull BankRusDto dto) {

        return BankRus.builder()
            .bic(dto.bic())
            .name(dto.name())
            .zip(dto.zip())
            .settlementType(dto.settlementType())
            .settlementName(dto.settlementName())
            .address(dto.address())
            .account(dto.account())
            .build();
    }

    @Override
    public boolean isNew() {

        return this.createdDate == null;
    }

    //endregion
}
