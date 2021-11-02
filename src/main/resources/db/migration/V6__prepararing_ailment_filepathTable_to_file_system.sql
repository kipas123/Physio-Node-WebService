ALTER TABLE ailment_filepath
    RENAME TO ailment_files;

ALTER TABLE ailment_files
DROP COLUMN path;

ALTER TABLE ailment_files
    ADD data BLOB NOT NULL;
ALTER TABLE ailment_files
    ADD name VARCHAR(100) NOT NULL;
ALTER TABLE ailment_files
    ADD type VARCHAR(100) NOT NULL;