package com.physio.node.webservice.model.DTO.Ailment;
import com.physio.node.webservice.model.JPA.Ailment;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class AilmentReadModel {
    private int idailment;
    private String ailmentDescription;
    private String ailmentName;
    private List<AilmentNoteDTO>  ailmentNotes;
    private List<AilmentIndicationDTO>  ailmentIndications;
    private List<AilmentFilepathDTO>  ailmentFilepaths;
    private String attendingphysician;

    public AilmentReadModel(){}

    public AilmentReadModel(int idailment, String ailmentName, String ailmentDescription){
        this.idailment = idailment;
        this.ailmentName = ailmentName;
        this.ailmentDescription = ailmentDescription;
    }

    public AilmentReadModel(Ailment ailment) {
        this.idailment = ailment.getIdailment();
        this.ailmentDescription = ailment.getAilmentDescription();
        this.ailmentName = ailment.getAilmentName();
        this.ailmentNotes = ailment.getAilmentNotes().stream().map(AilmentNoteDTO::new).collect(Collectors.toList());
        this.ailmentIndications = ailment.getAilmentIndications().stream().map(AilmentIndicationDTO::new).collect(Collectors.toList());
        //this.ailmentFilepaths = ailment.getAilmentFiles().stream().map(AilmentFilepathDTO::new).collect(Collectors.toList());
        this.attendingphysician = ailment.getAttendingphysician().getUserName() + " " + ailment.getAttendingphysician().getUserSurname();
    }
}
