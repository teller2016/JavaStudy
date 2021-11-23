package singleton;

public class CarFactory {
	private static CarFactory instance = new CarFactory();
	private int carNum = 1000;
	
	private CarFactory() {}
	
	public static CarFactory getInstance() {
		if(instance == null)
			instance = new CarFactory();
		return instance;
	}
	
	public Car createCar() {
		Car newCar = new Car(carNum);
		carNum++;
		return newCar;
	}
}
