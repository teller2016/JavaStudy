package witharraylist;

public class VIPCustomer extends Customer {
	private int agentID; 	//VIP �� ���� ���̵�
	double saleRatio;

	public VIPCustomer(int customerID, String customerName, int agentID) {
		super(customerID, customerName);	//����Ŭ���� ������ ȣ��
		customerGrade = "VIP";
		bonusRatio = 0.05;
		saleRatio = 0.1;
		this.agentID = agentID;

	}
	
	@Override
	public int calcPrice(int price) {
		bonusPoint += price * bonusRatio;
		return price - (int)(price * saleRatio);	//���ε� ���� ����Ͽ� ��ȯ
	}
	
	@Override
	public String showCustomerInfo() {
		return super.showCustomerInfo() + ", ��� ���� ��ȣ: "+agentID;
	}

	public int getAgentID() {
		return agentID;
	}

}
