package be.e2partners.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: DEV
 * Date: 3/11/11
 * Time: 14:24
 *
 */

@Entity
@javax.persistence.Table(name = "klant")
public class Klant implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "klant_id")
    protected Long id;

    private String naam;

    private String adres;

    @ManyToMany(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinTable(name = "klantpersonen", joinColumns = {@JoinColumn(name = "klant_id")}, inverseJoinColumns = {@JoinColumn(name = "persoon_id")})
    private List<Persoon> relatedPersons;


    public Klant() {
        if(relatedPersons == null){
            relatedPersons = new ArrayList<Persoon>();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public List<Persoon> getRelatedPersons() {
        return relatedPersons;
    }

    public void setRelatedPersons(List<Persoon> relatedPersons) {
        this.relatedPersons = relatedPersons;
    }

    public void addRelatedPerson(Persoon persoon){
        this.relatedPersons.add(persoon);
    }
}
