declare 
a int := 10;
b int := 81;

begin
if a > b THEN
dbms_output.put_line('Az "a" sz�m a nagyobb.');

else 
dbms_output.put_line('A "b" sz�m  a nagyobb.');

end if;

end;