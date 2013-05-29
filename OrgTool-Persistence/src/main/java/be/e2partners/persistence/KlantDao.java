package be.e2partners.persistence;

import be.e2partners.domain.Klant;

/**
 * Created with IntelliJ IDEA.
 * User: peeteth
 * Date: 17/05/13
 * Time: 13:52
 */
public interface KlantDao extends GenericDao<Klant,Long> {

    public boolean removeKlantPersonen(Klant klant);

}
