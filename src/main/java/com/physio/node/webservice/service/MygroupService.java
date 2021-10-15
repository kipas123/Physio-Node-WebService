package com.physio.node.webservice.service;

import com.physio.node.webservice.model.DTO.Mygroup.MyGroupReadModel;
import com.physio.node.webservice.model.DTO.Mygroup.MyGroupUserListDTO;
import com.physio.node.webservice.model.DTO.Mygroup.MyGroupWriteModel;
import com.physio.node.webservice.model.JPA.Mygroup;
import com.physio.node.webservice.model.JPA.Mygroup_Users;
import com.physio.node.webservice.model.JPA.Mygroup_UsersPK;
import com.physio.node.webservice.model.JPA.User;
import com.physio.node.webservice.model.MygroupTaskRepository;
import com.physio.node.webservice.model.Mygroup_UsersTaskRepository;
import com.physio.node.webservice.model.UserTaskRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class MygroupService {
    private MygroupTaskRepository mygroupTaskRepository;
    private UserTaskRepository userTaskRepository;
    private Mygroup_UsersTaskRepository mygroup_usersTaskRepository;

    public MygroupService(MygroupTaskRepository mygroupTaskRepository, UserTaskRepository userTaskRepository, Mygroup_UsersTaskRepository mygroup_usersTaskRepository) {
        this.mygroupTaskRepository = mygroupTaskRepository;
        this.userTaskRepository = userTaskRepository;
        this.mygroup_usersTaskRepository = mygroup_usersTaskRepository;
    }

    public List<MyGroupReadModel> findAllGroupsByUserOwner(int id){
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

    public List<MyGroupUserListDTO> findAllUsersByMygroupId(int id){
        List<MyGroupUserListDTO> userList = mygroupTaskRepository.findByIdmygroup(id)
                .getUserMygroups().
                        stream().
                        map(userMyGroup -> new MyGroupUserListDTO(userMyGroup.getUser())).collect(Collectors.toList());
        return userList;
    }

    public ResponseEntity<?> addUserToGroup(int userId, int mygroupId){
        Mygroup mygroup = mygroupTaskRepository.findByIdmygroup(mygroupId);
        User user = userTaskRepository.findByIduser(userId);
        if(mygroup == null || user == null) return ResponseEntity.badRequest().build();
        if(mygroup_usersTaskRepository.findAllByUser_IduserAndMygroup_Idmygroup(userId, mygroupId).size()!=0) return ResponseEntity.badRequest().build();
        Mygroup_UsersPK mygroup_usersPK = new Mygroup_UsersPK(userId, mygroupId);
        Mygroup_Users mygroup_users = new Mygroup_Users(mygroup_usersPK, mygroup, user);
        mygroup_usersTaskRepository.save(mygroup_users);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> removeUserFromGroup(int userId, int mygroupId){
        Optional<Mygroup_Users> mygroup_users  = mygroup_usersTaskRepository
                .findByUserIduserAndMygroupIdmygroup(userId,mygroupId);
        if(mygroup_users.isEmpty()) return ResponseEntity.badRequest().build();
        mygroup_usersTaskRepository.delete(mygroup_users.get());
        return ResponseEntity.ok().build();
    }
}
