package com.physio.node.webservice.model.JPA.VisitSystem;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.physio.node.webservice.model.JPA.User;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.List;

@Data
@Entity
@Table(name = "visit_system_user_service_type")
public class VisitSystemUserServiceType {
    @Id
    @Column(name = "idvisit_system_user_service_type")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUserServiceType;

    @Column(name = "user_service_type_name")
    private String userServiceTypeName;

    @Column(name = "user_service_type_duration")
    private LocalTime userServiceTypeDuration;

    @Column(name = "user_service_type_active")
    private Boolean userServiceTypeActive;

    @ManyToOne
    @JoinColumn(name = "user_iduser")
    @JsonBackReference(value = "user_iduser")
    private User userOwner;

    @JsonManagedReference
    @OneToMany(mappedBy = "visitSystemUserServiceType")
    private List<VisitSystemUserVisit> visitSystemUserVisits;

    public VisitSystemUserServiceType() {
    }
    public VisitSystemUserServiceType(int idUserServiceType) {
        this.idUserServiceType = idUserServiceType;
    }

    public VisitSystemUserServiceType(String userServiceTypeName, LocalTime userServiceTypeDuration, User userOwner, Boolean userServiceTypeActive) {
        this.userServiceTypeName = userServiceTypeName;
        this.userServiceTypeDuration = userServiceTypeDuration;
        this.userOwner = userOwner;
        this.userServiceTypeActive = userServiceTypeActive;
    }
}