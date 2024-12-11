package pro.entera.resource_service.dtos.banks.rus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Модель представления информации о банке.</p>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ParticipantInfo", namespace = "urn:cbr-ru:ed:v2.0")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ParticipantInfoType {
    //region Fields

    /**
     * <p>Название банка.</p>
     */
    @XmlAttribute(name = "NameP", required = true)
    private String nameP;

    /**
     * <p>Индекс.</p>
     */
    @XmlAttribute(name = "Ind")
    private String ind;

    /**
     * <p>Тип населенного пункта.</p>
     */
    @XmlAttribute(name = "Tnp")
    private String tnp;

    /**
     * <p>Название населенного пункта.</p>
     */
    @XmlAttribute(name = "Nnp")
    private String nnp;

    /**
     * <p>Адрес.</p>
     */
    @XmlAttribute(name = "Adr")
    private String adr;

    //endregion
}
