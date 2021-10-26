package com.physio.node.webservice.service;

import com.physio.node.webservice.model.DTO.User.UserReadModel;
import com.physio.node.webservice.model.DTO.User.UserWriteModel;
import com.physio.node.webservice.model.JPA.User;
import com.physio.node.webservice.model.JPA.UserRole;
import com.physio.node.webservice.model.UserRoleTaskRepository;
import com.physio.node.webservice.model.UserTaskRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public Boolean findUserByUserPasswordResetToken(String token){
        UserReadModel userReadModel = new UserReadModel(userTaskRepository.findByResetPasswordToken(token).get());
        if(userReadModel==null) return false;
        return true;
    }
    public List<UserReadModel> findUserByEmailOrNameOrSurname(String value){
        return userTaskRepository.findAllByUserNameOrUserSurname(value)
                .stream()
                .map(UserReadModel::new).collect(Collectors.toList());
    }


    public List<UserReadModel> findUnverifiedUser(int page, int size){
        List <UserReadModel> userList = userTaskRepository.findAllByUserRole_RoleName("unverified", PageRequest.of(page,size))
                .stream()
                .map(UserReadModel::new)
                .collect(Collectors.toList());
        if(userList.size()==0) userList=null;
        return userList;
    }

    public List<UserReadModel> findUsersWithModRole(){
        List <UserReadModel> userList = userTaskRepository.findAllUserWithModRole()
                .stream()
                .map(UserReadModel::new)
                .collect(Collectors.toList());
        if(userList==null) return null;
        return userList;
    }



    public List<UserReadModel> findUnverifiedUserByUserNameOrUserSurname(String value){
        List <UserReadModel> userList = userTaskRepository.findAllUnverifiedUserByUserNameOrUserSurname(value)
                .stream()
                .map(UserReadModel::new)
                .collect(Collectors.toList());
        if(userList==null) return null;
        return userList;
    }


    public void saveUser(UserWriteModel user){
        User createdUser = new User(user);

    }



    public void changeUserRole(int userId, int roleId) {
        User user = userTaskRepository.findByIduser(userId);
        UserRole userRole = userRoleTaskRepository.findByIduserRole(roleId);
        user.setUserRole(userRole);
        userTaskRepository.save(user);
    }




    public Long countUnverifedUser(){
        return userTaskRepository.countUnverifiedUser();
    }
}
