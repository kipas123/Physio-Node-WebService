package com.physio.node.webservice.model.JPA.VisitSystem;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "visit_system_user_visit_status")
public class VisitSystemUserVisitStatus {

    @Id
    @Column(name = "idvisit_system_user_visit_status")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUserVisitStatus;

    @Column(name = "user_visit_status_name")
    private  String userVisitStatusName;

    @Column(name = "user_visit_status_frequency")
    private  int userVisitStatusFrequency;

    @JsonManagedReference
    @OneToMany(mappedBy = "visitSystemUserVisitStatus")
    private List<VisitSystemUserVisit> visitSystemUserVisits;

    public VisitSystemUserVisitStatus() { }

    public VisitSystemUserVisitStatus(int idUserVisitStatus) {
        this.idUserVisitStatus = idUserVisitStatus;
    }
}
