package be.e2partners.persistence.service;

import be.e2partners.domain.*;
import be.e2partners.persistence.PersoonDao;
import be.e2partners.persistence.PersoonGeschiedenisDao;
import org.apache.commons.lang.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: user
 * Date: 16/11/11
 * Time: 10:59
 *
 */
@Service
public class PersoonServiceImpl implements PersoonService,Serializable {

    @Autowired
    private PersoonDao persoonDao;

    @Autowired
    private PersoonGeschiedenisDao persoonGeschiedenisDao;


    @Override
    public Persoon createPersoon(Persoon persoon) {
        Validate.notNull(persoon,"Een persoon kan niet null zijn wanneer deze wordt gepersisteerd");

        Persoon persisted = persoonDao.create(persoon);

        PersoonGeschiedenis pg = new PersoonGeschiedenis();
        pg.setPersoonId(persisted.getId());
        pg.setPersoonTypeId(persisted.getPersoonType().getTypeId());
        pg.setEntryDate(new Date());

        persoonGeschiedenisDao.create(pg);

        return persisted;

    }

    @Override
    public Persoon getById(Long id) {
        return persoonDao.findById(id);
    }

    @Override
    public boolean deleteById(Persoon persoon) {
        Validate.notNull(persoon,"Een persoon kan niet null zijn wanneer deze wordt verwijderd");

        Persoon retrieved = persoonDao.findById(persoon.getId());

        persoonDao.delete(retrieved);
        return true;
    }

    @Override
    public Persoon update(Persoon persoon) {
        Validate.notNull(persoon,"Een persoon kan niet null zijn wanneer deze wordt gepersisteerd");
        Persoon persisted = persoonDao.update(persoon);


        PersoonGeschiedenis pg = new PersoonGeschiedenis();
        pg.setPersoonId(persisted.getId());
        pg.setPersoonTypeId(persisted.getPersoonType().getTypeId());
        pg.setEntryDate(new Date());

        persoonGeschiedenisDao.create(pg);

        return persisted;

    }

    @Override
    public List<PersoonGeschiedenis> getPersoonGeschiedenis(Long id) {
        return persoonGeschiedenisDao.getGeschiedenisPerPersoon(id);
    }

    @Override
    public List<Persoon> getAllPersons() {
        return persoonDao.findAll();
    }

    @Override
    public List<? extends Persoon> getAllPersons(PersoonType type) {
        return persoonDao.getSpecificList(type);
    }

    @Override
    public void addPersoonGeschiedenis(PersoonGeschiedenis geschiedenis) {
        persoonGeschiedenisDao.create(geschiedenis);
    }

    @Override
    public List<PersoonType> getAllTypes() {
        List<PersoonType> types = Arrays.asList(PersoonType.values());
        return types;
    }

    @Override
    public Persoon modifyPersonType(Persoon source, PersoonType target) {
        Persoon ret = null;
//        if(source.getPersoonType().equals(target)){
//            return source;
//        }
        switch (target){
            case CONTACT:{
                ret = new ContactPersoon();
                ret.setNaam(source.getNaam());
                ret.setVoornaam(source.getVoornaam());
                ret.setId(source.getId());
                break;
            }
            case FREELANCER:{
                ret = new Freelancer();
                ret.setNaam(source.getNaam());
                ret.setVoornaam(source.getVoornaam());
                ret.setId(source.getId());
                break;
            }
            case KANDIDAAT: {
                ret = new Kandidaat();
                ret.setNaam(source.getNaam());
                ret.setVoornaam(source.getVoornaam());
                ret.setId(source.getId());
                break;
            }
            case MEDEWERKER: {
                ret = new Medewerker();
                ret.setNaam(source.getNaam());
                ret.setVoornaam(source.getVoornaam());
                ret.setId(source.getId());
                break;
            }
            default:return source;
        }
        return ret;
    }
}
