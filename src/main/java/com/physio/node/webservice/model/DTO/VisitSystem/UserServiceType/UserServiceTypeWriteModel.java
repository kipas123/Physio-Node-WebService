package com.physio.node.webservice.model.DTO.VisitSystem.UserServiceType;

import lombok.Data;

@Data
public class UserServiceTypeWriteModel {
    private String userServiceTypeName;
    private int userServiceTypeDurationInMinute;
    private int userId;

    public UserServiceTypeWriteModel(String userServiceTypeName, int userServiceTypeDurationInMinute, int userId) {
        this.userServiceTypeName = userServiceTypeName;
        this.userServiceTypeDurationInMinute = userServiceTypeDurationInMinute;
        this.userId = userId;
    }
}
