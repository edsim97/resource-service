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
 * Валюта.
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
     * Трехзначный (альфа-3) буквенный код валюты, состоящий из латинского алфавита.
     */
    @Id
    @EqualsAndHashCode.Include
    @Column(name = "alpha_3_code")
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
