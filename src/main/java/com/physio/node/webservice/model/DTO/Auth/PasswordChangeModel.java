package com.physio.node.webservice.model.DTO.Auth;

import com.physio.node.webservice.model.DTO.User.UserReadModel;
import lombok.Data;

@Data
public class PasswordChangeModel {
    private int userId;
    private String oldPassword;
    private String newPassword;

    public PasswordChangeModel(String oldPassword, String newPassword) {
        this.userId = getUserId();
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }
}
