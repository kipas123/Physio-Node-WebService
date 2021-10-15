package com.physio.node.webservice.controller;

import com.physio.node.webservice.model.DTO.Mygroup.MyGroupReadModel;
import com.physio.node.webservice.model.DTO.Mygroup.MyGroupUserListDTO;
import com.physio.node.webservice.model.DTO.Mygroup.MyGroupWriteModel;
import com.physio.node.webservice.model.MygroupTaskRepository;
import com.physio.node.webservice.service.MygroupService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/group")
@RestController
public class MygroupTaskController {
    private MygroupService mygroupService;
    private MygroupTaskRepository mygroupTaskRepository;

    public MygroupTaskController(MygroupService mygroupService, MygroupTaskRepository mygroupTaskRepository) {
        this.mygroupService = mygroupService;
        this.mygroupTaskRepository = mygroupTaskRepository;
    }

        /*
    Availability: admin
    **/
    @GetMapping("/all/{id}")
    List<MyGroupReadModel> getAllGroupsByUserId(@PathVariable int id){
        return mygroupService.findAllGroupsByUserOwner(id);
    }
    /*
    Availability: admin, physiotherapist, coach
    **/
    @GetMapping("/userList/{id}")
    List<MyGroupUserListDTO> getAllUsersByMygroupId(@PathVariable int id){
        return mygroupService.findAllUsersByMygroupId(id);
    }
    /*
    Availability: admin, physiotherapist, coach
    **/
    @GetMapping("/{id}")
    MyGroupReadModel getGroupByGroupId(@PathVariable int id){
        return mygroupService.findGroupByGroupId(id);
    }

    /*
    Availability: coach
    **/
    @GetMapping("/all")
    List<MyGroupReadModel> getAllGroups(){
        return mygroupService.findAllGroups();
    }

    /*
    Availability: admin, physiotherapist
    **/
    @PostMapping("/create")
    ResponseEntity<?> createGroup(@RequestBody MyGroupWriteModel mygroup){
        mygroupService.createGroup(mygroup);
        return ResponseEntity.noContent().build();
    }
    /*
    Availability: admin, physiotherapist
    **/
    @PutMapping("/changeGroupInfo")
    ResponseEntity<?> changeGroupInfo(@RequestBody MyGroupWriteModel myGroupWriteModel){
        mygroupService.changeGroupInfo(myGroupWriteModel);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/addUserToGroup/{userId}/{groupId}")
    ResponseEntity<?> addUserToGroup(@PathVariable int userId, @PathVariable int groupId){
        return mygroupService.addUserToGroup(userId, groupId);
    }

    @GetMapping("/removeUserFromGroup/{userId}/{groupId}")
    ResponseEntity<?> removeUserFromGroup(@PathVariable int userId, @PathVariable int groupId){
        return mygroupService.removeUserFromGroup(userId, groupId);
    }
}
