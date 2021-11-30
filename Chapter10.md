# 10장 인터페이스

## 10-1 인터페이스란?

### 구현 코드가 없는 인터페이스

- `인터페이스(interface)`는 클래스 혹은 프로그램이 제공하는 기능을 명시적으로 선언하는 역할을 한다
  - 인터페이스는 **추상 메서드와 상수**로만 이루어져 있다
  - 인스턴스를 생성할 수 없다

#### 인터페이스 만들기

- [package 우클릭 => New => Interface]

- 계산기 interface 만들기

  ```java
  package interfaceex;
  
  public interface Calc {
  	double PI = 3.14;		//컴파일 과정에서 상수로 변환됨
  	int ERROR = -999999999;
  	
  	int add(int num1, int num2);		//컴파일 과정에서 추상 메서드로 변환됨
  	int substract(int num1, int num2);
  	int times(int num1, int num2);
  	int divide(int num1, int num2);
  }
  ```

  - 인터페이스에서 선언한 메서드는 모두 구현 코드가 없는 **추상 메서드**이다
    - `public abstract` 예약어 없어도 자동으로 변환
  - 인터페이스에서 선언한 변수는 모두 **상수**로 자동 변환된다
    - `public final` 예약어어 없이도 자동으로 변환

### 클래스에서 인터페이스 구현하기

- `클래스에서 인터페이스를 구현한다(implements)` == 선언한 인터페이스를 클래스가 사용 하는것

- `implements` 예약어를 통해 인터페이스에 선언한 기능을 클래스가 구현

  ```java
  public class Calculator implements Calc {	//오류 발생
  
  }
  ```

  - **추상 메서드**를 구현하지 않아 오류 발생
    - 추상 메서드를 구현하거나, Calculator을 추상 클래스로 만들어야 된다
  - 메서드 2개만 선언하고, **추상 클래스**로 만든다

  ```java
  public abstract class Calculator implements Calc {
  
  	@Override
  	public int add(int num1, int num2) {
  		return num1 + num2;
  	}
  
  	@Override
  	public int substract(int num1, int num2) {
  		return num1 - num2;
  	}
  }
  ```

  - `Calculator`은 추상 클래스이다

  - 클래스 다이어그램에서 **인터페이스를 구현한 것은 점선**으로 표시

    > [Calculator] - - - > [Calc]

### 클래스 완성하고 실행하기

- 모든 메서드를 구현한 클래스

  ```java
  public class CompleteCalc extends Calculator {
  	@Override
  	public int times(int num1, int num2) {
  		return num1 * num2;
  	}
  
  	@Override
  	public int divide(int num1, int num2) {
  		if(num2!=0)
  			return num1/num2;
  		else
  			return Calc.ERROR;
  	}
  	
  	public void showInfo() {
  		System.out.println("Calc 인터페이스를 구현했다");
  	}
  }
  ```

- 테스트 프로그램

  ```java
  public class CalculatorTest {
  
  	public static void main(String[] args) {
  		int num1 = 10;
  		int num2 = 5;
  		
  		CompleteCalc calc = new CompleteCalc();
  		System.out.println(calc.add(num1, num2));
  		System.out.println(calc.substract(num1, num2));
  		System.out.println(calc.times(num1, num2));
  		System.out.println(calc.divide(num1, num2));
  		calc.showInfo();
  	}
  }
  /*
  15
  5
  50
  2
  Calc 인터페이스를 구현했다
  */
  ```

  - 구체적인 클래스인 `CompleteCalc` 클래스만 인스턴스 생성가능

### 인터페이스 구현과 형 변환

> [CompleteCalc] ---> [*Calculator*] - - -> [*Calc*]

