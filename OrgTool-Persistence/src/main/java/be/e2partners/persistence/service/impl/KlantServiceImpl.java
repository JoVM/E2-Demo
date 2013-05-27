package be.e2partners.persistence.service.impl;

import be.e2partners.domain.Klant;
import be.e2partners.domain.KlantGeschiedenis;
import be.e2partners.persistence.KlantDao;
import be.e2partners.persistence.service.KlantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: peeteth
 * Date: 17/05/13
 * Time: 15:29
 */

@Service
public class KlantServiceImpl implements KlantService {


    @Autowired
    private KlantDao klantDao;




    @Override
    public Klant createKlant(Klant klant) {
        return klantDao.create(klant);
    }

    @Override
    public Klant getById(Long id) {
        return klantDao.findById(id);
    }

    @Override
    public boolean delete(Klant klant) {
        klantDao.delete(klant);
        return true;  //otherwise an exception is thrown ??
    }

    @Override
    public Klant update(Klant klant) {
        return klantDao.update(klant);
    }

    @Override
    public List<KlantGeschiedenis> getKlantGeschiedenis(Long klantId) {
        //TODO: implement
        return null;
    }

    @Override
    public List<Klant> getAllClients() {
        return klantDao.findAll();
    }
}
