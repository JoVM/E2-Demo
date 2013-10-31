package be.e2partners.web;

import be.e2partners.domain.*;
import be.e2partners.persistence.service.PersoonService;
import be.e2partners.util.JSFUtils;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


//import org.richfaces.component.html.HtmlTree;
//import org.richfaces.event.NodeSelectedEvent;

/**
 * Created by IntelliJ IDEA.
 * User: user
 * Date: 21/11/11
 * Time: 13:40
 *
 */


@Controller
@Scope("session")
@ManagedBean
@SessionScoped
public class PersonBean implements Serializable{

    private Persoon currentPerson;

    private PersoonType currentPersonType;
    private String mode = "CREATE";

    private String nameFilter;

    @Autowired
    private PersoonService persoonService;

    //    TreeNode<PersoonGeschiedenis> treeNode;
    private String headerName;
    private String getDocument;

    public List<Persoon> getPersons(){
        return persoonService.getAllPersons();
    }

    public List<Medewerker> getMedewerkers(){
        return (List<Medewerker>) persoonService.getAllPersons(PersoonType.MEDEWERKER);

    }

    public List<Freelancer> getFreelancers(){
        return (List<Freelancer>) persoonService.getAllPersons(PersoonType.FREELANCER);
    }

    public List<Kandidaat> getKandidaten(){
        return (List<Kandidaat>) persoonService.getAllPersons(PersoonType.KANDIDAAT);
    }

    public List<ContactPersoon> getContacts(){
        return (List<ContactPersoon>) persoonService.getAllPersons(PersoonType.CONTACT);
    }

    public String manageCVs(Persoon persoon){
        //TODO: rename

//        Set<PersoonGeschiedenis> geschiedenis = persoon.getPersoonGeschiedenis();
        currentPerson = persoon;
        currentPersonType = persoon.getPersoonType();
        mode = "EDIT";
        FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().put("persoon",currentPerson);


        return "medewerkers-detail";
    }

    public String editPersoon(Persoon persoon){

        currentPerson = persoon;
        currentPersonType = persoon.getPersoonType();
        mode = "EDIT";
        FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().put("persoon",currentPerson);

        return "edit.persoon";
    }

    public String deletePerson(Persoon persoon){

        String res = persoon.getPersoonType().name().toLowerCase();
        boolean ok = persoonService.deleteById(persoon);
        if(ok){
            FacesContext.getCurrentInstance().addMessage("Delete",new FacesMessage(persoon.getNaam() + " " + persoon.getVoornaam() +" deleted"));
        } else {

            FacesContext.getCurrentInstance().addMessage("Delete",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,persoon.getNaam() + persoon.getVoornaam() +" NOT deleted","Person NOT deleted"));
        }
        currentPerson = null;
        currentPersonType = null;
//        return "freelancer";
        return res;
    }




//    private TreeNode<PersoonGeschiedenis> loadTree(List<PersoonGeschiedenis> geschiedenis) {
//
//        TreeNode<PersoonGeschiedenis> treeNode = new TreeNodeImpl<PersoonGeschiedenis>();
//        for (PersoonGeschiedenis geschiedenisItem : geschiedenis) {
//            TreeNodeImpl<PersoonGeschiedenis> head = new TreeNodeImpl<PersoonGeschiedenis>();
//            head.setData(geschiedenisItem);
//            //TODO: more details?  Or better: "kandidaat with history children", next: Medewerker with history children
////            for (Subcategory _subCat : geschiedenisItem.getSubCategories()) {
////                TreeNodeImpl<INameable> child = new TreeNodeImpl<INameable>();
////                child.setData(_subCat);
////                head.addChild(_subCat.getId(), child);
////            }
//            treeNode.addChild(geschiedenisItem.getId(), head);
//        }
//        return treeNode;
//    }

//    public TreeNode<PersoonGeschiedenis> getTreeNode() {
//        return treeNode;
//    }

