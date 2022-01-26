package com.physio.node.webservice.adapter;

import com.physio.node.webservice.model.ExerciseBookTaskRepository;
import com.physio.node.webservice.model.JPA.ExerciseBook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SqlExerciseBookTaskRepository extends ExerciseBookTaskRepository, JpaRepository<ExerciseBook, Integer> {
}
