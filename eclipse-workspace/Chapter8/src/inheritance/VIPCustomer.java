package inheritance;

public class VIPCustomer extends Customer {
	private int agentID; 	//VIP �� ���� ���̵�
	double saleRatio;
	/*
	public VIPCustomer() {
		super();		//�����Ϸ��� �ڵ����� �߰��ϴ� �ڵ�, ���� Ŭ������ ������ ȣ���
		customerGrade = "VIP";
		bonusRatio = 0.05;
		saleRatio = 0.1;
		System.out.println("VIPCustomer() ������ ȣ��");
	}
	*/
	public VIPCustomer(int customerID, String customerName, int agentID) {
		super(customerID, customerName);	//����Ŭ���� ������ ȣ��
		customerGrade = "VIP";
		bonusRatio = 0.05;
		saleRatio = 0.1;
		this.agentID = agentID;
		//System.out.println("VIPCustomer(int, String, int) ������ ȣ��");
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
