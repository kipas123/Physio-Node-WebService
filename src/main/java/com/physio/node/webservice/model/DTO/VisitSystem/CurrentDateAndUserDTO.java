package com.physio.node.webservice.model.DTO.VisitSystem;

import lombok.Data;

import java.sql.Date;

@Data
public class CurrentDateAndUserDTO {
    private Date currentDate;
    private int userId;

   public CurrentDateAndUserDTO(Date currentDate, int userId) {
        this.currentDate = currentDate;
        this.userId = userId;
    }
}
