package be.e2partners.web;

import be.e2partners.domain.Persoon;
import org.apache.commons.io.FilenameUtils;
import org.apache.myfaces.custom.fileupload.UploadedFile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;

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
    private UploadedFile uploadedFile;

    public void submit(Persoon persoon){
        try {
            String fileName = FilenameUtils.getName(uploadedFile.getName());
            String contentType = uploadedFile.getContentType();
            byte[] bytes = uploadedFile.getBytes();

            //TODO: db upload

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(String.format("File '%s' of type '%s' successfully uploaded!", fileName, contentType)));

        }catch (IOException ioe){
            throw new RuntimeException(ioe);
        }



    }


    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }
}
