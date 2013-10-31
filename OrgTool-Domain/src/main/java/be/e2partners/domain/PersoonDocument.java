package be.e2partners.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: peeteth
 * Date: 28/05/13
 * Time: 11:37
 * Model for storing material such as CV documents
 */

@Entity
@Table(name = "persoondocuments")
public class PersoonDocument implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doc_id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="owner_id",insertable=false, updatable=false)
    private Persoon owner;

    @Lob
    @Column(name = "content")
    private byte[] content;

    @Column(name = "bestandsnaam")
    private String bestandsnaam;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Persoon getOwner() {
        return owner;
    }

    public void setOwner(Persoon owner) {
        this.owner = owner;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public String getBestandsnaam() {
        return bestandsnaam;
    }

    public void setBestandsnaam(String bestandsnaam) {
        this.bestandsnaam = bestandsnaam;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PersoonDocument)) return false;

        PersoonDocument document = (PersoonDocument) o;

        if (!bestandsnaam.equals(document.bestandsnaam)) return false;
        if (!Arrays.equals(content, document.content)) return false;
        if (!owner.equals(document.getOwner())){
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
//        int result = bestandsnaam.hashCode();
        int result = 666;
        result = 31 * result + (content != null ? Arrays.hashCode(content) : 0);
        return result;
    }

    //    @Override
//    public int hashCode() {
//        int result = 1;
//        result = 31 * result + Arrays.hashCode(content);
//        result = 31 * result + bestandsnaam.hashCode();
//        return result;
//    }
}
