package com.physio.node.webservice.model.DTO;

import com.physio.node.webservice.model.JPA.User;
import lombok.Data;

@Data
public class UserDTO {
    private int userId;
    private String userName;
    private String userEmail;
    private String userSurname;

    public UserDTO(User user) {
        this.userName = user.getUserName();
        this.userSurname=user.getUserSurname();
        this.userEmail = user.getUserEmail();
        this.userId = user.getIduser();
    }
}
