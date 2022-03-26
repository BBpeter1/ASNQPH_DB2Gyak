declare 

begin 
    update car set kor = to_char(sysdate, 'yyyy')-year;
end;