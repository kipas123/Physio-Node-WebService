package com.physio.node.webservice.service;

import com.physio.node.webservice.model.DTO.MyGroupReadModel;
import com.physio.node.webservice.model.DTO.MyGroupWriteModel;
import com.physio.node.webservice.model.JPA.Mygroup;
import com.physio.node.webservice.model.JPA.User;
import com.physio.node.webservice.model.MygroupTaskRepository;
import com.physio.node.webservice.model.UserTaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class MygroupService {
    private MygroupTaskRepository mygroupTaskRepository;
    private UserTaskRepository userTaskRepository;

    public MygroupService(MygroupTaskRepository mygroupTaskRepository) {
        this.mygroupTaskRepository = mygroupTaskRepository;
    }

    public List<MyGroupReadModel> findAllGroupsByUserId(int id){
        User a = new User();
        a.setIduser(id);
        return mygroupTaskRepository.findAllByMygroupOwner(a)
                .stream().map(MyGroupReadModel::new).collect(Collectors.toList());
    }
    public List<MyGroupReadModel> findAllGroups(){
        return mygroupTaskRepository.findAll()
                .stream().map(MyGroupReadModel::new).collect(Collectors.toList());
    }
    public void createGroup(MyGroupWriteModel mygroup){
        Mygroup createdMygroup = new Mygroup(mygroup);
        mygroupTaskRepository.save(createdMygroup);
    }

    public MyGroupReadModel findGroupByGroupId(int id) {
        Mygroup mygroup = mygroupTaskRepository.findByIdmygroup(id);
        MyGroupReadModel myGroupReadModel = new MyGroupReadModel(mygroup);
        //User user = userTaskRepository.findByIduser(mygroup.getFounder().getIduser());
        //String groupFounderName = user.getUserName();
        //MyGroupDTO myGroupDTO = new MyGroupDTO(mygroup.getIdmygroup(), mygroup.getMygroupName(), mygroup.getMygroupDescription(), groupFounderName);
        return myGroupReadModel;
    }
    public void changeGroupInfo(MyGroupWriteModel myGroupWriteModel){
        Mygroup mygroup = new Mygroup(myGroupWriteModel);
        System.out.println("Tutaj jestem - " + myGroupWriteModel.getIdmygroup());
        Mygroup findMyGroup = mygroupTaskRepository.findByIdmygroup(myGroupWriteModel.getIdmygroup());
        mygroup.setMygroupOwner(findMyGroup.getMygroupOwner());
        mygroupTaskRepository.save(mygroup);
    }
}
