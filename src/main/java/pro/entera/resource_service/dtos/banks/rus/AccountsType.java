package pro.entera.resource_service.dtos.banks.rus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * Объект корреспондентского счета.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Accounts", namespace = "urn:cbr-ru:ed:v2.0")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AccountsType {
    //region Fields

    /**
     * Корреспондентский счет.
     */
    @XmlAttribute(name = "Account", required = true)
    private String account;

    //endregion

}
