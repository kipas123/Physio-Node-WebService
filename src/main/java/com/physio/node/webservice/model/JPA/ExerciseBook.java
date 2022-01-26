package com.physio.node.webservice.model.JPA;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Table(name = "exercise_book")
@Entity
@Data
public class ExerciseBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idexerciseBook;

    @Column(name="exercise_name")
    private String exerciseName;

    @Column(name="exercise_description" , columnDefinition = "LONGTEXT")
    private String exerciseDescription;

    //bi-directional many-to-one association to User
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_iduser")
    private User user;

    //bi-directional many-to-one association to User
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="attendingcoach_iduser")
    private User attendingcoach;

    @OneToMany(mappedBy = "exerciseBook")
    List<ExerciseDetails> exerciseDetails;

    public ExerciseBook() {
    }
    public ExerciseBook(int idexerciseBook) {
        this.idexerciseBook = idexerciseBook;
    }

    public ExerciseBook(String exerciseName, String exerciseDescription, User user, User attendingcoach) {
        this.exerciseName = exerciseName;
        this.exerciseDescription = exerciseDescription;
        this.user = user;
        this.attendingcoach = attendingcoach;
    }
}
