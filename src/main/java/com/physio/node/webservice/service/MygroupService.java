package com.physio.node.webservice.service;

import com.physio.node.webservice.model.DTO.MyGroupDTO;
import com.physio.node.webservice.model.JPA.Mygroup;
import com.physio.node.webservice.model.JPA.User;
import com.physio.node.webservice.model.MygroupTaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class MygroupService {
    private MygroupTaskRepository mygroupTaskRepository;

    public MygroupService(MygroupTaskRepository mygroupTaskRepository) {
        this.mygroupTaskRepository = mygroupTaskRepository;
    }

    public List<MyGroupDTO> findAllGroupsByUserId(int id){
        return mygroupTaskRepository.findAllByUsers_Iduser(id)
                .stream().map(MyGroupDTO::new).collect(Collectors.toList());
    }
    public List<MyGroupDTO> findAllGroups(){
        return mygroupTaskRepository.findAll()
                .stream().map(MyGroupDTO::new).collect(Collectors.toList());
    }
    public void createGroup(MyGroupDTO mygroup){
        User founder = new User();
        founder.setIduser(1);
        Mygroup createdMygroup = new Mygroup(mygroup.getMygroupName(), mygroup.getMygroupDescription(), founder);
        mygroupTaskRepository.save(createdMygroup);
    }
}
