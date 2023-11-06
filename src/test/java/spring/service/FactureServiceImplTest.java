package tn.esprit.spring.service;
import org.junit.jupiter.api.*;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import tn.esprit.rh.achat.entities.Fournisseur;
import tn.esprit.rh.achat.entities.DetailFacture;
import tn.esprit.rh.achat.entities.Facture;
import java.util.*;
import static org.junit.Assert.assertSame;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;


@Configuration
@EnableAutoConfiguration
@ComponentScan
public class FactureServiceImplTest { 

    private Facture facture;

    @BeforeEach
    public void setUp() {
        facture = new Facture();
    }

    @Test
    public void testFactureFields() {
        // Set field values
        facture.setIdFacture(1L);
        facture.setMontantRemise(100.0f);
        facture.setMontantFacture(500.0f);

        // Check if field values are set correctly
        assertEquals(1L, facture.getIdFacture());
        assertEquals(100.0f, facture.getMontantRemise());
        assertEquals(500.0f, facture.getMontantFacture());
    }

    @Test
    public void testFactureDateFields() {
        
        facture.setDateCreationFacture(new Date());
        facture.setDateDerniereModificationFacture(new Date());

        // Check if date fields are not null
        assertNotNull(facture.getDateCreationFacture());
        assertNotNull(facture.getDateDerniereModificationFacture());
    }

    @Test
    public void testFactureRelationships() {
       
        Set<DetailFacture> detailsFacture = new HashSet<>();
        Fournisseur fournisseur = new Fournisseur();

       
        facture.setDetailsFacture(detailsFacture);
        facture.setFournisseur(fournisseur);

        
        assertSame(detailsFacture, facture.getDetailsFacture());
        assertSame(fournisseur, facture.getFournisseur());
    }
}
