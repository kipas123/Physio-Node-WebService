-- create password column in User table

ALTER TABLE user
    ADD reset_password_token varchar (255);