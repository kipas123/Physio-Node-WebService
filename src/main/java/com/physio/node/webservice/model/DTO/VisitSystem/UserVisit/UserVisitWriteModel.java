package com.physio.node.webservice.model.DTO.VisitSystem.UserVisit;

import lombok.Data;

import java.sql.Date;
import java.time.LocalTime;

@Data
public class UserVisitWriteModel {
    private int userId;
    private Date bookingDate;
    private String userServiceId;
    private LocalTime bookingTime;

    public UserVisitWriteModel(int userId, Date bookingDate, String userServiceId, LocalTime bookingTime) {
        this.userId = userId;
        this.bookingDate = bookingDate;
        this.userServiceId = userServiceId;
        this.bookingTime = bookingTime;
    }
}
