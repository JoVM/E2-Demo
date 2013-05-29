package be.e2partners.domain;

import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 * User: user
 * Date: 18/11/11
 * Time: 11:17
 *
 */

@Entity
@Table(name = "persoonhistory")
@NamedQueries( {
		@NamedQuery(name = "geschiedenis.perpersoon", query = "select ph FROM PersoonGeschiedenis ph where ph.persoon = :persoonId")
})
public class PersoonGeschiedenis extends Geschiedenis {


    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id")
    private Persoon persoon;


    @Column(name = "persoontype_id")
    private long persoonTypeId;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name="klant_id")
    private Klant klant;


    public Persoon getPersoon() {
        return persoon;
    }

    public void setPersoon(Persoon persoon) {
        this.persoon = persoon;
    }

    public long getPersoonTypeId() {
        return persoonTypeId;
    }

    public void setPersoonTypeId(long persoonTypeId) {
        this.persoonTypeId = persoonTypeId;
    }

    public Klant getKlant() {
        return klant;
    }

    public void setKlant(Klant klant) {
        this.klant = klant;
    }
}
