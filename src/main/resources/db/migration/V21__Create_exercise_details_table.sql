CREATE TABLE exercise_details
(
    idexercise_details INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    exercise_details_header VARCHAR(255) NOT NULL,
    exercise_details_description LONGTEXT NOT NULL,
    exercise_book_idailment INT(11) NOT NULL,
    FOREIGN KEY (exercise_book_idailment) REFERENCES exercise_book(idexercise_book)
);