package be.e2partners.domain;

import javax.persistence.*;
import java.io.Serializable;

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

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    @JoinColumn(name="id")
    private Persoon owner;

    @Lob
    @Column(name = "content")
    private byte[] content;

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
}
