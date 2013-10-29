package be.e2partners.persistence.impl;

import be.e2partners.domain.*;
import be.e2partners.persistence.PersoonDao;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: peeteth
 * Date: 29/10/13
 * Time: 13:12
 * To change this template use File | Settings | File Templates.
 */
public class PersoonDaoTest extends E2PSpringTest {


    private Medewerker medewerker;
    private Kandidaat kan;
    private Freelancer free;
    private ContactPersoon cont;

    @Autowired
    private PersoonDao persoonDao;

    @Before
    public void setUp() throws Exception {
        medewerker = new Medewerker();
        medewerker.setNaam("Bosmans");
        medewerker.setVoornaam("Jos");


        kan = new Kandidaat();
        kan.setNaam("Vermeulen");
        kan.setVoornaam("Joske");

        free = new Freelancer();
        free.setNaam("Hoogerland");
        free.setVoornaam("Johnny");

        cont = new ContactPersoon();
        cont.setNaam("Strauss-Kahn");
        cont.setVoornaam("Dominique");
    }

    @Test
    public void testUpdate_changeType() throws Exception {
        Persoon med = persoonDao.create(medewerker);

        med.setPersoonType(PersoonType.KANDIDAAT);
        persoonDao.update(med);
        Persoon medewerkerToCandidate = persoonDao.findById(med.getId());
        assertTrue(medewerkerToCandidate.getPersoonType().equals(PersoonType.KANDIDAAT));
    }

    @Test
    public void testGetSpecificList() throws Exception {
        persoonDao.create(kan);
        Kandidaat twee = new Kandidaat();
        String tweedeNaam = "tweede";
        twee.setNaam(tweedeNaam);
        String tweedevoornaam = "tweedevoornaam";
        twee.setVoornaam(tweedevoornaam);
        persoonDao.create(twee);
        persoonDao.create(free);

        List<? extends Persoon> specificList = persoonDao.getSpecificList(PersoonType.KANDIDAAT);
        assertTrue(specificList.contains(kan));
        assertTrue(specificList.contains(twee));
        assertFalse(specificList.contains(free));
    }

}
