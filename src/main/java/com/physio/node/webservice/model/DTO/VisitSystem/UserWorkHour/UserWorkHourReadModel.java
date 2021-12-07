package com.physio.node.webservice.model.DTO.VisitSystem.UserWorkHour;

import com.physio.node.webservice.model.JPA.VisitSystem.VisitSystemUserWorkHour;
import lombok.Data;

import java.time.LocalTime;
@Data
public class UserWorkHourReadModel {
    private int idUserWorkHour;
    private LocalTime userWorkHourBeginningTime;
    private LocalTime userWorkHourEndingTime;

    public UserWorkHourReadModel(int idUserWorkHour, LocalTime userWorkHour_beginningTime, LocalTime userWorkHour_endingTime) {
        this.idUserWorkHour = idUserWorkHour;
        this.userWorkHourBeginningTime = userWorkHour_beginningTime;
        this.userWorkHourEndingTime = userWorkHour_endingTime;
    }
    public UserWorkHourReadModel(VisitSystemUserWorkHour visitSystem_userWorkHour){
        this.idUserWorkHour = visitSystem_userWorkHour.getIdUserWorkHour();
        this.userWorkHourBeginningTime = visitSystem_userWorkHour.getUserWorkHour_beginningTime();
        this.userWorkHourEndingTime = visitSystem_userWorkHour.getUserWorkHour_endingTime();
    }
}
