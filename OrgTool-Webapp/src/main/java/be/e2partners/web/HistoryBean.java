package be.e2partners.web;

import be.e2partners.domain.Persoon;
import be.e2partners.domain.PersoonGeschiedenis;
import be.e2partners.persistence.service.PersoonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: user
 * Date: 22/11/11
 * Time: 15:58
 *
 */

@Component
@Scope("request")
@Qualifier("historyBean")
public class HistoryBean {

    @Autowired
    private PersoonService persoonService;

    public List<PersoonGeschiedenis> getGeschiedenis(Persoon persoon){
        return persoonService.getPersoonGeschiedenis(persoon.getId());
    }
}
