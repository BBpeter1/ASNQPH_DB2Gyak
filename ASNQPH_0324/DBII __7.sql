declare 
c car%rowtype;
c_id car.id%type;

begin 
select * into c from car where id = :c_id;
dbms_output.put_line(c.id || ' ' || c.manufacturer || ' ' ||c.color|| ' '|| c.price);
end;