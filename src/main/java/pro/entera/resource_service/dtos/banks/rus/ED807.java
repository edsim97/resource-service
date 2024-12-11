package pro.entera.resource_service.dtos.banks.rus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;


/**
 * <p>Модель списка БИК'ов банков.</p>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "ED807")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ED807 {
    //region Fields

    /**
     * <p>Список записей БИК'ов.</p>
     */
    @XmlElement(name = "BICDirectoryEntry")
    private List<BICDirectoryEntry> bicDirectoryEntry;

    //endregion
}