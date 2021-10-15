package com.physio.node.webservice.adapter;

import com.physio.node.webservice.model.JPA.Mygroup_Users;
import com.physio.node.webservice.model.JPA.Mygroup_UsersPK;
import com.physio.node.webservice.model.Mygroup_UsersTaskRepository;
import org.hibernate.sql.Delete;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SqlMygroup_UsersTaskRepository extends Mygroup_UsersTaskRepository, JpaRepository<Mygroup_Users, Mygroup_UsersPK> {
}
