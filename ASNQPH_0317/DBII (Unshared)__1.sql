DECLARE
    l_seed char(100);
    r number(4);

BEGIN
    l_seed := to_char(SYSTIMESTAMP, 'YYYYDDMMHH24MISSFFFF');
    r := dbms_random.value(-100,100);
    if r < 0 THEN
    dbms_output.put_line(r || ' Negatív');
    elsif r = 0 then
    dbms_output.put_line(r || ' Ez nulla');
    else
    dbms_output.put_line(r || ' Pozitív');
    end if;

END;
