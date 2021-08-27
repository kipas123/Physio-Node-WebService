package com.physio.node.webservice.controller;

import com.physio.node.webservice.adapter.SqlUserTaskRepository;
import com.physio.node.webservice.model.JPA.User;
import com.physio.node.webservice.model.UserTaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserTaskController {
    public static final Logger logger = LoggerFactory.getLogger(UserTaskController.class);
    private final UserTaskRepository userTaskRepository;


    public UserTaskController(UserTaskRepository userTaskRepository, SqlUserTaskRepository sqlUserTaskRepository) {
        this.userTaskRepository = userTaskRepository;
    }
    @GetMapping("/test/{id}")
    List<User> findAll(@PathVariable String id){
        return userTaskRepository.findByUserName(id);
    }

}
