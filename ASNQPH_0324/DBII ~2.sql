declare 
felso int :=: felso;
also int :=: also;
c int :=: c;
begin 
dbms_output.put_line('K�rek egy sz�mot');
if c < felso and c > also THEN
dbms_output.put_line('A sz�m beleesik az intervallumba.');
else 
dbms_output.put_line('A sz�m nem esik bele az intervallumba.');
end if;

end;
