package com.physio.node.webservice.model.JPA.VisitSystem;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.physio.node.webservice.model.JPA.User;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "visit_system_user_visit")
public class VisitSystemUserVisit {

    @Id
    @Column(name = "idvisit_system_user_visit")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUserVisit;

    @Column(name = "user_visit_time")
    private LocalTime userVisitTime;

    @Column(name = "user_visit_accepted")
    private boolean accepted;

    @ManyToOne
    @JoinColumn(name = "visit_system_user_work_day_idvisit_system_user_work_day")
    @JsonBackReference
    private VisitSystemUserWorkDay visitSystemUserWorkDay;


    @ManyToOne
    @JoinColumn(name = "visit_system_user_service_type_idvisit_system_user_service_type")
    @JsonBackReference
    private VisitSystemUserServiceType visitSystemUserServiceType;


    @ManyToOne
    @JoinColumn(name = "user_iduser")
    @JsonBackReference
    private User user;



    @ManyToOne
    @JoinColumn(name = "visit_system_user_visit_status_idvisit_system_user_visit_status")
    @JsonBackReference
    private VisitSystemUserVisitStatus visitSystemUserVisitStatus;
    public VisitSystemUserVisit(){}

    public VisitSystemUserVisit(LocalTime userVisitTime, VisitSystemUserWorkDay visitSystemUserWorkDay, VisitSystemUserServiceType visitSystemUserServiceType, User user, VisitSystemUserVisitStatus visitSystemUserVisitStatus, boolean accepted) {
        this.userVisitTime = userVisitTime;
        this.visitSystemUserWorkDay = visitSystemUserWorkDay;
        this.visitSystemUserServiceType = visitSystemUserServiceType;
        this.user = user;
        this.visitSystemUserVisitStatus = visitSystemUserVisitStatus;
        this.accepted = accepted;
    }
}
