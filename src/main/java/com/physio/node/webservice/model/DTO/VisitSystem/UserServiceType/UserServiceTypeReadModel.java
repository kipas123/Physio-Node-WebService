package com.physio.node.webservice.model.DTO.VisitSystem.UserServiceType;

import com.physio.node.webservice.model.JPA.VisitSystem.VisitSystemUserServiceType;
import lombok.Data;

import java.time.LocalTime;

@Data
public class UserServiceTypeReadModel {
    private int idUserServiceType;
    private String userServiceTypeName;
    private LocalTime userServiceTypeDuration;

    public UserServiceTypeReadModel(VisitSystemUserServiceType visitSystem_userServiceType){
        this.idUserServiceType = visitSystem_userServiceType.getIdUserServiceType();
        this.userServiceTypeName = visitSystem_userServiceType.getUserServiceTypeName();
        this.userServiceTypeDuration = visitSystem_userServiceType.getUserServiceTypeDuration();
    }
}
