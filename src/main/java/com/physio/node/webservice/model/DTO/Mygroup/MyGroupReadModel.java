package com.physio.node.webservice.model.DTO.Mygroup;

import com.physio.node.webservice.model.JPA.Mygroup;
import lombok.Data;

@Data
public class MyGroupReadModel {

    private int idMygroup;
    private String mygroupName;
    private String mygroupDescription;
    private String mygroupFounder;

    public MyGroupReadModel(int idMygroup, String mygroupName, String mygroupDescription, String mygroupFounder){
        this.idMygroup = idMygroup;
        this.mygroupName = mygroupName;
        this.mygroupDescription = mygroupDescription;
        this.mygroupFounder = mygroupFounder;
    }


    public MyGroupReadModel(Mygroup mygroup){
        this.idMygroup = mygroup.getIdmygroup();
        this.mygroupName = mygroup.getMygroupName();
        this.mygroupDescription = mygroup.getMygroupDescription();
        this.mygroupFounder = mygroup.getMygroupOwner().getUserName() + " " +mygroup.getMygroupOwner().getUserSurname();
    }

}