- 이러한 관계에서 하위클래스의 형 변환

  ```java
  Calc calc = new CompleteCalc();
  ```

  - `CompleteCalc` 클래스는 상위 클래스인 `Calculator`형이면서 `Calc`형이기도 하다
  - `calc` 인스턴스는 `Calc`에서 선언한 추상 메서드`add(), substract(), times(), divide()`는 사용 가능
    - `CompleteCalc()`에서 선언한 `showInfo()`메서드는 사용 불가능

  ```java
  public static void main(String[] args) {
  		int num1 = 10;
  		int num2 = 5;
  		
  		Calc cal = new CompleteCalc();
  		System.out.println(cal.add(num1, num2));
  	}
  // 15
  ```

  

## 10-2 인터페이스와 다형성

### 인터페이스의 역할

- 인터페이스는 클라이언트 프로그램에 어떤 메서드를 제공하는 미리 알려주는 **명세(specification) 또는 약속의 역할**을 한다

  > EX
  >
  > Abc 인터페이스를 구현한 클래스 A가 있다
  > 이 클래스를 사용하는 프로그램 Z가 있다
  >
  > Z 프로그램은 A 클래스의 구현 코드를 보지 않고
  > Abc 인터페이스의 선언부만 보고 A를 어떻게 사용할지 알 수 있다
  >
  > Abc 인터페이스를 구현한 클래스 B도 있다
  > Z 프로그램에서 B를 사용하고 싶으면 인터페이스에 따라 A에서 B로 교체해서 사용가능
  >
  > ```java
  > Abc abc;
  > abc = new A();
  > abc = new B();		// A에서 B로 교체
  > ```

### 인터페이스와 다형성

- 인터페이스를 사용한 다형성 구현

  > **고객 상담 전화 배분 프로그램**
  >
  > 고객 센터에 있는 상담원에게 전화를 분배하는 정책 3가지가 있다
  >
  > 1. 순서대로 분배: 상담원이 동일한 건수 처리
  > 2. 짧은 대기열 찾아 분배: 적은 대기열 보유한 상담원에 분배
  > 3. 우선순위에 따라 분배: 고객 등급에 따라 높은 고객의 전화를 우선 가져온다

- Scheduler 인터페이스

  ```java
  package scheduler;
  
  public interface Scheduler {
  	public void getNextCall();		//다음 전화 가져오는 기능
  	public void sendCallToAgend();	// 상담원에게 전화 배분하는 기능
  }
  ```

  - 추상 메서드 2개 선언

- RoundRobin 클래스: 순서대로 배분

  ```java
  //상담원 한명씩 돌아가며 동일하게 상담업무를 배분합니다.
  public class RoundRobin implements Scheduler{
  	@Override
  	public void getNextCall() {
  		System.out.println("상담 전화를 순서대로 대기열에서 가져옵니다");
  		
  	}
  	@Override
  	public void sendCallToAgent() {
  		System.out.println("다음 순서 상담원에게 배분합니다.");
  		
  	}
  }
  ```

- LeastJob 클래스

  ```java
  // 현재 상담업무가 없거나 상담대기가 가장 작은 상담원에게 배분합니다.
  public class LeastJob implements Scheduler{
  	@Override
  	public void getNextCall() {
  		System.out.println("상담 전화를 순서대로 대기열에서 가져옵니다");
  		
  	}
  	@Override
  	public void sendCallToAgent() {
  		System.out.println("현재 상담업무가 없거나 상담대기가 가장 작은 상담원에게 할당합니다.");
  		
  	}
  }
  ```

- PriorityAllocation 클래스

  ```java
  // 고객등급이 높은 고객부터 대기열에서 가져와 업무 능력이 높은 상담원 우선으로 배분합니다.
  public class PriorityAllocation implements Scheduler{
  	@Override
  	public void getNextCall() {
  		System.out.println("고객 등급이 높은 고객의 전화를 먼저 가져옵니다.");
  		
  	}
  	@Override
  	public void sendCallToAgent() {
  		System.out.println("업무 skill 값이 높은 상담원에게 우선적으로 배분합니다.");
  		
  	}
  }
  ```

