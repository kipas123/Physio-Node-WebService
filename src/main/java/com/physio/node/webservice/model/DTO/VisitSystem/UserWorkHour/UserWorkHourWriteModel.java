package com.physio.node.webservice.model.DTO.VisitSystem.UserWorkHour;

import lombok.Data;

import java.sql.Date;
import java.time.LocalTime;

@Data
public class UserWorkHourWriteModel {
    private LocalTime userWorkHourBeginningTime;
    private LocalTime userWorkHourEndingTime;
    private int userId;
    private Date userWorkDay;

    public UserWorkHourWriteModel(LocalTime userWorkHourBeginningTime, LocalTime userWorkHourEndingTime, int userId, Date userWorkDay) {
        this.userWorkHourBeginningTime = userWorkHourBeginningTime;
        this.userWorkHourEndingTime = userWorkHourEndingTime;
        this.userId = userId;
        this.userWorkDay = userWorkDay;
    }
}
