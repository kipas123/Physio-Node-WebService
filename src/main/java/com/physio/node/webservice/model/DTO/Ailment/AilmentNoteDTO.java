package com.physio.node.webservice.model.DTO.Ailment;

import com.physio.node.webservice.model.JPA.AilmentIndication;
import com.physio.node.webservice.model.JPA.AilmentNote;
import lombok.Data;

import java.util.List;

@Data
public class AilmentNoteDTO {
    private int idailmentNote;
    private String noteDescription;
    private String noteHeader;
    private int ailmentId;
    AilmentNoteDTO(AilmentNote ailmentNote){
        this.idailmentNote = ailmentNote.getIdailmentNote();
        this.noteDescription = ailmentNote.getNoteDescription();
        this.noteHeader = ailmentNote.getNoteHeader();
    }
    AilmentNoteDTO(String noteHeader,String noteDescription, int ailmentId){
        this.ailmentId = ailmentId;
        this.noteHeader = noteHeader;
        this.noteDescription = noteDescription;
    }
}
