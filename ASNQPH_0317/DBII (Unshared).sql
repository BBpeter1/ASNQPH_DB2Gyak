DECLARE
    szam integer;
BEGIN
IF MOD(:szam,2) = 0 THEN
    dbms_output.put_line('A sz�m p�ros');
ELSIF szam = 1 THEN
    dbms_output.put_line('A sz�m 1');
ELSE
    dbms_output.put_line('A sz�m p�ratlan');
END IF;

END;