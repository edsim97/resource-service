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
import pro.entera.resource_service.dtos.banks.rus.BICDirectoryEntry;

import java.time.Instant;
import java.util.UUID;

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
     * <p>Статическая фабрика для получения сущности банка из записи БИК.</p>
     *
     * @param entry Запись БИК.
     *
     * @return DTO представление банка созданное из записи БИК.
     */
    public static BankRus fromEntry(@NonNull BICDirectoryEntry entry) {

        String account = null;

        if (!entry.getAccounts().isEmpty()) {
            account = entry.getAccounts().get(0).getAccount();
        }

        return BankRus.builder()
            .bic(entry.getBic())
            .name(entry.getParticipantInfo().getNameP())
            .zip(entry.getParticipantInfo().getInd())
            .settlementType(entry.getParticipantInfo().getTnp())
            .settlementName(entry.getParticipantInfo().getNnp())
            .address(entry.getParticipantInfo().getAdr())
            .account(account)
            .build();
    }

    @Override
    public boolean isNew() {

        return this.createdDate == null;
    }

    //endregion
}