- 프로그램 실행

  ```java
  public class SchedulerTest {
  
  	public static void main(String[] args) throws IOException {	//System.in.read()사용을 위한 오류 처리
  		System.out.println("전화 상담 할당 방식을 선택 하세요.");
  		System.out.println("R : 한명씩 차례로 할당 ");
  		System.out.println("L : 쉬고 있거나 대기가 가장 적은 상담원에게 할당 ");
  		System.out.println("P : 우선순위가 높은 고객 먼저 할당 ");
  		
  		int ch = System.in.read();		//할당 방식을 입력받아 ch 변수에 대입
  		Scheduler scheduler = null;
  		
  		if(ch == 'R' || ch == 'r'){
  			scheduler = new RoundRobin();
  		}
  		else if(ch == 'L' || ch == 'l'){
  			scheduler = new LeastJob();
  		}
  		else if(ch == 'P'|| ch == 'p'){
  			scheduler = new PriorityAllocation();
  		}
  		else{
  			System.out.println("지원되지 않는 기능입니다.");
  			return;
  		}
  		
  		scheduler.getNextCall();
  		scheduler.sendCallToAgent();
  	}
  
  }
  /*
  전화 상담 할당 방식을 선택 하세요.
  R : 한명씩 차례로 할당 
  L : 쉬고 있거나 대기가 가장 적은 상담원에게 할당 
  P : 우선순위가 높은 고객 먼저 할당 
  L
  상담 전화를 순서대로 대기열에서 가져옵니다
  현재 상담업무가 없거나 상담대기가 가장 작은 상담원에게 할당합니다.
  */
  ```

### 클라이언트가 클래스를 사용하는 방법

> 위 프로그램을 어느 회사에 판매하려는데 새로운 기능을 원한다
>
> - Scheduler 인터페이스를 구현하는 새로운 클래스를 만들면 해결된다
>
>   - 어떠한 클래스를 구현하건 사용 방법은
>
>     ```java
>     scheduler.getNextCall();
>     scheduler.sendCallToAgent();
>     ```

- 클라이언트 프로그램은 각 클래스의 구현 방법을 몰라도 인터페이스에서 선언된 매개변수, 반환값을 보고 클래스 사용 가능



## 10-3 인터페이스 요소 살펴보기

### 인터페이스 상수

- 인터페이스는 추상 메서드로 이루어져

  - 인스턴스 생성 못함

  - 멤버 변수 사용 못함

  - 그러나 **변수 선언해도 오류 발생하지 않는다**

    ```java
    public interface Calc{
        double PI = 3.14;
        int ERROR = -99999999;
    }
    ```

    - 컴파일하면 상수로 변환된다
      `public static final double PI = 3.14` 즉 상수로 변환

### 디폴트 메서드와 정적 메서드

- 자바8부터 인터페이스에 **`디폴트 메서드`와 `정적 메서드` 기능 제공**
  - `디폴트 메서드`: 인터페이스에 구현 코드까지 작성한 메서드
  - `정적 메서드`: 인스턴스 생성과 상관없이 사용할 수 있는 메서드
    - **BUT** 인터페이스가 인스턴스 생성할 수 있는건 아니다

### 디폴트 메서드

- 기본으로 제공되는 메서드

- `default` 예약어 사용

  ```java
  package interfaceex;
  
  public interface Calc {
  	...
  	default void description() {
  		System.out.println("정수 계산기를 사용합니다");
  	}
  }
  ```

  - 메서드 자료형 앞에 `default` 예약어만 쓰면 된다

- 디폴트 메서드 호출하기

  ```java
  public class CalculatorTest {
  
  	public static void main(String[] args) {
  		int num1 = 10;
  		int num2 = 5;
  		
  		CompleteCalc calc = new CompleteCalc();
  		...
  		calc.description();		//디폴트 메서드 호출
  	}
  
  }
  // ... 정수 계산기를 사용합니다
  ```

#### 디폴트 메서드 재정의하기

- 인터페이스에 구현된 `디폴트 메서드`가 새로 생성한 클래스에서 다른 기능을 해야된다면

  - 하위 클래스에서 **디폴트 메서드 재정의할 수 있다**

    ```java
    public class CompleteCalc extends Calculator {
    	...
    	@Override
    	public void description() {
    		// TODO Auto-generated method stub
    		super.description();
    	}
    }
    ```

