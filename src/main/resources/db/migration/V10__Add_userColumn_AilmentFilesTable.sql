ALTER TABLE ailment_files
    ADD user_iduser INT(11) NOT NULL;
ALTER TABLE ailment_files
    ADD FOREIGN KEY (user_iduser) REFERENCES user(iduser);

ALTER TABLE ailment_files
    MODIFY COLUMN data LONGBLOB NOT NULL;
