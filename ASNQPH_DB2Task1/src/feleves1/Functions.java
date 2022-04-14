package feleves1;

import java.io.BufferedWriter;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Functions {
	
	static Scanner input = new Scanner(System.in);
	private static int selection;
	
	
		
		public static void createTables(Connection conn) throws SQLException 
		{
			Statement stmt1 = conn.createStatement();
			Statement stmt2 = conn.createStatement();
			stmt1.execute("CREATE TABLE KIADOK(KIADOID INT PRIMARY KEY, NEV CHAR(50) NOT NULL, KIADASEVE CHAR(10) NOT NULL)");
			stmt2.execute("CREATE TABLE KONYVEK(KID INT PRIMARY KEY, AR INT NOT NULL, CIM CHAR (50) NOT NULL, KATEGORIA CHAR (20) NOT NULL, SZERZO CHAR (50) NOT NULL,KIADOID INT, FOREIGN KEY (KIADOID) REFERENCES KIADOK(KIADOID))");
			stmt1.close();
			stmt2.close();
			System.out.println("Konyvek tabla letrehozva!\n");
			System.out.println("Kiadok tabla letrehozva!");
			
		} //end createTable
		
		public static void insertKonyvek(Connection conn) throws SQLException 
		{
			
			Statement stmt = conn.createStatement();
			System.out.println("Insert returned: " + stmt.executeUpdate("INSERT INTO KONYVEK VALUES(1,1000,'A KÖNYV','DRÁMA','ARANY JÁNOS',1)"));
			System.out.println("Insert returned: " + stmt.executeUpdate("INSERT INTO KONYVEK VALUES(2,2000,'A MÁSIK KÖNYV','LÍRA','VALAKI',2)"));
			System.out.println("Insert returned: " + stmt.executeUpdate("INSERT INTO KONYVEK VALUES(3,3000,'HARRY POTTER','MISZTIKUS','JK ROWLING',3)"));
			System.out.println("Insert returned: " + stmt.executeUpdate("INSERT INTO KONYVEK VALUES(4,2500,'LEGENDÁS ÁLLATOK','MISZTIKUS','JK ROWLING',4)"));
			System.out.println("Insert returned: " + stmt.executeUpdate("INSERT INTO KONYVEK VALUES(5,5000,'MAKK MARCI','KALAND','ÁRPÁD BÉLA',5)"));
			System.out.println("Konyvek tabla feltoltve!\n");
			
			stmt.close();
			
		} //end insertKonyv
		
		public static void insertKiado(Connection conn) throws SQLException 
		{
			
			Statement stmt = conn.createStatement();
			System.out.println("Insert returned: " + stmt.executeUpdate("INSERT INTO KIADOK VALUES(1,'PARAMOUNT','2011-05-03')"));
			System.out.println("Insert returned: " + stmt.executeUpdate("INSERT INTO KIADOK VALUES(2,'JO KIADO','2012-03-02')"));
			System.out.println("Insert returned: " + stmt.executeUpdate("INSERT INTO KIADOK VALUES(3,'ROSSZ KIADO','2013-07-24')"));
			System.out.println("Insert returned: " + stmt.executeUpdate("INSERT INTO KIADOK VALUES(4,'LIBRI','2014-11-23')"));
			System.out.println("Insert returned: " + stmt.executeUpdate("INSERT INTO KIADOK VALUES(5,'MORA','2015-10-31')"));
			System.out.println("Kiadok tábla feltoltve!\n");
			stmt.close();
			
		} //end insertKiado
		
		public static void insertConsole(Connection conn) throws SQLException
		{
			int id=0,price=0,kid=0;
			String cim="",szerzo="",mufaj="";
			PreparedStatement prstmt = conn.prepareStatement("INSERT INTO KONYVEK VALUES(?,?,?,?,?,?)");
			Scanner sc = new Scanner(System.in);
			
				System.out.println("Kérem adja meg a könyv id-jét!");
				id = sc.nextInt();
				System.out.println("Kérem adja meg árát!");
				price = sc.nextInt();
				System.out.println("Kérem adja meg a könyv címét!");
				cim = sc.next();
				System.out.println("Kérem adja meg a könyv mûfaját!");
				mufaj = sc.next();
				System.out.println("Kérem adja meg a könyv szerzõjét!");
				szerzo = sc.next();
				System.out.println("Kérem adja meg a könyv kiadó id-jét!");
				kid = sc.nextInt();
				System.out.println("Siker");
				
				if(id != 0 && price != 0 && kid != 0 && cim != "" && mufaj !="" && szerzo !="")
				{
				prstmt.setInt(1, id);
				prstmt.setInt(2, price);
				prstmt.setString(3, cim);
				prstmt.setString(4, mufaj);
				prstmt.setString(5, szerzo);
				prstmt.setInt(6, kid);
				prstmt.execute();
				System.out.println("A rekord felvive!\n");
				}
				else
				{
					System.out.println("Hibás input");
				}
			
		} // end insertConsole
		
		public static void setPriceOfKonyv(Connection conn,int price) throws SQLException 
		{
			PreparedStatement prstmt = conn.prepareStatement("UPDATE KONYVEK SET AR = ? WHERE KATEGORIA = 'MISZTIKUS'");
			try {
				Scanner sc = new Scanner(System.in);
				System.out.println("Adja meg az uj ar összegét!\n");
				price = sc.nextInt();
				if(price > 0 && price < 10000000 )
				{
					prstmt.setInt(1, price);
					prstmt.executeUpdate();
					System.out.println("Konyv ara modositva!\n");
				}
				} catch(Exception e)
				{
					System.out.println("Nem megfelelõek az adatok");
				} 
			prstmt.close();
			
			
		} //end setPriceOfKonyv
		
		public static void deleteById(Connection conn, int id) throws SQLException
		{
			
			PreparedStatement prstmt = conn.prepareStatement("DELETE KONYVEK WHERE KID=?");
			try {
				Scanner sc = new Scanner(System.in);
				System.out.println("Adja meg a törlendõ adat id-jét!");
				id = sc.nextInt();
				if(id >0)
				{
					prstmt.setInt(1, id);
				}
				} catch(Exception e)
				{
					System.out.println("Nem megfelelõek az adatok");
				} 
			System.out.println("Deleted konyvek: "+ prstmt.executeUpdate());
			prstmt.close();
		} // end deleteById
		
		public static void getDatabaseMetaData(Connection conn) throws SQLException
		{
			System.out.println("Driver verzió: "+conn.getMetaData().getDriverVersion());
			String[] specifyTables= {"TABLE"};
			ResultSet rs= conn.getMetaData().getTables(null, null, "%", specifyTables);
			while(rs.next()) {
				System.out.println(rs.getString(3));
			}
			rs.close();
		} // end MetaData
		
		public static void getMostExpensiveKonyv(Connection conn) throws SQLException
		{
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM KONYVEK WHERE AR = (SELECT Max(AR) FROM KONYVEK)");
			rs.next();
			Konyv konyv = new Konyv(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6));
			
			System.out.println(konyv);
			stmt.close();
			rs.close();
		} // end getMostExpensiveKonyv
		
		public static void getAllKonyvMeta(Connection conn) throws SQLException
		{
			PreparedStatement prstmt = conn.prepareStatement("SELECT * FROM KONYVEK");
			ResultSet rs = prstmt.executeQuery();	
			ResultSetMetaData rsmd = rs.getMetaData();
			System.out.println("Number of columns: " +rsmd.getColumnCount());
			for (int i = 1; i <= rsmd.getColumnCount(); i++) 
			{
				System.out.println(rsmd.getColumnName(i)+":"+ rsmd.getColumnTypeName(i));
			}
			prstmt.close();
			rs.close();
		
		} // end getAllKonyvMeta
		
		public static void getAllKiadokMeta(Connection conn) throws SQLException
		{
			PreparedStatement prstmt = conn.prepareStatement("SELECT * FROM KIADOK");
			ResultSet rs = prstmt.executeQuery();	
			ResultSetMetaData rsmd = rs.getMetaData();
			System.out.println("Number of columns: " +rsmd.getColumnCount());
			for (int i = 1; i <= rsmd.getColumnCount(); i++) 
			{
				System.out.println(rsmd.getColumnName(i)+":"+ rsmd.getColumnTypeName(i));
			}
			prstmt.close();
			rs.close();
		
		} // end getAllKonyvMeta
		
		public static void getKonyvByKategoria(Connection conn,String kategoria) throws SQLException
		{
			PreparedStatement prstmt = conn.prepareStatement("SELECT * FROM KONYVEK WHERE KATEGORIA = ?");
			prstmt.setString(1, kategoria);
			ResultSet rs = prstmt.executeQuery();
			List<Konyv> konyvList = new ArrayList<>();
			while(rs.next())
			{
				Konyv konyv = new Konyv(rs.getInt("KID"),rs.getInt("AR"),rs.getString("CIM"),rs.getString("KATEGORIA"),rs.getString("SZERZO"),rs.getInt("KIADOID"));
				konyvList.add(konyv);
				
				System.out.println(konyv);
			}
			System.out.println(konyvList);
			rs.close();
			prstmt.close();
		} // getKonyvByKategoria
		
		public static void getKonyvDragabbMint(Connection conn) throws SQLException
		{
			PreparedStatement prstmt = conn.prepareStatement("SELECT NEV,CIM FROM KIADOK INNER JOIN KONYVEK ON KID=KIADOK.KIADOID WHERE AR>(SELECT AVG(AR) FROM KONYVEK INNER JOIN KIADOK ON KID=KIADOK.KIADOID WHERE KATEGORIA='MISZTIKUS') AND KATEGORIA != 'MISZTIKUS'");
			ResultSet rs = prstmt.executeQuery();
			List <Lekerdezes> lekerdezes = new ArrayList<>();
			while(rs.next())
			{
				Lekerdezes leker = new Lekerdezes(rs.getString("NEV"),rs.getString("CIM"));
				lekerdezes.add(leker);
			}
			System.out.println(lekerdezes);
			prstmt.close();
			rs.close();
		} // getKonyvDragabbMint
		
		public static void getKonyvByKategoriaCase(Connection conn) throws SQLException
		{
			PreparedStatement prstmt = conn.prepareStatement("SELECT NEV,CIM,(CASE WHEN AR>2400 THEN 'DRÁGA' ELSE 'OLCSÓ' END) ARKATEGORIA FROM KONYVEK INNER JOIN KIADOK ON KID=KIADOK.KIADOID");
			ResultSet rs = prstmt.executeQuery();
			ArrayList<Case> leker = new ArrayList<>();
			while(rs.next())
			{
				leker.add(new Case(rs.getString(1),rs.getString(2),rs.getString(3)));
			}
			System.out.println(leker);
			prstmt.close();
			rs.close();
		} // getKonyvByKategoriaCase
		
		public static void writeToFile(java.util.List<String> list, String path) {
			
	        BufferedWriter out = null;
	        try {
	            File file = new File(path);
	            out = new BufferedWriter(new FileWriter(file, true));
	            for (Object s : list) {
	                out.write((String) s);
	                out.newLine();
	 
	            }
	            out.flush();
	            out.close();
	            
	        } catch (IOException e) {
	        	System.out.println(e);
	        }
	    }
		
		//Main menu
		public static int menu() {
			
		    input = new Scanner(System.in);

		    System.out.println("Valasszon a lehetosegekbol");
		    System.out.println("-------------------------\n");
		    System.out.println("1 - Tablak letrehozasa");
		    System.out.println("2 - Tablak automatikus feltoltese");
		    System.out.println("3 - Konyvek tabla modositasa");
		    System.out.println("4 - Torles konyvek tablabol ID alapjan");
		    System.out.println("5 - Adatbazis metadata lekerdezese");
		    System.out.println("6 - Fajlba iras");
		    System.out.println("7 - Tabla metadata");
		    System.out.println("8 - Legdragabb konyv lekerdezese");
		    System.out.println("9 - Bonyolult lekerdezes");
		    System.out.println("10 - Konyvek tabla csoportositasa (case)");
		    System.out.println("11 - Konyvek tabla feltoltese (Console)");
		    System.out.println("12 - Kilepes");

		    selection = input.nextInt();
		    return selection;    
		} // end menu

		//Tabla metadata menu
		public static int menuTablaMeta() {

		    input = new Scanner(System.in);

		    System.out.println("Valasszon a tablakbol");
		    System.out.println("-------------------------\n");
		    System.out.println("1 - Konyvek tabla");
		    System.out.println("2 - Kiadok tabla");
		    System.out.println("3 - Vissza");

		    selection = input.nextInt();
		    return selection;    
	    } // end menuTablaMeta 
}
