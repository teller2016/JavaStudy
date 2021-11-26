package question5;

public abstract class Car {
	
	public abstract void start();
	public abstract void drive();
	public abstract void stop();
	
	public void run() {
		start();
		drive();
		stop();
		washCar();
	}
	public void washCar() {
		System.out.println("세차를 합니다");
	}
}
