package com.physio.node.webservice.model.DTO;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.physio.node.webservice.model.JPA.Ailment;
import com.physio.node.webservice.model.JPA.AilmentFilepath;
import com.physio.node.webservice.model.JPA.AilmentIndication;
import com.physio.node.webservice.model.JPA.AilmentNote;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class AilmentDTO {
    private int idailment;
    private String ailmentDescription;
    private String ailmentName;
    private List<AilmentNoteDTO>  ailmentNotes;
    private List<AilmentIndicationDTO>  ailmentIndications;
    private List<AilmentFilepathDTO>  ailmentFilepaths;

    public AilmentDTO(){}

    public AilmentDTO(int idailment, String ailmentName, String ailmentDescription){
        this.idailment = idailment;
        this.ailmentName = ailmentName;
        this.ailmentDescription = ailmentDescription;
    }

    public AilmentDTO(Ailment ailment) {
        this.idailment = ailment.getIdailment();
        this.ailmentDescription = ailment.getAilmentDescription();
        this.ailmentName = ailment.getAilmentName();
        this.ailmentNotes = ailment.getAilmentNotes().stream().map(AilmentNoteDTO::new).collect(Collectors.toList());
        this.ailmentIndications = ailment.getAilmentIndications().stream().map(AilmentIndicationDTO::new).collect(Collectors.toList());
        this.ailmentFilepaths = ailment.getAilmentFilepaths().stream().map(AilmentFilepathDTO::new).collect(Collectors.toList());
    }
}
