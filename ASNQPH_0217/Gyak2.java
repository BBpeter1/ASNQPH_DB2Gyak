package gyak2;

import java.sql.*;

public class Gyak2 {
	
	//URL
	private static final String URL = "jdbc:oracle:thin:@193.6.5.58:1521:XE";
	
	
	
	//MAIN
	public static void main(String[] args) throws ClassNotFoundException {
		try {
			Connection conn = connect("H22_ASNQPH", "ASNQPH");
			//createTable(conn); //csak egyszer
			//insertCar(conn);
			//setPriceOfCarByCorlor(conn, "zöld", 800);
			//setPriceOfCarByColorPrep(conn, "zöld", 800);
			String[] sqlString = {"insert into Car values(10, 'Opel', 'fehér', 300)", 
			                      "insert into Car values(11, 'Seat', 'zöld', 700)",
			                      "insert into Car values(12, 'Opel', 'fehér', 200)"};
			insertMultipleCar(conn, sqlString);
			System.out.println("Lefutott, yes!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	} //end main
	
	
	//CONNECTION
	public static Connection connect(String username, String password) throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection(URL, username, password);
		return conn;
	} //end connect
	
	
	//FÜGGVÉNYEK
	public static void createTable (Connection conn) throws SQLException {
		Statement stmt = conn.createStatement();
		stmt.execute("CREATE TABLE CAR (" + 
				"id number(4) primary key, " + 
				"manufacturer varchar2(200), " + 
				"color varchar2(20) NOT NULL, " +
				"price number(5) NOT NULL " +
				")");
	} //end createTable
	
	public static void insertCar(Connection conn) throws SQLException {
		Statement stmt = conn.createStatement();
		System.out.println("Insert returned: " + stmt.executeUpdate("" +
				"insert into car values(1, 'Skoda', 'zöld', 600) "));
	} //end insertCar
	
	public static void setPriceOfCarByCorlor(Connection conn, String color, int price) throws SQLException {
		Statement stmt = conn.createStatement();
		stmt.executeUpdate("UPDATE Car SET price = 700" + price + " where  color = '" + color + "'");
	} //end setPriceOfCarByCorlor  ---> nem biztonsagos, prep jobb
	
	public static void setPriceOfCarByColorPrep(Connection conn, String color, int price) throws SQLException {
		PreparedStatement prstmt = conn.prepareStatement("UPDATE Car SET price=? WHERE color=?");
		prstmt.setInt(1, price);
		prstmt.setString(2, color);
		prstmt.execute();
	} //end setPriceOfCarByColorPrep
	
	public static void insertMultipleCar(Connection conn, String[] insertSql) throws SQLException {
		Statement stmt = conn.createStatement();
		for (String sql:insertSql) {
			stmt.addBatch(sql);
		}
		System.out.println(stmt.executeBatch());
	} //end insertMultipleCar

} //end program
