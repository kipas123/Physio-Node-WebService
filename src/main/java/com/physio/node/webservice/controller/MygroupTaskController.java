package com.physio.node.webservice.controller;

import com.physio.node.webservice.model.DTO.Mygroup.MyGroupReadModel;
import com.physio.node.webservice.model.DTO.Mygroup.MyGroupUserListDTO;
import com.physio.node.webservice.model.DTO.Mygroup.MyGroupWriteModel;
import com.physio.node.webservice.model.MygroupTaskRepository;
import com.physio.node.webservice.service.MygroupService;
import com.physio.node.webservice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/group")
@RestController
public class MygroupTaskController {
    private UserService userService;
    private MygroupService mygroupService;
    private MygroupTaskRepository mygroupTaskRepository;

    public MygroupTaskController(UserService userService, MygroupService mygroupService, MygroupTaskRepository mygroupTaskRepository) {
        this.userService = userService;
        this.mygroupService = mygroupService;
        this.mygroupTaskRepository = mygroupTaskRepository;
    }

        /*
    Availability: admin
    **/
    @GetMapping("/all/{userOwnerId}")
    ResponseEntity<List<MyGroupReadModel>> getAllGroupsByUserOwner(@PathVariable int userOwnerId){
        List<MyGroupReadModel> myGroupReadModel = mygroupService.findAllGroupsByUserOwner(userOwnerId);
        return new ResponseEntity<>(myGroupReadModel, HttpStatus.OK);
    }
    /*
    Availability: admin, physiotherapist, coach
    **/
    @GetMapping("/userList/{groupId}")
    ResponseEntity<List<MyGroupUserListDTO>> getAllUsersByMygroupId(@PathVariable int groupId){
        List<MyGroupUserListDTO> myGroupReadModel = mygroupService.findAllUsersByMygroupId(groupId);
        return new ResponseEntity<>(myGroupReadModel, HttpStatus.OK);
    }

    @GetMapping("all/user/{userId}")
    ResponseEntity<List<MyGroupReadModel>> getAllGroupByUserId(@PathVariable int userId){
        List<MyGroupReadModel> myGroupReadModel = mygroupService.findAllGroupsByUserId(userId);
        return new ResponseEntity<>(myGroupReadModel, HttpStatus.OK);
    }


    /*
    Availability: admin, physiotherapist, coach
    **/
    @GetMapping("/{id}")
    ResponseEntity<MyGroupReadModel> getGroupByGroupId(@PathVariable int id){
        MyGroupReadModel myGroupReadModel = mygroupService.findGroupByGroupId(id);
        return new ResponseEntity<>(myGroupReadModel, HttpStatus.OK);
    }

    /*
    Availability: coach
    **/
    @GetMapping("/all")
    ResponseEntity<List<MyGroupReadModel>> getAllGroups(){
        List<MyGroupReadModel> myGroupReadModel = mygroupService.findAllGroups();
        return new ResponseEntity<>(myGroupReadModel, HttpStatus.OK);
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
        mygroupService.addUserToGroup(userId, groupId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/removeUserFromGroup/{userId}/{groupId}")
    ResponseEntity<?> removeUserFromGroup(@PathVariable int userId, @PathVariable int groupId){
        mygroupService.removeUserFromGroup(userId, groupId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/deleteGroup/{groupId}")
    ResponseEntity<?> deleteGroup( @PathVariable int groupId){
        mygroupService.deleteGroup(groupId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
