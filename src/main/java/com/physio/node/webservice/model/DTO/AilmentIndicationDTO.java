package com.physio.node.webservice.model.DTO;

import com.physio.node.webservice.model.JPA.Ailment;
import com.physio.node.webservice.model.JPA.AilmentIndication;
import lombok.Data;

@Data
public class AilmentIndicationDTO {
    private int idailmentIndication;
    private String indicationDescription;
    private String indicationHeader;
    private int ailmentId;

    AilmentIndicationDTO(AilmentIndication ailmentIndication){
        this.idailmentIndication = ailmentIndication.getIdailmentIndication();
        this.indicationDescription = ailmentIndication.getIndicationDescription();
        this.indicationHeader = ailmentIndication.getIndicationHeader();
    }
    AilmentIndicationDTO(String indicationHeader, String indicationDescription, int ailmentId){
        this.indicationHeader = indicationHeader;
        this.indicationDescription = indicationDescription;
        this.ailmentId = ailmentId;
    }
}
