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
 * <p>Страна по ОКСМ, находящаяся в нашем справочнике.</p>
 */
@AllArgsConstructor
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder(toBuilder = true)
@Entity
@Table(name = "country")
public class Country {
    //Fields

    /**
     * <p>Трехзначный (альфа-3) буквенный код страны, состоящий из букв латинского алфавита.</p>
     */
    @Id
    @EqualsAndHashCode.Include
    @Column(name = "alpha_3_code")
    private final String alpha3Code;

    /**
     * <p>Двухзначный (альфа-2) буквенный код страны, состоящий из букв латинского алфавита.</p>
     */
    @Column(name = "alpha_2_code")
    private final String alpha2Code;

    /**
     * <p>Трехзначный цифровой код ОКСМ.</p>
     */
    private final String code;

    /**
     * <p>Краткое наименование страны.</p>
     */
    private final String shortName;

    /**
     * <p>Полное наименование страны.</p>
     */
    private final String fullName;

    /**
     * <p>Английское название страны.</p>
     */
    private final String nameEng;

    //endregion
}
