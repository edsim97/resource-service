package pro.entera.resource_service.dtos.banks.kaz;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Модель списка xml БИК'ов банков Казахстана.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "root")
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class KazBankXmlRoot {
    //region Fields

    /**
     * Список записей xml банков Казахстана.
     */
    @XmlElement(name = "row")
    private final List<KazBankXmlRow> kazBankXmlRowList;

    //endregion
}
