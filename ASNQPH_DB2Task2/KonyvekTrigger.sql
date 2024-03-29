CREATE OR REPLACE TRIGGER KONYVVEKTRIGGER
BEFORE INSERT OR DELETE OR UPDATE ON KONYVEK FOR EACH ROW
BEGIN
    IF INSERTING THEN
        INSERT INTO DATA_LOG VALUES(CURRENT_TIMESTAMP, 'BESZ�R�S', 'KONYVEK');
    END IF;
    IF DELETING THEN
        INSERT INTO DATA_LOG VALUES(CURRENT_TIMESTAMP, 'T�RL�S', 'KONYVEK');
    END IF;
    IF UPDATING THEN
        INSERT INTO DATA_LOG VALUES(CURRENT_TIMESTAMP, 'M�DOS�T�S', 'KONYVEK');
    END IF;
END;