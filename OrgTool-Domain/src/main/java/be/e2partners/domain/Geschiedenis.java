package be.e2partners.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: user
 * Date: 18/11/11
 * Time: 10:52
 * Every history (client, person, ...) will have an id and begin/end date.  End dat can be null
 */

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Geschiedenis implements Serializable{

    @Id
    @Column(name = "history_id")
    @GeneratedValue(strategy = GenerationType.TABLE)
    protected Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_from")
    protected Date entryDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_to")
    protected Date stopDate; //wanneer een nieuwe geschiedenis entry binnen komt moet dit de vorige afsluiten.




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public Date getStopDate() {
        return stopDate;
    }

    public void setStopDate(Date stopDate) {
        this.stopDate = stopDate;
    }
}
