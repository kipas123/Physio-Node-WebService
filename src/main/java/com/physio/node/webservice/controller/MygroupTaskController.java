package com.physio.node.webservice.controller;

import com.physio.node.webservice.model.DTO.MyGroupDTO;
import com.physio.node.webservice.model.JPA.Mygroup;
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
    @GetMapping("/{id}")
    List<MyGroupDTO> getAllGroupsByUserId(@PathVariable int id){
        return mygroupService.findAllGroupsByUserId(id);
    }


    @GetMapping("/all")
    List<MyGroupDTO> getAllGroups(){
        return mygroupService.findAllGroups();
    }


    @PostMapping("/create")
    public ResponseEntity<?> createGroup(@RequestBody MyGroupDTO mygroup){
        mygroupService.createGroup(mygroup);
        return ResponseEntity.noContent().build();
    }
}
