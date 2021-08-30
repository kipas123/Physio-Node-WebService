package com.physio.node.webservice.model;

import com.physio.node.webservice.model.JPA.User;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserTaskRepository {
    Optional<User> findById(Integer id);
    List<User> findAll();
    List<User> findByUserName(String username);
//    User getUser(@Param("name") String userName);

}
