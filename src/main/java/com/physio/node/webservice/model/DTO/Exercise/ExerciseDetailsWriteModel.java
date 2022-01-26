package com.physio.node.webservice.model.DTO.Exercise;

import com.physio.node.webservice.model.JPA.ExerciseBook;
import com.physio.node.webservice.model.JPA.User;
import lombok.Data;

@Data
public class ExerciseDetailsWriteModel {
    private String exerciseDetailsHeader;
    private String exerciseDetailsDescription;
    private int exerciseBook;

    ExerciseDetailsWriteModel(String exerciseDetailsHeader, String exerciseDetailsDescription, int exerciseBook) {
        this.exerciseDetailsHeader = exerciseDetailsHeader;
        this.exerciseDetailsDescription = exerciseDetailsDescription;
        this.exerciseBook = exerciseBook;
    }
}
