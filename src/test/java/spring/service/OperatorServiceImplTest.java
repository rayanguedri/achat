package tn.esprit.spring.service;


import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import tn.esprit.rh.achat.entities.Operateur;
import tn.esprit.rh.achat.services.IOperateurService;

import java.util.List;
@ComponentScan(basePackages = "tn.esprit.rh.achat.services")
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OperatorServiceImplTest {

    @Autowired
    public IOperateurService ios;

    @Test
    @Order(1)
    public void testRetreiveAllOp(){
        List<Operateur> ops = ios.retrieveAllOperateurs();
        Assertions.assertEquals(0, ops.size());
    }


}