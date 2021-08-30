package com.physio.node.webservice.service;

import com.physio.node.webservice.model.DTO.UserDTO;
import com.physio.node.webservice.model.UserTaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private UserTaskRepository userTaskRepository;

    public UserService(UserTaskRepository userTaskRepository) {
        this.userTaskRepository = userTaskRepository;
    }

    public UserDTO getUserbyID(String username){
        return userTaskRepository.findByUserName(username)
                .stream().
                        map(UserDTO::new).
                        collect(Collectors.toList()).get(0);
    }




}
