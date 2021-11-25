# 8장 - 상속과 다형성

## 8-1 상속이란?

- 상속은 무엇인가를 물려받는 의미
- B 클래스가 A클래스를 상속받으면 B 클래스는 A 클래스의 **멤버 변수**와 **메서드**를 사용할 수 있다
- 상속을 통해 객체 지향 프로그램의 **유지 보수 편리함, 수정, 추가의 유연성**을 제공받는다

### 클래스의 상속

> A 클래스 (상위 클래스)
> ^
> |
> B 클래스 (하위 클래스)

- A클래스가 B클래스에게 상속한다 == B클래스가 A클래스를 상속받는다
  - **상속받는 클래스에서 상속하는 클래스로 화살표가 간다**
  - `부모 클래스 (parent class)`를 `상위 클래스`
  - `자식 클래스 (child class)`를 `하위 클래스`

#### 클래스 상속 문법

- `extend` 예약어 사용

  ```java
  class B extends A{	//클래스B 가 A클래스를 상속받는다
  }
  ```

  - 상위 클래스가 하위 클래스보다 일반적인 개념
  - 하위 클래스는 상위 클래스보다 **구체적인 클래스**

### 상속을 사용하여 고객 관리 프로그램 구현하기

- 고객 클래스

  ```java
  package inheritance;
  
  public class Customer {
  	private int customerID;
  	private String customerName;
  	private String customerGrade;// 고객 등급
  	int bonusPoint;		//보너스 포인트
  	double bonusRatio;	//적립 비율
  	
  	public Customer() {
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
  }
  ```

  - 단골 고객을 위한 VIP 등급의 고객은 다음과 같은 혜택을 받는다

    > - 제품을 살 때 항상 10% 할인
    > - 보너스 포인트를 5% 적립
    > - 담당 전문 상담원을 배정

    - **Customer 클래스에 VIP 고객에 필요한 변수와 메서드를 추가하면 불필요한 정보가 들어간다**
    - **VIP 클래스를 따로 새로 만들면** 중복된 멤버 변수와 변형된 메서드가 생긴다
      - 이럴때 **`상속`**을 사용한다

  ```java
  public class VIPCustomer extends Customer {	//Customer 클래스를 상속받음
  	private int agentID; 	//VIP 고객 상담원 아이디
  	double saleRatio;
  	
  	public VIPCustomer() {
  		customerGrade = "VIP";	//상위 클래스에서 private변수라 오류발생!
  		bonusRatio = 0.05;
  		saleRatio = 0.1;
  	}
  	
  	public int getAgentID() {
  		return agentID;
  	}
  }
  ```

  - Customer 클래스를 상속받아 해당 클래스의 멤머변수와 메서드도 같이 사용 가능하다
  - **아직 VIP고객 혜택 적용은 구현하지 않았다**

#### 상위 클래스 변수를 사용하기 위한 protected 예약어

- 상위 클래스의 `private`변수는 하위 클래스에서 사용하지 못한다
- `protected`: **외부 클래스에서 사용할 수 없지만**, **하위 클래스에서 사용할 수 있도록** 하는 예약어
  - protected는 상속된 하위 클래스를 제외한 나머지 외부 클래스에서는 private과 동일한 역할을 한다

- protected를 사용한 고객 클래스

  ```java
  public class Customer {
  	protected int customerID;
  	protected String customerName;
  	protected String customerGrade;// 고객 등급
  	int bonusPoint;		//보너스 포인트
  	double bonusRatio;	//적립 비율
  	
  	...
  	
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
  ```

  - `protected` 예약어로 선언한 변수는 외부 클래스에서 `get()`, `set()`을 통해 값을 사용 가능
  - VIPCustomer 클래스는 `protected`, `public`으로 선언한 변수, 메서드 사용 가능

#### 테스트 프로그램 실행하기

- 일반고객 1명, VIP 고객 1명

  ```java
  public class CustomerTest1 {
  
  	public static void main(String[] args) {
  		Customer customerLee = new Customer();
  		customerLee.setCustomerID(10010);
  		customerLee.setCustomerName("이순신");
  		customerLee.bonusPoint = 1000;
  		System.out.println(customerLee.showCustomerInfo());
  		
  		VIPCustomer customerKim = new VIPCustomer();
  		customerKim.setCustomerID(10020);
  		customerKim.setCustomerName("김유신");
  		customerKim.bonusPoint = 10000;
  		System.out.println(customerKim.showCustomerInfo());
  	}
  }
  /*
  이순신님의 등급: SILVER, 보너스 포인트: 1000
  김유신님의 등급: VIP, 보너스 포인트: 10000
  */
  ```

  

