package be.e2partners.integration;

import be.e2partners.domain.*;
import be.e2partners.persistence.service.KlantService;
import be.e2partners.persistence.service.PersoonService;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static junit.framework.Assert.*;

/**
 * Created by IntelliJ IDEA.
 * User: user
 * Date: 16/11/11
 * Time: 11:34
 *
 */


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:be.e2partners.integration/applicationContext-test.xml"})
//@Ignore // TODO: re-enable and make this work damnit!
public class PersoonServiceIntegratieTest implements ApplicationContextAware{


    private Persoon medewerker;

    private Kandidaat kan;

    PersoonService pService;
    KlantService klantService;

    ApplicationContext applicationContext;
    private Freelancer free;
    private ContactPersoon cont;
    private Klant klant;

    @Before
    public void setup(){
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


//        applicationContext = new FileSystemXmlApplicationContext(
//                new String[]{"/applicationContext-test.xml"}
//        );
        pService = (PersoonService) applicationContext.getBean("persoonServiceImpl");
        klantService = (KlantService) applicationContext.getBean("klantServiceImpl");

        klant = new Klant();
        klant.setNaam("testklant");
        klant.setAdres("klantstraat");
        klantService.createKlant(klant);

    }

    @Test
    public void testService(){

        Persoon medew = pService.createPersoon(medewerker);
        List<PersoonGeschiedenis> history = pService.getPersoonGeschiedenis(medew.getId());
        assertTrue(history.size() > 0);

        medewerker.setVoornaam("Frans");
        pService.update(medewerker);
        assertTrue(pService.getById(medewerker.getId()).getVoornaam().equals("Frans"));
        history = pService.getPersoonGeschiedenis(medew.getId());
        assertTrue(history.size() > 1); //new entry because of update

        pService.createPersoon(kan);
        kan.setNaam("V3rmeulen");
        pService.update(kan);
        assertTrue(pService.getById(kan.getId()).getNaam().equals("V3rmeulen"));

        pService.createPersoon(free);

        pService.createPersoon(cont);

        List<Persoon> alle = pService.getAllPersons();

        //indien de db bezoedeld is werkt dit uiteraard niet.
        assertTrue(alle.size() >= 4);

        Medewerker changed = (Medewerker) pService.modifyPersonType(kan,PersoonType.MEDEWERKER);
        pService.update(changed);

        for(Persoon persoon: pService.getAllPersons()){
            if(persoon.getNaam().equals(changed.getNaam())){
                assertTrue("The person is not as expected",persoon instanceof Medewerker);
            }
        }

        PersoonGeschiedenis persoonGeschiedenis = new PersoonGeschiedenis();
        persoonGeschiedenis.setPersoonId(medewerker.getId());
        persoonGeschiedenis.setPersoonTypeId(medewerker.getPersoonType().getTypeId());
        persoonGeschiedenis.setKlant(klant);

        pService.addPersoonGeschiedenis(persoonGeschiedenis);



    }


    @After
    public void breakDown(){
        pService.deleteById(medewerker);

        pService.deleteById(kan);

        pService.deleteById(free);

        pService.deleteById(cont);

        klantService.delete(klant);
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
       this.applicationContext = applicationContext;
    }
}
