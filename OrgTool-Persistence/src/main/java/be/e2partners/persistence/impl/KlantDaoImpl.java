package be.e2partners.persistence.impl;

import be.e2partners.domain.Klant;
import be.e2partners.persistence.KlantDao;
import be.e2partners.persistence.impl.GenericHibernateDaoImpl;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;

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
        return super.create(entity);
    }

    @Override
    public boolean removeKlantPersonen(Klant klant) {
        klant.getRelatedPersons().clear();
        update(klant);
//        String deleteQry = "DELETE from klantpersonen where klant_id = :id";
//
//        Query query = getEntityManager().createNativeQuery(deleteQry);
//        query.setParameter("id",klant.getId());
//        int result = query.executeUpdate();
        return true;
    }

    @Override
    public void delete(Klant entity) {
        removeKlantPersonen(entity);
        super.delete(entity);
    }
}
