package com.physio.node.webservice.controller;

import com.physio.node.webservice.model.DTO.AilmentDTO;
import com.physio.node.webservice.service.AilmentService;
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

    @GetMapping("/{id}")
    List<AilmentDTO> findUserAilment(@PathVariable int id){
        return ailmentService.getUserAilment(id);
    }
}
