package question5;

public class Sonata extends Car {

	@Override
	public void start() {
		System.out.println("Sonata ½Ãµ¿À» ÄÕ´Ï´Ù");
	}

	@Override
	public void drive() {
		System.out.println("Sonata ´Ş¸°´Ù");
	}

	@Override
	public void stop() {
		System.out.println("Sonata ¸ØÃá´Ù");
	}

}
