package be.e2partners.persistence.impl;

import be.e2partners.domain.Klant;
import be.e2partners.persistence.KlantDao;
import be.e2partners.persistence.impl.GenericHibernateDaoImpl;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * User: peeteth
 * Date: 17/05/13
 * Time: 11:36
 */
@Repository
public class KlantDaoImpl extends GenericHibernateDaoImpl<Klant,Long> implements KlantDao {

    @Override
    public Klant create(Klant entity) {
        return super.create(entity);    //To change body of overridden methods use File | Settings | File Templates.
    }
}
