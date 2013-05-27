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
		@NamedQuery(name = "geschiedenis.perpersoon", query = "select ph FROM PersoonGeschiedenis ph where ph.persoonId = :persoonId")
})
public class PersoonGeschiedenis extends Geschiedenis {


    @Column(name = "persoon_id")
    private long persoonId;


    @Column(name = "persoontype_id")
    private long persoonTypeId;

    @ManyToOne
    @JoinColumn(name="klant_id")
    private Klant klant;


    public long getPersoonId() {
        return persoonId;
    }

    public void setPersoonId(long persoonId) {
        this.persoonId = persoonId;
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
