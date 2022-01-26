package com.physio.node.webservice.service;

import com.physio.node.webservice.model.DTO.Exercise.ExerciseDetailsWriteModel;
import com.physio.node.webservice.model.DTO.Exercise.ExerciseReadModel;
import com.physio.node.webservice.model.DTO.Exercise.ExerciseWriteModel;
import com.physio.node.webservice.model.ExerciseBookTaskRepository;
import com.physio.node.webservice.model.ExerciseDetailsTaskRepository;
import com.physio.node.webservice.model.JPA.ExerciseBook;
import com.physio.node.webservice.model.JPA.ExerciseDetails;
import com.physio.node.webservice.model.JPA.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExerciseService {
    private ExerciseBookTaskRepository exerciseBookTaskRepository;
    private ExerciseDetailsTaskRepository exerciseDetailsTaskRepository;

    public ExerciseService(ExerciseBookTaskRepository exerciseBookTaskRepository, ExerciseDetailsTaskRepository exerciseDetailsTaskRepository) {
        this.exerciseBookTaskRepository = exerciseBookTaskRepository;
        this.exerciseDetailsTaskRepository = exerciseDetailsTaskRepository;
    }

   public  List<ExerciseReadModel> getAllUserExerciseByIdUser(@PathVariable int id) {
        List<ExerciseReadModel> exerciseReadModels = exerciseBookTaskRepository.findAllByUserIduser(id).stream().map(ExerciseReadModel::new).collect(Collectors.toList());;
        return exerciseReadModels;
    }

    public ResponseEntity<?> createExercise(ExerciseWriteModel exerciseWriteModel) {

        User user = new User(exerciseWriteModel.getUser());
        User attendingCoach = new User(exerciseWriteModel.getAttendingCoach());
        ExerciseBook exerciseBook = new ExerciseBook(exerciseWriteModel.getExerciseName(), exerciseWriteModel.getExerciseDescription(), user, attendingCoach);
        exerciseBookTaskRepository.save(exerciseBook);
        return ResponseEntity.ok().build();
    }

    public ExerciseReadModel findExerciseByIdExercise(int id) {
        Optional<ExerciseBook> exerciseBook = exerciseBookTaskRepository.findFirstByIdexerciseBook(id);
        if(exerciseBook.isEmpty()) return  null;
        return new ExerciseReadModel(exerciseBook.get());
    }

    public ResponseEntity<?> createExerciseDetail(ExerciseDetailsWriteModel exerciseDetailsWriteModel) {
        ExerciseDetails exerciseDetails = new ExerciseDetails(exerciseDetailsWriteModel.getExerciseDetailsHeader(), exerciseDetailsWriteModel.getExerciseDetailsDescription(), exerciseDetailsWriteModel.getExerciseBook());
        exerciseDetailsTaskRepository.save(exerciseDetails);
        return ResponseEntity.ok().build();
    }
}
