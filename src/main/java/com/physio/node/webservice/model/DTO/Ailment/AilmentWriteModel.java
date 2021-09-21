package com.physio.node.webservice.model.DTO.Ailment;

import lombok.Data;

@Data
public class AilmentWriteModel {
    private String ailmentDescription;
    private String ailmentName;
    private int user;
    private int attendingphysician;

    public AilmentWriteModel(){}

    public AilmentWriteModel(String ailmentName, String ailmentDescription, int user, int attendingphysician){
        this.ailmentName = ailmentName;
        this.ailmentDescription = ailmentDescription;
        this.user = user;
        this.attendingphysician = attendingphysician;
    }
}
