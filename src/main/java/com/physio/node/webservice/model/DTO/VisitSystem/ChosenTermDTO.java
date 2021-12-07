package com.physio.node.webservice.model.DTO.VisitSystem;

import lombok.Data;

import java.sql.Date;
import java.time.LocalTime;

@Data
public class ChosenTermDTO {
    private LocalTime chosenTimeTerm;
    private Date chosenDataTerm;
    private int serviceProviderId;

    public ChosenTermDTO(LocalTime chosenTimeTerm, Date chosenDataTerm, int serviceProviderId) {
        this.chosenTimeTerm = chosenTimeTerm;
        this.chosenDataTerm = chosenDataTerm;
        this.serviceProviderId = serviceProviderId;
    }
}
