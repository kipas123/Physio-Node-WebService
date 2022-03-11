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
import org.springframework.http.HttpStatus;
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
    ResponseEntity<UserReadModel> getUserByIdUser(@PathVariable int id){
        UserReadModel userReadModel = userService.findUserByIdUser(id);
        return new ResponseEntity<>(userReadModel, HttpStatus.OK);
    }

    @GetMapping("/test")
    ResponseEntity<?> test(){
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/roleManagement/foundUser/{value}")
    public ResponseEntity<List<UserReadModel>> getUserByEmailOrNameOrSurname(@PathVariable String value){
        List<UserReadModel> userReadModel = userService.findUserByEmailOrNameOrSurname(value);
        return new ResponseEntity<>(userReadModel, HttpStatus.OK);
    }

    @GetMapping("/roleManagement/foundUserFiltr/{value}")
    public ResponseEntity<List<UserReadModel>> getUnverifiedUserByUserNameOrUserSurname(@PathVariable String value){
        List<UserReadModel> userReadModel = userService.findUnverifiedUserByUserNameOrUserSurname(value);
        return new ResponseEntity<>(userReadModel, HttpStatus.OK);
    }

    @GetMapping("/roleManagement/foundVerifiedUserFiltr/{value}")
    public ResponseEntity<List<UserReadModel>> getVerifiedUserByUserNameOrUserSurname(@PathVariable String value){
        List<UserReadModel> userReadModel = userService.findVerifiedUserByUserNameOrUserSurname(value);
        return new ResponseEntity<>(userReadModel, HttpStatus.OK);
    }

    @GetMapping("/roleManagement/changeRole/{userId}/{roleId}")
    public ResponseEntity<?> getUserByEmailOrNameOrSurname(@PathVariable int userId, @PathVariable int roleId){
        userService.changeUserRole(userId, roleId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/roleManagement/unverified/{page}/{size}")
    public ResponseEntity<List<UserReadModel>> getAllUnverifiedUser(@PathVariable int page, @PathVariable int size){
        List<UserReadModel> userReadModel = userService.findUnverifiedUser(page, size);
        return new ResponseEntity<>(userReadModel, HttpStatus.OK);
    }


    @GetMapping("/roleManagement/modRole")
    public ResponseEntity<List<UserReadModel>> getUsersWithModRole(){

        List<UserReadModel> userReadModel = userService.findUsersWithModRole();
        return new ResponseEntity<>(userReadModel, HttpStatus.OK);
    }
    @GetMapping("/roleManagement/countUnverfied")
    public ResponseEntity<Long> getCountOfUnverfiedUser(){
        Long counter = userService.countUnverifedUser();
        return new ResponseEntity<>(counter,HttpStatus.OK);
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
