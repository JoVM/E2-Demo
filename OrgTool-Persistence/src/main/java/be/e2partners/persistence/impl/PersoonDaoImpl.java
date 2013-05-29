package be.e2partners.persistence.impl;

import be.e2partners.domain.Persoon;
import be.e2partners.domain.PersoonType;
import be.e2partners.persistence.PersoonDao;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: user
 * Date: 16/11/11
 * Time: 10:29
 *
 */
@Repository
public class PersoonDaoImpl extends GenericHibernateDaoImpl<Persoon,Long> implements PersoonDao{

    @Override
    public Persoon update(Persoon entity) {
        String updatePT = "UPDATE Persoon P set P.persoontype_id = :typeId where P.id = :id";


//        Query query = getEntityManager().createQuery(updatePT);
        Query query = getEntityManager().createNativeQuery(updatePT);
        query.setParameter("id",entity.getId());
        query.setParameter("typeId",entity.getPersoonType().getTypeId());
        query.executeUpdate();

        return super.update(entity);
    }

    @Override
    public List<? extends Persoon> getSpecificList(PersoonType type) {
        String select = "from Persoon P where P.persoonType = :type";
        Query query = getEntityManager().createQuery(select);
        query.setParameter("type",type);

        return query.getResultList();
    }


}
