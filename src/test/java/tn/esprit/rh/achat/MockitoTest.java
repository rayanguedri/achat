
package tn.esprit.rh.achat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import tn.esprit.rh.achat.entities.Facture;
import tn.esprit.rh.achat.entities.Fournisseur;
import tn.esprit.rh.achat.repositories.FactureRepository;
import tn.esprit.rh.achat.services.FactureServiceImpl;

import java.util.*;

import static com.fasterxml.jackson.databind.jsonFormatVisitors.JsonValueFormat.UUID;
import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {FactureServiceImpl.class})
public class MockitoTest {

    @Mock
    private FactureRepository repository;

    @InjectMocks
    private FactureServiceImpl factureService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getFactureTest() {
        System.out.println("get test facture");

        long id = java.util.UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
        long id2 = java.util.UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;

        List<Facture> factureList = new ArrayList<>();
        factureList.add(new Facture(id, 100, 5, Date.from(java.time.Instant.now()), Date.from(java.time.Instant.now()), false, new HashSet<>(), new Fournisseur(), new HashSet<>()));
        factureList.add(new Facture(id2, 50, 10, Date.from(java.time.Instant.now()), Date.from(java.time.Instant.now()), false, new HashSet<>(), new Fournisseur(), new HashSet<>()));


        when(repository.findAll()).thenReturn(factureList);}
}
