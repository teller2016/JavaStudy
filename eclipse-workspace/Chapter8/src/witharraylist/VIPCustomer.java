package witharraylist;

public class VIPCustomer extends Customer {
	private int agentID; 	//VIP 고객 상담원 아이디
	double saleRatio;

	public VIPCustomer(int customerID, String customerName, int agentID) {
		super(customerID, customerName);	//상위클래스 생성자 호출
		customerGrade = "VIP";
		bonusRatio = 0.05;
		saleRatio = 0.1;
		this.agentID = agentID;

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
