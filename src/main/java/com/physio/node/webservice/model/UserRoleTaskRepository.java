package com.physio.node.webservice.model;

import com.physio.node.webservice.model.JPA.UserRole;

public interface UserRoleTaskRepository {
    UserRole findByRoleName(String roleName);
    UserRole findByIduserRole(int roleId);
}
