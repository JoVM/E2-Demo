package be.e2partners.persistence.impl;

import be.e2partners.domain.Medewerker;
import be.e2partners.domain.PersoonDocument;
import be.e2partners.persistence.PersoonDao;
import be.e2partners.persistence.PersoonDocumentsDao;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: peeteth
 * Date: 31/10/13
 * Time: 13:32
 * Dedicated delete test
 */
public class PersoonDocumentsDeleteDaoTest extends E2PSpringTest {


    private Medewerker medewerker;

    @Autowired
    private PersoonDocumentsDao persoonDocumentsDao;

    @Autowired
    private PersoonDao persoonDao;

    @Before
    public void setUp() throws Exception {
        medewerker = new Medewerker();
        medewerker.setNaam("Bosmans");
        medewerker.setVoornaam("Jos");
        medewerker = (Medewerker)persoonDao.create(medewerker);
        PersoonDocument document = new PersoonDocument();
        byte[] content = {111, 1, 3};
        document.setContent(content);
        String bestandsnaam = "testBestand";
        document.setBestandsnaam(bestandsnaam);
//        document.setOwner(medewerker);
//        PersoonDocument persistedDocument = persoonDocumentsDao.create(document);

        medewerker.addDocument(document);
        persoonDao.update(medewerker);
        medewerker = (Medewerker) persoonDao.findById(medewerker.getId());
        medewerker.getDocuments().remove(document);
        persoonDao.update(medewerker);
    }

    @Test
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void testDelete(){
        medewerker = (Medewerker)persoonDao.findById(medewerker.getId());
        assertTrue(medewerker.getDocuments().size() == 0);
    }


}
