DECLARE 
 l_seed char(100);
 r number(10);

BEGIN
l_seed := to_char(SYSTIMESTAMP, 'YYYYDDMMHH24MISSFFFF');
r := dbms_random.value(0,4);
    case r
    when 0 then dbms_output.put_line('Nulla');
    when 1 then dbms_output.put_line('Egy');
    when 2 then dbms_output.put_line('Kettõ');
    else 
    dbms_output.put_line('Valami más');
    end case;
END;
