package inheritance;

public class OverridingTest2 {

	public static void main(String[] args) {
		Customer vc = new VIPCustomer(10030, "나플라", 2000);
		vc.bonusPoint = 1000;
		
		System.out.println("지불금액: "+vc.calcPrice(10000));
	}

}
