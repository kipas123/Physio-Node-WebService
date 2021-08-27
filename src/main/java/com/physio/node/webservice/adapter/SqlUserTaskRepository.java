package com.physio.node.webservice.adapter;

import com.physio.node.webservice.model.JPA.User;
import com.physio.node.webservice.model.UserTaskRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SqlUserTaskRepository extends UserTaskRepository, JpaRepository<User, Integer> {

}
