package fr.unice.polytech.isa.polyevent;

import fr.unice.polytech.isa.polyevent.entities.*;
import fr.unice.polytech.isa.polyevent.utils.Database;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.assertEquals;


@RunWith(Arquillian.class)
public class DemandePrestationTest {
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(DemandePrestation.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml").addPackage(Database.class.getPackage()).addPackage(EnvoyerMail.class.getPackage());
    }

    @EJB
    private Database memory;

    @EJB
    private DemanderPrestation demanderPrestation;

    @Before
    public void flushDatabase() { memory.flush(); }

    @Test
    public void ajoutPrestation(){
        Organisateur organisateur = new Organisateur("organisateur@gmail.com");
        Evenement evenement = new Evenement("Evenement", new Date(2018, 4, 1), new Date(2018, 4, 2),
                organisateur, null, new StatusHistorique() );
        TypeService typeService = new TypeService("café");
        demanderPrestation.ajouterService(evenement, typeService, new Date(), new Date());
        assertEquals(memory.getPrestations().size(),1);

    }

}
