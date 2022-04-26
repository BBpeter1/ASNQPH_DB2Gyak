package feleves1;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;


public class Task1 {
	
	private static boolean nextRound_main = true;
	private static boolean nextRound_meta = true;
	private static int userChoice;
	static Scanner input = new Scanner(System.in);
	private static int id =0;
	private static int price = 0;
	private static String nev = "";
	
	//URL
	private static final String URL = "jdbc:oracle:thin:@193.6.5.58:1521:XE";
	static ArrayList<String> data = new ArrayList<String>();
	

	public static void main(String[] args) {
		
			 
	//HIBA		//Functions.getKonyvByKategoria(conn,"LÍRA"); // Könyv lekérdezése megadott kategória alapján. 		
		
		try {
			Connection conn = connect();
			
			while(nextRound_main) {
				
				nextRound_meta = true;
				userChoice = Functions.menu();
			
				switch (userChoice) {
				case 1: //create table
					Functions.createTables(conn);										//Táblák létrehozása
					break;
					
				case 2: //auto insert	
					Functions.insertKonyvek(conn); 										//Könyvek tabla feltoltese
					Functions.insertKiado(conn); 										//Kiadok tabla feltoltese
					break;
					
				case 3: //rendeles add/update
					Functions.setPriceOfKonyv(conn,price);								// Konyv árának megemelése egy kategória alapján.									//Rendelas tablaban szereplo vegosszeg mezonek ertekadasa
					break;
					
				case 4: //delete pincer by id
					Functions.deleteById(conn,id);										//Konyvek tablabol valo torles ID alapjan
					break;
					
				case 5:	//Adatbazis metadata lekerdezese
					Functions.getDatabaseMetaData(conn); 										
					break;
					
				case 6: //write to file
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
					break;
					
				case 7: //tabla meta menu
					while(nextRound_meta) {
					userChoice = Functions.menuTablaMeta();
						switch (userChoice) {
						
						case 1:	//A rendelesel tabla metadata
							Functions.getAllKonyvMeta(conn); 						
							nextRound_meta = false;
							break;
							
						case 2:	//A pincer tabla metadata
							Functions.getAllKiadokMeta(conn); 							
							nextRound_meta = false;
							break;
							
						case 3: //back
							nextRound_meta = false;
							break;

						default:
							break;
						}
					} //end while meta
					break;
					
				case 8: //Legdrágább könyv lekérdezése. 
					Functions.getMostExpensiveKonyv(conn); 
					break;
					
				case 9: //Bármely "misztikus" könyvnél áránál drágább nem misztikus könyv kiadója.
					Functions.getKonyvDragabbMint(conn); // Bármely "misztikus" könyvnél áránál drágább nem misztikus könyv kiadója.
					break;
					
				case 10: //Könyvek árának csoportosítása kategóriánként case-el. 
					Functions.getKonyvByKategoriaCase(conn);  
					break;
					
				case 11: // Konyvek tabla feltoltese console-ról.
					Functions.insertConsole(conn);  
					break;
					
				case 12: // Kiadok tabla feltoltese console-ról.
					Functions.insertConsoleKiadok(conn);
					break;
					
				case 13: //Kiadók törlése ID alapján.
					Functions.deleteByIdKiadok(conn, id);
					break;
					
				case 14: //Kiadók nevének módosítása ID alapján.
					Functions.setNameOfKiadok(conn, nev);
					break;
					
				case 15: //exit
					System.out.println("Viszlát!");
					nextRound_main = false;
					break;

				default:
					break;
				} //end main switch
			} //end while nextRound_main
			
			System.out.println("Lefutott!");
			
		} catch (Exception e) {
			e.printStackTrace();
		}

}	
	
	//CONNECTION
			public static Connection connect() throws ClassNotFoundException, SQLException 
			{
				
				String username = "";
				String password = "";
				input = new Scanner(System.in);
				System.out.println("Kerem adja meg a felhasznalonevet!");
				username = input.nextLine(); //H22_ASNQPH
				System.out.println("Kerem adja meg a jeszavat!");
				password = input.nextLine(); //ASNQPH
				
				Class.forName("oracle.jdbc.driver.OracleDriver");			
				Connection conn = DriverManager.getConnection(URL, username, password);
				return conn;
				
			}//end connect
	
}