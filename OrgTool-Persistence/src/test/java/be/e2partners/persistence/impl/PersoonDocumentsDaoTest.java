package be.e2partners.persistence.impl;

import be.e2partners.domain.Medewerker;
import be.e2partners.domain.Persoon;
import be.e2partners.domain.PersoonDocument;
import be.e2partners.persistence.PersoonDao;
import be.e2partners.persistence.PersoonDocumentsDao;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: peeteth
 * Date: 29/10/13
 * Time: 14:04
 * To change this template use File | Settings | File Templates.
 */
public class PersoonDocumentsDaoTest extends E2PSpringTest {

    @Autowired
    PersoonDocumentsDao persoonDocumentsDao;

    @Autowired
    PersoonDao persoonDao;

    private Medewerker medewerker;
    private PersoonDocument document;

    @Before
    public void setUp() throws Exception {
        medewerker = new Medewerker();
        medewerker.setNaam("Bosmans");
        medewerker.setVoornaam("Jos");
        medewerker = (Medewerker)persoonDao.create(medewerker);
    }

    @Test
    public void testAddDocument(){
        PersoonDocument document = new PersoonDocument();
        byte[] content = {111, 1, 3};
        document.setContent(content);
        String bestandsnaam = "testBestand";
        document.setBestandsnaam(bestandsnaam);
        document.setOwner(medewerker);
        PersoonDocument persistedDocument = persoonDocumentsDao.create(document);
        assertTrue(persistedDocument.getBestandsnaam().equals(bestandsnaam));
        assertTrue(Arrays.equals(persistedDocument.getContent(), content));
    }

}
