declare

n number;			
i number;		
temp number;	

begin

n :=: N;				
i := 2;
temp := 1;

for i in 2..n/2
	loop
		if mod(n, i) = 0
		then
			temp := 0;
			exit;
		end if;
	end loop;

	if temp = 1
	then
		dbms_output.put_line('Prím szám');
	else
		dbms_output.put_line('Nem prím szám');
	end if;
end;		