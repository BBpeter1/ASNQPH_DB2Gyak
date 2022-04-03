declare 
    a auto%rowtype;
    r auto.rsz%type := 'AAAOOO';
    darab number(10);
    
begin 
    select * into a from auto au where rsz =r;
    dbms_output.put_line(a.rsz || ' '|| a.tipus || ' ' || a.szin);
    select count(*) into darab from auto;
    dbms_output.put_line('Az autok száma: ' || darab);

end;

---explicit
declare 
    CURSOR sauto is select * from auto;
    a auto%rowtype;
    
begin
    open sauto;
    loop 
        fetch sauto into  a;
        exit when sauto%notfound;
        dbms_output.put_line(a.rsz || ' ' || a.evjarat|| ' ' || a.tipus);
    end loop;
    
end;

---implicit
declare
    a auto%rowtype;
begin
    for a in (select * from auto) loop
        dbms_output.put_line(a.rsz || ' ' || a.tipus);
        end loop;
end;

---parameteres
declare 
    cursor pauto(sz char) is select * from auto where szin=sz;
    a auto%rowtype;
begin
    open pauto('piros');
    loop
        fetch pauto into a;
        exit when pauto%notfound;
        dbms_output.put_line(a.rsz || ' ' || a.szin);
        end loop;
end;

---parameteres implicit
declare
    a auto%rowtype;
    sz auto.szin%type;
begin
    sz :=:sz;
    for a in(select * from auto where szin=sz) loop
    dbms_output.put_line(a.rsz || ' ' || a.szin);
    end loop;

end;

---lekérdezni az autokat és kiíratni hány évesek az autok explicit és implicit kurzorral
declare
    cursor c is select * from auto;
    a auto%rowtype;
    jelen number(4) := TO_CHAR(SYSDATE,'YYYY');
    kor auto.evjarat%type;
    ev number(4);
begin
    open c;
    loop
        fetch c into a;
        exit when c%notfound;
        kor := a.evjarat;
        ev := jelen - kor;
        dbms_output.put_line(a.rsz || ' ' || ev);
        end loop;
end;

---implicittel
declare
    a auto%rowtype;
begin
    for a in (select * from auto) loop
    dbms_output.put_line(a.rsz ||' ' || a.evjarat);
    end loop;

end;

---növelni az autok árát 10%-al ha fiatalabb 12 évnél implicittel
declare
 a auto%rowtype;
 jelen number(4) := TO_CHAR(SYSDATE,'YYYY');
 kor auto.evjarat%type;
 ev number(4);
 temprsz auto.rsz%type;
 tempar auto.ar%type;
begin
    
    for a in (select *  from auto) loop
    kor := a.evjarat;
    ev :=jelen-kor;
    
    if kor <= 12 then
        temprsz := a.rsz;
        tempar := a.ar * 1.1;
        update auto set ar = tempar where rsz=temprsz;
        end if;
    end loop;
    
end;

---explicit
declare
    cursor pauto is select * from auto for update of ar;
    a auto%rowtype;
begin
    open pauto;
    loop
        fetch pauto into a;
        exit when pauto%notfound;
        if to_char(sysdate,'yyyy')-a.evjarat < 16 then
            update auto set ar = ar*1.1 where current of pauto;
        else
        dbms_output.put_line('Idõs: ' || a.rsz || ' ' || a.tipus);
        end if;
    end loop;
end;

---hibakezelés
declare
    a auto%rowtype;
begin
    select * into a from auto where rsz='1';
exception
    when no_data_found then
    dbms_output.put_line('Nincs ilyen');

end;

declare 
    e exception;
    x number(2);
begin
    if :x<0 then
    raise e;
    end if;
    
    dbms_output.put_line('Vége');
    
exception
    when e then dbms_output.put_line('nem jó');
end;

---eljárások
create or replace PROCEDURE proba is a auto%rowtype;

begin
    select * into a from auto where szin ='feher';
    dbms_output.put_line(a.rsz || ' ' ||a.szin);
    exception
        when too_many_rows then
        dbms_output.put_line('Túl sok');
        
        when no_data_found then
        dbms_output.put_line('Túl kevés');
end;

begin
proba();
end;

---modosítás
create or replace procedure oregauto(hatar in number, rek_szam out number) is
zero_record exception;

begin
    update auto set ar = ar*0.8 where to_char(sysdate,'yyyy')-evjarat>hatar;
    rek_szam := sql%rowcount;
    if sql%notfound then
    raise zero_record;
    elsif sql%found then
    dbms_output.put_line('siker');
    end if;
exception
    when zero_record then
    dbms_output.put_line('Hiba');
end;

declare
    rszam number(2);
begin
    oregauto(1,rszam);
    dbms_output.put_line('Módosított : ' || rszam);
end;
