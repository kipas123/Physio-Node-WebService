package com.physio.node.webservice.model.DTO;

import com.physio.node.webservice.model.JPA.Ailment;
import com.physio.node.webservice.model.JPA.AilmentFilepath;
import com.physio.node.webservice.model.JPA.AilmentIndication;
import com.physio.node.webservice.model.JPA.AilmentNote;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AilmentDTO {
    private String ailmentDescription;
    private String ailmentName;
    private List<AilmentNote>  ailmentNotes;
    private List<AilmentIndication>  ailmentIndications;
    private List<AilmentFilepath>  ailmentFilepaths;

    public AilmentDTO(Ailment ailment) {
        this.ailmentDescription = ailment.getAilmentDescription();
        this.ailmentName = ailment.getAilmentName();
        this.ailmentNotes = ailment.getAilmentNotes();
        this.ailmentIndications = ailment.getAilmentIndications();
        this.ailmentFilepaths = getAilmentFilepaths();
    }
}