## 8-2 상속에서 클래스 생성과 형 변환

- **하위 클래스가 생성될 때 상위 클래스의 생성자가 먼저 호출된다**

### 하위 클래스가 생성되는 과정

- Customer, VIPCustomer 클래스 생성자에 출력문 추가

  ```java
  package inheritance;
  
  public class Customer {
  	protected int customerID;
  	protected String customerName;
  	protected String customerGrade;
  	int bonusPoint;		
  	double bonusRatio;	
  	
  	public Customer() {
  		customerGrade = "SILVER";
  		bonusRatio = 0.01;
  		System.out.println("Customer() 생성자 호출");	//상위 클래스 생성할 때 출력
  	}
  	
  	...
  }
  
  ```

  ```java
  public class VIPCustomer extends Customer {
  	private int agentID; 	//VIP 고객 상담원 아이디
  	double saleRatio;
  	
  	public VIPCustomer() {
  		customerGrade = "VIP";	
  		bonusRatio = 0.05;
  		saleRatio = 0.1;
  		System.out.println("VIPCustomer() 생성자 호출");		//하위 클래스 생성할 때 출력
  	}
  	
  	...
  	
  }
  ```

- 클래스 생성 후 출력

  ```java
  public class CustomerTest2 {
  
  	public static void main(String[] args) {
  		VIPCustomer customerKim = new VIPCustomer();
  		customerKim.setCustomerID(10020);
  		customerKim.setCustomerName("김유신");
  		customerKim.bonusPoint = 10000;
  		System.out.println(customerKim.showCustomerInfo());
  	}
  }
  /*
  Customer() 생성자 호출
  VIPCustomer() 생성자 호출
  김유신님의 등급: VIP, 보너스 포인트: 10000
  */
  ```

  - Customer() 생성자 먼저 호출 뒤, VIPCustomer() 생성자가 호출 되었다

  - **상위 클래스를 상속받은 하위 클래스가 생성될 때는 반드시 상위 클래스의 생성자가 먼저 호출된다**

    >힙 메모리
    >
    >|customerID
    >|customerName
    >|customerGrade
    >|  ...							=> 1. Customer() 생성자 호출 -> Customer 클래스의 멤버 변수가 메모리에 생성됨
    >
    >|agentID
    >|salesRatio			  => 2. VIPCustomer() 생성자 호출 -> VIPCustomer 클래스의 멤버 변수가 메모리에 생성됨

    - 상위 클래스의 변수가 메모리에 먼저 생성되어서, 하위 클래스에서도 이 값들을 사용 가능
    - private 변수의 경우 **생성은 되지만**, 하위 클래스에서 접근 못하는 것

### 부모를 부르는 예약어, super

- `super` 예약어는 하위 클래스에서 상위 클래스로 접근할 때 사용
  - 하위는 상위의 주소, 즉 참조 값을 알고 있다
  - ```super```: 참조 값을 가지고 있는 예약어
    - **this가 자기 자신의 참조 값을 가지는 것과 유사**

#### 상위 클래스 생성자 호출하기

- 하위 클래스 생성자만 호출했는데 상위 클래스 호출된 이유는 **하위 클래스 생성자에서 `super()`을 자동으로 호출**하기 때문이다

  ```java
  public class VIPCustomer extends Customer {
  	private int agentID;
  	double saleRatio;
  	
  	public VIPCustomer() {
  		super();		//컴파일러가 자동으로 추가하는 코드, 상위 클래스의 생성자 호출됨
  		customerGrade = "VIP";
  		bonusRatio = 0.05;
  		saleRatio = 0.1;
  		System.out.println("VIPCustomer(int, String) 생성자 호출");
  	}
  	...
  	
  }
  ```

#### super 예약어로 매개변수가 있는 생성자 호출하기

