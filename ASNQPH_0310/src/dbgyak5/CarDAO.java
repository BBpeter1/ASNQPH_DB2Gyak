package dbgyak5;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;

import com.sun.tools.javac.util.List;



public class CarDAO {
	
	public static void insert(Car car) {
		
		SqlSession session = MyBatisUtil.getFactory().openSession();
		session.insert("CarMapper.insertCar",car);
		session.commit();
		session.close();
		
	}
	
	public ArrayList<Car> selectAll()
	{
		ArrayList<Car> carList;
		SqlSession session = MyBatisUtil.getFactory().openSession();
		carList = session.selectList("CarMapper.selectAllCar");
		session.commit();
		session.close();
		return carList;
	}
	
}
