package pro.entera.resource_service.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;
import pro.entera.resource_service.dtos.banks.kaz.BankKazDto;

import java.time.Instant;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Банк в Казахстане.
 */
@AllArgsConstructor(onConstructor_ = @PersistenceCreator)
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder(toBuilder = true)
@Table(name = "bank_kaz")
public class BankKaz implements Persistable<UUID> {
    //region Public

    @Id
    @EqualsAndHashCode.Include
    private final UUID id;

    /**
     * БИК банка.
     */
    private final String bic;

    /**
     * БИН банка
     */
    private final String bin;

    /**
     * Категория банка
     */
    private final String category;

    /**
     * Город банка
     */
    private final String city;

    /**
     * Страна банка
     */
    private final String country;

    /**
     * Фактический адрес банка.
     */
    private final String dsc;

    /**
     * Номер дома банка.
     */
    private final String house;

    /**
     * Id банка
     */
    private final String bankId;

    /**
     * Номер региона Казахстана банка.
     */
    private final String kato;

    /**
     * Наименование банка.
     */
    private final String name;

    /**
     * Почтовый индекс банка.
     */
    private final String zip;

    /**
     * РНН банка.
     */
    private final String rnn;

    /**
     * Улица на которой находится банк
     */
    private final String street;

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
    //region Static factories

    /**
     * Статическая фабрика для получения сущности банка из DTO представления банка.
     *
     * @param dto DTO представление банка.
     *
     * @return Новая сущность банка созданная из DTO представления банка.
     */
    public static BankKaz fromDTO(BankKazDto dto) {

        return BankKaz.builder()
            .id(UUID.randomUUID())
            .bin(dto.bin())
            .bic(dto.bic())
            .category(dto.category())
            .city(dto.city())
            .country(dto.country())
            .dsc(dto.dsc())
            .house(dto.house())
            .bankId(dto.id())
            .kato(dto.kato())
            .name(dto.name())
            .zip(dto.zip())
            .rnn(dto.rnn())
            .street(dto.street())
            .build();
    }

    //endregion
    //region Public

    @Override
    public boolean isNew() {

        return this.createdDate == null;
    }

    /**
     * Возвращает полный адрес банка.
     *
     * @return Строка, представляющая полный адрес банка с индексом.
     */
    public String getFullAddress() {

        String address = Stream.of(this.getStreet(), this.getHouse(), this.getDsc())
            .filter(Objects::nonNull)
            .collect(Collectors.joining(" "));

        String addressWithComma = Optional.of(address)
            .map((String a) -> ", " + a)
            .orElse(null);

        return Stream.of(this.getZip(), this.getCountry(), this.getCity(), addressWithComma)
            .filter(Objects::nonNull)
            .collect(Collectors.joining(" "));
    }

    //endregion
}
