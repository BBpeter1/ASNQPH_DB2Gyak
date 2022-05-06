---NAPLO LOG TABLA
CREATE TABLE NAPLO(DATUM DATE, MUVELET VARCHAR2(50), FELHASZNALO VARCHAR2(20));

---TRIGGER NAPLOZASRA
CREATE OR REPLACE TRIGGER AUTO_DEL AFTER DELETE ON AUTO FOR EACH ROW WHEN (OLD.TIPUS = 'ASD')
BEGIN
    INSERT INTO NAPLO VALUES(CONCAT('DELETE: ',:OLD.RSZ),SYSDATE, USER);
END;

DELETE AUTO WHERE SZIN= 'S�RGA';

INSERT INTO AUTO VALUES(2000,'ASD','S�RGA',2013,33000);

SELECT * FROM NAPLO;

---ID L�PTET�S
CREATE OR REPLACE TRIGGER AUTO_KEY BEFORE INSERT OR UPDATE OF RSZ ON AUTO FOR EACH ROW

DECLARE
TYPE A_RSZT IS RECORD(BETUK CHAR(3),SZAMOK CHAR(3));
RENDSZAM A_RSZT;
BEGIN
    SELECT SUBSTR(:NEW.RSZ,1,3) INTO RENDSZAM.BETUK FROM DUAL;
    SELECT SUBSTR(:NEW.RSZ,4,3) INTO RENDSZAM.SZAMOK FROM DUAL;
    IF RENDSZAM.SZAMOK = 100 THEN
        RENDSZAM.SZAMOK := RSZ_N.NEXTVAL;
    END IF;
    :NEW.RSZ := RENDSZAM.BETUK || RENDSZAM.SZAMOK;
    DBMS_OUTPUT.PUT_LINE('FELVITT RENDSZ�M: ' || :NEW.RSZ);
END;

---SZ�K�TETT MEZ�KRE VONATKOZ� TRIGGER
CREATE OR REPLACE TRIGGER AUTO_T BEFORE DELETE OR UPDATE OF AR ON AUTO FOR EACH ROW
BEGIN
    IF DELETING THEN
        DBMS_OUTPUT.PUT_LINE('DELETE');
    ELSIF UPDATING THEN
        DBMS_OUTPUT.PUT_LINE('UPDATE');
    END IF;
END;

UPDATE AUTO SET AR = 100 WHERE SZIN = 'S�RGA';

DELETE AUTO WHERE SZIN = 'S�RGA';


---P�LDA VARRAY-RE
CREATE OR REPLACE PROCEDURE TEST1 AS type VARRAY_T is VARRAY(4) OF VARCHAR2(100);
TOMB VARRAY_T := VARRAY_T('A','B','C');
BEGIN
    
    TOMB(1) := 'ALMA';
    FOR i IN 1..4 LOOP
        DBMS_OUTPUT.PUT_LINE(i || '.' || VARRAY_T(I));
    END LOOP;
END;

---PACKAGE PELDA
CREATE OR REPLACE PACKAGE MY_TYPE IS TYPE ALMA IS 
TABLE OF VARCHAR2(20) INDEX BY STRING(255);
FUNCTION INIT RETURN ALMA;
END;

CREATE OR REPLACE PACKAGE BODY MY_TYPE IS
    FUNCTION INIT RETURN ALMA IS
        RET ALMA;
    BEGIN
        RET('A') := 'ASD';
        RET('B') := 'QWE';
        RETURN RET;
    END;
END;


DECLARE
    V MY_TYPE.ALMA := MY_TYPE.INIT;
BEGIN
    DBMS_OUTPUT.PUT_LINE(V('B'));
END;

