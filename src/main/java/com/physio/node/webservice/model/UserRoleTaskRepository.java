package com.physio.node.webservice.model;

import com.physio.node.webservice.model.JPA.UserRole;

import java.util.Optional;

public interface UserRoleTaskRepository {
    Optional<UserRole> findByRoleName(String roleName);
    Optional<UserRole> findByIduserRole(int roleId);
}
