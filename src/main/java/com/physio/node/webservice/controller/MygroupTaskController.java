package com.physio.node.webservice.controller;

import com.physio.node.webservice.model.DTO.MyGroupReadModel;
import com.physio.node.webservice.model.DTO.MyGroupWriteModel;
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
    @GetMapping("/all/{id}")
    List<MyGroupReadModel> getAllGroupsByUserId(@PathVariable int id){
        return mygroupService.findAllGroupsByUserId(id);
    }

    @GetMapping("/{id}")
    MyGroupReadModel getGroupByGroupId(@PathVariable int id){
        return mygroupService.findGroupByGroupId(id);
    }

    @GetMapping("/all")
    List<MyGroupReadModel> getAllGroups(){
        return mygroupService.findAllGroups();
    }


    @PostMapping("/create")
    ResponseEntity<?> createGroup(@RequestBody MyGroupWriteModel mygroup){
        mygroupService.createGroup(mygroup);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/changeGroupInfo")
    ResponseEntity<?> changeGroupInfo(@RequestBody MyGroupWriteModel myGroupWriteModel){
        mygroupService.changeGroupInfo(myGroupWriteModel);
        return ResponseEntity.noContent().build();
    }
}
