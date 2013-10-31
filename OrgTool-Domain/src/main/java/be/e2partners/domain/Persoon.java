package be.e2partners.domain;


import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.IndexColumn;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: DEV
 * Date: 3/11/11
 * Time: 14:19
 * Abstract person class
 */

@Entity
@Table(name = "persoon")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "persoontype_id",discriminatorType = DiscriminatorType.INTEGER)
@NamedQueries({
    @NamedQuery(name = "persoon.by.name", query = "SELECT p FROM Persoon p WHERE p.naam = :naam")
})
public abstract class Persoon implements Serializable {

    @Column(name = "naam")
    private String naam;

    @Column(name = "voornaam")
    private String voornaam;

//    @Column(name = "persoontype_id")
//    @OneToOne(mappedBy = "typeId")
//    @Enumerated()
    protected PersoonType persoonType;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "persoon",fetch = FetchType.EAGER)
    private Set<PersoonGeschiedenis> persoonGeschiedenis;



    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,orphanRemoval=true)
    @JoinColumn(name = "owner_id")
    private Set<PersoonDocument> documents = new HashSet<PersoonDocument>();



    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public PersoonType getPersoonType() {
        return persoonType;
    }

    public void setPersoonType(PersoonType persoonType) {
        this.persoonType = persoonType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPersonIdentifier(){
        //TODO: geboortedatum && vanaf dan equals+hashcode
        //Dit is in gebruik voor converters in JSF
        return this.naam+" "+this.voornaam;
    }


    public Set<PersoonDocument> getDocuments() {
        return documents;
    }

    private void setDocuments(Set<PersoonDocument> documents) {
        this.documents = documents;
    }

    public void addDocument(PersoonDocument document){
//        if(this.documents == null){
//            documents = new HashSet<PersoonDocument>();
//        }
        document.setOwner(this);
        getDocuments().add(document);
//        documents.add(document);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Persoon)) return false;

        Persoon persoon = (Persoon) o;

        if (!naam.equals(persoon.naam)) return false;
        if (persoonType != persoon.persoonType) return false;
        if (!voornaam.equals(persoon.voornaam)) return false;

        return true;
    }

//    @Override
//    public int hashCode() {
//        int result = naam.hashCode();
//        result = 31 * result + voornaam.hashCode();
//        result = 31 * result + persoonType.hashCode();
//        return result;
//    }

    public Set<PersoonGeschiedenis> getPersoonGeschiedenis() {
        return persoonGeschiedenis;
    }

    public void setPersoonGeschiedenis(Set<PersoonGeschiedenis> persoonGeschiedenis) {
        this.persoonGeschiedenis = persoonGeschiedenis;
    }

    public void addPersoonGeschiedenis(PersoonGeschiedenis geschiedenis){
        if(this.persoonGeschiedenis == null){
            this.persoonGeschiedenis = new HashSet<PersoonGeschiedenis>();
        }
        this.persoonGeschiedenis.add(geschiedenis);
    }

    //    @JoinColumn(name = "ptype_id", nullable = false)
//    @Column(name = "persoontype_id")
//    public int getPersoonTypeId(){
//        return this.persoonType.getTypeId();
//    }


}
