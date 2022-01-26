package com.physio.node.webservice.model.JPA;

import lombok.Data;

import javax.persistence.*;

@Table(name = "exercise_details")
@Entity
@Data
public class ExerciseDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idexerciseDetails;

    @Column(name="exercise_details_header")
    private String exerciseDetailsHeader;

    @Column(name="exercise_details_description" , columnDefinition = "LONGTEXT")
    private String exerciseDetailsDescription;

    //bi-directional many-to-one association to User
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="exercise_book_idailment")
    private ExerciseBook exerciseBook;
    public ExerciseDetails(){}

    public ExerciseDetails(String exerciseDetailsHeader, String exerciseDetailsDescription, int idExerciseBook) {
        this.exerciseDetailsHeader = exerciseDetailsHeader;
        this.exerciseDetailsDescription = exerciseDetailsDescription;
        this.exerciseBook = new ExerciseBook(idExerciseBook);
    }
}