- Customer클래스 생성시 **반드시** 고객ID와 이름을 받아야 된다고 가정

  - **기존 디폴트 생성자는 삭제**

  ```java
  public class Customer {
  	...
  	public Customer(int customerID, String customerName) {
  		this.customerID = customerID;
  		this.customerName = customerName;
  		customerGrade = "SILVER";
  		bonusRatio = 0.01;
  		System.out.println("Customer() 생성자 호출");
  	}
  	...
  }
  
  ```

  - **오류 발생**
    - 묵시적으로 호출될 디폴트 생성자 Customer()가 정의되지 않아서 그렇다, 반드시 명시적으로 다른 생성자를 호출해야 된다
  - VIPCustomer 클래스도 디폴트 생성자 지우고, 필요한 매개변수를 포함하는 생성자 작성

  ```java
  public class VIPCustomer extends Customer {
  	private int agentID; 	//VIP 고객 상담원 아이디
  	double saleRatio;
  	
  	public VIPCustomer(int customerID, String customerName, int agentID) {
  		super(customerID, customerName);	//상위클래스 생성자 호출
  		customerGrade = "VIP";
  		bonusRatio = 0.05;
  		saleRatio = 0.1;
  		this.agentID = agentID;
  		System.out.println("VIPCustomer(int, String, int) 생성자 호출");
  	}
  	
  	...
  	
  }
  ```

  - `super` 예약어를 통해 `Customer(int, String)` 생성자를 호출했다
    - **결론**: 상위 클래스에 디폴트 생성자가 없고 매개변수가 있는 생성자만 있을 경우, 하위 클래스 생성자의 `super()`에 매개변수를 추가하여, 매개변수가 있는 상위 클래스의 생성자를 직접 호출해야 된다

#### 상위 클래스의 멤버 변수나 메서드를 참조하는 super

- 상위 클래스에 선언한 멤버 변수나 메서드를 하위 클래스에서 참조할 때도 `super`을 사용한다

  ```java
  public String showVIPInfo(){
      return super.showCustomerInfo() + "담당 상담원 ID: "+agentID;
  }
  ```

  - `super`을 안붙여도 상위 클래스의 메서드가 호출된다 **But** **상위, 하위 클래스의 메서드 이름이 동일하면** 상위 클래스 메서드를 가리킬 때 `super`을 붙인다

### 상위 클래스로 묵시적 클래스 형 변환

- 상위 클래스가 하위보다 일반적인 개념이며, 하위는 상위보다 기능이 더 많다

  - VIPCustomer은 **VIPCustomer형이면서 동시에 Customer형이기도 한다**

- **VIPCustomer 클래스로 인스턴스를 생성할 때 자료형을 Customer형으로 `클래스 형 변환`하여 선언할 수 있다**

  ```java
  Customer vc = new VIPCustomer();
  ```

  - 반대로 Customer을 VIPCustomer형으로 선언은 안된다

#### 형 변환된 vc가 가리키는 것

> 힙 메모리
>
> |customerID
> |customerName
> |customerGrade
> |  ...							=> 1. Customer() 생성자 호출 -> Customer 클래스의 멤버 변수가 메모리에 생성됨
>
> |agentID
> |salesRatio			  => 2. VIPCustomer() 생성자 호출 -> VIPCustomer 클래스의 멤버 변수가 메모리에 생성됨

- `Customer vc = new VIPCustomer();` 문장은 VIPCustomer 생성자가 호출되어 위와 같은 메모리에 만들어진다
  - 클래스 자료형이 Customer로 한정되어 **`vc`참조 변수가 가리킬 수 있는 변수와 메서드는 Customer 클래스의 멤버만 가능하다**
- **상속 계층 구조가 여러 단계일 경우**에도 묵시적으로 형 변환이 가능하다



## 8-3 메서드 오버라이딩

### 상위 클래스 메서드 재정의하기

- `메서드 오버라이딩(method overriding)`: 상위 클래스에서 정의한 메서드가 하위 클래스에서 구현할 내용과 맞지 않을 경우 **하위 클래스에서 메서드를 재정의 하는것**
  - 오버라이딩을 위해서는 `반환형`, `메서드 이름`, `매개변수 개수`, `매개변수 자료형`이 반드시 같아야 된다

#### VIP 고객 클래스의 제품 가격 계산 메서드 재정의하기

