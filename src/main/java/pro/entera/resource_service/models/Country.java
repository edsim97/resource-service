package pro.entera.resource_service.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * Страна по ОКСМ, находящаяся в нашем справочнике.
 */
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Builder(toBuilder = true)
@Table(name = "country")
public class Country {
    //Fields

    /**
     * Трехзначный (альфа-3) буквенный код страны, состоящий из букв латинского алфавита.
     */
    @Id
    @EqualsAndHashCode.Include
    @Column("alpha_3_code")
    private final String alpha3Code;

    /**
     * Двухзначный (альфа-2) буквенный код страны, состоящий из букв латинского алфавита.
     */
    @Column("alpha_2_code")
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
