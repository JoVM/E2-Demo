package be.e2partners.web;

import be.e2partners.domain.Persoon;
import be.e2partners.domain.PersoonDocument;
import be.e2partners.persistence.service.PersoonService;
import org.apache.commons.io.FilenameUtils;
//import org.apache.myfaces.custom.fileupload.UploadedFile;
import org.richfaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: peeteth
 * Date: 27/05/13
 * Time: 14:43
 *
 */


@Controller
@Scope("request")
@ManagedBean
//@SessionScoped
@RequestScoped
public class UploadBean {

    @Autowired
    private PersoonService persoonService;

    private UploadedFile uploadedFile;

    public void submit(Persoon persoon){
        try {
            String fileName = FilenameUtils.getName(uploadedFile.getName());
            String contentType = uploadedFile.getContentType();
            byte[] bytes = uploadedFile.getData();

            PersoonDocument document = new PersoonDocument();
            document.setOwner(persoon);
            document.setContent(bytes);
            document.setBestandsnaam(fileName);
            persoon.addDocument(document);

            persoonService.update(persoon);

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(String.format("File '%s' of type '%s' successfully uploaded!", fileName, contentType)));

        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }


//    public void startDownload(PersoonDocument document){
//        FacesContext facesContext = FacesContext.getCurrentInstance();
//        ExternalContext externalContext = facesContext.getExternalContext();
//        externalContext.setResponseHeader("Content-Type", garbage.getContentType());
//        externalContext.setResponseHeader("Content-Length", garbage.getContent().length);
//        externalContext.setResponseHeader("Content-Disposition", "attachment;filename=\"" + garbage.getFileName() + "\"");
//        externalContext.getResponseOutputStream().write(garbage.getContent());
//        facesContext.responseComplete();
//    }


    public List<PersoonDocument> documents(Persoon persoon){
        List<PersoonDocument> docs = new ArrayList<PersoonDocument>();
        docs.addAll(persoon.getDocuments());
        return docs;
    }


    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }
}
