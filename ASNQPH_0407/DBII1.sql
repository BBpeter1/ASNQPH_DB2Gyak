CREATE OR REPLACE PROCEDURE PAUTO IS
a auto%rowtype;

begin
    select * into a from auto where szin = 'feher';
    dbms_output.put_line('Auto: ' || a.szin || a.tipus);
    EXCEPTION
    when too_many_rows then
        dbms_output.put_line('Túl sok');
    when no_data_found then
        dbms_output.put_line('Túl kevés');
end;

begin
PAUTO;
end;