- VIPCustomer클래스의 calcPrice() 메서드 재정의

  ```java
  public class VIPCustomer extends Customer {
  	private int agentID;
  	double saleRatio;
  	...
  	@Override
  	public int calcPrice(int price) {
  		bonusPoint += price * bonusRatio;
  		return price - (int)(price * saleRatio);	//할인된 가격 계산하여 반환
  	}
  	
  }
  ```

  > 이클립스 기능
  > [우클릭 => Source => Override/Implement Methods... => 재정의할 메서드 선택]

  - `@Override` 애노테이션은 '이 메서드는 재정의된 메서드입니다'라고 컴파일러에 명확히 알리는 역할

    > 애노테이션은 영어로 주석이라는 의미
    > '@(애노테이션 이름)'표현
    >
    > - @Override의 경우 메서드의 선언부가 다르면 컴파일 오류를 발생시켜 실수를 막아준다
    > - 미리 정의된 애노테이션을 `표준 애노테이션`이라한다
    >   - @Override, @FunctionalInterface, @Deprecated, @SuppressWarnings
    >   - 이 외에 `메타 애노테이션`도 존재

### 묵시적 클래스 형 변환과 메서드 재정의

- ```java
  Customer vc = new VIPCustomer(10030, "나플라", 2000);
  vc.calcPrice(10000);
  ```

  - `calcPrice()`는 하위 클래스에 재정의된 메서드이며, 상위 하위 클래스에 모두 존재

  ```java
  public class OverridingTest2 {
  
  	public static void main(String[] args) {
  		Customer vc = new VIPCustomer(10030, "나플라", 2000);
  		vc.bonusPoint = 1000;
  		
  		System.out.println("지불금액: "+vc.calcPrice(10000));
  	}
  }
  /*
  지불금액: 9000
  */
  ```

  - `vc.calcPrice(10000)`은 선언한 클래스형인 `Customer` 클래스의 `calcPrice()`메서드 호출이 아닌
    **`VIPCustomer`클래스의 재정의된 메서드를 호출했다**
  - **상속에서 상위와 하위 클래스에 같은 이름의 메서드가 존재할 때, 호출되는 메서드는 `인스턴스`에 따라 결정된다**
    - 다시 말해, 선언한 클래스형이 아닌 **생성된 인스턴스의 메서드를 호출한다**
    - `가상 메서드`: 인스턴스의 메서드가 호출되는 기술
      - 자바의 모든 메서드는 `가상 메서드`이다
      - 가상 메서드는 각 클래스의 `가상 메서드 테이블`을 만들어 메서드 이름과, 메서드의 주소 위치를 저장하여, 주소값에 해당하는 메서드를 실행한다

### 가상 메서드

- 자바의 클래스는 `멤버 변수`와 `메서드`로 이루어진다

  - 클래스를 생성하여 `인스턴스`가 생성되면 `멤버 변수`는 **힙 메모리**에 위치한다
  - **변수가 사용하는 메모리와 메서드가 사용하는 메모리는 다르다**
    - 변수는 인스턴스가 생성될때마다 새로 생성
    - **메서드는 인스턴스가 여러개 생성되어도 여러개 생성되지 않는다**

- Example

  ```java
  public class TestA {
  	int num;
  	
  	void aaa() {
  		System.out.println("aaa() 출력");
  	}
  	
  	public static void main(String[] args) {
  		TestA a1 = new TestA();
  		a1.aaa();
  		
  		TestA a2 = new TestA();
  		a2.aaa();
  	}
  }
  ```

  > 메모리 상태
  >
  > 힙 메모리						: [a1의 num] [a2의 num]
  > 스택 메모리					: [a1] [a2] [args(main() 함수의 지역변수)]
  > 메서드 영역(코드 영역) : [aaa() 메서드 영역]
  >
  > - a1, a2는 메서드 영역의 aaa() 호출
  > - 메서드를 호출하면 메서드 영역의 주소를 참조하여 명령이 실행된다
  >   - 인스턴스가 달라도 동일한 메서드가 호출된다

#### 가상 메서드의 원리

- 일반 프로그램에서의 메서드 호출은 메서드의 명령 집합이 있는 메모리 위치를 참조하여 명령을 실행한다

