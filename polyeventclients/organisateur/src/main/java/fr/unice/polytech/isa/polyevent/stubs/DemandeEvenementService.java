
package fr.unice.polytech.isa.polyevent.stubs;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "DemandeEvenementService", targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/polyevent/demandeEvenement", wsdlLocation = "file:/C:/Users/user/IdeaProjects/main/polyeventclients/organisateur/src/main/resources/DemandeEvenementWS.wsdl")
public class DemandeEvenementService
    extends Service
{

    private final static URL DEMANDEEVENEMENTSERVICE_WSDL_LOCATION;
    private final static WebServiceException DEMANDEEVENEMENTSERVICE_EXCEPTION;
    private final static QName DEMANDEEVENEMENTSERVICE_QNAME = new QName("http://www.polytech.unice.fr/si/4a/isa/polyevent/demandeEvenement", "DemandeEvenementService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("file:/C:/Users/user/IdeaProjects/main/polyeventclients/organisateur/src/main/resources/DemandeEvenementWS.wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        DEMANDEEVENEMENTSERVICE_WSDL_LOCATION = url;
        DEMANDEEVENEMENTSERVICE_EXCEPTION = e;
    }

    public DemandeEvenementService() {
        super(__getWsdlLocation(), DEMANDEEVENEMENTSERVICE_QNAME);
    }

    public DemandeEvenementService(WebServiceFeature... features) {
        super(__getWsdlLocation(), DEMANDEEVENEMENTSERVICE_QNAME, features);
    }

    public DemandeEvenementService(URL wsdlLocation) {
        super(wsdlLocation, DEMANDEEVENEMENTSERVICE_QNAME);
    }

    public DemandeEvenementService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, DEMANDEEVENEMENTSERVICE_QNAME, features);
    }

    public DemandeEvenementService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public DemandeEvenementService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns DemanderEvenement
     */
    @WebEndpoint(name = "DemandeEvenementPort")
    public DemanderEvenement getDemandeEvenementPort() {
        return super.getPort(new QName("http://www.polytech.unice.fr/si/4a/isa/polyevent/demandeEvenement", "DemandeEvenementPort"), DemanderEvenement.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns DemanderEvenement
     */
    @WebEndpoint(name = "DemandeEvenementPort")
    public DemanderEvenement getDemandeEvenementPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://www.polytech.unice.fr/si/4a/isa/polyevent/demandeEvenement", "DemandeEvenementPort"), DemanderEvenement.class, features);
    }

    private static URL __getWsdlLocation() {
        if (DEMANDEEVENEMENTSERVICE_EXCEPTION!= null) {
            throw DEMANDEEVENEMENTSERVICE_EXCEPTION;
        }
        return DEMANDEEVENEMENTSERVICE_WSDL_LOCATION;
    }

}
