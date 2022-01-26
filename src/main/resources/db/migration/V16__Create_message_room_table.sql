CREATE TABLE message_room
(
    idmessage_room INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    first_user INT(11) NOT NULL,
    second_user INT(11) NOT NULL,
    FOREIGN KEY (first_user) REFERENCES user(iduser),
    FOREIGN KEY (second_user) REFERENCES user(iduser)
);