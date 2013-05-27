package be.e2partners.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by IntelliJ IDEA.
 * User: DEV
 * Date: 3/11/11
 * Time: 14:22
 *
 */

@Entity
@DiscriminatorValue("3")
public class ContactPersoon extends Persoon {

    public ContactPersoon() {
        this.persoonType = PersoonType.CONTACT;
    }


}
