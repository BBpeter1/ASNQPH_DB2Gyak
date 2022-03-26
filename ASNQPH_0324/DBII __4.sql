declare 
c constant car.color%type := 'white';
begin
update car set manufacturer = 'Suzuki' where color=c;
end;