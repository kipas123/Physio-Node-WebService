CREATE TABLE visit_system_user_work_day (
                     idvisit_system_user_work_day INT NOT NULL  AUTO_INCREMENT PRIMARY KEY ,
                     user_work_day DATE NOT NULL,
                     user_iduser INT NOT NULL
);

ALTER TABLE visit_system_user_work_day
    ADD FOREIGN KEY (user_iduser) REFERENCES user(iduser);

