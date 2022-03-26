alter table car add(temp number(2));

declare 
stmt char(100) := 'alter table car drop(temp)';
begin 
execute immediate stmt;

end;