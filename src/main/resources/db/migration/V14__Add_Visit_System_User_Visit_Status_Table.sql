CREATE TABLE visit_system_user_visit_status
(
    idvisit_system_user_visit_status           INT  NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_visit_status_name                     VARCHAR(40) NOT NULL,
    user_visit_status_frequency                INT NOT NULL
);