- **가상 메서드**의 경우 `가상 메서드 테이블`이 만들어진다

  - `가상 메서드 테이블`은 각 메서드 이름과 실제 메모리 주소가 짝을 이루고 있다

  - 메서드가 호출되면 테이블에서 주소 값을 찾아서 해당 메서드의 명령을 수행

    > Customer 클래스의 가상 메서드 테이블
    >
    > [메서드], [메서드 주소]
    >
    > [calcPrice], [0xFF00FFAA] 						=> 재정의됨
    > [showCustomerInfo], [0x112233AA]	 => 재정의되지 않음

    > VIPCustomer 클래스의 가상 메서드 테이블
    >
    > [메서드], [메서드 주소]								
    >
    > [calcPrice], [0x00335577]							=> 재정의됨
    > [showCustomerInfo], [0x112233AA]	 => 재정의되지 않음
    > [getAgentID], [0x8899BB33]					=> 하위 클래스에서 새로 추가된 메서드

    - 재정의된 `calcPrice`는 서로 다른 메서드 주소를 갖고 있다
    - `showCustomerInfo`의 경우 재정의되지 않아 둘다 동일한 주소를 갖고 있다



## 8-4 다형성

### 다형성이란?

- `다형성(polymorphism)`: 하나의 코드가 여러 자료형으로 구현되어 실행되는 것

  - 같은 코드에서 여러 실행 결과가 나오는 것

- Animal 클래스 예시

  ```java
  package polymorphism;
  
  class Animal{
  	public void move() {
  		System.out.println("동물이 움직입니다");
  	}
  }
  
  class Human extends Animal {
  	public void move() {
  		System.out.println("사람이 두 발로 걷습니다");
  	}
  }
  
  class Tiger extends Animal{
  	public void move(){
  		System.out.println("호랑이가 네 발로 뜁니다. ");
  	}
  }
  
  class Eagle extends Animal{
  	public void move(){
  		System.out.println("독수리가 하늘을 납니다 ");
  	}
  }
  
  public class AnimalTest1 {
  
  	public static void main(String[] args) {
  		  AnimalTest1 aTest = new AnimalTest1();
  		  aTest.moveAnimal(new Human());
  		  aTest.moveAnimal(new Tiger());
  		  aTest.moveAnimal(new Eagle());
  	 }
  	
  	public void moveAnimal(Animal animal) {
  		animal.move();							//재정의된 메서드가 호출됨
  	}
  }
  /*
  사람이 두 발로 걷습니다
  호랑이가 네 발로 뜁니다. 
  독수리가 하늘을 납니다 
  */
  ```

  - `moveAnimal()`메서드는 어떤 인스턴스가 매개변수로 넘어와도 모두 Animal 형으로 변환한다
    - Animal에서 상속받은 클래스가 매개변수로 넘어오면 Animal형으로 변환되므로 `animal.move()`메서드를 호출할 수 있다
    - **`가상 메서드` 원리에 따라** `move()`메서드는 Animal의 메서드가 아닌 매개변수로 **넘어온 인스턴스의 메서드이다**
  - **`animal.move()`코드는 변함 없지만 어떤 매개변수가 넘어왔느냐에 따라 출력문이 달라진다**
    - 이것이 **`다형성`**이다

### 다형성의 장점

- 새로운 동물이 추가될 경우
  - Animal 클래스 상속받아 **모든 클래스를 Animal 자료형 하나로 관리 가능**
- 상위 클래스에서 공통 부분의 메서드 제공, 하위 클래스는 필요 추가 요소만 추가하면 된다
  - 코드 양도 줄고 유지보수 편리해진다
- 필요에 따라 상속받은 모든 클래스를 **하나의 상위 클래스로 처리 가능**
  - 다형성에 의해 각 클래스의 여러가지 구현을 실행 가능
    - 프로그램을 쉽게 확장 가능

#### 다형성을 활용해 VIP 고객 클래스 완성하기

```java
package inheritance;

public class Customer {
	protected int customerID;
	protected String customerName;
	protected String customerGrade;
	int bonusPoint;		
	double bonusRatio;	
	
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
		//System.out.println("Customer(int, String) 생성자 호출");
	}
	...
}
```

- Customer 생성자에서 공통으로 사용되는 코드를 `initCustomer()` 메서드로 분리하여 호출

