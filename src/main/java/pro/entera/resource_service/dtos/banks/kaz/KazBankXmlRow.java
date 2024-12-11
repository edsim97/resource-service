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
     * <p>Индекс.</p>
     */
    @XmlElement(name = "bic")
    protected final String bic;

    /**
     * <p>Тип населенного пункта.</p>
     */
    @XmlElement(name = "bin")
    protected final String bin;

    /**
     * <p>Название населенного пункта.</p>
     */
    @XmlElement(name = "category")
    protected final String category;

    /**
     * <p>Адрес.</p>
     */
    @XmlElement(name = "city")
    protected final String city;

    /**
     * <p>Адрес.</p>
     */
    @XmlElement(name = "country")
    protected final String country;

    /**
     * <p>Адрес.</p>
     */
    @XmlElement(name = "dsc")
    protected final String dsc;

    /**
     * <p>Адрес.</p>
     */
    @XmlElement(name = "house")
    protected final String house;

    /**
     * <p>Адрес.</p>
     */
    @XmlElement(name = "id")
    protected final String id;

    /**
     * <p>Адрес.</p>
     */
    @XmlElement(name = "kato")
    protected final String kato;

    /**
     * <p>Название банка.</p>
     */
    @XmlElement(name = "name", required = true)
    protected final String name;

    /**
     * <p>Адрес.</p>
     */
    @XmlElement(name = "postidx")
    protected final String postidx;

    /**
     * <p>Адрес.</p>
     */
    @XmlElement(name = "rnn")
    protected final String rnn;

    /**
     * <p>Адрес.</p>
     */
    @XmlElement(name = "street")
    protected final String street;

    //endregion
}
