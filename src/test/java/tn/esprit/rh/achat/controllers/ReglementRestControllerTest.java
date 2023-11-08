package tn.esprit.rh.achat.controllers;

import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tn.esprit.rh.achat.entities.CategorieFournisseur;
import tn.esprit.rh.achat.entities.DetailFournisseur;
import tn.esprit.rh.achat.entities.Facture;
import tn.esprit.rh.achat.entities.Fournisseur;
import tn.esprit.rh.achat.entities.Reglement;
import tn.esprit.rh.achat.services.IReglementService;

@ContextConfiguration(classes = {ReglementRestController.class})
@ExtendWith(SpringExtension.class)
class ReglementRestControllerTest {
    @MockBean
    private IReglementService iReglementService;

    @Autowired
    private ReglementRestController reglementRestController;

    /**
     * Method under test: {@link ReglementRestController#addReglement(Reglement)}
     */
    @Test
    void testAddReglement() throws Exception {
        Fournisseur fournisseur = new Fournisseur();
        fournisseur.setCategorieFournisseur(CategorieFournisseur.ORDINAIRE);
        fournisseur.setCode("Code");
        fournisseur.setDetailFournisseur(new DetailFournisseur());
        fournisseur.setFactures(new HashSet<>());
        fournisseur.setIdFournisseur(1L);
        fournisseur.setLibelle("Libelle");
        fournisseur.setSecteurActivites(new HashSet<>());

        DetailFournisseur detailFournisseur = new DetailFournisseur();
        detailFournisseur.setAdresse("Adresse");
        detailFournisseur.setDateDebutCollaboration(
                Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        detailFournisseur.setEmail("jane.doe@example.org");
        detailFournisseur.setFournisseur(fournisseur);
        detailFournisseur.setIdDetailFournisseur(1L);
        detailFournisseur.setMatricule("Matricule");

        Fournisseur fournisseur2 = new Fournisseur();
        fournisseur2.setCategorieFournisseur(CategorieFournisseur.ORDINAIRE);
        fournisseur2.setCode("Code");
        fournisseur2.setDetailFournisseur(detailFournisseur);
        fournisseur2.setFactures(new HashSet<>());
        fournisseur2.setIdFournisseur(1L);
        fournisseur2.setLibelle("Libelle");
        fournisseur2.setSecteurActivites(new HashSet<>());

        Facture facture = new Facture();
        facture.setArchivee(true);
        facture
                .setDateCreationFacture(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        facture.setDateDerniereModificationFacture(
                Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        facture.setDetailsFacture(new HashSet<>());
        facture.setFournisseur(fournisseur2);
        facture.setIdFacture(1L);
        facture.setMontantFacture(10.0f);
        facture.setMontantRemise(10.0f);
        facture.setReglements(new HashSet<>());

        Reglement reglement = new Reglement();
        reglement.setDateReglement(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        reglement.setFacture(facture);
        reglement.setIdReglement(1L);
        reglement.setMontantPaye(10.0f);
        reglement.setMontantRestant(10.0f);
        reglement.setPayee(true);
        when(iReglementService.addReglement(Mockito.<Reglement>any())).thenReturn(reglement);

        Fournisseur fournisseur3 = new Fournisseur();
        fournisseur3.setCategorieFournisseur(CategorieFournisseur.ORDINAIRE);
        fournisseur3.setCode("Code");
        fournisseur3.setDetailFournisseur(new DetailFournisseur());
        fournisseur3.setFactures(new HashSet<>());
        fournisseur3.setIdFournisseur(1L);
        fournisseur3.setLibelle("Libelle");
        fournisseur3.setSecteurActivites(new HashSet<>());

        DetailFournisseur detailFournisseur2 = new DetailFournisseur();
        detailFournisseur2.setAdresse("Adresse");
        detailFournisseur2.setDateDebutCollaboration(
                Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        detailFournisseur2.setEmail("jane.doe@example.org");
        detailFournisseur2.setFournisseur(fournisseur3);
        detailFournisseur2.setIdDetailFournisseur(1L);
        detailFournisseur2.setMatricule("Matricule");

        Fournisseur fournisseur4 = new Fournisseur();
        fournisseur4.setCategorieFournisseur(CategorieFournisseur.ORDINAIRE);
        fournisseur4.setCode("Code");
        fournisseur4.setDetailFournisseur(detailFournisseur2);
        fournisseur4.setFactures(new HashSet<>());
        fournisseur4.setIdFournisseur(1L);
        fournisseur4.setLibelle("Libelle");
        fournisseur4.setSecteurActivites(new HashSet<>());

        Facture facture2 = new Facture();
        facture2.setArchivee(true);
        facture2
                .setDateCreationFacture(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        facture2.setDateDerniereModificationFacture(
                Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        facture2.setDetailsFacture(new HashSet<>());
        facture2.setFournisseur(fournisseur4);
        facture2.setIdFacture(1L);
        facture2.setMontantFacture(10.0f);
        facture2.setMontantRemise(10.0f);
        facture2.setReglements(new HashSet<>());

        Reglement reglement2 = new Reglement();
        reglement2.setDateReglement(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        reglement2.setFacture(facture2);
        reglement2.setIdReglement(1L);
        reglement2.setMontantPaye(10.0f);
        reglement2.setMontantRestant(10.0f);
        reglement2.setPayee(true);
        String content = (new ObjectMapper()).writeValueAsString(reglement2);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/reglement/add-reglement")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(reglementRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"idReglement\":1,\"montantPaye\":10.0,\"montantRestant\":10.0,\"payee\":true,\"dateReglement\":0}"));
    }

    /**
     * Method under test: {@link ReglementRestController#retrieveReglement(Long)}
     */
    @Test
    void testRetrieveReglement() throws Exception {
        Fournisseur fournisseur = new Fournisseur();
        fournisseur.setCategorieFournisseur(CategorieFournisseur.ORDINAIRE);
        fournisseur.setCode("Code");
        fournisseur.setDetailFournisseur(new DetailFournisseur());
        fournisseur.setFactures(new HashSet<>());
        fournisseur.setIdFournisseur(1L);
        fournisseur.setLibelle("Libelle");
        fournisseur.setSecteurActivites(new HashSet<>());

        DetailFournisseur detailFournisseur = new DetailFournisseur();
        detailFournisseur.setAdresse("Adresse");
        detailFournisseur.setDateDebutCollaboration(
                Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        detailFournisseur.setEmail("jane.doe@example.org");
        detailFournisseur.setFournisseur(fournisseur);
        detailFournisseur.setIdDetailFournisseur(1L);
        detailFournisseur.setMatricule("Matricule");

        Fournisseur fournisseur2 = new Fournisseur();
        fournisseur2.setCategorieFournisseur(CategorieFournisseur.ORDINAIRE);
        fournisseur2.setCode("Code");
        fournisseur2.setDetailFournisseur(detailFournisseur);
        fournisseur2.setFactures(new HashSet<>());
        fournisseur2.setIdFournisseur(1L);
        fournisseur2.setLibelle("Libelle");
        fournisseur2.setSecteurActivites(new HashSet<>());

        Facture facture = new Facture();
        facture.setArchivee(true);
        facture.setDateCreationFacture(
                Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        facture.setDateDerniereModificationFacture(
                Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        facture.setDetailsFacture(new HashSet<>());
        facture.setFournisseur(fournisseur2);
        facture.setIdFacture(1L);
        facture.setMontantFacture(10.0f);
        facture.setMontantRemise(10.0f);
        facture.setReglements(new HashSet<>());

        Reglement reglement = new Reglement();
        reglement.setDateReglement(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        reglement.setFacture(facture);
        reglement.setIdReglement(1L);
        reglement.setMontantPaye(10.0f);
        reglement.setMontantRestant(10.0f);
        reglement.setPayee(true);
        when(iReglementService.retrieveReglement(Mockito.<Long>any())).thenReturn(reglement);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/reglement/retrieve-reglement/{reglement-id}", 1L);
        MockMvcBuilders.standaloneSetup(reglementRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"idReglement\":1,\"montantPaye\":10.0,\"montantRestant\":10.0,\"payee\":true,\"dateReglement\":0}"));
    }

    /**
     * Method under test: {@link ReglementRestController#retrieveReglementByFacture(Long)}
     */
    @Test
    void testRetrieveReglementByFacture() throws Exception {
        when(iReglementService.retrieveReglementByFacture(Mockito.<Long>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/reglement/retrieveReglementByFacture/{facture-id}", 1L);
        MockMvcBuilders.standaloneSetup(reglementRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link ReglementRestController#getChiffreAffaireEntreDeuxDate(Date, Date)}
     */
    @Test
    void testGetChiffreAffaireEntreDeuxDate() throws Exception {
        Date fromResult = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/reglement/getChiffreAffaireEntreDeuxDate/{startDate}/{endDate}", fromResult,
                Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(reglementRestController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    /**
     * Method under test: {@link ReglementRestController#getReglement()}
     */
    @Test
    void testGetReglement() throws Exception {
        when(iReglementService.retrieveAllReglements()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/reglement/retrieve-all-reglements");
        MockMvcBuilders.standaloneSetup(reglementRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}

