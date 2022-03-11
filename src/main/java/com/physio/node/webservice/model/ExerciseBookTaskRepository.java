package com.physio.node.webservice.model;

import com.physio.node.webservice.model.JPA.Ailment;
import com.physio.node.webservice.model.JPA.ExerciseBook;

import java.util.List;
import java.util.Optional;

public interface ExerciseBookTaskRepository {
    List<ExerciseBook> findAllByUserIduser(int idUser);
    ExerciseBook save(ExerciseBook exerciseBook);
    Optional<ExerciseBook> findFirstByIdexerciseBook(int id);
    void delete (ExerciseBook exerciseBook);
}
