CREATE OR REPLACE PROCEDURE OLDAUTO(hatar IN number, rek_szam out number) is
zero_record exception;

begin

    update auto set ar = ar * 0.8 where to_char(sysdate,'yyyy')-evjarat>hatar;
    rek_szam := sql%rowcount;
    if sql%notfound then
    raise zero_record;
    elsif sql%found then
    dbms_output.put_line('Yee');
    end if;
    exception
    when zero_record then dbms_output.put_line('Nincs');
    
end;

declare 
    akor number(2) := 10;
    rszam number(2);
begin
    oldauto(akor,rszam);
    dbms_output.put_line('Módosított adatok: ' || rszam);
end;

CREATE OR REPLACE PROCEDURE arazo (alsoar in number, felsoar in number, ujar in number, preferaltmodositasok in out number) is 

begin
    update auto set ar = ujar where ar between alsoar and felsoar;
    if sql%rowcount = preferaltmodositasok then
        dbms_output.put_line('Jó');
        else
        preferaltmodositasok := sql%rowcount;
    end if;
end;

declare 
    pref number(2) := 2;
begin
    arazo(10000,200000,100000,pref);
    dbms_output.put_line('Valós változtatott: ' || pref);
end;


create or replace procedure tomb (n in number, mini out integer, maxi out integer) is
type ttipus is varray(10) of integer;
elemek ttipus := ttipus();
l_seed char(100);

begin
    l_seed := to_char(systimestamp,'yyyyddmmhh24missffff');
    for i in 1..n loop
        select dbms_random.value(-100,100) into elemek(i) from dual;
        dbms_output.put_line(i || ' . elem: ' || elemek(i));
    end loop;
    
    mini := elemek(1);
    maxi := elemek(1);
    
    for i in 1..n loop
    if mini>elemek(2) then mini := elemek(i);
    end if;
    if maxi<elemek(i) then
    maxi := elemek(i);
    end if;
    end loop;
end;

declare
    mini integer;
    maxi integer;
    n integer;
begin
    tomb(n,mini,maxi);
    dbms_output.put_line(mini || ' ' || maxi);
end;

create or replace function faktorialis(n in number) return number is
    fakt number(8) := 1;
begin
    for i in 1..n loop
        fakt := fakt * i;
    end loop;
    return fakt;
end;

select faktorialis(4) from dual;

declare
f number(10);
begin
    f := faktorialis(4);
    dbms_output.put_line(f);
end;

drop function faktorialis;

select * from user_objects where object_name like 'PROCEDURE' or object_type like 'FUNCTION';


create or replace function szamolas(n in number) return number is szam number(8) := 1;

begin
    szam := n + 2;
    return szam;
end;

select szamolas(4) from dual;