package be.e2partners.integration;

import be.e2partners.domain.ContactPersoon;
import be.e2partners.domain.Klant;
import be.e2partners.domain.Medewerker;
import be.e2partners.domain.Persoon;
import be.e2partners.persistence.KlantDao;
import be.e2partners.persistence.service.KlantService;
import be.e2partners.persistence.service.PersoonService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: peeteth
 * Date: 17/05/13
 * Time: 13:50
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:be.e2partners.integration/applicationContext-test.xml"})
public class KlantServiceIntegrationTest implements ApplicationContextAware {

    private KlantService klantService;
    private PersoonService persoonService;
    private ApplicationContext applicationContext;
    private Klant klant;
    private Medewerker medewerker;
    private ContactPersoon contact;

    @Before
    public void setUp(){
        klantService = (KlantService) applicationContext.getBean("klantServiceImpl");
        persoonService = (PersoonService) applicationContext.getBean("persoonServiceImpl");

        klant = new Klant();
        klant.setNaam("KBC");

        medewerker = new Medewerker();
        medewerker.setNaam("medewerkerkerel");
        medewerker.setVoornaam("test");

        contact = new ContactPersoon();
        contact.setVoornaam("test");
        contact.setNaam("contactkerel");



    }

    @Test
    public void testKlantDao(){

        Persoon test = persoonService.createPersoon(medewerker);
        persoonService.createPersoon(contact);
        klantService.createKlant(klant);

        klant.addRelatedPerson(contact);
        klant.addRelatedPerson(medewerker);

        klantService.update(klant);

        Klant fetched = klantService.getById(klant.getId());
        assertTrue("q.people not correct for this client",fetched.getRelatedPersons().size() == 2);


    }

    @After
    public void tearDown(){
        klant.getRelatedPersons().clear();
        klantService.update(klant);
        klantService.delete(klant);
        persoonService.deleteById(contact);
        persoonService.deleteById(medewerker);
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
