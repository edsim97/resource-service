package pro.entera.resource_service.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * Валюта.
 */
@AllArgsConstructor
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder(toBuilder = true)
@Table(name = "currency")
public class Currency {
    //Fields

    /**
     * Трехзначный (альфа-3) буквенный код валюты, состоящий из латинского алфавита.
     */
    @Id
    @EqualsAndHashCode.Include
    @Column("alpha_3_code")
    private final String alpha3Code;

    /**
     * Трехзначный цифровой код ISO-4217.
     */
    private final String numCode;

    /**
     * Наименование валюты на русском языке.
     */
    private final String nameRus;

    /**
     * Наименование валюты на английском языке.
     */
    private final String nameEng;

    //endregion
}
