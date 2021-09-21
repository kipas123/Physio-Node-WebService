package com.physio.node.webservice.model.JPA;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the user_mygroup database table.
 *
 */
@Data
@Entity
@Table(name="user_mygroup")
@NamedQuery(name="Mygroup_Users.findAll", query="SELECT u FROM Mygroup_Users u")
public class Mygroup_Users implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    private Mygroup_UsersPK id;

    //bi-directional many-to-one association to Mygroup
    @ManyToOne
    @MapsId("mygroupIdmygroup")
    @JsonBackReference
    @JoinColumn(name="mygroup_idmygroup")
    private Mygroup mygroup;

    //bi-directional many-to-one association to User
    @ManyToOne
    @MapsId("userIduser")
    @JsonBackReference
    @JoinColumn(name="user_iduser")
    private User user;

    public Mygroup_Users() {
    }

}