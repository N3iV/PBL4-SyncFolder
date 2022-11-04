use PBL4;
ALTER TABLE file CHANGE created_date created_date TIMESTAMP;
ALTER TABLE file CHANGE modified_date modified_date TIMESTAMP;

ALTER TABLE user CHANGE created_date created_date TIMESTAMP;
ALTER TABLE user CHANGE modified_date modified_date TIMESTAMP;