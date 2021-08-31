package com.physio.node.webservice.model.DTO;

import com.physio.node.webservice.model.JPA.Mygroup;
import lombok.Data;

@Data
public class MyGroupDTO {
    private int idMygroup;
    private String mygroupName;
    private String mygroupDescription;

    public MyGroupDTO(Mygroup mygroup){
        this.idMygroup = mygroup.getIdmygroup();
        this.mygroupName = mygroup.getMygroupName();
        this.mygroupDescription = mygroup.getMygroupDescription();
    }

}