```java
public class VIPCustomer extends Customer {
	private int agentID; 	//VIP 고객 상담원 아이디
	double saleRatio;

	public VIPCustomer(int customerID, String customerName, int agentID) {
		super(customerID, customerName);	//상위클래스 생성자 호출
		customerGrade = "VIP";
		bonusRatio = 0.05;
		saleRatio = 0.1;
		this.agentID = agentID;
		//System.out.println("VIPCustomer(int, String, int) 생성자 호출");
	}
	
	@Override
	public int calcPrice(int price) {	//할인율 반영한 가격 반환
		bonusPoint += price * bonusRatio;
		return price - (int)(price * saleRatio);	
	}
	
	@Override
	public String showCustomerInfo() {	//상당원 번호 추가
		return super.showCustomerInfo() + ", 담당 상담원 번호: "+agentID;
	}

	public int getAgentID() {
		return agentID;
	}

}
```

- `calcPrice()`, `showCustomerInfo()`메서드 재정의

```java
public class CustomerTest {

	public static void main(String[] args) {
		Customer customerLee = new Customer();
		customerLee.setCustomerID(10010);
		customerLee.setCustomerName("이순신");
		customerLee.bonusPoint = 1000;
		
		System.out.println(customerLee.showCustomerInfo());
		
		Customer customerKim = new VIPCustomer(10020, "김유신", 12345);
		customerKim.bonusPoint = 1000;
		
		System.out.println(customerKim.showCustomerInfo());
		System.out.println("===== 할인율과 보너스 포인트 계산 =====");
		
		int price = 10000;
		int leePrice = customerLee.calcPrice(price);
		int kimPrice = customerKim.calcPrice(price);
		System.out.println(customerLee.getCustomerName() +" 님이 " + leePrice + "원 지불하셨습니다.");
		System.out.println(customerLee.showCustomerInfo());
		System.out.println(customerKim.getCustomerName() +" 님이 " + kimPrice + "원 지불하셨습니다.");
		System.out.println(customerKim.showCustomerInfo());
		
	}

}
/*
이순신님의 등급: SILVER, 보너스 포인트: 1000
김유신님의 등급: VIP, 보너스 포인트: 1000, 담당 상담원 번호: 12345
===== 할인율과 보너스 포인트 계산 =====
이순신 님이 10000원 지불하셨습니다.
이순신님의 등급: SILVER, 보너스 포인트: 1100
김유신 님이 9000원 지불하셨습니다.
김유신님의 등급: VIP, 보너스 포인트: 1500, 담당 상담원 번호: 12345
*/
```

- `customerLee`, `customerKim` 모두 Customer형으로 선언
  - 자료형은 둘다 같지만 **메서드는 인스턴스에 맞게 호출되었다**

#### 정리

- 상속관계에 있는 상위 클래스와 하위 클래스는 같은 상위 클래스 자료형으로 선언되어 생성할 수 있지만 
  - 재정의된 메서드는 각각 호출된다
  - 이름이 같은 메서드가 서로 다른 역할을 구현하고 있다



## 8-5 다형성 활용하기

- 상속과 다형성을 활용하면 프로그램 유지보수에 편리함
  - 배열을 함께 사용하면 여러 하위 클래스 자료형을 상위 클래스 자료형으로 한꺼번에 관리 가능

### 일반 고객과 VIP 고객의 중간 등급 만들기

> Gold 등급 고객 추가
>
> - 제품 구매시 10% 무조건 할인
> - 보너스 포인트를 2% 적립
> - 담당 전문 상담원은 없다

- GoldCustomer 클래스

  ```java
  package witharraylist;
  
  public class GoldCustomer extends Customer {
  	double saleRatio;
  	
  	public GoldCustomer(int customerID, String customerName) {
  		super(customerID, customerName);
  		customerGrade = "GOLD";
  		bonusRatio = 0.02;
  		saleRatio = 0.1;
  	}
  
  	@Override
  	public int calcPrice(int price) {
  		bonusPoint += price*bonusRatio;
  		return price - (int)(price*saleRatio);
  	}
  }
  ```

  - `calcPrice()`메서드만 재정의했다

#### 배열로 고객 5명 구현하기

> 회사 고객은 5명
> VIP 1명, GOLD 2명, SILVER 2명
> 각각 10,000원짜리 상품을 구매했을 때 결과를 출력

- 고객 인스턴스가 총 5개
  - 배열의 자료형을 Customer로 지정
  - `VIPCustomer`, `GoldCustomer` 모두 Customer형으로 선언
    - 이를 통해 배열은 `VIPCustomer`, `GoldCustomer`, `Customer`모두 사용 가능

