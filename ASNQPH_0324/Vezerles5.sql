declare 
a int :=: a;
b int :=:b;
c int :=:c;
d int := a+b;
e int := a+c;
f int := b+c;
begin 

if e > b and f > a and d > c then
dbms_output.put_line('A számok nem lehetnek egy háromszög oldalai.');

else
dbms_output.put_line('A számok lehetnek egy háromszög oldalai.');
end if;
end;