package com.physio.node.webservice.controller;

import com.physio.node.webservice.adapter.SqlMygroupTaskRepository;
import com.physio.node.webservice.jwt.JwtTokenProvider;
import com.physio.node.webservice.model.DTO.User.UserReadModel;
import com.physio.node.webservice.model.MygroupTaskRepository;
import com.physio.node.webservice.model.UserTaskRepository;
import com.physio.node.webservice.service.UserService;
import com.physio.node.webservice.service.security.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/user")
@RestController
public class UserTaskController {
    public static final Logger logger = LoggerFactory.getLogger(UserTaskController.class);
    private final UserService userService;


    public UserTaskController(UserService userService) {
        this.userService = userService;
    }

    /*
    Availability: admin, physiotherapist, coach
    **/
    @GetMapping("/{id}")
    UserReadModel getUserByIdUser(@PathVariable int id){
        return userService.findUserByIdUser(id);
    }

    @GetMapping("/test")
    ResponseEntity<?> test(){
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/roleManagement/foundUser/{value}")
    public List<UserReadModel> getUserByEmailOrNameOrSurname(@PathVariable String value){
        List<UserReadModel> userList = userService.findUserByEmailOrNameOrSurname(value);
        if(userList.size()==0) userList = null;
        return userList;
    }

    @GetMapping("/roleManagement/foundUserFiltr/{value}")
    public List<UserReadModel> getUnverifiedUserByUserNameOrUserSurname(@PathVariable String value){
        List<UserReadModel> userList = userService.findUnverifiedUserByUserNameOrUserSurname(value);
        if(userList.size()==0) userList = null;
        return userList;
    }

    @GetMapping("/roleManagement/changeRole/{userId}/{roleId}")
    public ResponseEntity<?> getUserByEmailOrNameOrSurname(@PathVariable int userId, @PathVariable int roleId){
        userService.changeUserRole(userId, roleId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/roleManagement/unverified/{page}/{size}")
    public List<UserReadModel> getAllUnverifiedUser(@PathVariable int page, @PathVariable int size){
        return userService.findUnverifiedUser(page, size);
    }


    @GetMapping("/roleManagement/modRole")
    public List<UserReadModel> getUsersWithModRole(){
        return userService.findUsersWithModRole();
    }
    @GetMapping("/roleManagement/countUnverfied")
    public Long getCountOfUnverfiedUser(){
        return userService.countUnverifedUser();
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
