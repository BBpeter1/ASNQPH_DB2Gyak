declare
vezeteknev VARCHAR(300);
keresztnev VARCHAR(300);
begin
dbms_output.put_line('Add meg a keresztnevet: ');
keresztnev :=: keresztnev;
dbms_output.put_line('Add meg a vezeteknevet: ');
vezeteknev:=: vezeteknev;

for i in 1..3 loop
if keresztnev like 's�n' THEN
dbms_output.put_line('Ez nem egy n�v');
else
dbms_output.put_line(:vezeteknev || ' '|| :keresztnev);
end if;

end loop;

end;
