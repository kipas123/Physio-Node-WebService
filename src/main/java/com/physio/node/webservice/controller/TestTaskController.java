package com.physio.node.webservice.controller;

import com.physio.node.webservice.adapter.SqlMygroupTaskRepository;
import com.physio.node.webservice.model.DTO.Mygroup.MyGroupUserListDTO;
import com.physio.node.webservice.model.DTO.User.UserReadModel;
import com.physio.node.webservice.model.MygroupTaskRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TestTaskController {

    private SqlMygroupTaskRepository sqlMygroupTaskRepository;
    private MygroupTaskRepository mygroupTaskRepository;

    public TestTaskController(SqlMygroupTaskRepository sqlMygroupTaskRepository, MygroupTaskRepository mygroupTaskRepository){
        this.sqlMygroupTaskRepository = sqlMygroupTaskRepository;
        this.mygroupTaskRepository = mygroupTaskRepository;
    }


    @GetMapping("/test")
    List<MyGroupUserListDTO> getAllUsers(){
        List<MyGroupUserListDTO> userList = mygroupTaskRepository.findByIdmygroup(1).
                getUserMygroups().
                stream().map(user -> new MyGroupUserListDTO(user.getUser())).
                collect(Collectors.toList());
        return userList;
    }
}
