package com.physio.node.webservice.model.DTO;

import com.physio.node.webservice.model.JPA.Mygroup;
import lombok.Data;

@Data
public class MyGroupDTO {

    private int idMygroup;
    private String mygroupName;
    private String mygroupDescription;
    private String mygroupFounder;

    public MyGroupDTO(int idMygroup, String mygroupName, String mygroupDescription, String mygroupFounder){
        this.idMygroup = idMygroup;
        this.mygroupName = mygroupName;
        this.mygroupDescription = mygroupDescription;
        this.mygroupFounder = mygroupFounder;
    }


    public MyGroupDTO(Mygroup mygroup){
        this.idMygroup = mygroup.getIdmygroup();
        this.mygroupName = mygroup.getMygroupName();
        this.mygroupDescription = mygroup.getMygroupDescription();
        this.mygroupFounder = mygroup.getMygroupFounder().getUserName() + " " +mygroup.getMygroupFounder().getUserSurname();
    }

}
