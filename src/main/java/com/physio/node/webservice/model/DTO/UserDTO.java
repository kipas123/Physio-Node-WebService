package com.physio.node.webservice.model.DTO;

import com.physio.node.webservice.model.JPA.User;
import lombok.Data;

@Data
public class UserDTO {
    public int userId;
    public String userName;
    public String userEmail;
    public String userSurname;

    public UserDTO(User user) {
        this.userName = user.getUserName();
        this.userSurname=user.getUserSurname();
        this.userEmail = user.getUserEmail();
        this.userId = user.getIduser();
    }
}
