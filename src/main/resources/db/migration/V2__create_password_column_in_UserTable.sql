-- create password column in User table

ALTER TABLE user
    ADD user_password varchar (255) NOT NULL;