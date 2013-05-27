package be.e2partners.persistence.service;

import be.e2partners.domain.Klant;
import be.e2partners.domain.KlantGeschiedenis;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: peeteth
 * Date: 17/05/13
 * Time: 11:19
 *
 */
public interface KlantService {

    @Transactional(readOnly = false)
    public Klant createKlant(Klant klant);

    @Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
    public Klant getById(Long id);

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public boolean delete(Klant klant);

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public Klant update(Klant klant);

    @Transactional(readOnly = true)
    public List<KlantGeschiedenis> getKlantGeschiedenis(Long klantId);

    @Transactional(readOnly = true)
    public List<Klant> getAllClients();


}
