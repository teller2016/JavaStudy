# 6장

## 6-1 this 예약어

### 자신의 메모리를 가리키는 this

- ```this```: 인스턴스 스스로를 가리키는 예약어이다

  ```java
  package thisex;
  
  class BirthDay{
  	int day;
  	int month;
  	int year;
  	
  	public void setYear(int year) {
  		this.year = year;			//bDay.year = year;와 같음
  	}
  	public void printThis() {
  		System.out.println(this);	//System.out.println(bDay);와 같음
  	}
  }
  
  public class ThisExample {
  
  	public static void main(String[] args) {
  		BirthDay bDay = new BirthDay();
  		bDay.setYear(2000);
  		System.out.println(bDay);
  		bDay.printThis();
  	}
  }
  ```

  - 인스턴스 가리키는 변수가 **참조 변수**이며 추력하면 ```(클래스 이름)@(메모리 주소)``` 문자열 값이 나온다

  - ```this```값이 참조 변수 ```bDay```를 출력한 값과 같다

    > 스택 메모리
    >
    > setYear() | this           |  에서 this와 bDay는 힙메모리의 같은 BirthDay 인스턴스 주소를 가리키고 있다
    > main()	 | bDay, args|

### 생성자에서 다른 생성자를 호출하는 this

- 클래스에 생성자가 여러개 있을때 어떤 생성자에서 다른 생성자를 호출하는 경우가 있다

  ```java
  class Person{
  	String name;
  	int age;
  	
  	Person(){
  		this("이름 없음", 1);	//this를 사용해 Person(String, int) 생성자 호출
  	}
  	
  	Person(String name, int age){
  		this.name = name;
  		this.age = age;
  	}
  }
  ```

  - 디폴트 생성자에서 **this를 활용**하여 다른 생성자를 호출하였다

### 자신의 주소를 반환하는 this

- this를 사용하여 생성된 클래스의 **주소 값**을 반환할 수 있다

  ```java
  class Person{
  	String name;
  	int age;
  	
  	Person returnItSelf(){	//반환형은 클래스이다
          return this;		//this 반환
      }
  	...
  }
  
  public class CallAnotherConst {
  	public static void main(String[] args) {
  		Person p = noName.returnItSelf();	//this값을 클래스 변수에 대입
  		System.out.println(p);				
  		System.out.println(noName);
  	}
  }
  ```

  

## 6-2 객체간 협력

- 객체 지향 프로그램은 객체를 정의하고 객체 간 협력으로 만든다
- 학생이 버스나 지하철을 타고 학교에 가는 것을 객체 지향으로 프로그래밍 해본다

### 학생 클래스 구현하기

- 학생 클래스는 '이름', '학년', '가진 돈'을 **멤버 변수(속성)**로 가진다

- '버스를 탄다', '지하철을 탄다', '학생의 현재 정보를 보여준다'를 **메서드(멤버 함수)**로 가진다

  ```java
  package cooperation;
  
  public class Student {
  	public String studentName;
  	public int grade;
  	public int money;
  	
  	public Student(String studentName, int money) {
  		this.studentName = studentName;
  		this.money = money;
  	}
  	
  	public void takeBus(Bus bus) {
  		bus.take(1000);
  		this.money -= 1000;
  	}
  	
  	public void takeSubway(Subway subway) {
  		subway.take(1500);
  		this.money -= 1500;
  	}
  	
  	public void showInfo() {
  		System.out.println(studentName+"님의 남은 돈은"+money);
  	}
  }
  ```

### 버스 클래스 구현하기

- 학생 한명이 승차하면 버스요금을 받고 승객 수가 증가한다

  ```java
  public class Bus {
  	int busNumber;
  	int passengerCount;
  	int money;
  	
  	public Bus(int busNumber) {
  		this.busNumber = busNumber;
  	}
  	
  	public void take(int money) {
  		this.money += money;
  		passengerCount++;
  	}
  	
  	public void showInfo() {
  		System.out.println("버스 "+busNumber+"번의 승객은 "+passengerCount+"명이고,"
  				+ "수입은 "+money+"입니다");
  	}
  }
  ```

### 지하철 클래스 구현하기

- 버스와 유사

  ```java
  public class Subway {
  	int lineNumber;
  	int passengerCount;
  	int money;
  	
  	public Subway(int lineNumber) {
  		this.lineNumber = lineNumber;
  	}
  	
  	public void take(int money) {
  		this.money += money;
  		passengerCount++;
  	}
  	
  	public void showInfo() {
  		System.out.println(lineNumber+"의 승객은 "+passengerCount+"명이고,"
  				+ "수입은 "+money+"입니다");
  	}
  }
  ```

### 학생, 버스, 지하철 객체 협력하기

- 학생 James, Tomas 둘이 있고, 각각 버스, 지하철을 타고 학교에 간다

  ```java
  public class TakeTrans {
  
  	public static void main(String[] args) {
  		Student studentJames = new Student("James", 5000);
  		Student studentTomas = new Student("Tomas", 10000);
  		
  		Bus bus100 = new Bus(100);
  		studentJames.takeBus(bus100);	//james가 100번 버스를 탐
  		studentJames.showInfo();
  		bus100.showInfo();
  		
  		Subway subwayGreen = new Subway("2호선");
  		studentTomas.takeSubway(subwayGreen);
  		studentTomas.showInfo();
  		subwayGreen.showInfo();
  	}
  }
  /*
  James님의 남은 돈은4000
  버스 100번의 승객은 1명이고,수입은 1000입니다
  Tomas님의 남은 돈은8500
  2호선의 승객은 1명이고,수입은 1500입니다
  */
  ```

  - 학생, 버스, 지하철 객체간 협력이 이루어졌다



