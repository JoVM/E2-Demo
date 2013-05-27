package be.e2partners.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: DEV
 * Date: 3/11/11
 * Time: 14:21
 *
 */
@Entity
@DiscriminatorValue("1")
public class Medewerker extends AbstractMedewerker{

    @Column(name = "begindatum")
    public Date beginDatum;

    public Medewerker() {
        persoonType = PersoonType.MEDEWERKER;
    }

    public Date getBeginDatum() {
        return beginDatum;
    }

    public void setBeginDatum(Date beginDatum) {
        this.beginDatum = beginDatum;
    }
}