- 고객 관리 프로그램 구현

  ```java
  public class CustomerTest {
  	public static void main(String[] args) {
  		
  		ArrayList<Customer> customerList = new ArrayList<Customer>();
  		
  		Customer customerLee = new Customer(10010, "이순신");
  		Customer customerShin = new Customer(10020, "신사임당");
  		Customer customerHong = new GoldCustomer(10030, "홍길동");
  		Customer customerYul = new GoldCustomer(10040, "이율곡");
  		Customer customerKim = new VIPCustomer(10050, "김유신", 12345);
  		
  		customerList.add(customerLee);
  		customerList.add(customerShin);
  		customerList.add(customerHong);
  		customerList.add(customerYul);
  		customerList.add(customerKim);
  		
  		System.out.println("====== 고객 정보 출력 =======");
  		
  		for( Customer customer : customerList){
  			System.out.println(customer.showCustomerInfo());
  		}
  		
  		System.out.println("====== 할인율과 보너스 포인트 계산 =======");
  		
  		int price = 10000;
  		for( Customer customer : customerList){
  			int cost = customer.calcPrice(price);
  			System.out.println(customer.getCustomerName() +" 님이 " +  + cost + "원 지불하셨습니다.");
  			System.out.println(customer.getCustomerName() +" 님의 현재 보너스 포인트는 " + customer.bonusPoint + "점입니다.");
  		}
  	}
  }
  ```

  - 배열에 Customer 하위 클래스의 인스턴스가 추가될 때 모두 **Customer형으로 묵시적 형 변환 이루어졌다**

  - Customer형으로 배열에 저장되었다

    - 각 인스턴스가 `calcPrice()`메서드를 호출하면, 실제 인스턴스에 맞는 메서드를 호출하여 계산한다

      - 이것이 **`다형성`**이다

        > 메서드가 `가상 메서드`방식에 의해 자동으로 호출되지 않는다면
        > if-else if문을 사용하여 구현했어야 됐다
        >
        > 상속과 다형성을 통해 복잡한 코드를 간격하게 줄이고, 확장성 있는 프로그램으로 구현 가능하다

### 상속은 언제 사용할까?

- 고객 등급에 따라 코드를 구현하면

  ```java
  if(customerGrade == "VIP"){}	//할인, 적립 많이
  else if(customerGrade == "GOLD"){}	//할인, 적립 적당히
  else if(customerGrade == "SILVER"){}//적립만 해준다
  ```

- 상속을 사용하면 공통으로 사용되는 코드 부분은 상위 클래스에 구현

  - 등급별 고객의 내용은 하위 클래스에 구현
  - 새로운 등급이 추가되어도 **기존의 코드를 거의 수정하지 않고** 새로운 클래스 추가 가능하다
    - **확장성 있고 유지보수하기 좋다**

#### 상속을 항상 사용하는 것이 좋을까?

- 아니다

- `IS-A 관계(is a relationship; inheritance)`: 일반적인 개념과 구체적인 개념의 관계

  - `사람은 표유류이다`와 같은 관계

- `IS-A 관계`에서 사용하는 것이 가장 효율적

  - **일반 클래스를 점차 구체화하는 상황**에서 상속을 사용

  - 이질적인 클래스 간에서 사용하지 않는 것이 좋다

    > Student와 Subject 클래스의 경우 상속은 옳지 않다
    >
    > Subject가 Student를 포괄하는 개념의 클래스가 아니기 때문이다
    >
    > 이런경우
    > `HAS-A 관계(has a relationship; association)`로 표현한다
    >
    > - `HAS-A 관계`: 한 클래스가 다른 클래스를 소유하는 관계
    >
    >   ```java
    >   class Student{
    >       Subject majorSubject;
    >   }
    >   ```



## 8-6 다운 캐스팅과 instanceof

### 하위 클래스로 형 변환, 다운 캐스팅

- 상위 클래스로 형 변환의 경우
  - `Animal ani = new Human();`
  - Animal 클래스에서 선언한 메서드와 멤버 변수만 사용할 수 있다
  - **필요에 따라 다시 원래 인스턴스의 자료형으로 돌아가야 되는 경우가 있다**
  - `다운 캐스팅`: 상위 클래스로 형 변환되었던 하위 클래스를 **다시 원래 자료형으로 형 변환하는 것**

