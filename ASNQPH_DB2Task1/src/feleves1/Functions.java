package feleves1;

import java.io.BufferedWriter;
import java.util.List;
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
	
		
		public static void createTables(Connection conn) throws SQLException 
		{
			
			Statement stmt1 = conn.createStatement();
			Statement stmt2 = conn.createStatement();
			stmt1.execute("CREATE TABLE KIADOK(KIADOID INT PRIMARY KEY, NEV CHAR(50) NOT NULL, KIADASEVE CHAR(10) NOT NULL)");
			stmt2.execute("CREATE TABLE KONYVEK(KID INT PRIMARY KEY, AR INT NOT NULL, CIM CHAR (50) NOT NULL, KATEGORIA CHAR (20) NOT NULL, SZERZO CHAR (50) NOT NULL,KIADOID INT, FOREIGN KEY (KIADOID) REFERENCES KIADOK(KIADOID))");
			stmt1.close();
			stmt2.close();
			
		} //end createTable
		
		public static void insertKonyvek(Connection conn) throws SQLException 
		{
			
			Statement stmt = conn.createStatement();
			System.out.println("Insert returned: " + stmt.executeUpdate("INSERT INTO KONYVEK VALUES(1,1000,'A KÖNYV','DRÁMA','ARANY JÁNOS',1)"));
			System.out.println("Insert returned: " + stmt.executeUpdate("INSERT INTO KONYVEK VALUES(2,2000,'A MÁSIK KÖNYV','LÍRA','VALAKI',2)"));
			System.out.println("Insert returned: " + stmt.executeUpdate("INSERT INTO KONYVEK VALUES(3,3000,'HARRY POTTER','MISZTIKUS','JK ROWLING',3)"));
			System.out.println("Insert returned: " + stmt.executeUpdate("INSERT INTO KONYVEK VALUES(4,2500,'LEGENDÁS ÁLLATOK','MISZTIKUS','JK ROWLING',4)"));
			System.out.println("Insert returned: " + stmt.executeUpdate("INSERT INTO KONYVEK VALUES(5,5000,'MAKK MARCI','KALAND','ÁRPÁD BÉLA',5)"));
			
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
			stmt.close();
			
		} //end insertKiado
		
		public static void setPriceOfKonyv(Connection conn,int price) throws SQLException 
		{
			
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("UPDATE KONYVEK SET AR = 700+" + price + " WHERE KATEGORIA = 'MISZTIKUS'");
			stmt.close();
			
		} //end setPriceOfKonyv
		
		public static void deleteById(Connection conn, int id) throws SQLException
		{
			PreparedStatement prstmt = conn.prepareStatement("DELETE KONYVEK WHERE KID=?");
			prstmt.setInt(1, id);
			System.out.println("Deleted cars: "+ prstmt.executeUpdate());
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
		
		public static void getAllKonyv(Connection conn) throws SQLException
		{
			Statement stmt= conn.createStatement();
			ResultSet rs =stmt.executeQuery("SELECT * FROM KONYVEK");
			ArrayList<Konyv> konyvList=new ArrayList<>();
			while(rs.next())
			{
				Konyv konyv= new Konyv(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6));
				konyvList.add(konyv);
			}
			stmt.close();
			rs.close();
		} // end getAllKonyv
		
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

}