### 정적 메서드

- `static` 예약어를 사용하여 선언

  - **클래스 생성과 무관하게 사용할 수 있다**

  ```java
  public interface Calc {
  	...
  	static int total(int[] arr) {
  		int total = 0;
  		
  		for(int i : arr)
  			total += i;
  		return total;
  	}
  }
  ```

- 정적 메서드 호출하기

  ```java
  public class CalculatorTest {
  
  	public static void main(String[] args) {
  		...
  		int[] arr = {1,2,3,4,5,6};
  		System.out.println(Calc.total(arr));	//정적 메서드 사용
  	}
  
  }
  ```

  - `Calc.total(arr)`처럼 **인터페이스 이름으로 직접 참조**하여 정적 메서드 호출
    - **인스턴스는 생성 못한다** / **직접 참조했다**

### private 메서드

- 인터페이스를 구현한 클래스에서 **사용하거나 재정의할 수 없다**
  - **기존에 구현된 코드를 변경하지 않고 구현한 클래스**에서 공통으로 사용하는 경우에 private 메서드 사용
    - 코드 재사용성을 높인다

- private 메서드는 코드를 모두 구현해야 하므로 추상 메서드에서 `private` 예약어 사용 못한다
  - `static`예약어와 함께 사용 가능
  - **`private static`메서드는 `정적 메서드`에서 호출하여 사용한다**

- `private` 메서드는 `디폴트 메서드`에 / `private static` 메서드는 `정적 메서드`에 사용(?)

  ```java
  public interface Calc {
  	...
  	default void description() {
  		System.out.println("정수 계산기를 사용합니다");
  		myMethod();		//디폴트 메서드에서 private 메서드 호출
  	}
  	
  	static int total(int[] arr) {
  		int total = 0;
  		
  		for(int i : arr)
  			total += i;
  		myStaticMethod();		//정적 메서드에서 private static 메서드 호출
  		return total;
  	}
  	
  	private void myMethod() {	//private 메서드
  		System.out.println("private 메서드입니다");
  	}
  	
  	private static void myStaticMethod() {	//private static 메서드
  		System.out.println("private static 메서드입니다");
  	}
  }
  ```

  

## 10-4 인터페이스 활용하기

### 한 클래스가 여러 인터페이스를 구현하는 경우

- 한 클래스가 여러 클래스를 상속받으면 메서드 호출이 모호해지는 문제 발생
  - **BUT** 인터페이스는 **한 클래스가 여러 인터페이스를 구현할 수 있다**

> [Customer(실제 구현 클래스)] - - ->[Buy(인터페이스)], [Sell(인터페이스)]

- Buy, Sell 인터페이스

  ```java
  package interfaceex;
  
  public interface Buy {
  	void buy();
  }
  ```

  ```java
  public interface Sell {
  	void sell();
  }
  ```

- Customer 클래스

  ```java
  public class Customer implements Buy, Sell {	// Buy, Sell 인터페이스를 모두 구현함
  
  	@Override
  	public void sell() {
  		System.out.println("판매하기");
  	}
  	@Override
  	public void buy() {
  		System.out.println("구매하기");
  	}
  }
  ```

  - `Customer`클래스는 `Buy`, `Sell` 인터페이스를 **모두 구현했다**

- 인터페이스는 구현 코드나 멤버 변수를 가지지 않기 때문에 **여러 개를 동시에 구현 가능하다**

  - 두 인터페이스에 이름이 같은 메서드가 선언되어도 구현은 클래스에서 이루어져, 어떤 메서드를 호출해야 하는지 모호하지 않다

  ```java
  public class CustomerTest {
  	public static void main(String[] arg) {
  		Customer customer = new Customer();
  		
  		Buy buyer = customer;
  		buyer.buy();	//Buy 인터페이스의 메서드만 호출 가능
  		
  		Sell seller = customer;
  		seller.sell();	//Sell 인터페이스의 메서드만 호출 가능
  		
  		if(seller instanceof Customer) {
  			Customer customer2 = (Customer)seller;
  			customer2.buy();
  			customer2.sell();
  		}
  	}
  }
  /*
  구매하기
  판매하기
  구매하기
  판매하기
  */
  ```

  - `Customer` 클래스형인 customer을 `Buy`, `Sell` 인터페이스형 `buyer`, `seller`에 대입하였다
    - `buyer`, `seller`은 각 인터페이스의 메서드만 호출 가능
      - 즉 두개의 인터페이스에 중복된 메서드 이름있어도 호출가능

