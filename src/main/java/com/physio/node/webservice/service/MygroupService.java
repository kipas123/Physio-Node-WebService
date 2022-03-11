package com.physio.node.webservice.service;

import com.physio.node.webservice.Exception.ResourceBadRequestException;
import com.physio.node.webservice.Exception.ResourceNotFoundException;
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
        List<MyGroupReadModel> myGroupReadModel = mygroupTaskRepository.findAllByMygroupOwner(a)
                .stream().map(MyGroupReadModel::new).collect(Collectors.toList());
        if (myGroupReadModel.isEmpty()) {
            throw new ResourceNotFoundException("Not found!");
        }
        return myGroupReadModel;
    }

    public List<MyGroupReadModel> findAllGroupsByUserId(int id){
        User user = userTaskRepository.findByIduser(id).orElseThrow(()-> new ResourceNotFoundException("Not found!"));
        List<MyGroupReadModel> usersGroups = user.getUserMygroups().stream().map(
                Mygroup_Users-> new MyGroupReadModel(Mygroup_Users.getMygroup())
        ).collect(Collectors.toList());
        if (usersGroups.isEmpty()) {
            throw new ResourceNotFoundException("Not found!");
        }
        return usersGroups;
    }


    public List<MyGroupReadModel> findAllGroups(){
        List<MyGroupReadModel> myGroupReadModel = mygroupTaskRepository.findAll()
                .stream().map(MyGroupReadModel::new).collect(Collectors.toList());
        if (myGroupReadModel.isEmpty()) {
            throw new ResourceNotFoundException("Not found!");
        }
        return myGroupReadModel;
    }

    public void createGroup(MyGroupWriteModel mygroup){
        Mygroup createdMygroup = new Mygroup(mygroup);
        try{
            mygroupTaskRepository.save(mygroupTaskRepository.save(createdMygroup));
        }catch (Exception e){
            throw new ResourceBadRequestException("Error: Bad argument");
        }
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
        try{
            mygroupTaskRepository.save(mygroup);
        }catch (Exception e){
            throw new ResourceBadRequestException("Error: Bad argument");
        }

    }

    public List<MyGroupUserListDTO> findAllUsersByMygroupId(int id){
        List<MyGroupUserListDTO> userList = mygroupTaskRepository.findByIdmygroup(id)
                .getUserMygroups().
                        stream().
                        map(userMyGroup -> new MyGroupUserListDTO(userMyGroup.getUser())).collect(Collectors.toList());
        if (userList.isEmpty()) {
            throw new ResourceNotFoundException("Not found for IdUser" + id);
        }
        return userList;
    }

    public void addUserToGroup(int userId, int mygroupId){
        Mygroup mygroup = mygroupTaskRepository.findByIdmygroup(mygroupId);
        User user = userTaskRepository.findByIduser(userId).orElseThrow(()-> new ResourceNotFoundException("Not found!"));
        if(mygroup == null || user == null) throw new ResourceNotFoundException("Not found!");
        if(mygroup_usersTaskRepository.findAllByUser_IduserAndMygroup_Idmygroup(userId, mygroupId).size()!=0) throw new ResourceNotFoundException("Not found!");
        Mygroup_UsersPK mygroup_usersPK = new Mygroup_UsersPK(userId, mygroupId);
        Mygroup_Users mygroup_users = new Mygroup_Users(mygroup_usersPK, mygroup, user);
        try{
            mygroup_usersTaskRepository.save(mygroup_users);
        }catch (Exception e){
            throw new ResourceBadRequestException("Error: Bad argument");
        }
    }

    public void removeUserFromGroup(int userId, int mygroupId){
        Optional<Mygroup_Users> mygroup_users  = mygroup_usersTaskRepository
                .findByUserIduserAndMygroupIdmygroup(userId,mygroupId);
        if(mygroup_users.isEmpty())  throw new ResourceNotFoundException("Not found!");

        try{
            mygroup_usersTaskRepository.delete(mygroup_users.get());
        }catch (Exception e){
            throw new ResourceBadRequestException("Error: Bad argument");
        }
    }

    public void deleteGroup(int groupId) {
        try{
            mygroupTaskRepository.delete(new Mygroup(groupId));
        }catch (Exception e){
            throw new ResourceBadRequestException("Error: Bad argument" + e);
        }
    }
}
