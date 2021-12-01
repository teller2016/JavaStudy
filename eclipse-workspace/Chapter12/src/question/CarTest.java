package question;


public class CarTest {

	public static void main(String[] args) {
		CarFactory factory = CarFactory.getInstance();
		
		Car sonata1 = factory.createCar("������");
		Car sonata2 = factory.createCar("������");
		System.out.println(sonata1 == sonata2); // true
	
		Car avante1 = factory.createCar("�¿���");
		Car avante2 = factory.createCar("�¿���");
		System.out.println(avante1 == avante2); // true
		System.out.println(sonata1 == avante1); // false
	}

}
