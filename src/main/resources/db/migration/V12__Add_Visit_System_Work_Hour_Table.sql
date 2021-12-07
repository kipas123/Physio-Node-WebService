CREATE TABLE visit_system_user_work_hour (
            idvisit_system_user_work_hour INT NOT NULL  AUTO_INCREMENT PRIMARY KEY ,
            user_work_hour_beginning_time TIME NOT NULL,
            user_work_hour_ending_time TIME NOT NULL,
            user_work_day_idvisit_system_user_work_day INT NOT NULL
);

ALTER TABLE visit_system_user_work_hour
    ADD FOREIGN KEY (user_work_day_idvisit_system_user_work_day) REFERENCES visit_system_user_work_day(idvisit_system_user_work_day);

