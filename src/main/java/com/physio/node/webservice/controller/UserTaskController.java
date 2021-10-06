package com.physio.node.webservice.controller;

import com.physio.node.webservice.adapter.SqlMygroupTaskRepository;
import com.physio.node.webservice.jwt.JwtTokenProvider;
import com.physio.node.webservice.model.DTO.User.UserReadModel;
import com.physio.node.webservice.model.DTO.User.UserWriteModel;
import com.physio.node.webservice.model.MygroupTaskRepository;
import com.physio.node.webservice.model.UserTaskRepository;
import com.physio.node.webservice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/user")
@RestController
public class UserTaskController {
    public static final Logger logger = LoggerFactory.getLogger(UserTaskController.class);
    private JwtTokenProvider jwtTokenProvider;
    private final UserService userService;
    private final MygroupTaskRepository mygroupTaskRepository;
    private UserTaskRepository userTaskRepository;


    public UserTaskController(UserService userService, SqlMygroupTaskRepository sqlMygroupTaskRepository, JwtTokenProvider jwtTokenProvider, MygroupTaskRepository mygroupTaskRepository, UserTaskRepository userTaskRepository) {
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.mygroupTaskRepository = mygroupTaskRepository;
        this.userTaskRepository = userTaskRepository;
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

        /*
        Availability: permitAll
        **/
    @PostMapping("/registration")
    public ResponseEntity<?> register(@RequestBody UserWriteModel user){
        return userService.registerUser(user);
    }

        /*
        Availability: permitAll
        **/
    @GetMapping("/login")
    public ResponseEntity<?> login(Principal principal){
        System.out.println("wchodze");
        if(principal == null){
            //This should be ok http status because this will be used for logout path.
            return ResponseEntity.ok(principal);
        }
        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) principal;
        UserReadModel userReadModel = userService.findUserByUserEmail(authenticationToken.getName());
        userReadModel.setToken(jwtTokenProvider.generateToken(authenticationToken));
        return new ResponseEntity<>(userReadModel, HttpStatus.OK);
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
