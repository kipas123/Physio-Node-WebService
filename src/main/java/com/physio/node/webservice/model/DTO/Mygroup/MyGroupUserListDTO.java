package com.physio.node.webservice.model.DTO.Mygroup;

import com.physio.node.webservice.model.DTO.User.UserRoleDTO;
import com.physio.node.webservice.model.JPA.User;
import lombok.Data;

@Data
public class MyGroupUserListDTO {
    private int userId;
    private String userName;
    private String userSurname;
    private String userRole;



    public MyGroupUserListDTO(User user) {
        this.userId = user.getIduser();
        this.userName = user.getUserName();
        this.userSurname = user.getUserSurname();
        this.userRole = user.getUserRole().getRoleName();
    }
}
