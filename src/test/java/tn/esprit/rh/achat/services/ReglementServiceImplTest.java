package tn.esprit.rh.achat.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tn.esprit.rh.achat.entities.CategorieFournisseur;
import tn.esprit.rh.achat.entities.DetailFournisseur;
import tn.esprit.rh.achat.entities.Facture;
import tn.esprit.rh.achat.entities.Fournisseur;
import tn.esprit.rh.achat.entities.Reglement;
import tn.esprit.rh.achat.repositories.FactureRepository;
import tn.esprit.rh.achat.repositories.ReglementRepository;

@ContextConfiguration(classes = {ReglementServiceImpl.class})
@ExtendWith(SpringExtension.class)
class ReglementServiceImplTest {
    @MockBean
    private FactureRepository factureRepository;

    @MockBean
    private ReglementRepository reglementRepository;

    @Autowired
    private ReglementServiceImpl reglementServiceImpl;



    /**
     * Method under test: {@link ReglementServiceImpl#addReglement(Reglement)}
     */
    @Test
    void testAddReglement() {
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
        when(reglementRepository.save(Mockito.<Reglement>any())).thenReturn(reglement);

        DetailFournisseur detailFournisseur2 = new DetailFournisseur();
        detailFournisseur2.setAdresse("Adresse");
        detailFournisseur2.setDateDebutCollaboration(
                Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        detailFournisseur2.setEmail("jane.doe@example.org");
        detailFournisseur2.setFournisseur(new Fournisseur());
        detailFournisseur2.setIdDetailFournisseur(1L);
        detailFournisseur2.setMatricule("Matricule");

        Fournisseur fournisseur3 = new Fournisseur();
        fournisseur3.setCategorieFournisseur(CategorieFournisseur.ORDINAIRE);
        fournisseur3.setCode("Code");
        fournisseur3.setDetailFournisseur(detailFournisseur2);
        fournisseur3.setFactures(new HashSet<>());
        fournisseur3.setIdFournisseur(1L);
        fournisseur3.setLibelle("Libelle");
        fournisseur3.setSecteurActivites(new HashSet<>());

        Facture facture2 = new Facture();
        facture2.setArchivee(true);
        facture2
                .setDateCreationFacture(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        facture2.setDateDerniereModificationFacture(
                Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        facture2.setDetailsFacture(new HashSet<>());
        facture2.setFournisseur(fournisseur3);
        facture2.setIdFacture(1L);
        facture2.setMontantFacture(10.0f);
        facture2.setMontantRemise(10.0f);
        facture2.setReglements(new HashSet<>());

        Reglement r = new Reglement();
        r.setDateReglement(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        r.setFacture(facture2);
        r.setIdReglement(1L);
        r.setMontantPaye(10.0f);
        r.setMontantRestant(10.0f);
        r.setPayee(true);
        Reglement actualAddReglementResult = reglementServiceImpl.addReglement(r);
        verify(reglementRepository).save(Mockito.<Reglement>any());
        assertSame(r, actualAddReglementResult);
    }

    /**
     * Method under test: {@link ReglementServiceImpl#retrieveReglement(Long)}
     */
    @Test
    void testRetrieveReglement() {
        DetailFournisseur detailFournisseur = new DetailFournisseur();
        detailFournisseur.setAdresse("Adresse");
        detailFournisseur.setDateDebutCollaboration(
                Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        detailFournisseur.setEmail("jane.doe@example.org");
        detailFournisseur.setFournisseur(new Fournisseur());
        detailFournisseur.setIdDetailFournisseur(1L);
        detailFournisseur.setMatricule("Matricule");

        Fournisseur fournisseur = new Fournisseur();
        fournisseur.setCategorieFournisseur(CategorieFournisseur.ORDINAIRE);
        fournisseur.setCode("Code");
        fournisseur.setDetailFournisseur(detailFournisseur);
        fournisseur.setFactures(new HashSet<>());
        fournisseur.setIdFournisseur(1L);
        fournisseur.setLibelle("Libelle");
        fournisseur.setSecteurActivites(new HashSet<>());

        Facture facture = new Facture();
        facture.setArchivee(true);
        facture.setDateCreationFacture(
                Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        facture.setDateDerniereModificationFacture(
                Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        facture.setDetailsFacture(new HashSet<>());
        facture.setFournisseur(fournisseur);
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
        Optional<Reglement> ofResult = Optional.of(reglement);
        when(reglementRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        Reglement actualRetrieveReglementResult = reglementServiceImpl.retrieveReglement(1L);
        verify(reglementRepository).findById(Mockito.<Long>any());
        assertSame(reglement, actualRetrieveReglementResult);
    }

    /**
     * Method under test: {@link ReglementServiceImpl#retrieveReglementByFacture(Long)}
     */
    @Test
    void testRetrieveReglementByFacture() {
        ArrayList<Reglement> reglementList = new ArrayList<>();
        when(reglementRepository.retrieveReglementByFacture(Mockito.<Long>any())).thenReturn(reglementList);
        List<Reglement> actualRetrieveReglementByFactureResult = reglementServiceImpl.retrieveReglementByFacture(1L);
        verify(reglementRepository).retrieveReglementByFacture(Mockito.<Long>any());
        assertTrue(actualRetrieveReglementByFactureResult.isEmpty());
        assertSame(reglementList, actualRetrieveReglementByFactureResult);
    }

    /**
     * Method under test: {@link ReglementServiceImpl#getChiffreAffaireEntreDeuxDate(Date, Date)}
     */
    @Test
    void testGetChiffreAffaireEntreDeuxDate() {
        when(reglementRepository.getChiffreAffaireEntreDeuxDate(Mockito.<Date>any(), Mockito.<Date>any()))
                .thenReturn(10.0f);
        Date startDate = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        float actualChiffreAffaireEntreDeuxDate = reglementServiceImpl.getChiffreAffaireEntreDeuxDate(startDate,
                Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        verify(reglementRepository).getChiffreAffaireEntreDeuxDate(Mockito.<Date>any(), Mockito.<Date>any());
        assertEquals(10.0f, actualChiffreAffaireEntreDeuxDate);
    }
}

