declare 
    new_row car%rowtype 
begin 
--new_row.id := 12;
--new_row.manufacturer := 'Seat';
--new_row.color := 100;
insert into car(id, manufacturer,color,price) values(new_row.id,new_row.manufacturer,new_row.color,new_row.price);
end;