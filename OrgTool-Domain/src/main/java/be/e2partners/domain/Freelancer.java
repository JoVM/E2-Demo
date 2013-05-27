package be.e2partners.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by IntelliJ IDEA.
 * User: DEV
 * Date: 3/11/11
 * Time: 14:21
 * Freelancer class
 */

@Entity
@DiscriminatorValue("2")
public class Freelancer extends AbstractMedewerker {

    public Freelancer() {
        persoonType = PersoonType.FREELANCER;
    }


}
