package be.e2partners.util;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 * Created with IntelliJ IDEA.
 * User: peeteth
 * Date: 14/05/13
 * Time: 10:15
 * Simple bean to be able to reload the application
 */

@Controller
@Scope("request")
@Qualifier("reloadBean")
@ManagedBean
@RequestScoped
public class ReloadBean {

    public void reload(){
        FacesContext context = FacesContext.getCurrentInstance();
        context.setViewRoot(context.getApplication().getViewHandler().createView(context, "/index.html"));
        context.getPartialViewContext().setRenderAll(true);
        context.renderResponse();
    }


}
