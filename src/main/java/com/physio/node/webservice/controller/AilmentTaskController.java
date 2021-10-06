package com.physio.node.webservice.controller;

import com.physio.node.webservice.model.DTO.Ailment.AilmentReadModel;
import com.physio.node.webservice.model.DTO.Ailment.AilmentIndicationDTO;
import com.physio.node.webservice.model.DTO.Ailment.AilmentNoteDTO;
import com.physio.node.webservice.model.DTO.Ailment.AilmentWriteModel;
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

    /*
    Availability: user,admin
    **/
    @GetMapping("/user/{id}")
    List<AilmentReadModel> getAllUserAilmentByIdUser(@PathVariable int id) {
        return ailmentService.findAllUserAilmentByIdUser(id);
    }

    /*
    Availability: admin, physiotherapist, coach
    **/
    @GetMapping("/{id}")
    AilmentReadModel getAilmentByIdAilment(@PathVariable int id) {
        return ailmentService.findAilmentByIdAilment(id);
    }

    /*
    Availability: admin, physiotherapist,
    **/
    @PostMapping("/create")
    public ResponseEntity<?> createGroup(@RequestBody AilmentWriteModel ailmentWriteModel) {
        ailmentService.createAilment(ailmentWriteModel);
        return ResponseEntity.noContent().build();
    }

    /*
    Availability: admin, physiotherapist
    **/
    @PostMapping("/createAilmentNote")
    public ResponseEntity<?> createAilmentNote(@RequestBody AilmentNoteDTO ailmentNoteDTO) {
        ailmentService.createAilmentNote(ailmentNoteDTO);
        return ResponseEntity.noContent().build();
    }

    /*
    Availability: admin, physiotherapist
    **/
    @PostMapping("/createAilmentIndication")
    public ResponseEntity<?> createAilmentIndication(@RequestBody AilmentIndicationDTO ailmentIndicationDTO) {
        ailmentService.createAilmentIndication(ailmentIndicationDTO);
        return ResponseEntity.noContent().build();
    }
}
