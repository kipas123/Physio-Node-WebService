package com.physio.node.webservice.model.DTO.VisitSystem;

import lombok.Data;

import java.time.LocalTime;

@Data
public class WorkHourListOfAvailableHourDTO {
    private LocalTime availableHour;
    private boolean empty;

    public WorkHourListOfAvailableHourDTO(LocalTime availableHour, boolean empty) {
        this.availableHour = availableHour;
        this.empty = empty;
    }
}
