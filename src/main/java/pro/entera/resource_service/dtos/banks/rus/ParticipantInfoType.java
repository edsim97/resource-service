package pro.entera.resource_service.dtos.banks.rus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 * Модель представления информации о банке.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ParticipantInfo", namespace = "urn:cbr-ru:ed:v2.0")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ParticipantInfoType {
    //region Fields

    /**
     * Название банка.
     */
    @XmlAttribute(name = "NameP", required = true)
    private String nameP;

    /**
     * Индекс.
     */
    @XmlAttribute(name = "Ind")
    private String ind;

    /**
     * Тип населенного пункта.
     */
    @XmlAttribute(name = "Tnp")
    private String tnp;

    /**
     * Название населенного пункта.
     */
    @XmlAttribute(name = "Nnp")
    private String nnp;

    /**
     * Адрес.
     */
    @XmlAttribute(name = "Adr")
    private String adr;

    //endregion
}
