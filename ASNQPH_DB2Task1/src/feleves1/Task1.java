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
			
			Connection conn = connect("H22_ASNQPH","ASNQPH"); // Kapcsolat felépítése.
			
			//Functions.createTables(conn); //Táblák létrehozása. (Csak egyszer.)
			//Functions.insertKonyvek(conn); // Könyv tábla feltöltése. (Csak egyszer.)
			//Functions.insertKiado(conn); // Kiadó tábla feltöltése. (Csak egyszer.)
			//Functions.setPriceOfKonyv(conn,1500); // Konyv árának megemelése egy kategória alapján.
			//Functions.deleteById(conn,1); // Könyvek törlése ID alapján. 
			//Functions.getDatabaseMetaData(conn); // Metadata lekérdezés.
			//Functions.getMostExpensiveKonyv(conn); // Legdrágább könyv lekérdezése. 
			//Functions.getAllKonyvMeta(conn); // Könyvek tábla részletes elemzése. 
	//HIBA		//Functions.getKonyvByKategoria(conn,"LÍRA"); // Könyv lekérdezése megadott kategória alapján.
			//Functions.getKonyvDragabbMint(conn); // Bármely "misztikus" könyvnél áránál drágább nem misztikus könyv kiadója.
			//Functions.getKonyvByKategoriaCase(conn); // Könyvek árának csoportosítása kategóriánként case-el. 
			
		// File-ba írás kezdete	
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
	        	
	      //File-ba írás vége
			
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