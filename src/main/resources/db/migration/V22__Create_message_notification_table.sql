CREATE TABLE message_notification
(
    idmessage_notification INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    post_date DATETIME NOT NULL,
    recipient_iduser INT(11) NOT NULL,
    sender_iduser INT(11) NOT NULL,
    FOREIGN KEY (recipient_iduser) REFERENCES user(iduser),
    FOREIGN KEY (sender_iduser) REFERENCES user(iduser)
);