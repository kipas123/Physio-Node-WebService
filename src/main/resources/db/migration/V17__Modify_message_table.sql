ALTER TABLE message
    ADD message_room_idmessage_room INT(11) NOT NULL;
ALTER TABLE message
    ADD FOREIGN KEY (message_room_idmessage_room) REFERENCES message_room(idmessage_room);