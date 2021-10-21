CREATE TABLE message
(
    idmessage INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    message_text MEDIUMTEXT NOT NULL,
    post_date       DATETIME NOT NULL,
    ailment_idailment INT(11) NOT NULL,
    user_iduser INT(11) NOT NULL,
        FOREIGN KEY (ailment_idailment) REFERENCES ailment(idailment),
        FOREIGN KEY (user_iduser) REFERENCES user(iduser)
);