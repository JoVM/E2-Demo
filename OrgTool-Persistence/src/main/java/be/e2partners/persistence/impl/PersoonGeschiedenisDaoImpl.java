package be.e2partners.persistence.impl;

import be.e2partners.domain.PersoonGeschiedenis;
import be.e2partners.persistence.PersoonGeschiedenisDao;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: user
 * Date: 18/11/11
 * Time: 12:29
 *
 */

@Repository
public class PersoonGeschiedenisDaoImpl extends GenericHibernateDaoImpl<PersoonGeschiedenis,Long> implements PersoonGeschiedenisDao{

    @Override
    public List<PersoonGeschiedenis> getGeschiedenisPerPersoon(long persoonId) {
        Query q = getEntityManager().createNamedQuery("geschiedenis.perpersoon");
		q.setParameter("persoonId", persoonId);

        return q.getResultList();     //zullen zien


    }

    @Override
    /**
     * TODO: start of history 2 ends history 1, however is this useful?  check!
     */
    public PersoonGeschiedenis create(PersoonGeschiedenis entity) {

        List<PersoonGeschiedenis> geschiedenis = getGeschiedenisPerPersoon(entity.getPersoonId());
        if(geschiedenis != null && geschiedenis.size() > 0) {
            for (int i = geschiedenis.size() - 1; i >= 0; i--) {
                PersoonGeschiedenis pg = geschiedenis.get(i);
                if (pg.getPersoonTypeId() == entity.getPersoonTypeId()) {
                    pg.setStopDate(new Date());
                    update(pg);
                    break;
                }
            }
        }
        return super.create(entity);
    }
}
