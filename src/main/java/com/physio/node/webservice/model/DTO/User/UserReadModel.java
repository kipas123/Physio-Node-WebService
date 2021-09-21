package com.physio.node.webservice.model.DTO;

import com.physio.node.webservice.model.JPA.User;
import lombok.Data;

import java.util.Date;

@Data
public class UserReadModel {
    private int userId;
    private String userEmail;
    private String userName;
    private String userSurname;
    private UserRoleDTO userRoleDTO;
    private Date userDob;
    private String token;

    public UserReadModel(User user) {
        this.userId = user.getIduser();
        this.userEmail = user.getUserEmail();
        this.userName = user.getUserName();
        this.userSurname=user.getUserSurname();
        this.userRoleDTO = new UserRoleDTO(user.getUserRole());
        this.userDob=user.getUserDob();
        this.token = user.getToken();
    }
}
