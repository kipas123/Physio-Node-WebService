CREATE TABLE message_room_membership
(
    user_iduser                 INT(11) NOT NULL,
    message_room_idmessage_room INT(11) NOT NULL,
    PRIMARY KEY (user_iduser, message_room_idmessage_room),
    FOREIGN KEY (user_iduser) REFERENCES user(iduser),
    FOREIGN KEY (message_room_idmessage_room) REFERENCES message_room(idmessage_room)
);