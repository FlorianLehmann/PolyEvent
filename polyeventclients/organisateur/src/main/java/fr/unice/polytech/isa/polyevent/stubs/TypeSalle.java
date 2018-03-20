
package fr.unice.polytech.isa.polyevent.stubs;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour typeSalle.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="typeSalle">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="SALLE"/>
 *     &lt;enumeration value="AMPHI"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "typeSalle")
@XmlEnum
public enum TypeSalle {

    SALLE,
    AMPHI;

    public String value() {
        return name();
    }

    public static TypeSalle fromValue(String v) {
        return valueOf(v);
    }

}
