package pro.entera.resource_service.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.regex.Pattern;
import java.util.stream.Stream;

@AllArgsConstructor
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@Entity
@Table(name = "unit")
public class Unit {
    //region Constants

    /**
     * Разделитель синонимов в поле {@link #synonym}.
     */
    public static final String SYNONYM_DELIMITER = ";";

    /**
     * Шаблон для разбиения синонимов.
     */
    private static final Pattern SYNONYM_SPLIT = Pattern.compile(SYNONYM_DELIMITER);

    //endregion
    //region Fields

    @Id
    @EqualsAndHashCode.Include
    private UUID id;

    /**
     * Код единицы измерения по классификатору ОКЕИ.
     */
    private String code;

    /**
     * Полное название единицы измерения.
     */
    private String fullName;

    /**
     * Краткое название единицы измерения.
     */
    private String name;

    /**
     * Синонимы единицы измерения для улучшения сопоставления.
     * Синонимы соединены через символом {@link #SYNONYM_DELIMITER}.
     */
    private String synonym;

    //endregion
    //region Public

    /**
     * Возвращает список синонимов для единицы измерения.
     *
     * @return Список синонимов для единицы измерения.
     */
    public List<String> getSynonyms() {

        List<String> synonyms = new ArrayList<>();

        if (this.getSynonym() != null) {

            synonyms.addAll(
                Arrays.asList(SYNONYM_SPLIT.split(this.getSynonym()))
            );
        }

        return synonyms;
    }

    /**
     * Заданное строкове представление единицы измерения синонимично текущей единице измерения?
     *
     * @param unitStr Строковое представление единицы измерения.
     *
     * @return Да/Нет.
     */
    public boolean isSynonymous(String unitStr) {

        boolean synonymous = false;

        if (unitStr != null && !unitStr.isEmpty()) {

            Stream<String> synonyms = this.getSynonyms().stream()
                .filter((String unitName) -> !unitName.isEmpty())
                .map((String unitName) -> unitName.toLowerCase(Locale.ROOT));

            synonymous = unitStr.equals(this.getName()) || synonyms.anyMatch(unitStr.toLowerCase(Locale.ROOT)::equals);
        }

        return synonymous;
    }

    //endregion
}
