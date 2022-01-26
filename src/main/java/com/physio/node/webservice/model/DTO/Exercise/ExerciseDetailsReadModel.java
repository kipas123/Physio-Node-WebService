package com.physio.node.webservice.model.DTO.Exercise;

import com.physio.node.webservice.model.JPA.ExerciseDetails;
import lombok.Data;

@Data
public class ExerciseDetailsReadModel {
    private String exerciseDetailsHeader;
    private String exerciseDetailsDescription;

    public ExerciseDetailsReadModel(String exerciseDetailsHeader, String exerciseDetailsDescription) {
        this.exerciseDetailsHeader = exerciseDetailsHeader;
        this.exerciseDetailsDescription = exerciseDetailsDescription;
    }
    public ExerciseDetailsReadModel(ExerciseDetails exerciseDetails){
        this.exerciseDetailsHeader = exerciseDetails.getExerciseDetailsHeader();
        this.exerciseDetailsDescription = exerciseDetails.getExerciseDetailsDescription();
    }
}
