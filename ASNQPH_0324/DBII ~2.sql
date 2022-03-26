declare 
felso int :=: felso;
also int :=: also;
c int :=: c;
begin 
dbms_output.put_line('Kérek egy számot');
if c < felso and c > also THEN
dbms_output.put_line('A szám beleesik az intervallumba.');
else 
dbms_output.put_line('A szám nem esik bele az intervallumba.');
end if;

end;
