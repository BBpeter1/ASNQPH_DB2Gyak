declare

first number := 0;
second number := 1;
temp number;
  
n number :=: n;
i number;
  
begin
  
    dbms_output.put_line('Series:');
  
    dbms_output.put_line(first);
    dbms_output.put_line(second);
  
-- loop i = 2 to n
    for i in 2..n
    loop
        temp:=first+second;
  
first := second;
second := temp;
  
    dbms_output.put_line(temp);
end loop;
  
end;