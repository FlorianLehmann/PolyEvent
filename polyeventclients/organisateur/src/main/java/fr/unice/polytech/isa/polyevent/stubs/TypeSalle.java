
package fr.unice.polytech.isa.polyevent.stubs;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour typeSalle.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="typeSalle"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="SALLE"/&gt;
 *     &lt;enumeration value="AMPHI"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
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
