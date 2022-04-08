package com.physio.node.webservice.service;

import com.physio.node.webservice.Exception.ResourceNotFoundException;
import com.physio.node.webservice.adapter.SqlUserTaskRepository;
import com.physio.node.webservice.model.DTO.User.UserReadModel;
import com.physio.node.webservice.model.DTO.User.UserWriteModel;
import com.physio.node.webservice.model.JPA.User;
import com.physio.node.webservice.model.JPA.UserRole;
import com.physio.node.webservice.model.UserRoleTaskRepository;
import com.physio.node.webservice.model.UserTaskRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private PasswordEncoder passwordEncoder;
    private UserTaskRepository userTaskRepository;
    private UserRoleTaskRepository userRoleTaskRepository;
    private SqlUserTaskRepository sqlUserTaskRepository;

    public UserService(PasswordEncoder passwordEncoder, UserTaskRepository userTaskRepository, UserRoleTaskRepository userRoleTaskRepository, SqlUserTaskRepository sqlUserTaskRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userTaskRepository = userTaskRepository;
        this.userRoleTaskRepository = userRoleTaskRepository;
        this.sqlUserTaskRepository = sqlUserTaskRepository;
    }

    public UserReadModel findUserByIdUser(int id){
        UserReadModel userReadModel = new UserReadModel(userTaskRepository.findByIduser(id).orElseThrow(() -> new ResourceNotFoundException("User not found by Id: " + id)));
        return userReadModel;
    }


    public UserReadModel findUserByUserEmail(String userEmail){
       UserReadModel userReadModel = new UserReadModel(userTaskRepository.findByUserEmail(userEmail).orElseThrow(() -> new ResourceNotFoundException("User not found by UserEmail: " + userEmail)));
        return userReadModel;
    }

    public Boolean findUserByUserPasswordResetToken(String token){
        UserReadModel userReadModel = new UserReadModel(userTaskRepository.findByResetPasswordToken(token).orElseThrow(() -> new ResourceNotFoundException("Not found for token: " + token)));
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
        if(userList.size()==0) throw new ResourceNotFoundException("User not found");
        return userList;
    }

    public List<UserReadModel> findVerifiedUserByUserNameOrUserSurname(String value){
        List <UserReadModel> userList = userTaskRepository.findAllVerifiedUserByUserNameOrUserSurname(value)
                .stream()
                .map(UserReadModel::new)
                .collect(Collectors.toList());
        if(userList.size()==0) throw new ResourceNotFoundException("User not found");
        return userList;
    }



    public void changeUserRole(int userId, int roleId) {
        User user = userTaskRepository.findByIduser(userId).orElseThrow(() -> new ResourceNotFoundException("User not found!"));
        UserRole userRole = userRoleTaskRepository.findByIduserRole(roleId).orElseThrow(() -> new ResourceNotFoundException("Role not found!"));;
        user.setUserRole(userRole);
        userTaskRepository.save(user);
    }




    public Long countUnverifedUser(){
        return userTaskRepository.countUnverifiedUser();
    }
}
