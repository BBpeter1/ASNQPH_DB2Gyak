SELECT * FROM CAR

ALTER TABLE CAR ADD(KOR NUMBER(2));
ALTER TABLE CAR MODIFY(YEAR NUMBER(10));

declare 
year number(2);
begin
    new_year := year;
    color :=:color;
    update car set year = new_year where color=:color;
end;
