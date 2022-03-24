DECLARE
    szam integer;
BEGIN
IF MOD(:szam,2) = 0 THEN
    dbms_output.put_line('A szám páros');
ELSIF szam = 1 THEN
    dbms_output.put_line('A szám 1');
ELSE
    dbms_output.put_line('A szám páratlan');
END IF;

END;