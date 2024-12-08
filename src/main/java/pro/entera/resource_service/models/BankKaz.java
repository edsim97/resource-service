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
public class BankKaz {
    //region Public

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
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
    //region Public

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
