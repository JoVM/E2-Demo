package be.e2partners.persistence.impl;

import be.e2partners.domain.ContactPersoon;
import be.e2partners.domain.Klant;
import be.e2partners.domain.Persoon;
import be.e2partners.persistence.KlantDao;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: peeteth
 * Date: 29/10/13
 * Time: 11:08
 * To change this template use File | Settings | File Templates.
 */
public class KlantDaoTest extends E2PSpringTest {

    private Klant klant;

    @Autowired
    private KlantDao klantDao;
    private String adres;
    private String naam;

    @Before
    public void setup(){
        klant = new Klant();
        adres = "testadres";
        klant.setAdres(adres);
        naam = "test";
        klant.setNaam(naam);

    }

    @Test
    public void testCreate() throws Exception {
        Klant persisted = klantDao.create(klant);

        assertTrue(persisted.getNaam().equals(naam));
        assertTrue(persisted.getAdres().equals(adres));
    }

    @Test
    public void testUpdate(){
        Klant persisted = klantDao.create(klant);
        String updatedNaam = naam + "bijkomend";
        persisted.setNaam(updatedNaam);
        Klant update = klantDao.update(klant);
        assertTrue(update.getNaam().equals(updatedNaam));
    }

    @Test
    public void testAddRemoveKlantPersonen() throws Exception {
        Klant persisted = klantDao.create(klant);
        Persoon related = new ContactPersoon();
        related.setNaam("josmans");
        related.setVoornaam("jos");
        klant.addRelatedPerson(related);
        persisted = klantDao.update(klant);
        klantDao.removeKlantPersonen(klant);
        persisted = klantDao.findById(persisted.getId());
        assertTrue(persisted.getRelatedPersons().size() == 0);

    }

    @Test
    public void testDelete() throws Exception {
        Klant persisted = klantDao.create(klant);
        klantDao.delete(persisted);
        assertTrue(klantDao.findById(persisted.getId()) == null);
    }
}
