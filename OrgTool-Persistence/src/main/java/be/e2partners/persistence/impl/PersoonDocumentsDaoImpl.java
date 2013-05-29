package be.e2partners.persistence.impl;

import be.e2partners.domain.PersoonDocument;
import be.e2partners.persistence.PersoonDocumentsDao;
import be.e2partners.persistence.impl.GenericHibernateDaoImpl;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * User: peeteth
 * Date: 28/05/13
 * Time: 12:59
 *
 */
@Repository
public class PersoonDocumentsDaoImpl extends GenericHibernateDaoImpl<PersoonDocument,Long> implements PersoonDocumentsDao {
}
