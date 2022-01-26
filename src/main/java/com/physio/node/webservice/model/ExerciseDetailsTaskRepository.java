package com.physio.node.webservice.model;

import com.physio.node.webservice.model.JPA.ExerciseDetails;

public interface ExerciseDetailsTaskRepository {
    ExerciseDetails save(ExerciseDetails exerciseDetails);
}
