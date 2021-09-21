package com.physio.node.webservice.model.JPA;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
@Data
@Embeddable
public class Mygroup_UsersPK implements Serializable {
    //default serial version id, required for serializable classes.
    private static final long serialVersionUID = 1L;

    @Column(name="user_iduser", insertable=false, updatable=false)
    private int userIduser;

    @Column(name="mygroup_idmygroup", insertable=false, updatable=false)
    private int mygroupIdmygroup;

    public Mygroup_UsersPK() {
    }


    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Mygroup_UsersPK)) {
            return false;
        }
        Mygroup_UsersPK castOther = (Mygroup_UsersPK) other;
        return
                (this.userIduser == castOther.userIduser)
                        && (this.mygroupIdmygroup == castOther.mygroupIdmygroup);
    }

    public int hashCode() {
        final int prime = 31;
        int hash = 17;
        hash = hash * prime + this.userIduser;
        hash = hash * prime + this.mygroupIdmygroup;

        return hash;
    }
}
