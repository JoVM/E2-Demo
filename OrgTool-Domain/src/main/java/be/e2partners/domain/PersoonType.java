package be.e2partners.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by IntelliJ IDEA.
 * User: user
 * Date: 15/11/11
 * Time: 12:18
 * Represents a persontype enumerator
 */

@Entity
@Table(name = "persoontype")
public enum PersoonType {

    //This is Ordinal based, alternative is a huge amount of code to get rid of ordinal based enum types mapped in JPA
    KANDIDAAT(0),MEDEWERKER(1),FREELANCER(2),CONTACT(3);

    @Id
    @Column(name = "ptype_id")
    private int typeId;

    PersoonType() {
        //Only for mapping to a Table
    }

    private PersoonType(int typeId){
        this.typeId = typeId;
    }

    public int getTypeId() {
        return typeId;
    }

    public static PersoonType valueOf(int id) {
        switch (id) {
            case 1: return KANDIDAAT;
            case 2: return MEDEWERKER;
            case 3: return FREELANCER;
            default: return CONTACT;
        }
    }

}