//    public void processSelection(NodeSelectedEvent event) {
//		HtmlTree tree = (HtmlTree) event.getComponent();
//		TreeNode<PersoonGeschiedenis> currentNode = tree.getModelTreeNode(tree.getRowKey());
//		PersoonGeschiedenis historyItem = currentNode.getData();
//        headerName = PersoonType.valueOf((int) historyItem.getPersoonTypeId()).name();
////		if (currentNode.isLeaf() && historyItem instanceof Subcategory) {
////			selectedPanel = NAMES[1];
////			headerName = historyItem.getName();
////			description = ((Subcategory) historyItem).getDescription();
////			Subcategory selSubcat = (Subcategory) historyItem;
////			skillRatings = surveyResult.getSkillRatingsForCategory(selSubcat);
////			if (getProgress().intValue() > 0) {
////				save();
////			}
////		} else {
////			skillRatings = new ArrayList<SkillRating>();
////		}
//	}

    //todo: more abstract...  an entity is not necessarily a person.
    public Persoon getNewEntity(){
//         || !this.mode.equals("CREATE")
        if(currentPerson == null) {
            currentPerson = new Kandidaat();  //default
            currentPersonType = currentPerson.getPersoonType();
//            persoonCache.put(currentPerson.getPersoonType(),currentPerson);
        }
        this.mode="CREATE";
        return currentPerson;
    }

    public Medewerker getNewMedewerker(){
        return (Medewerker) getNewPersoon(PersoonType.MEDEWERKER);
    }

    public Freelancer getNewFreelancer(){
        return (Freelancer) getNewPersoon(PersoonType.FREELANCER);
    }

    public Kandidaat getNewKandidaat(){
        return (Kandidaat) getNewPersoon(PersoonType.KANDIDAAT);
    }

    public ContactPersoon getNewContact(){
        return (ContactPersoon) getNewPersoon(PersoonType.CONTACT);
    }

    private Persoon getNewPersoon(PersoonType type){
        Persoon persoon = null;
        this.mode="CREATE";

        if(currentPerson == null || !this.currentPerson.getPersoonType().equals(type))
        {
            switch (type){
                case MEDEWERKER: persoon = new Medewerker(); break;
                case CONTACT: persoon = new ContactPersoon(); break;
                case KANDIDAAT: persoon = new Kandidaat(); break;
                case FREELANCER: persoon = new Freelancer();  break;
                default: return null;
            }

//        if(currentPerson == null){
            currentPerson = persoon;
            currentPersonType = currentPerson.getPersoonType();
//            persoonCache.put(currentPersonType,currentPerson);
//        }
            return currentPerson;
        }else {
            return currentPerson;
        }

    }

    public PersoonType getCurrentPersonType() {
        return currentPersonType;
    }

    public void setCurrentPersonType(PersoonType currentPersonType) {
//        if(currentPerson != null && !currentPersonType.equals(this.currentPersonType)){
//            currentPerson = null;
//        }
        this.currentPersonType = currentPersonType;
    }

    public List<SelectItem> getPersoonTypes(){
        List<PersoonType> persoonEnums = persoonService.getAllTypes();
        List<SelectItem> types = new ArrayList<SelectItem>(persoonEnums.size());
        for(PersoonType type:persoonEnums){
            types.add(new SelectItem(type));
        }
        return types;
    }

    public void switchPerson(){
//        FacesContext.getCurrentInstance().getApplication().get

        Persoon result = persoonService.modifyPersonType(currentPerson,currentPersonType);
        currentPerson = result;
        FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().put("persoon",currentPerson);

    }

    public boolean isMedewerkerRendered(){
        return currentPerson != null && currentPerson.getPersoonType().equals(PersoonType.MEDEWERKER);
    }

    public boolean isContactRendered(){
        return currentPerson.getPersoonType().equals(PersoonType.CONTACT);
    }

    public boolean isFreelancerRendered(){
        return currentPerson.getPersoonType().equals(PersoonType.FREELANCER);
    }

    public boolean isKandidaatRendered(){
        return currentPerson.getPersoonType().equals(PersoonType.KANDIDAAT);
    }

    public String submit(){
        try {
            if(mode.equals("CREATE")){
                persoonService.createPersoon(currentPerson);
                String res = currentPerson.getPersoonType().name().toLowerCase();
                currentPerson = null;
                return res;
            }else if(mode.equals("EDIT")){
                persoonService.update(currentPerson);
                String res = currentPerson.getPersoonType().name().toLowerCase();
                currentPerson = null;
                return res;
            }

        }catch (HibernateException e){
            JSFUtils.addMessageDirect(FacesMessage.SEVERITY_ERROR, e.getLocalizedMessage());
        }
        return "home";
    }

//    public String submit(Persoon persoon){
//        System.out.print("called");
//        return "home";
//    }


    public String getNameFilter() {
        return nameFilter;
    }

    public void setNameFilter(String nameFilter) {
        this.nameFilter = nameFilter;
    }

    public void clearNameFilter(){
        this.nameFilter = "";
    }

    private static void close(Closeable resource) {
        if (resource != null) {
            try {
                resource.close();
            } catch (IOException e) {
                throw new RuntimeException(e); //todo: throw?
            }
        }
    }

    public void getDocument(PersoonDocument doc) {
        ByteArrayInputStream bais = new ByteArrayInputStream(doc.getContent());

        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        externalContext.responseReset();
        externalContext.setResponseContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        externalContext.setResponseHeader("Content-Length", String.valueOf(bais.available()));
        externalContext.setResponseHeader("Content-Disposition", "attachment; filename=\"" + doc.getBestandsnaam() + "\"");
        try {
            OutputStream output = externalContext.getResponseOutputStream();
            output.write(doc.getContent());
            output.flush();
            output.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        facesContext.responseComplete();
    }

    public void removeDocument(PersoonDocument doc) {
        boolean removed = currentPerson.getDocuments().remove(doc);
        if(!removed){
            throw new RuntimeException("Invalid document removal.  Wrong person or document");
        }
        currentPerson = persoonService.update(currentPerson);

    }
}

