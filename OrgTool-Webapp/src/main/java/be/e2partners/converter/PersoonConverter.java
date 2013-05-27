package be.e2partners.converter;

import be.e2partners.domain.Persoon;
import be.e2partners.persistence.service.PersoonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: peeteth
 * Date: 23/05/13
 * Time: 13:10
 *
 */

//@FacesConverter("PersoonConverter")
@Component
@ManagedBean
@RequestScoped
@Scope("request")
public class PersoonConverter implements Converter {


    @Autowired
    protected PersoonService persoonService;


    /**
     * Override for a specific type of person in subclass
     * @return
     */
    protected List<? extends Persoon> getPersoonList(){
        return persoonService.getAllPersons();
    }



    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) throws ConverterException {
        for(Persoon persoon:getPersoonList()){
            if(persoon.getPersonIdentifier().equals(s)) {
                return persoon;
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) throws ConverterException {
        if(o ==  null){
            return null;
        }else {
            return ((Persoon)o).getPersonIdentifier();
        }
    }
}