### 두 인터페이스의 디폴트 메서드가 중복되는 경우

- **정적 메서드**의 경우 인스턴스 생성과 상관없이 사용 가능

- 정적 메서드 `pay()` 추가

  - 정적 메서드이기에 `Buy.pay()`, `Sell.pay()`와 같이 호출 가능

- 디폴트 메서드 중복 경우

  ```java
  public interface Buy {
  	void buy();
      default void order(){
          System.out.println("구매 주문");
      }
  }
  ```

  ```java
  public interface Sell {
  	void sell();
      default void order(){
          System.out.println("판매 주문");
      }
  }
  ```

  - 디폴트 메시가 중복되니 재정의하라는 오류 발생

    ```java
    public class Customer implements Buy, Sell {
    
    	@Override
    	public void sell() {
    		System.out.println("판매하기");
    	}
    
    	@Override
    	public void buy() {
    		System.out.println("구매하기");
    	}
    
    	@Override
    	public void order() {
    		System.out.println("고객 판매 주문");
    	}
    
    }
    ```

  ```java
  public class CustomerTest {
  	public static void main(String[] arg) {
  		Customer customer = new Customer();
  		
  		Buy buyer = customer;
  		buyer.buy();
  		buyer.order();	//재정의된 메서드 호출됨
  		
  		Sell seller = customer;
  		seller.sell();
  		seller.order();	//재정의된 메서드 호출됨
  		
  		if(seller instanceof Customer) {
  			Customer customer2 = (Customer)seller;
  			customer2.buy();
  			customer2.sell();
  			customer2.order();	//재정의된 메서드 호출됨
  		}
  	}
  }
  /*
  구매하기
  고객 판매 주문
  판매하기
  고객 판매 주문
  구매하기
  판매하기
  고객 판매 주문
  */
  ```

  - `Buy`형, `Sell`형이어도 **`가상 메서드`**원리에 의해 **`Customer`클래스 인스턴스의 `order()`메서드가 호출됐다**

### 인터페이스 상속하기

- 인터페이스간에도 상속이 가능
- 인터페이스간 상속은 **기능을 상속하는 것이 아니므로** `형 상속(type inheritance)`라고 부른다
- 인터페이스는 **여러 개를 동시에** 상속받을 수 있다
  - 클래스는 하나의 클래스만 상속받을 수 있다
  - 상속받은 인터페이스는 **상위 인터페이스에 선언한 추상 메서드를 모두 가지게 된다**

> [`MyClass`] - - -> [*`MyInterface`*] ---> [*`X`*]  ,  [*`Y`*]
> 						     [*`myMethod()`*]	     [*`x()`*]   [*`y()`*]
>
> - `MyInterface`는 `X, Y` 인터페이스를 상속받는다
> - `MyClass`는 `MyInterface` 인터페이스를 구현한다
>   - `MyClass`는 추상 메서드 `x() y() myMethod()` 3개를 구현해야 된다

- `X, Y` 인터페이스

  ```java
  package interfaceex;
  
  public interface X {
  	void x();
  }
  ```

  ```java
  public interface Y {
  	void y();
  }
  ```

- `X, Y` 상속받는 `MyInterface` 인터페이스

  ```java
  public interface MyInterface extends X,Y {	//인터페이스 여러개를 상속받을 수 있다
  	void myMethod();
  }
  ```

