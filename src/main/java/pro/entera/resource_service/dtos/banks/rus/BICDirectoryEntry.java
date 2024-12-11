package pro.entera.resource_service.dtos.banks.rus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>Преставление БИКа.</p>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BICDirectoryEntry {
    //region Fields

    /**
     * <p>Информация о банке.</p>
     */
    @XmlElement(name = "ParticipantInfo", required = true, namespace = "urn:cbr-ru:ed:v2.0")
    private ParticipantInfoType participantInfo;

    /**
     * <p>Записи коррю счетов.</p>
     */
    @XmlElement(name = "Accounts", namespace = "urn:cbr-ru:ed:v2.0")
    private List<AccountsType> accounts = new ArrayList<>();

    /**
     * <p>БИК.</p>
     */
    @XmlAttribute(name = "BIC", required = true)
    private String bic;

    //endregion
}
