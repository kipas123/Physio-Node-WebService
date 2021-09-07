package com.physio.node.webservice.model.DTO;

import com.physio.node.webservice.model.JPA.AilmentIndication;
import com.physio.node.webservice.model.JPA.AilmentNote;
import lombok.Data;

@Data
public class AilmentNoteDTO {
    private int idailmentNote;
    private String noteDescription;
    private String noteHeader;
    AilmentNoteDTO(AilmentNote ailmentNote){
        this.idailmentNote = ailmentNote.getIdailmentNote();
        this.noteDescription = ailmentNote.getNoteDescription();
        this.noteHeader = ailmentNote.getNoteHeader();
    }
}