- `MyInterface`를 구현하는 `MyClass` 클래스

  ```java
  public class MyClass implements MyInterface{
  
  	@Override
  	public void x() {
  		System.out.println("x()");
  	}
  
  	@Override
  	public void y() {
  		System.out.println("y()");
  	}
  
  	@Override
  	public void myMethod() {
  		System.out.println("myMethod()");		
  	}
  }
  ```

- 실행 프로그램

  ```java
  public class MyClassTest {
  
  	public static void main(String[] args) {
  		MyClass mClass = new MyClass();
  		
  		X xClass = mClass;
  		xClass.x();		//X에 선언한 메서드만 호출 가능
  		
  		Y yClass = mClass;
  		yClass.y();		//Y에 선언한 메서드만 호출 가능
  		
  		MyInterface iClass = mClass;
  		iClass.myMethod();	//인터페이스가 상속한 모든 메서드 사용 가능
  		iClass.x();
  		iClass.y();
  	}
  }
  /*
  x()
  y()
  myMethod()
  x()
  y()
  */
  ```

  - 상위 인터페이스형으로 클래스 형을 변환 가능
    - 상위 인터페이스형으로 변환하면 **해당 인터페이스에서 선언한 메서드만 사용 가능**

### 인터페이스구현과 클래스 상속 함께 쓰기

- 한 클래스에서 클래스 상속과 인터페이스 구현을 모두 할 수 있다

> [`BookShelf`] ---> [`Shelf`]
> 					  ] - - -> [*`Queue`*]
>
> - `Queue`인터페이스를 구현하며 `Shelf`클래스를 상속받는 `BookShelf`클래스
>   - `BookShelf`: 책을 넣은 순서대로 꺼내어 볼 수 있다

- `Shelf`클래스

  ```java
  package bookshelf;
  import java.util.ArrayList;
  
  public class Shelf {
  	protected ArrayList<String> shelf;
  	
  	public Shelf() {
  		shelf = new ArrayList<String>();	//클래스 생성시 ArrayList 생성
  	}
  	
  	public ArrayList<String> getShelf(){
  		return shelf;
  	}
  	
  	public int getCount() {
  		return shelf.size();
  	}
  }
  ```

- `Queue` 인터페이스

  ```java
  public interface Queue {
  	void enQueue(String title);	//배열 맨 마지막에 추가
  	String deQueue();	//배열 맨 처음 항목 반환
  	int getSize();	//Queue에 있는 개수 반환
  }
  ```

- `BookShelf` 클래스

  ```java
  public class BookShelf extends Shelf implements Queue {
  
  	@Override
  	public void enQueue(String title) {
  		shelf.add(title);
  	}
  
  	@Override
  	public String deQueue() {
  		return shelf.remove(0);
  	}
  
  	@Override
  	public int getSize() {
  		return getCount();
  	}
  }
  ```

  - FIFO 구현했다
    - 입력 순서대로 값이 출력된다

- 실행 테스트 프로그램

  ```java
  public class BookShelfTest {
  
  	public static void main(String[] args) {
  
  		Queue shelfQueue = new BookShelf();
  		shelfQueue.enQueue("책 1");
  		shelfQueue.enQueue("책 2");
  		shelfQueue.enQueue("책 3");
  		
  		System.out.println(shelfQueue.deQueue());
  		System.out.println(shelfQueue.deQueue());
  		System.out.println(shelfQueue.deQueue());
  		
  	}
  }
  /*
  책 1
  책 2
  책 3
  */
  ```

### 실무에서 인터페이스를 사용하는 경우

- 인터페이스는 클래스가 제공할 기능을 선언하고 설계하는 것
- 여러 클래스가 **같은 메서드를 서로 다르게** 구현해야될 경우
  - 인터페이스에 메서드를 선언 후
    - 인터페이스를 구현한 각 클래스에서 **같은 메서드에 대해 다양한 기능을 구현**하면 된다
    - 이것이 **인터페이스를 이용한 다형성 구현**

