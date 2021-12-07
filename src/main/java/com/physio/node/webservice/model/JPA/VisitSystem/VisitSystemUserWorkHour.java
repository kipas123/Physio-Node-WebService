package com.physio.node.webservice.model.JPA.VisitSystem;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "visit_system_user_work_hour")
public class VisitSystemUserWorkHour {
    @Id
    @Column(name = "idvisit_system_user_work_hour")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int idUserWorkHour;

    @Column(name = "user_work_hour_beginning_time")
    private LocalTime userWorkHour_beginningTime;

    @Column(name = "user_work_hour_ending_time")
    private LocalTime userWorkHour_endingTime;

    @ManyToOne
    @JoinColumn(name="user_work_day_idvisit_system_user_work_day")
    @JsonBackReference
    private VisitSystemUserWorkDay visitSystemUserWorkDay;

    public VisitSystemUserWorkHour(){}

    public VisitSystemUserWorkHour(int workHourId){
        this.idUserWorkHour = workHourId;
    }

    public VisitSystemUserWorkHour(LocalTime startWorkHour, LocalTime endWorkHour, VisitSystemUserWorkDay visitSystem_userWorkDay) {
        this.userWorkHour_beginningTime = startWorkHour;
        this.userWorkHour_endingTime = endWorkHour;
        this.visitSystemUserWorkDay = visitSystem_userWorkDay;
    }
}
