package be.e2partners.persistence.service;

import be.e2partners.domain.Persoon;
import be.e2partners.domain.PersoonGeschiedenis;
import be.e2partners.domain.PersoonType;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: user
 * Date: 16/11/11
 * Time: 10:57
 * Service that is responsible for the DB stuff of all person types
 */
public interface PersoonService {

    @Transactional(readOnly = false)
    Persoon createPersoon(Persoon persoon);

    @Transactional(readOnly = true)
    Persoon getById(Long id);

    @Transactional(readOnly = false)
    boolean deleteById(Persoon persoon);

    @Transactional(readOnly = false)
    Persoon update(Persoon persoon);

    @Transactional(readOnly = true)
    List<PersoonGeschiedenis> getPersoonGeschiedenis(Long id);

    @Transactional
    List<Persoon> getAllPersons();

    @Transactional
    List<? extends Persoon> getAllPersons(PersoonType type);

    @Transactional
    void addPersoonGeschiedenis(PersoonGeschiedenis geschiedenis);

    List<PersoonType> getAllTypes();

    Persoon modifyPersonType(Persoon source,PersoonType target);

}
