package com.physio.node.webservice.adapter;

import com.physio.node.webservice.model.JPA.User;
import com.physio.node.webservice.model.UserTaskRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SqlUserTaskRepository extends UserTaskRepository, JpaRepository<User, Integer> {

//    User findByIduser(int id User);
}
