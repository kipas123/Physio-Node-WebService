package com.physio.node.webservice.controller;

import com.physio.node.webservice.model.DTO.AilmentDTO;
import com.physio.node.webservice.model.DTO.AilmentIndicationDTO;
import com.physio.node.webservice.model.DTO.AilmentNoteDTO;
import com.physio.node.webservice.model.DTO.MyGroupDTO;
import com.physio.node.webservice.service.AilmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/ailment")
public class AilmentTaskController {
    private AilmentService ailmentService;

    public AilmentTaskController(AilmentService ailmentService) {
        this.ailmentService = ailmentService;
    }

    @GetMapping("/user/{id}")
    List<AilmentDTO> getUserAilmentByIdUser(@PathVariable int id){
        return ailmentService.findUserAilmentByIdUser(id);
    }
    @GetMapping("/{id}")
    AilmentDTO getAilmentByIdAilment(@PathVariable int id){
        return ailmentService.findAilmentByIdAilment(id);
    }


    @PostMapping("/create")
    public ResponseEntity<?> createGroup(@RequestBody AilmentDTO ailmentDTO){
        ailmentService.createAilment(ailmentDTO);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/createAilmentNode")
    public ResponseEntity<?> createAilmentNote(@RequestBody AilmentNoteDTO ailmentNoteDTO){
        ailmentService.createAilmentNote(ailmentNoteDTO);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/createAilmentIndication")
    public ResponseEntity<?> createAilmentIndication(@RequestBody AilmentIndicationDTO ailmentIndicationDTO){
        ailmentService.createAilmentIndication(ailmentIndicationDTO);
        return ResponseEntity.noContent().build();
    }
}