## 6-3 static 변수

### 변수를 여러 클래스에서 공통으로 사용하려면?

- 학생을 예로, 학생 인스턴스가 생성될때 고유한 학번을 부여하기 위해 **공통으로 사용할 수 있는 기준 변수가 있어야된다**
  - 학생 한명이 생성될 때마다 기준 변수 값을 하나씩 증가시켜주면 된다
- ```static 변수```: 클래스에서 공통으로 사용하는 변수

### static 변수의 정의와 사용 방법

- static 변수란 다른 용어로 ```정적 변수```, ```클래스 변수```라고도 한다

  - **클래스 내부**에 선언한다

  - **static** 예약어를 사용

    > static int serialNum;

  - 클래스 내부에 선언하지만, 다른 멤버 변수처럼 인스턴스가 생성될 때마다 새로 생성되지 않는다

  - static변수는 **프로그램이 실행**되어 메모리에 올라갔을 때 **딱 한번 메모리 공간이 할당된다**

  - static 변수 값은 **모든 인스턴스가 공유**한다

- 새로운 학생에게 학번을 차례로 부여하는 예제를 통해 static 변수 사용

  ```java
  package staticex;
  
  public class Student {
  	public static int serialNum = 1000;	//static 변수 사용
  	public int studentID;
  	public String studentName;
  	public int grade;
  	public String address;
  	
  	public String getStudentName() {
  		return studentName;
  	}
  
  	public void setStudentName(String name) {
  		studentName = name;
  	}
  }
  ```

  - **static 변수는 인스턴스 생성과 상관없이 먼저 생성된다**

  ```java
  public class StudentTest1 {
  
  	public static void main(String[] args) {
  		Student studentLee = new Student();
  		studentLee.setStudentName("이지원");
  		System.out.println(studentLee.serialNum); // 초깃값 출력
  		studentLee.serialNum++;					// static 변수 증가
  		
  		Student studentSon = new Student();
  		studentSon.setStudentName("손수경");
  		System.out.println(studentSon.serialNum); // 증가된 값 출력
  		System.out.println(studentLee.serialNum); // 증가된 값 출력
  	}
  }
  /*
  1000
  1001
  1001
  */
  ```

  - 학생 둘다 1001로 증가하였다. 즉 두 개의 참조 변수가 **동일한 변수의 메모리를 가리키고 있다는 것**이다

### 학번 생성하기

- 학번 자동 부여 프로그램

  ```java
  public class Student1 {
  	public static int serialNum = 1000;
  	public int studentID;
  	public String studentName;
  	public int grade;
  	public String address;
  	
  	public Student1() {
  		serialNum++;			//학생이 생성될때 마다 증가
  		studentID = serialNum;	//증가된 값을 학번 변수에 부여
  	}
  	
  	public String getStudentName() {
  		return studentName;
  	}
  
  	public void setStudentName(String name) {
  		studentName = name;
  	}
  }
  ```

  - 학생이 생성될 때마다 static 변수값이 증가

  ```java
  public class StudentTest2 {
  
  	public static void main(String[] args) {
  		Student1 studentLee = new Student1();
  		studentLee.setStudentName("이지원");
  		System.out.println(studentLee.serialNum);
  		System.out.println(studentLee.studentName + " 학번:"  
  		                        + studentLee.studentID);
  		
  		Student1 studentSon = new Student1();
  		studentSon.setStudentName("손수경");
  		System.out.println(studentSon.serialNum);
  		System.out.println(studentSon.studentName + " 학번:" 
  		                        + studentSon.studentID);
  	}
  
  }
  /*
  1001
  이지원 학번:1001
  1002
  손수경 학번:1002
  */
  ```

  - static 변수값을 복사 저장해, 서로 다른 학번을 저장한다

### 클래스 변수

- static 변수는 클래스를 선언할 때 특정 메모리에 저장되어 모든 인스턴스가 공유하는 변수이다.

- 즉, static 변수는 인스턴스보다 **먼저 생성된다**

- **인스턴스가 아닌 클래스 이름으로도 참조하여 사용할 수 있다** 그래서 **```클래스 변수```**라고도 한다

  ```java
  public class StudentTest2 {
  
  	public static void main(String[] args) {
  		Student1 studentLee = new Student1();
  		studentLee.setStudentName("이지원");
  		//System.out.println(studentLee.serialNum);
          System.out.println(Student1.serialNum);				//serialNum 변수를 직접 클래스 이름으로 참조
  		System.out.println(studentLee.studentName + " 학번:"  
  		                        + studentLee.studentID);
  	}
  }
  ```

  - ```studentLee.serialNum```와 같이 인스턴스로 참조 가능
  - ```Student1.serialNum```와 같이 클래스 이름으로 참조 가능

### 클래스 메서드