### instanceof

- `instanceof`: 다운 캐스팅 하기전에 상위 클래스로 형 변환된 인스턴스의 **원래 자료형을 확인하는** 예약어

  ```java
  Animal hAnimal = new Human();
  if(hAnimal instanceof Human){		//hAnimal 인스턴스 자료형이 Human형이라면
      Human human = (Human)hAnimal;	//인스턴스 hAnimal을 Human형으로 다운 캐스팅
  }
  ```

  - 상위 클래스로는 **묵시적으로** 형 변환이 되지만, 하위 클래스로는 **명시적으로** 해야된다

#### 다운 캐스팅 예

```java
package polymorphism;
import java.util.ArrayList;

class Animal{
	public void move() {
		System.out.println("동물이 움직입니다");
	}
}

class Human extends Animal{
	public void move()
	{
		System.out.println("사람이 두 발로 걷습니다. ");
	}
	
	public void readBook()
	{
		System.out.println("사람이 책을 읽습니다. ");
	}
}

class Tiger extends Animal{
	public void move()
	{
		System.out.println("호랑이가 네 발로 뜁니다. ");
	}
	
	public void hunting() 
	{
		System.out.println("호랑이가 사냥을 합니다. ");
	}
}

class Eagle extends Animal{
	public void move()
	{
		System.out.println("독수리가 하늘을 납니다 ");
	}
	
	public void flying() 
	{
		System.out.print("독수리가 날개를 쭉 펴고 멀리 날아갑니다");
	}
}

public class AnimalTest {
	ArrayList<Animal> aniList = new ArrayList<Animal>();
	
	public static void main(String[] args) {
		AnimalTest aTest = new AnimalTest();
		aTest.addAnimal();
		System.out.println("원래 형으로 다운 캐스팅");
		aTest.testCasting();
	}
	
	public void addAnimal() {
		aniList.add(new Human());
		aniList.add(new Tiger());
		aniList.add(new Eagle());
		
		for(Animal ani : aniList)
			ani.move();
	}
	
	public void testCasting() {					//모든 배열 돌면서 다운 캐스팅 진행
		for(int i=0;i<aniList.size();i++) {
			Animal ani = aniList.get(i);
			
			if(ani instanceof Human) {
				Human h = (Human)ani;
				h.readBook();
			}
			else if(ani instanceof Tiger) {
				Tiger t = (Tiger)ani;
				t.hunting();
			}
			else if(ani instanceof Eagle) {
				Eagle e = (Eagle)ani;
				e.flying();
			}
			else {
				System.out.println("지원하지 않는 형식입니다");
			}
		}
	}
}
/*
사람이 두 발로 걷습니다. 
호랑이가 네 발로 뜁니다. 
독수리가 하늘을 납니다 
원래 형으로 다운 캐스팅
사람이 책을 읽습니다. 
호랑이가 사냥을 합니다. 
독수리가 날개를 쭉 펴고 멀리 날아갑니다
*/
```

- Animal 형일때
  - `move()` 메서드를 호출하면 재정의된 메서드가 호출된다
    - **But** Animal형이기에 `readBook()`, `hunting()`, `flying()`을 호출할 수 없다
- 각각의 클래스로 다운 캐스팅했을때
  - **각 클래스에 있는 메서드 호출 가능**



## 연습문제

1. 자바에서 어떤 클래스의 기능을 확장하여 새로운 클래스를 만들기 위해 상속을 합니다. 이때 사용하는 예약어는 `extends`입니다
2. 하위 클래스가 상위 클래스의 생성자를 호출하거나 상위 클래스의 멤버 변수, 메서드를 호출하기위해 사용하는 예약어로 상위 클래스의 주소, 즉 참조 값을 나타내는 예약어는 `super`입니다
3. 클래스를 상속받은 상태에서 상위 클래스에 이미 정의되어 있는 메서드를 하위 클래스에서 사용하기에 적절하지 않은 경우에 해당 메서드를 재정의할 수 있습니다. 이것을 `메서드 오버라이딩`이라고 합니다
4. 상위 클래스에 디폴트 생성자가 없는경우 하위 클래스에서 생성자를 정의하고 super()을 사용하여 상위 클래스의 생성자를 명시적으로 호출해야 된다