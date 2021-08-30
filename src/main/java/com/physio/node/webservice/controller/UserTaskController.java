package com.physio.node.webservice.controller;

import com.physio.node.webservice.adapter.SqlGroupTaskRepository;
import com.physio.node.webservice.model.DTO.UserDTO;
import com.physio.node.webservice.model.GroupTaskRepository;
import com.physio.node.webservice.model.JPA.Mygroup;
import com.physio.node.webservice.model.JPA.User;
import com.physio.node.webservice.model.UserTaskRepository;
import com.physio.node.webservice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class UserTaskController {
    public static final Logger logger = LoggerFactory.getLogger(UserTaskController.class);
    private final UserService userService;
    private final GroupTaskRepository groupTaskRepository;
    private UserTaskRepository userTaskRepository;


    public UserTaskController(UserService userService, SqlGroupTaskRepository sqlGroupTaskRepository, GroupTaskRepository groupTaskRepository, UserTaskRepository userTaskRepository) {
        this.userService = userService;
        this.groupTaskRepository = groupTaskRepository;
        this.userTaskRepository = userTaskRepository;
    }


    @GetMapping("/test/{id}")
    UserDTO findUserByUsername(@PathVariable String id){
        return userService.getUserbyID(id);
    }

}
