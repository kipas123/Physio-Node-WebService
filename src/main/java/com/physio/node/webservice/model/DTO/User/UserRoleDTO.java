package com.physio.node.webservice.model.DTO.User;

import com.physio.node.webservice.model.JPA.UserRole;
import lombok.Data;

@Data
public class UserRoleDTO {
    private int iduserRole;
    private String roleName;

    public UserRoleDTO(UserRole userRole) {
        this.iduserRole = userRole.getIduserRole();
        this.roleName = userRole.getRoleName();
    }
}
