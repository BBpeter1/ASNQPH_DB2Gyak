declare 
a int :=: a;
b int :=:b;
c int :=:c;
d int := a+b;
e int := a+c;
f int := b+c;
begin 

if e > b and f > a and d > c then
dbms_output.put_line('A sz�mok nem lehetnek egy h�romsz�g oldalai.');

else
dbms_output.put_line('A sz�mok lehetnek egy h�romsz�g oldalai.');
end if;
end;