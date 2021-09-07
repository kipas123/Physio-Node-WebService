package com.physio.node.webservice.model;

import com.physio.node.webservice.model.JPA.User;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserTaskRepository {
    List<User> findAll();
    List<User> findByIduser(int id);
    List<User> findByUserName(String username);
    User save(User entity);

}
