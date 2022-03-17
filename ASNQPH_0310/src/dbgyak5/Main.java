package dbgyak5;

import java.io.IOException;

public class Main {
	
	private static final CarDAO carDAO = new CarDAO();

	public static void main(String[] args) throws IOException {
		
		new MyBatisUtil();
		System.out.println("Connected");
		insertCar(new Car(30,"Suzuki","zöld",200));

	}

	private static void insertCar(Car car)
	{
		CarDAO.insert(car);
	}
}
