package com.physio.node.webservice.model.DTO.Ailment;

import com.physio.node.webservice.model.JPA.AilmentFiles;
import lombok.Data;

@Data
public class AilmentFilepathDTO {
    private int idailmentFilepath;
    private String path;

}
