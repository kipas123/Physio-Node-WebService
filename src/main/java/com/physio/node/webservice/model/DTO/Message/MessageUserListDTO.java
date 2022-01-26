package com.physio.node.webservice.model.DTO.Message;

import com.physio.node.webservice.model.JPA.User;
import lombok.Data;

@Data
public class MessageUserListDTO {
    private int userId;
    private String userName;
    private String userSurname;
    private String userRole;



    public MessageUserListDTO(User user) {
        this.userId = user.getIduser();
        this.userName = user.getUserName();
        this.userSurname = user.getUserSurname();
        this.userRole = user.getUserRole().getRoleName();
    }
}
