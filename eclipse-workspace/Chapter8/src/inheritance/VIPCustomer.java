package inheritance;

public class VIPCustomer extends Customer {
	private int agentID; 	//VIP 고객 상담원 아이디
	double saleRatio;
	/*
	public VIPCustomer() {
		super();		//컴파일러가 자동으로 추가하는 코드, 상위 클래스의 생성자 호출됨
		customerGrade = "VIP";
		bonusRatio = 0.05;
		saleRatio = 0.1;
		System.out.println("VIPCustomer() 생성자 호출");
	}
	*/
	public VIPCustomer(int customerID, String customerName, int agentID) {
		super(customerID, customerName);	//상위클래스 생성자 호출
		customerGrade = "VIP";
		bonusRatio = 0.05;
		saleRatio = 0.1;
		this.agentID = agentID;
		//System.out.println("VIPCustomer(int, String, int) 생성자 호출");
	}
	
	@Override
	public int calcPrice(int price) {
		bonusPoint += price * bonusRatio;
		return price - (int)(price * saleRatio);	//할인된 가격 계산하여 반환
	}
	
	@Override
	public String showCustomerInfo() {
		return super.showCustomerInfo() + ", 담당 상담원 번호: "+agentID;
	}

	public int getAgentID() {
		return agentID;
	}

}
