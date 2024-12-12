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
 * Преставление БИКа.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BICDirectoryEntry {
    //region Fields

    /**
     * Информация о банке.
     */
    @XmlElement(name = "ParticipantInfo", required = true, namespace = "urn:cbr-ru:ed:v2.0")
    private ParticipantInfoType participantInfo;

    /**
     * Записи коррю счетов.
     */
    @XmlElement(name = "Accounts", namespace = "urn:cbr-ru:ed:v2.0")
    private List<AccountsType> accounts = new ArrayList<>();

    /**
     * БИК.
     */
    @XmlAttribute(name = "BIC", required = true)
    private String bic;

    //endregion
}
