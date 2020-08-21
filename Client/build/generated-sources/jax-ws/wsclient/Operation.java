
package wsclient;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for operation.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="operation">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="LOGIN"/>
 *     &lt;enumeration value="CREATE_GAME"/>
 *     &lt;enumeration value="START_GAME"/>
 *     &lt;enumeration value="USER_SHOOT"/>
 *     &lt;enumeration value="SERVER_SHOOT"/>
 *     &lt;enumeration value="USER_WIN"/>
 *     &lt;enumeration value="SERVER_WIN"/>
 *     &lt;enumeration value="SCOREBOARD"/>
 *     &lt;enumeration value="END"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "operation")
@XmlEnum
public enum Operation {

    LOGIN,
    CREATE_GAME,
    START_GAME,
    USER_SHOOT,
    SERVER_SHOOT,
    USER_WIN,
    SERVER_WIN,
    SCOREBOARD,
    END;

    public String value() {
        return name();
    }

    public static Operation fromValue(String v) {
        return valueOf(v);
    }

}
