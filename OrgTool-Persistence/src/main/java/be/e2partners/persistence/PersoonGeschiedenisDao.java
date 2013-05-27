package be.e2partners.persistence;

import be.e2partners.domain.PersoonGeschiedenis;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: user
 * Date: 18/11/11
 * Time: 11:59
 *
 */
public interface PersoonGeschiedenisDao extends GenericDao<PersoonGeschiedenis,Long> {

    public List<PersoonGeschiedenis> getGeschiedenisPerPersoon(long persoonId);


}
