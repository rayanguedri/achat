package tn.esprit.spring.service;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import tn.esprit.rh.achat.entities.Fournisseur;
import tn.esprit.rh.achat.repositories.FactureRepository;
import tn.esprit.rh.achat.services.IFactureService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.rh.achat.entities.DetailFacture;
import tn.esprit.rh.achat.entities.Facture;
import tn.esprit.rh.achat.repositories.FactureRepository;
import tn.esprit.rh.achat.services.IFactureService; // Import the service interface

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class FactureServiceImplTest implements IFactureService, FactureRepository{ // Rename the test class

    @Autowired
    private FactureRepository factureRepository;

    @Autowired
    private IFactureService factureService; // Inject the service interface

    @Test
    public void testCreateReadUpdateDeleteFacture() {
        // Create a Facture
        Facture facture = new Facture();
        facture.setMontantRemise(100.0f);
        facture.setMontantFacture(500.0f);
        facture.setDateCreationFacture(new Date());
        facture.setDateDerniereModificationFacture(new Date());
        facture.setArchivee(false);

        Set<DetailFacture> detailsFacture = new HashSet<>();
        facture.setDetailsFacture(detailsFacture);

        // Create
        Facture createdFacture = factureService.addFacture(facture); // Use the service method
        assertNotNull(createdFacture.getIdFacture());

        // Read
        Facture retrievedFacture = factureService.retrieveFacture(createdFacture.getIdFacture()); // Use the service method
        assertNotNull(retrievedFacture);
        assertEquals(100.0f, retrievedFacture.getMontantRemise());
        assertEquals(500.0f, retrievedFacture.getMontantFacture());

        // Update (if your service provides an update method)
        // Facture updatedFacture = factureService.updateFacture(retrievedFacture); // Use the service method

        // Delete (if your service provides a delete method)
        // factureService.cancelFacture(createdFacture.getIdFacture()); // Use the service method
        // assertNull(factureService.retrieveFacture(createdFacture.getIdFacture())); // Use the service method
    }

    @Override
    public List<Facture> getFactureByFournisseur(Fournisseur fournisseur) {
        return null;
    }

    @Override
    public float getTotalFacturesEntreDeuxDates(Date startDate, Date endDate) {
        return 0;
    }

    @Override
    public void updateFacture(Long id) {

    }

    @Override
    public List<Facture> findAll() {
        return null;
    }

    @Override
    public List<Facture> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Facture> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Facture> findAllById(Iterable<Long> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Facture facture) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> iterable) {

    }

    @Override
    public void deleteAll(Iterable<? extends Facture> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Facture> S save(S s) {
        return null;
    }

    @Override
    public <S extends Facture> List<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<Facture> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Facture> S saveAndFlush(S s) {
        return null;
    }

    @Override
    public <S extends Facture> List<S> saveAllAndFlush(Iterable<S> iterable) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Facture> iterable) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Facture getOne(Long aLong) {
        return null;
    }

    @Override
    public Facture getById(Long aLong) {
        return null;
    }

    @Override
    public <S extends Facture> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Facture> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Facture> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Facture> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Facture> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Facture> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public List<Facture> retrieveAllFactures() {
        return null;
    }

    @Override
    public List<Facture> getFacturesByFournisseur(Long idFournisseur) {
        return null;
    }

    @Override
    public Facture addFacture(Facture f) {
        return null;
    }

    @Override
    public void cancelFacture(Long id) {

    }

    @Override
    public Facture retrieveFacture(Long id) {
        return null;
    }

    @Override
    public void assignOperateurToFacture(Long idOperateur, Long idFacture) {

    }

    @Override
    public float pourcentageRecouvrement(Date startDate, Date endDate) {
        return 0;
    }
}
