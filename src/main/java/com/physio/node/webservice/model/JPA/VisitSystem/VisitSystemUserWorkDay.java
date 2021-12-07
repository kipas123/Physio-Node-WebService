package com.physio.node.webservice.model.JPA.VisitSystem;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.physio.node.webservice.model.JPA.User;
import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Data
@Entity
@Table(name = "visit_system_user_work_day")
public class VisitSystemUserWorkDay {
    @Id
    @Column(name = "idvisit_system_user_work_day")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUserWorkDay;

    @Column(name = "user_work_day")
    private Date userWorkDay;

    @ManyToOne
    @JoinColumn(name="user_iduser")
    @JsonBackReference(value="user_iduser")
    private User user;

    @JsonManagedReference
    @OneToMany(mappedBy = "visitSystemUserWorkDay")
    private List<VisitSystemUserWorkHour> visitSystemUserWorkHours;

    @JsonManagedReference
    @OneToMany(mappedBy = "visitSystemUserWorkDay")
    private List<VisitSystemUserVisit> visitSystemUserVisits;

    public VisitSystemUserWorkDay(){}
    public VisitSystemUserWorkDay(java.sql.Date workDate, User user) {
        this.userWorkDay = workDate;
        this.user = user;
    }
}
