package com.physio.node.webservice.controller;

import com.physio.node.webservice.model.DTO.Ailment.AilmentNoteDTO;
import com.physio.node.webservice.model.DTO.Ailment.AilmentReadModel;
import com.physio.node.webservice.model.DTO.Ailment.AilmentWriteModel;
import com.physio.node.webservice.model.DTO.Exercise.ExerciseDetailsWriteModel;
import com.physio.node.webservice.model.DTO.Exercise.ExerciseReadModel;
import com.physio.node.webservice.model.DTO.Exercise.ExerciseWriteModel;
import com.physio.node.webservice.model.JPA.ExerciseBook;
import com.physio.node.webservice.service.ExerciseService;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<ExerciseReadModel> getExerciseByIdExercise(@PathVariable int id) {
        ExerciseReadModel exerciseReadModel = exerciseService.findExerciseByIdExercise(id);
        return new ResponseEntity<>(exerciseReadModel, HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<ExerciseReadModel>> getAllUserExerciseByIdUser(@PathVariable int id) {
        List<ExerciseReadModel> exerciseReadModel = exerciseService.getAllUserExerciseByIdUser(id);
        return new ResponseEntity<>(exerciseReadModel, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createAilment(@RequestBody ExerciseWriteModel exerciseWriteModel) {
        exerciseService.createExercise(exerciseWriteModel);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/createExerciseDetail")
    public ResponseEntity<?> createExerciseDetail(@RequestBody ExerciseDetailsWriteModel exerciseDetailsWriteModel) {
        exerciseService.createExerciseDetail(exerciseDetailsWriteModel);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/deleteExerciseBook/{exerciseBookId}")
    public ResponseEntity<?> deleteExerciseBook(@PathVariable int exerciseBookId){
        exerciseService.deleteExerciseBook(exerciseBookId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
