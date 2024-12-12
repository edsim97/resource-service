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
    private String bic;

    /**
     * Тип населенного пункта.
     */
    @XmlElement(name = "bin")
    private String bin;

    /**
     * Название населенного пункта.
     */
    @XmlElement(name = "category")
    private String category;

    /**
     * Адрес.
     */
    @XmlElement(name = "city")
    private String city;

    /**
     * Адрес.
     */
    @XmlElement(name = "country")
    private String country;

    /**
     * Адрес.
     */
    @XmlElement(name = "dsc")
    private String dsc;

    /**
     * Адрес.
     */
    @XmlElement(name = "house")
    private String house;

    /**
     * Адрес.
     */
    @XmlElement(name = "id")
    private String id;

    /**
     * Адрес.
     */
    @XmlElement(name = "kato")
    private String kato;

    /**
     * Название банка.
     */
    @XmlElement(name = "name", required = true)
    private String name;

    /**
     * Адрес.
     */
    @XmlElement(name = "postidx")
    private String postidx;

    /**
     * Адрес.
     */
    @XmlElement(name = "rnn")
    private String rnn;

    /**
     * Адрес.
     */
    @XmlElement(name = "street")
    private String street;

    //endregion
}
