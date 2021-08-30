package com.physio.node.webservice.model.DTO;

import com.physio.node.webservice.model.JPA.Ailment;
import lombok.Data;

@Data
public class AilmentDTO {
    private String ailmentDescription;
    private String ailmentName;

    public AilmentDTO(Ailment ailment) {
        this.ailmentDescription = ailment.getAilmentDescription();
        this.ailmentName = ailment.getAilmentName();
    }
}
