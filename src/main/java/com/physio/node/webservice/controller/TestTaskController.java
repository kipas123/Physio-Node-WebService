package com.physio.node.webservice.controller;

import com.physio.node.webservice.adapter.SqlMygroupTaskRepository;
import com.physio.node.webservice.model.*;
import com.physio.node.webservice.model.DTO.FileSystem.AilmentFileDTO;
import com.physio.node.webservice.model.JPA.AilmentFiles;
import com.physio.node.webservice.service.MygroupService;
import com.physio.node.webservice.service.UserService;
import com.physio.node.webservice.service.security.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TestTaskController {


    private UserTaskRepository userTaskRepository;
    private UserService userService;
    private SqlMygroupTaskRepository sqlMygroupTaskRepository;
    private MygroupTaskRepository mygroupTaskRepository;
    private Mygroup_UsersTaskRepository mygroup_usersTaskRepository;
    private MygroupService mygroupService;
    private MessageTaskRepository messageTaskRepository;
    private AilmentFilesTaskRepository ailmentFilesTaskRepository;
    private JavaMailSender javaMailSender;
    private JavaMailSender getJavaMailSender;
    private AuthService authService;

    public TestTaskController(UserTaskRepository userTaskRepository, UserService userService, SqlMygroupTaskRepository sqlMygroupTaskRepository, MygroupTaskRepository mygroupTaskRepository, Mygroup_UsersTaskRepository mygroup_usersTaskRepository, MygroupService mygroupService, MessageTaskRepository messageTaskRepository, AilmentFilesTaskRepository ailmentFilesTaskRepository, JavaMailSender javaMailSender, JavaMailSender getJavaMailSender, AuthService authService){
        this.userTaskRepository = userTaskRepository;
        this.userService = userService;
        this.sqlMygroupTaskRepository = sqlMygroupTaskRepository;
        this.mygroupTaskRepository = mygroupTaskRepository;
        this.mygroup_usersTaskRepository = mygroup_usersTaskRepository;
        this.mygroupService = mygroupService;
        this.messageTaskRepository = messageTaskRepository;
        this.ailmentFilesTaskRepository = ailmentFilesTaskRepository;
        this.javaMailSender = javaMailSender;
        this.getJavaMailSender = getJavaMailSender;
        this.authService = authService;
    }


//    @GetMapping("/test")
//    ResponseEntity<?> test(){
//        try {
//            this.passwordResetService.executePaswordReset("machalica@gmail.com");
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }
//        return ResponseEntity.badRequest().build();
//    }
//    @GetMapping("/test/{token}")
//    ResponseEntity<?> test2(@PathVariable String token){
//        try{
//            UserReadModel user = userService.findUserByUserPasswordResetToken(token);
//            return new ResponseEntity<>(user, HttpStatus.OK);
//        }catch (NoSuchElementException e){
//            return ResponseEntity.badRequest().build();
//        }
//
//    }

//        @GetMapping("/test")
//        List<AilmentFiles> test(){
//
//        return ailmentFilesTaskRepository.findAllByAilment_Idailment(1).stream().map(
//                AilmentFileDTO::new
//        ).collect(Collectors.toList());
//    }

}
