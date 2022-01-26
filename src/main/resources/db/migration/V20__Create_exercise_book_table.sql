CREATE TABLE exercise_book
(
    idexercise_book INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    exercise_name VARCHAR(255) NOT NULL,
    exercise_description LONGTEXT NOT NULL,
    user_iduser INT(11) NOT NULL,
    attendingcoach_iduser INT(11) NOT NULL,
    FOREIGN KEY (user_iduser) REFERENCES user(iduser),
    FOREIGN KEY (attendingcoach_iduser) REFERENCES user(iduser)
);