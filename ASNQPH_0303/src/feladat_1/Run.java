package feladat_1;

import java.sql.*;
import java.util.*;
import model.Car;

public class Run {
	
	//URL
	private static final String URL = "jdbc:oracle:thin:@193.6.5.58:1521:XE";
	
	
	
	//MAIN
	public static void main(String[] args) throws ClassNotFoundException {
		try {
			Connection conn = connect("H22_JR9KY7", "JR9KY7");
			//createTable(conn); //csak egyszer
			//insertCar(conn);
			//setPriceOfCarByCorlor(conn, "zöld", 800);
			//setPriceOfCarByColorPrep(conn, "zöld", 800);
			String[] sqlString = {"insert into Car values(10, 'Opel', 'fehér', 300)", 
			                      "insert into Car values(11, 'Seat', 'zöld', 700)",
			                      "insert into Car values(12, 'Opel', 'fehér', 200)"};
			//insertMultipleCar(conn, sqlString);
			//deleteCarById(conn, 1);
			//getDatabaseMetadata(conn);
			//getAllCars(conn);
			//getMostExpensiveCar(conn);
			//getAllCarsMeta(conn);
			getCarsByManufacturer(conn, "Opel");
			System.out.println("\nLefutott, yes!");
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
	
	public static void deleteCarById(Connection conn, int id) throws SQLException {
		PreparedStatement prstmt = conn.prepareStatement("DELETE CAR WHERE id=?");
		prstmt.setInt(1, id);
		System.out.println("Deleted rows : " + prstmt.executeUpdate());
	} //end deleteCarById
	
	public static void getDatabaseMetadata(Connection conn) throws SQLException {
		System.out.println("A DB driver verziója: " + conn.getMetaData().getDriverVersion());
		String[] specifyTables = {"TABLE"};
		ResultSet rs = conn.getMetaData().getTables(null, null, "%", specifyTables);
		while(rs.next()) {
			System.out.println(rs.getString(3));
		}
		//System.out.println("A DB-ben szerplõ táblák: " + conn.getMetaData().getTables(null, null, "%", null));
	} //end getDatabaseMetadata
	
	public static void getAllCars(Connection conn) throws SQLException {
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("Select id, manufacturer, color, price from car");
		List<Car> carList = new ArrayList<>();
		while(rs.next()) {	//ha tud továbblép ---> true-t ad vissza ha nem tud akkor false
			Car car = new Car (
					rs.getInt(1), //ezeknél meg lehet adni hanyadik vagy mi a column neve
					rs.getString("manufacturer"),
					rs.getString(3),
					rs.getInt("price")
			); //end Car constructor
			carList.add(car);
		}
		System.out.println(carList);
	} //end getAllCars
	
	public static void getMostExpensiveCar(Connection conn) throws SQLException {
		PreparedStatement prstmt = conn.prepareStatement("SELECT * FROM Car WHERE price = (SELECT MAX(price) FROM car)");
		ResultSet rs = prstmt.executeQuery();
		rs.next();
		Car car = new Car(
				rs.getInt(1),
				rs.getString(2),
				rs.getString(3),
				rs.getInt(4)
		);
		System.out.println(car);
	} //end getMostExpensiveCar

	public static void getAllCarsMeta(Connection conn) throws SQLException {
		PreparedStatement prstmt = conn.prepareStatement("SELECT id, color FROM Car");
		ResultSet rs = prstmt.executeQuery();
		ResultSetMetaData rsmd = rs.getMetaData();
		System.out.println("Number of columns: " + rsmd.getColumnCount());
		for (int i = 1; i <= rsmd.getColumnCount(); i++) {
			System.out.print(rsmd.getColumnName(i) + " : " + rsmd.getColumnTypeName(i) + " --- ");
		}
	} //end getAllCarsMeta
	
	public static void getCarsByManufacturer(Connection conn, String manufacturer) throws SQLException {
		PreparedStatement prstmt = conn.prepareStatement("SELECT * FROM Car WHERE manufacturer = ?");
		prstmt.setString(1, manufacturer);
		ResultSet rs = prstmt.executeQuery();
		List<Car> carList = new ArrayList<>();
		while(rs.next()) {
			carList.add(new Car(
					rs.getInt(1), 
					rs.getString(2), 
					rs.getString(3), 
					rs.getInt(4))
			);
		}
		System.out.println(carList);
	} //end getCarsByManufacturer
	
} //end program
