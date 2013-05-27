package be.e2partners.persistence;

import be.e2partners.domain.Persoon;
import be.e2partners.domain.PersoonType;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: user
 * Date: 16/11/11
 * Time: 10:21
 * A dao for all persons
 */
public interface PersoonDao extends GenericDao<Persoon,Long> {
    public List<? extends Persoon> getSpecificList(PersoonType type);


}
