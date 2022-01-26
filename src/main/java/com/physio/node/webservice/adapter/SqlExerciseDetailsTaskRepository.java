package com.physio.node.webservice.adapter;

import com.physio.node.webservice.model.ExerciseDetailsTaskRepository;
import com.physio.node.webservice.model.JPA.ExerciseDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SqlExerciseDetailsTaskRepository extends ExerciseDetailsTaskRepository,JpaRepository<ExerciseDetails, Integer> {
}
