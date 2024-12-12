package pro.entera.resource_service.dtos.banks.kaz;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * Модель банка Казахстана.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class KazBankXmlRow {
    //region Fields

    /**
     * Индекс.
     */
    @XmlElement(name = "bic")
    protected final String bic;

    /**
     * Тип населенного пункта.
     */
    @XmlElement(name = "bin")
    protected final String bin;

    /**
     * Название населенного пункта.
     */
    @XmlElement(name = "category")
    protected final String category;

    /**
     * Адрес.
     */
    @XmlElement(name = "city")
    protected final String city;

    /**
     * Адрес.
     */
    @XmlElement(name = "country")
    protected final String country;

    /**
     * Адрес.
     */
    @XmlElement(name = "dsc")
    protected final String dsc;

    /**
     * Адрес.
     */
    @XmlElement(name = "house")
    protected final String house;

    /**
     * Адрес.
     */
    @XmlElement(name = "id")
    protected final String id;

    /**
     * Адрес.
     */
    @XmlElement(name = "kato")
    protected final String kato;

    /**
     * Название банка.
     */
    @XmlElement(name = "name", required = true)
    protected final String name;

    /**
     * Адрес.
     */
    @XmlElement(name = "postidx")
    protected final String postidx;

    /**
     * Адрес.
     */
    @XmlElement(name = "rnn")
    protected final String rnn;

    /**
     * Адрес.
     */
    @XmlElement(name = "street")
    protected final String street;

    //endregion
}
