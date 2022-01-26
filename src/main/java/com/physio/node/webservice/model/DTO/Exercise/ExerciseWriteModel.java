package com.physio.node.webservice.model.DTO.Exercise;

import com.physio.node.webservice.model.DTO.User.UserReadModel;
import lombok.Data;

@Data
public class ExerciseWriteModel {
    private String exerciseName;
    private String exerciseDescription;
    private int user;
    private int attendingCoach;

    ExerciseWriteModel(String exerciseName, String exerciseDescription, int userId, int attendingCoach) {
        this.exerciseName = exerciseName;
        this.exerciseDescription = exerciseDescription;
        this.user = userId;
        this.attendingCoach = attendingCoach;
    }
}
