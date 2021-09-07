package com.physio.node.webservice.controller;

import com.physio.node.webservice.adapter.SqlMygroupTaskRepository;
import com.physio.node.webservice.model.DTO.UserDTO;
import com.physio.node.webservice.model.JPA.User;
import com.physio.node.webservice.model.JPA.UserRole;
import com.physio.node.webservice.model.MygroupTaskRepository;
import com.physio.node.webservice.model.UserTaskRepository;
import com.physio.node.webservice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/user")
@RestController
public class UserTaskController {
    public static final Logger logger = LoggerFactory.getLogger(UserTaskController.class);
    private final UserService userService;
    private final MygroupTaskRepository mygroupTaskRepository;
    private UserTaskRepository userTaskRepository;


    public UserTaskController(UserService userService, SqlMygroupTaskRepository sqlMygroupTaskRepository, MygroupTaskRepository mygroupTaskRepository, UserTaskRepository userTaskRepository) {
        this.userService = userService;
        this.mygroupTaskRepository = mygroupTaskRepository;
        this.userTaskRepository = userTaskRepository;
    }


    @GetMapping("/{id}")
    UserDTO getUserByIdUser(@PathVariable int id){
        return userService.findUserByIdUser(id);
    }

//    @GetMapping("/testjpa")
//    void test(){
//        User user = new User();
//        UserRole userRole = new UserRole();
//        userRole.setIduserRole(1);
//        user.setUserName("TestujeJpass");
//        user.setUserSurname("TestujeJpass");
//        user.setUserEmail("Testuje@jspa.com");
//        userTaskRepository.save(user);
//    }
}
