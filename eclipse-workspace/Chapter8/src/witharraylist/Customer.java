package witharraylist;

public class Customer {
	protected int customerID;
	protected String customerName;
	protected String customerGrade;// 고객 등급
	int bonusPoint;		//보너스 포인트
	double bonusRatio;	//적립 비율
	
	public Customer() {
		initCustomer();	//고객 등급, 적립률 지정 함수 호출
	}
	
	public Customer(int customerID, String customerName) {
		this.customerID = customerID;
		this.customerName = customerName;
		initCustomer(); //고객 등급, 적립률 지정 함수 호출
	}
	
	private void initCustomer() {	//생성자에서만 호출하는 메서드이므로 private으로 선언
		customerGrade = "SILVER";
		bonusRatio = 0.01;
	}
	
	public int calcPrice(int price) {
		bonusPoint += price * bonusRatio;	//보너스 포인트 계산
		return price;
	}

	public String showCustomerInfo() {
		return customerName + "님의 등급: "+customerGrade+", 보너스 포인트: "+bonusPoint;
	}
	
	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerGrade() {
		return customerGrade;
	}

	public void setCustomerGrade(String customerGrade) {
		this.customerGrade = customerGrade;
	}
}
