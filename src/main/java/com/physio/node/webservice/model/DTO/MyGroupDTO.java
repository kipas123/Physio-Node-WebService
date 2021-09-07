package com.physio.node.webservice.model.DTO;

import com.physio.node.webservice.model.JPA.Mygroup;
import lombok.Data;

@Data
public class MyGroupDTO {
    private int idMygroup;
    private String mygroupName;
    private String mygroupDescription;
    private String mygroupCreatedBy;

    public MyGroupDTO(String mygroupName, String mygroupDescription){
        this.mygroupName = mygroupName;
        this.mygroupDescription = mygroupDescription;
    }


    public MyGroupDTO(Mygroup mygroup){
        this.idMygroup = mygroup.getIdmygroup();
        this.mygroupName = mygroup.getMygroupName();
        this.mygroupDescription = mygroup.getMygroupDescription();
        this.mygroupCreatedBy = mygroup.getFounder().getUserName();
    }

}
