package com.physio.node.webservice.service;

import com.physio.node.webservice.model.DTO.MyGroupDTO;
import com.physio.node.webservice.model.JPA.Mygroup;
import com.physio.node.webservice.model.JPA.User;
import com.physio.node.webservice.model.MygroupTaskRepository;
import com.physio.node.webservice.model.UserTaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class MygroupService {
    private MygroupTaskRepository mygroupTaskRepository;
    private UserTaskRepository userTaskRepository;

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

    public MyGroupDTO findGroupByGroupId(int id) {
        Mygroup mygroup = mygroupTaskRepository.findByIdmygroup(id);
        MyGroupDTO myGroupDTO = new MyGroupDTO(mygroup);
        //User user = userTaskRepository.findByIduser(mygroup.getFounder().getIduser());
        //String groupFounderName = user.getUserName();
        //MyGroupDTO myGroupDTO = new MyGroupDTO(mygroup.getIdmygroup(), mygroup.getMygroupName(), mygroup.getMygroupDescription(), groupFounderName);
        return myGroupDTO;
    }
    public void changeGroupInfo(MyGroupDTO myGroupDTO){
        Mygroup mygroup = new Mygroup(myGroupDTO);
        Mygroup findMyGroup = mygroupTaskRepository.findByIdmygroup(mygroup.getIdmygroup());
        mygroup.setMygroupFounder(findMyGroup.getMygroupFounder());
        mygroupTaskRepository.save(mygroup);
    }
}
