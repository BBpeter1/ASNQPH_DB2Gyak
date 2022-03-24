package feleves1;

import java.sql.*;
import java.util.ArrayList;


public class Task1 {
	
	//URL
	private static final String URL = "jdbc:oracle:thin:@193.6.5.58:1521:XE";
//	private static Statement stmt;
	static ArrayList<String> data = new ArrayList<String>();

	public static void main(String[] args) {
		
		try {
			
			Connection conn = connect("H22_ASNQPH","ASNQPH"); // Kapcsolat fel�p�t�se.
			
			//Functions.createTables(conn); //T�bl�k l�trehoz�sa. (Csak egyszer.)
			//Functions.insertKonyvek(conn); // K�nyv t�bla felt�lt�se. (Csak egyszer.)
			//Functions.insertKiado(conn); // Kiad� t�bla felt�lt�se. (Csak egyszer.)
			//Functions.setPriceOfKonyv(conn,1500); // Konyv �r�nak megemel�se egy kateg�ria alapj�n.
			//Functions.deleteById(conn,1); // K�nyvek t�rl�se ID alapj�n. 
			//Functions.getDatabaseMetaData(conn); // Metadata lek�rdez�s.
			//Functions.getMostExpensiveKonyv(conn); // Legdr�g�bb k�nyv lek�rdez�se. 
			//Functions.getAllKonyvMeta(conn); // K�nyvek t�bla r�szletes elemz�se. 
	//HIBA		//Functions.getKonyvByKategoria(conn,"L�RA"); // K�nyv lek�rdez�se megadott kateg�ria alapj�n.
			//Functions.getKonyvDragabbMint(conn); // B�rmely "misztikus" k�nyvn�l �r�n�l dr�g�bb nem misztikus k�nyv kiad�ja.
			//Functions.getKonyvByKategoriaCase(conn); // K�nyvek �r�nak csoportos�t�sa kateg�ri�nk�nt case-el. 
			
		// File-ba �r�s kezdete	
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("Select * FROM KONYVEK");
			 
	        while (rs.next()) {

	            String id = rs.getString(1);
	            String ar = rs.getString(2);
	            String cim = rs.getString(3);
	            String kategoria = rs.getString(4);
	            String szerzo = rs.getString(5);
	            String kid = rs.getString(6);

	            data.add(id + " " + ar + " " + cim + " "+ kategoria + " " + szerzo + " " + kid );
	        }
	        	Functions.writeToFile(data, "Konyv.txt");
	        	rs.close();
	        	stmt.close(); 
	        	
	      //File-ba �r�s v�ge
			
			System.out.println("Lefutott, yes!");
			
			
		} catch(Exception e) 
		
			{
				e.printStackTrace();
			}
}	
	
	//CONNECTION
			public static Connection connect(String username, String password) throws ClassNotFoundException, SQLException 
			{
				
				Class.forName("oracle.jdbc.driver.OracleDriver");			
				Connection conn = DriverManager.getConnection(URL, username, password);
				return conn;
				
			}//end connect
	
}