package com.physio.node.webservice.controller;

import com.physio.node.webservice.model.DTO.MyGroupDTO;
import com.physio.node.webservice.service.MygroupService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/group")
@RestController
public class MygroupTaskController {
    private MygroupService mygroupService;

    public MygroupTaskController(MygroupService mygroupService) {
        this.mygroupService = mygroupService;
    }
    @GetMapping("/{id}")
    List<MyGroupDTO> getAllGroupsByUserId(@PathVariable int id){
        return mygroupService.findAllGroupsByUserId(id);
    }
    @GetMapping("/all")
    List<MyGroupDTO> getAllGroups(){
        return mygroupService.findAllGroups();
    }
}
