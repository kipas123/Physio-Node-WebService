package com.physio.node.webservice.model.DTO.Exercise;

import com.physio.node.webservice.model.DTO.User.UserReadModel;
import com.physio.node.webservice.model.JPA.ExerciseBook;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class ExerciseReadModel {
    private int idexerciseBook;
    private String exerciseName;
    private String exerciseDescription;
    private UserReadModel attendingCoach;
    private List<ExerciseDetailsReadModel> exerciseDetailsReadModel;

    public ExerciseReadModel(ExerciseBook exerciseBook){
        this.idexerciseBook = exerciseBook.getIdexerciseBook();
        this.exerciseName = exerciseBook.getExerciseName();
        this.exerciseDescription = exerciseBook.getExerciseDescription();
        this.attendingCoach = new UserReadModel(exerciseBook.getAttendingcoach());
        this.exerciseDetailsReadModel = exerciseBook.getExerciseDetails().stream().map(ExerciseDetailsReadModel::new).collect(Collectors.toList());
    }
}
