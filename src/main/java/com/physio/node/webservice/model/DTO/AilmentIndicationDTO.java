package com.physio.node.webservice.model.DTO;

import com.physio.node.webservice.model.JPA.Ailment;
import com.physio.node.webservice.model.JPA.AilmentIndication;
import lombok.Data;

@Data
public class AilmentIndicationDTO {
    private int idailmentIndication;
    private String indicationDescription;
    private String indicationHeader;

    AilmentIndicationDTO(AilmentIndication ailmentIndication){
        this.idailmentIndication = ailmentIndication.getIdailmentIndication();
        this.indicationDescription = ailmentIndication.getIndicationDescription();
        this.indicationHeader = ailmentIndication.getIndicationHeader();
    }
}
