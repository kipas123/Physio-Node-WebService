package com.physio.node.webservice.controller;

import com.physio.node.webservice.model.DTO.Ailment.AilmentNoteDTO;
import com.physio.node.webservice.model.DTO.Ailment.AilmentReadModel;
import com.physio.node.webservice.model.DTO.Ailment.AilmentWriteModel;
import com.physio.node.webservice.model.DTO.Exercise.ExerciseDetailsWriteModel;
import com.physio.node.webservice.model.DTO.Exercise.ExerciseReadModel;
import com.physio.node.webservice.model.DTO.Exercise.ExerciseWriteModel;
import com.physio.node.webservice.service.ExerciseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/exercise")
public class ExerciseTaskController {
    private ExerciseService exerciseService;

    public ExerciseTaskController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @GetMapping("/{id}")
    public ExerciseReadModel getExerciseByIdExercise(@PathVariable int id) {
        return exerciseService.findExerciseByIdExercise(id);
    }

    @GetMapping("/user/{id}")
    public List<ExerciseReadModel> getAllUserExerciseByIdUser(@PathVariable int id) {
        return exerciseService.getAllUserExerciseByIdUser(id);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createAilment(@RequestBody ExerciseWriteModel exerciseWriteModel) {
        return exerciseService.createExercise(exerciseWriteModel);
    }

    @PostMapping("/createExerciseDetail")
    public ResponseEntity<?> createExerciseDetail(@RequestBody ExerciseDetailsWriteModel exerciseDetailsWriteModel) {
        return exerciseService.createExerciseDetail(exerciseDetailsWriteModel);
    }

}