> 회사에서 데이터베이스를 사용하는 시스템을 개발했는데
> 어떤회사는 MySQL을 사용, 다른 회사는 오라클, MS-SQL 사용한다
>
> 프로그램은 하나인데 사용하는 데이터베이스가 제각각이다
> 프로그램의 웹, 모바일 페이지는 데이터베이스와 관계없이 수행된다
>
> 즉, 데이터베이스와 연관된 코드는 프로그램의 특정 부분인 것이다
>
> 이런 경우 데이터베이스 기능을 수행할 인터페이스를 정의한다
>
> > [`OracleDao`],[`MysqlDao`],[`MssqlDao`] - - -> [`UserInfoDao`]
> >                                [`UserInfoWeb`] --(사용)-->
>
> - 사용자 정보를 데이터베이스에 입력, 업데이트, 삭제 기능은 `UserInfoDao` 인터페이스에서 정의
> - 각 데이터베이스에 맞게 구현은 [`OracleDao`],[`MysqlDao`],[`MssqlDao`] 클래스가 담당

- 인터페이스를 잘 정의하는 것이 **확장성 있는 프로그램**을 만드는 것이다



## 연습문제

1. 클래스가 인터페이스를 구현하기 위해 사용하는 예약어는 `implements`이다

2. 클래스가 인터페이스를 구현할 때 인터페이스에 선언한 메서드를 모두 구현하지 않으면 그 클래스는 `추상 클래스`가 된다

3. 인터페이스에 선언한 변수는 컴파일할 때 `상수`로 변환된다

4. 한 인터페이스를 여러 클래스가 다양한 방식으로 구현한 경우에 프로그램에서 인터페이스에 선언된 메서드를 사용할 때 각 클래스의 구현 내용과 상관없이 동일한 방식으로 사용할 수 있습니다. 이렇게 같은 코드가 여러 구현 내용으로 실행되는 객체 지향 특성을 `다형성`이라고 한다

5. 인터페이스에서 구현 코드를 제공하는 메서드는 `디폴트 메서드`와 `정적 메서드`이다

6. 한 클래스에서 여러 인터페이스를 구현할 수 `있다`

7.  숫자 정렬 알고리즘 인터페이스 구현

   ```java
   package sorting;
   
   public interface Sort {
   	void ascending(int[] arr);
   	void descending(int[] arr);
   	
   	default void description() {
   		System.out.println("숫자를 정렬하는 알고리즘 입니다");
   	}
   }
   ```

   ```java
   public class QuickSort implements Sort {
   
   	@Override
   	public void ascending(int[] arr) {
   		System.out.println("QuickSort ascending");
   	}
   
   	@Override
   	public void descending(int[] arr) {
   		System.out.println("QuickSort descending");
   	}
   
   	@Override
   	public void description() {
   		System.out.println("QuickSort를 사용해");
   		Sort.super.description();
   	}
   }
   ```

   - 나머지 `HeapSort`, `BubbleSort`도 동일한 형태

   ```java
   package sorting;
   import java.io.IOException;
   
   public class SortTest {
   
   	public static void main(String[] args) throws IOException {
   		System.out.println("정렬방식을 선택 하세요.");
   		System.out.println("B : BubbleSort ");
   		System.out.println("H : HeapSort ");
   		System.out.println("Q : QuickSort ");
   		
   		int ch = System.in.read();
   		Sort sort = null;
   		
   		if(ch == 'B' || ch == 'b'){
   			sort = new BubbleSort();
   		}
   		else if(ch == 'H' || ch == 'h'){
   			sort = new HeapSort();
   		}
   		else if(ch == 'Q'|| ch == 'q'){
   			sort = new QuickSort();
   		}
   		else{
   			System.out.println("지원되지 않는 기능입니다.");
   			return;
   		}
   		
   		int[] arr = new int[10];
   		sort.ascending(arr);
   		sort.descending(arr);
   		sort.description();
   	}
   }
   /*
   정렬방식을 선택 하세요.
   B : BubbleSort 
   H : HeapSort 
   Q : QuickSort 
   b
   BubbleSort ascending
   BubbleSort descending
   BubbleSort를 사용해
   숫자를 정렬하는 알고리즘 입니다
   */
   ```

   

   