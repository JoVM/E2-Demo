package be.e2partners.web;

import be.e2partners.domain.ContactPersoon;
import be.e2partners.domain.Klant;
import be.e2partners.domain.Persoon;
import be.e2partners.persistence.service.KlantService;
import be.e2partners.persistence.service.PersoonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: peeteth
 * Date: 17/05/13
 * Time: 10:43
 *
 */

@Controller
@Scope("session")
@ManagedBean
@SessionScoped
public class KlantBean {


    @Autowired
    private PersoonService persoonService;

    @Autowired
    private KlantService klantService;

    private String nameFilter;
    private Klant currentKlant;

    private String mode = "CREATE";


    public List<Klant> getAllClients(){
        return klantService.getAllClients();
    }


    public String editKlant(Klant klant){

        FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().put("klant",klant);
        this.currentKlant = klant;
        this.mode = "EDIT";
        return "klant.edit";
    }

    public String deleteKlant(Klant klant){

        boolean ok = klantService.delete(klant);
        if(ok){
            FacesContext.getCurrentInstance().addMessage("Delete",new FacesMessage(klant.getNaam() + " is deleted"));
        }


        return "klant";
    }



    public PersoonService getPersoonService() {
        return persoonService;
    }

    public void setPersoonService(PersoonService persoonService) {
        this.persoonService = persoonService;
    }

    public KlantService getKlantService() {
        return klantService;
    }

    public void setKlantService(KlantService klantService) {
        this.klantService = klantService;
    }

    public String getNameFilter() {
        return nameFilter;
    }

    public void setNameFilter(String nameFilter) {
        this.nameFilter = nameFilter;
    }

    public void clearNameFilter(){
        this.nameFilter = "";
    }

    public Klant getNewKlant() {


        if(currentKlant == null){
            currentKlant = new Klant();
            this.mode = "CREATE";
            return currentKlant;
        }else {
            return currentKlant;
        }

    }

    public String submit(Klant klant){
        if(this.mode.equals("CREATE")) {
            klantService.createKlant(klant);
        }else {
            klantService.update(klant);
        }
        currentKlant = null;
        return "klant";
    }

    public List<Persoon> getClientContacts(){
        List<Persoon> contacts = new ArrayList<Persoon>(currentKlant.getRelatedPersons().size());
        contacts.addAll(currentKlant.getRelatedPersons());
        return contacts;
    }

    public void setClientContacts(List<Persoon> contacts){
        currentKlant.setRelatedPersons(contacts);

    }

}
