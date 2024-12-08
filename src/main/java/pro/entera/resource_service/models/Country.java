package pro.entera.resource_service.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * Страна по ОКСМ, находящаяся в нашем справочнике.
 */
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Builder(toBuilder = true)
@Entity
@Table(name = "country")
public class Country {
    //Fields

    /**
     * Трехзначный (альфа-3) буквенный код страны, состоящий из букв латинского алфавита.
     */
    @Id
    @EqualsAndHashCode.Include
    @Column(name = "alpha_3_code")
    private final String alpha3Code;

    /**
     * Двухзначный (альфа-2) буквенный код страны, состоящий из букв латинского алфавита.
     */
    @Column(name = "alpha_2_code")
    private final String alpha2Code;

    /**
     * Трехзначный цифровой код ОКСМ.
     */
    private final String code;

    /**
     * Краткое наименование страны.
     */
    private final String shortName;

    /**
     * Полное наименование страны.
     */
    private final String fullName;

    /**
     * Английское название страны.
     */
    private final String nameEng;

    //endregion
}
