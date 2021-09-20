package com.physio.node.webservice.adapter;

import com.physio.node.webservice.model.JPA.UserRole;
import com.physio.node.webservice.model.UserRoleTaskRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SqlUserRoleTaskRepository extends UserRoleTaskRepository,JpaRepository<UserRole, Integer> {
}
