package question5;
import java.util.ArrayList;

public class CarTest {

	public static void main(String[] args) {
		ArrayList<Car> carList = new ArrayList<Car>();
		carList.add(new Sonata());
		carList.add(new Avante());
		
		for(Car car : carList) {
			car.run();
			System.out.println("==========");
		}
	}

}
