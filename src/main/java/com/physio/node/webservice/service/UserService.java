package com.physio.node.webservice.service;

import com.physio.node.webservice.model.DTO.UserReadModel;
import com.physio.node.webservice.model.DTO.UserWriteModel;
import com.physio.node.webservice.model.JPA.User;
import com.physio.node.webservice.model.JPA.UserRole;
import com.physio.node.webservice.model.UserRoleTaskRepository;
import com.physio.node.webservice.model.UserTaskRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private PasswordEncoder passwordEncoder;
    private UserTaskRepository userTaskRepository;
    private UserRoleTaskRepository userRoleTaskRepository;

    public UserService(PasswordEncoder passwordEncoder, UserTaskRepository userTaskRepository, UserRoleTaskRepository userRoleTaskRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userTaskRepository = userTaskRepository;
        this.userRoleTaskRepository = userRoleTaskRepository;
    }

    public UserReadModel findUserByIdUser(int id){
        UserReadModel userReadModel = new UserReadModel(userTaskRepository.findByIduser(id));
        return userReadModel;
    }

    public UserReadModel findUserByUserEmail(String userEmail){
       UserReadModel userReadModel = new UserReadModel(userTaskRepository.findByUserEmail(userEmail).get());
        return userReadModel;
    }
    public void saveUser(UserWriteModel user){
        User createdUser = new User(user);

    }

    public ResponseEntity<?> registerUser(UserWriteModel userWriteModel){
        Optional<User> user = userTaskRepository.findByUserEmail(userWriteModel.getUserEmail());
        if(!user.isEmpty()){
            System.out.println(userWriteModel.getUserEmail());
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        UserRole userRole = userRoleTaskRepository.findByRoleName("user");
        userWriteModel.setUserPassword(passwordEncoder.encode(userWriteModel.getUserPassword()));
        User createdUser = new User(userWriteModel);
        createdUser.setUserRole(userRole);

        return new ResponseEntity<>(userTaskRepository.save(createdUser), HttpStatus.CREATED);
    }




}
