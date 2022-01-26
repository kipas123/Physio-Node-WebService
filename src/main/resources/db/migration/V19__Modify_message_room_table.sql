ALTER TABLE message_room DROP FOREIGN KEY message_room_ibfk_1;
ALTER TABLE message_room DROP FOREIGN KEY message_room_ibfk_2;
ALTER TABLE message_room
DROP
COLUMN first_user;
ALTER TABLE message_room
DROP
COLUMN second_user;

ALTER TABLE message_room
    ADD created_by varchar(255);