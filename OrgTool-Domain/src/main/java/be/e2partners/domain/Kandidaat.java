package be.e2partners.domain;

import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 * User: DEV
 * Date: 3/11/11
 * Time: 14:21
 *
 */
@Entity
@DiscriminatorValue("0")
public class Kandidaat extends Persoon{

    public Kandidaat() {
        persoonType = PersoonType.KANDIDAAT;
    }

}
