CREATE TABLE visit_system_user_service_type
(
    idvisit_system_user_service_type           INT  NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_service_type_name                     VARCHAR(40) NOT NULL,
    user_service_type_duration                 TIME NOT NULL,
    user_iduser                                INT NOT NULL
);

ALTER TABLE visit_system_user_service_type
    ADD FOREIGN KEY (user_iduser) REFERENCES user(iduser);

