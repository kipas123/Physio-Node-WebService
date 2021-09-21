package com.physio.node.webservice.model.DTO.Ailment;

import com.physio.node.webservice.model.JPA.AilmentFilepath;
import lombok.Data;

@Data
public class AilmentFilepathDTO {
    private int idailmentFilepath;
    private String path;


    AilmentFilepathDTO(AilmentFilepath ailmentFilepath){
        this.idailmentFilepath = ailmentFilepath.getIdailmentFilepath();
        this.path = ailmentFilepath.getPath();
    }
}
