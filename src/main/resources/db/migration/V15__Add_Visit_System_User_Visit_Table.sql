CREATE TABLE visit_system_user_visit
(
    idvisit_system_user_visit          INT  NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_visit_time                    TIME NOT NULL,
    visit_system_user_work_day_idvisit_system_user_work_day     INT NOT NULL,
    visit_system_user_service_type_idvisit_system_user_service_type INT NOT NULL,
    user_iduser                         INT NOT NULL,
    visit_system_user_visit_status_idvisit_system_user_visit_status INT NOT NULL
);
ALTER TABLE visit_system_user_visit
    ADD FOREIGN KEY (user_iduser) REFERENCES user(iduser);
ALTER TABLE visit_system_user_visit
    ADD FOREIGN KEY (visit_system_user_visit_status_idvisit_system_user_visit_status) REFERENCES visit_system_user_visit_status(idvisit_system_user_visit_status );
ALTER TABLE visit_system_user_visit
    ADD FOREIGN KEY (visit_system_user_work_day_idvisit_system_user_work_day) REFERENCES visit_system_user_work_day(idvisit_system_user_work_day );
ALTER TABLE visit_system_user_visit
    ADD FOREIGN KEY (visit_system_user_service_type_idvisit_system_user_service_type) REFERENCES visit_system_user_service_type(idvisit_system_user_service_type);