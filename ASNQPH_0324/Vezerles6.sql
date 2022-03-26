declare 
a int := 3;
b int := 4;
c int := 5;
d int;
e float;
begin 
d := (a+b+c)/2;
c := sqrt(d*(d-a)*(d-b)*(d-c));
dbms_output.put_line('Az eredmény: ' ,c);

end;
