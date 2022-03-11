package com.physio.node.webservice.controller;

import com.physio.node.webservice.model.DTO.Ailment.AilmentReadModel;
import com.physio.node.webservice.model.DTO.Ailment.AilmentIndicationDTO;
import com.physio.node.webservice.model.DTO.Ailment.AilmentNoteDTO;
import com.physio.node.webservice.model.DTO.Ailment.AilmentWriteModel;
import com.physio.node.webservice.service.AilmentService;
import org.springframework.http.HttpStatus;
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
    ResponseEntity<List<AilmentReadModel>> getAllUserAilmentByIdUser(@PathVariable int id) {
        List<AilmentReadModel> ailmentReadModel = ailmentService.findAllUserAilmentByIdUser(id);
        return new ResponseEntity<>(ailmentReadModel, HttpStatus.OK);
    }

    /*
    Availability: admin, physiotherapist, coach
    **/
    @GetMapping("/{id}")
    ResponseEntity<AilmentReadModel> getAilmentByIdAilment(@PathVariable int id) {
        AilmentReadModel ailmentReadModel = ailmentService.findAilmentByIdAilment(id);
        return new ResponseEntity<>(ailmentReadModel, HttpStatus.OK);
    }

    /*
    Availability: admin, physiotherapist,
    **/
    @PostMapping("/create")
    public ResponseEntity<?> createAilment(@RequestBody AilmentWriteModel ailmentWriteModel) {
        ailmentService.createAilment(ailmentWriteModel);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /*
    Availability: admin, physiotherapist
    **/
    @PostMapping("/createAilmentNote")
    public ResponseEntity<?> createAilmentNote(@RequestBody AilmentNoteDTO ailmentNoteDTO) {
        ailmentService.createAilmentNote(ailmentNoteDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /*
    Availability: admin, physiotherapist
    **/
    @PostMapping("/createAilmentIndication")
    public ResponseEntity<?> createAilmentIndication(@RequestBody AilmentIndicationDTO ailmentIndicationDTO) {
        ailmentService.createAilmentIndication(ailmentIndicationDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/deleteAilment/{ailmentId}")
    ResponseEntity<?> deleteAilment(@PathVariable int ailmentId) {
        ailmentService.deleteAilment(ailmentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
