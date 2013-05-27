package be.e2partners.converter;

import be.e2partners.domain.ContactPersoon;
import be.e2partners.domain.Persoon;
import be.e2partners.domain.PersoonType;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: peeteth
 * Date: 23/05/13
 * Time: 15:10
 *
 */
//@FacesConverter("ContactConverter")
@Component
@ManagedBean
@RequestScoped
@Scope("request")
public class ContactConverter extends PersoonConverter {

    @Override
    protected List<ContactPersoon> getPersoonList() {
        return (List<ContactPersoon>) persoonService.getAllPersons(PersoonType.CONTACT);
    }


}
