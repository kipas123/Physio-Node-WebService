package com.physio.node.webservice.model.DTO.Mygroup;

import com.physio.node.webservice.model.DTO.User.UserWriteModel;
import lombok.Data;

@Data
public class MyGroupWriteModel {
    private int idmygroup;
    private String mygroupName;
    private String mygroupDescription;
    private UserWriteModel mygroupOwner;

    public MyGroupWriteModel(int idmygroup, String mygroupName, String mygroupDescription, UserWriteModel mygroupOwner){
        this.idmygroup = idmygroup;
        this.mygroupName = mygroupName;
        this.mygroupDescription = mygroupDescription;
        this.mygroupOwner = mygroupOwner;
    }

}
