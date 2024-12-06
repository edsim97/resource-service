package pro.entera.resource_service.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;

import java.time.Instant;

/**
 * <p>Валюта.</p>
 */
@AllArgsConstructor
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder(toBuilder = true)
@Entity
@Table(name = "currency")
public class Currency {
    //Fields

    /**
     * <p>Трехзначный (альфа-3) буквенный код валюты, состоящий из латинского алфавита.</p>
     */
    @Id
    @EqualsAndHashCode.Include
    @Column(name = "alpha_3_code")
    private final String alpha3Code;

    /**
     * <p>Трехзначный цифровой код ISO-4217.</p>
     */
    private final String numCode;

    /**
     * <p>Наименование валюты на русском языке.</p>
     */
    private final String nameRus;

    /**
     * <p>Наименование валюты на английском языке.</p>
     */
    private final String nameEng;

    //endregion
}
