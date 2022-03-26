declare 
a int := 10;
b int := 81;

begin
if a > b THEN
dbms_output.put_line('Az "a" szám a nagyobb.');

else 
dbms_output.put_line('A "b" szám  a nagyobb.');

end if;

end;