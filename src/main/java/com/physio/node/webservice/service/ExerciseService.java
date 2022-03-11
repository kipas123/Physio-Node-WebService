package com.physio.node.webservice.service;

import com.physio.node.webservice.Exception.ResourceBadRequestException;
import com.physio.node.webservice.Exception.ResourceNotFoundException;
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
import org.springframework.web.bind.annotation.GetMapping;
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
        List<ExerciseBook> exerciseBook = exerciseBookTaskRepository.findAllByUserIduser(id);
       if (exerciseBook.isEmpty()) {
           throw new ResourceNotFoundException("Not found for IdUser" + id);
       }
        List<ExerciseReadModel> exerciseReadModels = exerciseBook.stream().map(ExerciseReadModel::new).collect(Collectors.toList());;
        return exerciseReadModels;
    }

    public void createExercise(ExerciseWriteModel exerciseWriteModel) {

        User user = new User(exerciseWriteModel.getUser());
        User attendingCoach = new User(exerciseWriteModel.getAttendingCoach());
        ExerciseBook exerciseBook = new ExerciseBook(exerciseWriteModel.getExerciseName(), exerciseWriteModel.getExerciseDescription(), user, attendingCoach);
        try{
            exerciseBookTaskRepository.save(exerciseBook);
        }catch (Exception e){
            throw new ResourceBadRequestException("Error: Bad argument");
        }
    }

    public ExerciseReadModel findExerciseByIdExercise(int id) {
        ExerciseBook exerciseBook = exerciseBookTaskRepository.findFirstByIdexerciseBook(id).orElseThrow(()-> new ResourceNotFoundException("Not found exercise by id: " + id));
        return new ExerciseReadModel(exerciseBook);
    }

    public void createExerciseDetail(ExerciseDetailsWriteModel exerciseDetailsWriteModel) {
        ExerciseDetails exerciseDetails = new ExerciseDetails(exerciseDetailsWriteModel.getExerciseDetailsHeader(), exerciseDetailsWriteModel.getExerciseDetailsDescription(), exerciseDetailsWriteModel.getExerciseBook());
        try{
            exerciseDetailsTaskRepository.save(exerciseDetails);
        }catch (Exception e){
            throw new ResourceBadRequestException("Error: Bad argument");
        }
    }

    public void deleteExerciseBook(int exerciseBookId) {
        try{
            exerciseBookTaskRepository.delete(new ExerciseBook(exerciseBookId));
        }catch (Exception e){
            throw new ResourceBadRequestException("Error: Bad argument" + e);
        }
    }
}
