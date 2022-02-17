# 11장 - 기본 클래스

## 11-1 Object 클래스

### java.lang 패키지

- `java.lang` 패키지에는 **기본적으로 많이 사용하는 클래스들이 포함된다**

- `String`, `Integer`와 같은 클래스들은 `java.lang` 패키지에 속해있다
  - `String`의 전체 이름은 `java.lang.String`이다
- 자바 프로그래밍에서 외부 패키지를 선언하기 위해 `import`문으로 선언한다
  - `java.lang`의 경우 컴파일시 자동으로 `import java.lang.*`문장이 추가된다

### 모든 클래스의 최상위 클래스 Object

- `Object`클래스는 **모든 자바 클래스의 최상위 클래스이다**

  - 컴파일 과정에서 `extends Object`가 자동으로 쓰인다

    ```java
    class Student{}	//코드 작성시
    ```

    ```java
    class Student extends Object{}	//컴파일러가 변환
    ```

  - 모든 클래스는 `Object` 클래스의 메서드를 사용, 재정의할 수 있다

    - 모든 메서드를 재정의하진 못한다. final 예약어로 선언한 메서드는 재정의 불가능

    - 주로 사용되는 메서드

      | 메서드                     | 설명                                                         |
      | -------------------------- | ------------------------------------------------------------ |
      | String toString()          | 객체를 문자열로 표현하여 반환. 재정의하여 객체에 대한 설명이나 특정 변수 값을 반환 |
      | boolean equals(Object obj) | 두 인스턴스가 동일한지 여부를 반환. 재정의하여 논리적으로 동일한 인스턴스임을 정의할 수 있다 |
      | int hashCode()             | 객체의 해시 코드 값을 반환                                   |
      | Object clone()             | 객체를 복제하여 동일한 멤버 변수 값을 가진 새로운 인스턴스를 생성 |
      | Class getClass()           | 객체의 Class 클래스를 반환                                   |
      | void finalize()            | 인스턴스가 힙메모리에서 제거될 때 가비지 컬렉터(GC)에 의해 호출되는 메서드. <br />네트워크 연결 해제, 열려 있는 파일 스트림 해제 등을 구현 |
      | void wait()                | 멀티스레드 프로그램에서 사용하는 메서드. 스레드를 '기다리는 상태(non runnalbe)'로 만든다 |
      | void notify()              | wait() 메서드에 의해 기다리고 있는 스레드(non runnable 상태)를 실행 가능한 상태(runnable)로 가져온다 |

### toString() 메서드

- 기능: **객체 정보를 문자열로 바꾸어 준다**

#### Object 클래스의 toString() 메서드

- `toString()` 메서드의 원형은 **생성된 인스턴스의 클래스 이름과 주소 값을 보여준다**

  ```java
  package object;
  class Book{
  	int bookNumber;
  	String bookTitle;
  	
  	Book(int bookNumber, String bookTitle){
  		this.bookNumber = bookNumber;
  		this.bookTitle = bookTitle;
  	}
  }
  public class ToStringEx {
  	public static void main(String[] arg) {
  		Book book1 = new Book(200, "개미");
  		
  		System.out.println(book1);
  		System.out.println(book1.toString());
  	}
  
  }
  /*
  object.Book@54bedef2
  object.Book@54bedef2
  */
  ```

  - `book1`을 출력시 자동으로 `toString()`메서드가 호출된다

  - `toString()`의 원형
    `getClass().getName() + '@' + Integer.toHexString(hashCode())`

    > object.Book@54bedef2	==	(클래스 이름)@(해시 코드 값)

#### String과 Integer 클래스의 toString() 메서드

- `(클래스 이름)@(해시 코드 값)` 형태가 아닌 경우

  ```java
  String str = new String("test");
  System.out.println(str);		//test 출력
  Integer i1 = new Integer(100);
  System.out.println(i1);			//100 출력
  ```

  - `String`과 `Integer`의 `toString()` 메서드를 미리 재정의해놔 그렇다

#### Book 클래스에서 toString() 메서드 직접 재정의하기

- `책 이름, 책 번호` 출력하기

  - [Source => Override/Implements Methods]로 자동으로 메서드 재정의 가능

  ```java
  class Book{
  	int bookNumber;
  	String bookTitle;
  	
  	Book(int bookNumber, String bookTitle){
  		this.bookNumber = bookNumber;
  		this.bookTitle = bookTitle;
  	}
  
  	@Override
  	public String toString() {
  		return bookTitle + "," + bookNumber;
  	}
  }
  
  public class ToStringEx {
  	public static void main(String[] arg) {
  		Book book1 = new Book(200, "개미");
  		
  		System.out.println(book1);
  		System.out.println(book1.toString());
  	}
  }
  /*
  개미,200
  개미,200
  */
  ```

### equals() 메서드

- 기능: **두 인스턴스의 주소 값을 비교하여 boolean 값을 반환**
  - BUT 서로 다른 주소 값을 가져도 같은 인스턴스라고 정의할 수 있다
  - `물리적 동일성(인스턴스의 메모리 주소가 같음)`, `논리적 동일성(논리적으로 두 인스턴스가 같음)`을 구현할 때 `equals()`메서드를 재정의한다

#### Object 클래스의 equals() 메서드

- 두 인스턴스의 주소 값이 같다

  - 두 변수가 같은 메모리 주소를 가리킨다

  ```java
  Student studentLee = new Student(100, "이상원");
  Student studentLee2 = studentLee; 	// 주소 복사
  ```

  - 두 변수는 동일한 인스턴스를 가리킨다
    - `equals()`메서드를 이용하면 true 결과 나옴

- 데이터는 같으나 주소는 다른 경우

  ```java
  Student studentLee = new Student(100, "이상원");
  Student studentLee2 = studentLee; 	// 주소 복사
  Student studentSang = new Student(100, "이상원");
  ```

  - `studentLee`, `studentLee2`와  `studentSang`는 다른 주소를 가지지만 저장된 학생 데이터는 같다

    - **논리적으로는 같은 학생으로 처리하는게 맞다**

  - 예제 구현

    ```java
    class Student{
    	
    	int studentId;
    	String studentName;
    	
    	public Student(int studentId, String studentName){
    		this.studentId = studentId;
    		this.studentName = studentName;
    	}
    	
    	public String toString(){
    		return studentId + "," + studentName;
    	}
    	
    }
    
    public class EqualsTest {
    
    	public static void main(String[] args) {
    
    		Student studentLee = new Student(100, "이상원");
    		Student studentLee2 = studentLee;
    		Student studentSang = new Student(100, "이상원");
    		// 동일한 주소의 두 인스턴스 비교
    		if(studentLee == studentLee2)	// == 기호로 비교
    			System.out.println("studentLee와 studentLee2의 주소는 같습니다.");
    		else
    			System.out.println("studentLee와 studentLee2의 주소는 다릅니다.");
    		if(studentLee.equals(studentLee2))	// equals() 메서드로
    			System.out.println("studentLee와 studentLee2는 동일합니다.");
    		else
    			System.out.println("studentLee와 studentLee2는 동일하지 않습니다.");
    		
    		//주소는 다르지만 equals 의 결과가 true 인 경우
    		if(studentLee == studentSang)// == 기호로 비교
    			System.out.println("studentLee와 studentSang의 주소는 같습니다.");
    		else
    			System.out.println("studentLee와 studentSang의 주소는 다릅니다.");
    		if(studentLee.equals(studentSang))// equals() 메서드로
    			System.out.println("studentLee와 studentSang은 동일합니다.");
    		else
    			System.out.println("studentLee와 studentSang은 동일하지 않습니다.");
    
    	}
    }
    /*
    studentLee와 studentLee2의 주소는 같습니다.
    studentLee와 studentLee2는 동일합니다.
    studentLee와 studentSang의 주소는 다릅니다.
    studentLee와 studentSang은 동일하지 않습니다.
    */
    ```

    - 원래 `equals()` 메서드를 사용하였다
      - `studentLee`와 `studentLee2`는 true
      - `studentLee`와 `studentSange`은 false

#### String과 Integer 클래스의 equals() 메서드

- JDK에서 제공하는 `String`, `Integer`클래스의 `equals()`메서드는 재정의 되어있다

  ```java
  public class StringEquals {
  
  	public static void main(String[] args) {
  
  		String str1 = new String("abc");
  		String str2 = new String("abc");
  		
  		System.out.println(str1 == str2);  // 두 스트링 인스턴스의 주소 값은 다름
  		System.out.println(str1.equals(str2)); // String 클래스의 equals 메소드가 재정의 됨
  		
  		Integer i1 = new Integer(100);
  		Integer i2 = new Integer(100);
  		
  		System.out.println(i1 == i2);   // 두 정수 인스턴스의 주소 값은 다름 
  		System.out.println(i1.equals(i2)); // Integer 클래스의 equals 메소드가 재정의 됨
  	}
  }
  /*
  false
  true
  false
  true
  */
  ```

  - 재정의된 `equals()`메서드의 경우
    - `str1, str2`은 'abc'로 같은 값을 가져서 true 반환
    - `i1, i2`은 100으로 같은 값을 가져 true 반환

#### Student 클래스에서 equals() 메서드 직접 재정의하기

- 두 학생이 같다 == 학번이 같다

  ```java
  class Student{
  	
  	...
  
  	@Override
  	public boolean equals(Object obj) {
  		if(obj instanceof Student) {
  			Student std = (Student)obj;
  			if(this.studentId == std.studentId)
  				return true;
  			else
  				return false;
  		}
  		return false;
  	}
  }
  
  public class EqualsTest {
  
  	public static void main(String[] args) {
  
  		...
  	}
  }
  /*
  studentLee와 studentLee2의 주소는 같습니다.
  studentLee와 studentLee2는 동일합니다.
  studentLee와 studentSang의 주소는 다릅니다.
  studentLee와 studentSang은 동일합니다.
  */
  ```

  - `==` 연산의 경우 주소가 다르면 false 반환
  - `equals()`메서드의 경우 학번이 같으면 true 반환

### hashCode() 메서드

- `해시(hash)`는 **정보를 저장하거나 검색할 때 사용하는 자료 구조**
  - 정보를 어디에 저장할 것인지, 어디서 가져올 것인지 **해시 함수**를 사용하여 구현한다
  - `해시 함수`
    - 객체의 **특정 정보(키값)**를 매개변수 값으로 넣으면 객체가 저장되어야 할 위치나 저장된 **해시 테이블 주소**(위치)를 반환한다

- **자바에서 인스턴스를 힙 메모리에 생성하여 관리할 때 해시 알고리즘을 사용한다**

  ```java
  hashCode = hash(key);
  ```

  - `toString()`메서드 원형
    `getClass().getName() + '@' + Integer.toHexString(hashCode())`의 16진수 숫자는 `해시 코드 값`이다
    - 이 값은 자바 가상 머신이 힙 메모리에 저장한 **인스턴스의 주소 값**이다
    - 자바에서 두 인스턴스가 같으려면 `hashCode()` 메서드에서 반환하는 해시 코드 값이 같아야 한다
      - 논리적으로 같은 두 객체도 같은 해시 코드 값을 반환하도록 `hashCode()` 메서드를 재정의해야 된다
      - **`equals()` 메서드를 재정의했다면 `hashCode()` 메서드도 재정의 해야된다**

#### String과 Integer 클래스의 hashCode() 메서드

```java
public class HashCodeTest {

	public static void main(String[] args) {

		String str1 = new String("abc");
		String str2 = new String("abc");
		
		System.out.println(str1.hashCode());	//abc 문자열의 해시 코드 값 출력
		System.out.println(str2.hashCode());
		
		Integer i1 = new Integer(100);
		Integer i2 = new Integer(100);
		
		System.out.println(i1.hashCode());	//Integer(100)의 해시 코드값 출력
		System.out.println(i2.hashCode());
	}
}
/*
96354
96354
100
100
*/
```

- String 클래스의 경우 같은 문자열을 가지면 동일한 해시 코드 값을 반환
- Integer 클래스의 경우 정수 값을 반환하도록 재정의 되어 있다

#### Student 클래스에서 hashCode() 메서드 재정의하기

- 논리적으로 동일한 두 학생은 같은 해시 코드 값을 반환하도록 `hashCode()` 재정의 하기

- **일반적으로 `hashCode()`메서드를 재정의할 때 `equals()` 재정의에서 사용한 멤버변수를 활용하는 것이 좋다**

  ```java
  package object;
  
  class Student{
  	...
  	@Override
  	public boolean equals(Object obj) {
  		if(obj instanceof Student) {
  			Student std = (Student)obj;
  			if(this.studentId == std.studentId)
  				return true;
  			else
  				return false;
  		}
  		return false;
  	}
  
  	@Override
  	public int hashCode() {
  		return studentId;
  	}
  }
  
  public class EqualsTest {
  
  	public static void main(String[] args) {
  		...
  		System.out.println("studentLee의 hashCode :" +studentLee.hashCode());
  		System.out.println("studentSang의 hashCode :" +studentSang.hashCode());
  		
  		System.out.println("studentLee의 실제 주소값 :"+ System.identityHashCode(studentLee));
  		System.out.println("studentSang의 실제 주소값 :"+ System.identityHashCode(studentSang));
  
  	}
  }
  /*
  studentLee와 studentLee2의 주소는 같습니다.
  studentLee와 studentLee2는 동일합니다.
  studentLee와 studentSang의 주소는 다릅니다.
  studentLee와 studentSang은 동일합니다.
  studentLee의 hashCode :100
  studentSang의 hashCode :100
  studentLee의 실제 주소값 :1421795058
  studentSang의 실제 주소값 :1555009629
  */
  ```

  - `hashCode()` 메서드를 재정의했을 때 **실제 인스턴스의 주소 값은 `System.identityHashCode()` 메서드를 통해 확인 가능**

### clone() 메서드

- 객체 원본을 유지하며 복사본을 사용할때 사용

- 기본 틀(prototype)의 복사본을 사용해 동일한 인스턴스를 만들어 복잡한 생성 과정을 간단히 할때 사용

- 선언

  ```java
  protected Object clone();
  ```

  - 객체를 복제해 또 다른 객체를 반환해주는 메서드

- 예제

  ```java
  class Point{
  	int x;
  	int y;
  	
  	Point(int x, int y){
  		this.x = x;
  		this.y = y;
  	}
  	
  	public String toString(){
  		return "x = " + x + "," + "y = " + y;
  	}
  	
  }
  
  class Circle implements Cloneable{	///객체를 복제해도 된다는 의미로 cLONEALBE 인터페이스를 함께 선언
  	
  	Point point;
  	int radius;
  	
  	Circle(int x, int y, int radius){
  		this.radius = radius;
  		point = new Point(x, y);
  	}
  	
  	public String toString(){
  		return "원점:" + point + "," + "반지름:" + radius; 
  	}
  
  	@Override
  	public Object clone() throws CloneNotSupportedException {	// clone()메서드를 사용할 때 발생가능한 오류를 예외 처리
  		return super.clone();
  	}
  }
  
  public class ObjectCloneTest {
  
  	public static void main(String[] args) throws CloneNotSupportedException {
  
  		Circle circle = new Circle(10, 20, 30);
  		Circle copyCircle = (Circle)circle.clone();	//clone()메서드로 circle 인스턴스를 복제
  		
  		System.out.println(circle);
  		System.out.println(copyCircle);
  		
  		System.out.println(System.identityHashCode(circle));
  		System.out.println(System.identityHashCode(copyCircle));
  	}
  }
  /*
  원점:x = 10,y = 20,반지름:30
  원점:x = 10,y = 20,반지름:30
  1555009629
  41359092
  */
  ```

  - **`clone()`메서드를 사용하려면 객체를 복제해도 된다는 의미로 클래스에 `Cloneable`인터페이스를 구현해야 된다**
    - 명시하지 않으면 `CloneNotSupportedException`이 발생
  - 멤버 변수가 동일한 인스턴스가 다르 메모리에 새로 생성됐다



## 11-2 String 클래스

### String을 선언하는 두 가지 방법

```java
String str1 = new String("abc");	//생성자의 매개변수로 문자열 생성 방법
String str2 = "test";				//문자열 상수를 가리키는 방식
```

- `new` 예약어로 객체를 생성할 경우
  - "abc" 문자열을 위한 메모리가 할당되고 새로운 객체가 생성된다
- `str2 = "test"`처럼 바로 문자열 상수를 가리키는 경우
  - `str2`가 기존에 만들어져 있던 "test"라는 문자열 상수의 메모리 주소를 가리키게 된다
    - 상수 값을 저장하는 공간 `상수 풀(constant pool)`
      - 상수는 프로그램 시작시 메모리 공간 `상수 풀`에 놓인다

> str1 ------> 힙 메모리 ["abc"]
> str2 ------> 상수 풀["test"]

- 주소 값 비교

  ```java
  package string;
  
  public class StringTest1 {
  
  	public static void main(String[] args) {
  
  		String str1 = new String("abc");
  		String str2 = new String("abc");
  		
  		System.out.println(str1 == str2);    //false
  		System.out.println(str1.equals(str2)); //true
  	
  		String str3 = "abc";
  		String str4 = "abc";
  	
  		System.out.println(str3 == str4);  //true
  		System.out.println(str3.equals(str4)); //true
  	}
  }
  /*
  false
  true
  true
  true
  */
  ```

  - `str1, str2`는 **인스턴스가 매번 새로 생성되어 주소 값이 다르다**
  - `str3, str4`의 경우 **문자열 "abc"가 상수풀에 저장되어 가리키는 주소 값이 같다**

### String 클래스의 final char[] 변수

- 다른 프로그래밍 언어는 문자열 구현시 일반적으로 `char[]` 배열을 사용한다

  - 자바는 String 클래스를 제공하여 `char[]`배열 구현하지 않고 사용

- String.java 파일에 있는 String 클래스 선언

  - `private final char value[]`라고 선언되어 있다
  - `final`로 선언되어 한 번 생성된 문자열을 변경되지 않는다
    - 이런 문자열 특징을 `문자열은 불변(immutable)한다`라고 한다
    - 두 개의 문자열 연결시, 문자열 변경이 아닌 새로운 문자열이 생성된다

- `concat()`메서드 사용

  ```java
  public class StringTest2 {
  
  	public static void main(String[] args) {
  		String javaStr = new String("java");
  		String androidStr = new String("android");
  		System.out.println(javaStr);
  		System.out.println("처음 문자열 주소 값: "+System.identityHashCode(javaStr));
  		
  		javaStr = javaStr.concat(androidStr);
  		
  		System.out.println(javaStr);
  		System.out.println("연결된 문자열 주소 값: " +System.identityHashCode(javaStr));
  	}
  }
  /*
  java
  처음 문자열 주소 값: 804564176
  javaandroid
  연결된 문자열 주소 값: 1421795058
  ```

  - **문자열은 불변**하므로 javaStr 변수 값 자체가 변하는 것이 아닌 새로운 문자열이 생성된 것이다
    - `javaStr`은 새로 생성된 문자열을 가리킨다

### StringBuffer와 StringBuilder 클래스 활용하기

- 문자열 변경, 연결시 String 클래스는 내부의 문자열이 변경되지 않으므로 메모리가 낭비된다

  - 이를 해결하는 것이 `StringBuffer`, `StringBuilder` 클래스이다

- `StringBuffer`, `StringBuilder`은 final이 아닌 변경 가능한 `char[]`를 변수로 갖는다

  - 문자열을 연결하며 기존의 `char[]`배열이 확장되어 추가 메모리를 사용하지 않는다
  - 둘중 하나 사용하면 된다
    - 차이: 여러 작업(스레드)이 동시에 문자열을 변경하려 할 때 안전한 변경을 보장해주는 차이
      - `StringBuffer`: 문자열이 안전하게 변경되도록 보장
      - `StringBuilder`: 보장하지 않음
      - 멀티스레드 프로그램 아니면 `StringBuilder`가 빠르다

- 예제

  ```java
  public class StringBuilderTest {
  
  	public static void main(String[] args) {
  		
  		String javaStr = new String("Java");
  		System.out.println("javaStr 문자열 주소 :" +System.identityHashCode(javaStr));//처음 생성된 메모리 주소
  		
  		StringBuilder buffer = new StringBuilder(javaStr); //String으로 부터 StringBuilder생성
  		System.out.println("연산 전 buffer 메모리 주소:" + System.identityHashCode(buffer));//buffer 메모리 주소
  		buffer.append(" and");                // 문자열 추가
  		buffer.append(" android");            // 문자열 추가
  		buffer.append(" programming is fun!!!"); //문자열 추가
  		System.out.println("연산 후 buffer 메모리 주소:" + System.identityHashCode(buffer));//buffer 메모리 주소
  		
  		javaStr = buffer.toString(); //String 클래스로 반환
  		System.out.println(javaStr);
  		System.out.println("새로 만들어진 javaStr 문자열 주소 :" +System.identityHashCode(javaStr)); //새로 생성된 메모리 주소
  
  	}
  }
  /*
  javaStr 문자열 주소 :804564176
  연산 전 buffer 메모리 주소:1421795058
  연산 후 buffer 메모리 주소:1421795058
  Java and android programming is fun!!!
  새로 만들어진 javaStr 문자열 주소 :1555009629
  ```

  - `append()` 메서드 마다 메모리가 새로 생성되는 것이 아닌, **하나의 메모리에 계속 연결된다**
  - `toString()`으로 문자열로 반환



## 11-3 Wrapper 클래스

### 기본 자료형을 위한 클래스

- 매개변수가 객체거나 반환 값이 객체인 경우 **정수를 객체형으로 사용해야 하는 경우가 발생**

  ```java
  public void setValue(Integer i){...}	//객체를 매개변수로 받는 경우
  public Integer returnValue(){...}		//반환 값이 객체형인 경우
  ```

  - 이를 위해 기본 자료형처럼 사용하는 클래스 제공

    - `Wrapper 클래스`: 기본 자료형을 감쌌다는 의미

  - Wrapper 클래스 종류

    | 기본형  | Wrapper 클래스 |
    | ------- | -------------- |
    | boolean | Boolean        |
    | byte    | Byte           |
    | char    | Char           |
    | short   | Short          |
    | int     | Integer        |
    | long    | Long           |
    | float   | Float          |
    | double  | Double         |

### Integer 클래스 사용하기

- Integer 클래스의 생성자 2가지 경우

  - 특정 정수를 매개변수로 받는 경우
  - 문자열을 받는경우

  ```java
  Integer(int value){...}	//특정 정수를 매개변수로 받는 경우
  Integer(String s){...}	//특정 문자열을 매개변수로 받는 경우
  ```

- int 자료형의 특성이 그대로 구현되어 있다

  - 사용 가능한 최대값, 최소값이 static 변수로 정의되어 있다
  - 멤버 변수로 기본 자료형 int를 가지고 있다
  - int 값을 객체고 활용할 수 있는 여러 메서드 제공
  - `private final int value;`라 한번 생성되면 변경할 수 없다

#### Integer 클래스의 메서드

- `intValue()`: Integer 클래스 내부의 int 자료형 값을 가져온다

  ```java
  Integer iValue = new Integer(100);
  int myValue = iValue.intValue();	//myValue에 100이 저장됨
  ```

- `valueOf()`: 생성자를 사용하지 않고 정수나 문자열을 바로 Integer클래스로 반환받을 수 있다

  ```java
  Integer number1 = Integer.valueOf("100");
  Integer number2 = Integer.valueOf(100);
  ```

- `parseInt()`: 문자열이 어떤 숫자를 나타낼 때, int 값을 바로 가져와서 반환

  ```java
  int num = Integer.parseInt("100");
  ```

### 오토박싱과 언박싱

- int로 선언과 Integer로 선언은 전혀 다르다, 기본 자료형과 클래스의 만남이다

  - 이전에는 형을 일치시켜 연산 가능했지만 자바5부터 변환 없이 가능

    ```java
    Integer num1 = new Integer(100);
    int num2 = 200;
    int sum = num1 + num2;	//num1 => num.inValue()로 변환(언박싱)
    Integer num3 = num2;	//num2 => Integer.valueOf(num2)로 변환(오토박싱)
    ```

    - `오토박싱(autoboxing)`: 기본형을 객체형으로 바꾸는 것
    - `언박싱(unboxing)`: 객체형을 기본형으로 꺼내는 것
    - 컴파일러가 자동으로 해준다



## 11-4 클래스

- 자바의 모든 클래스, 인터페이스가 컴파일되면 class파일로 생성된다
  - a.java 파일이 컴파일 되면 a.class파일이 생성
  - a.class 파일에는 클래스나 인스턴스에 대한 변수, 메서드, 생성자 등의 정보가 들어 있다
- **Class 클래스는 컴파일된 class 파일에 저장된 클래스나 인터페이스 정보를 가져오는데 사용**

### Class 클래스란?

- 모르는 클래스의 정보를 사용할 경우에 **Class 클래스**를 사용한다

  - ex. 반환받는 클래스가 정확히 어떤 자료형인지 모를때

- Class 클래스 선언, 클래스 정보 가져오는 방법 세 가지

  1. Object 클래스의 getClass() 메서드 사용하기

     ```java
     String s = new String();
     Class c = s.getClass();	//getClass() 메서드의 반환형은 Class
     ```

  2. 클래스파일 이름을 Class 변수에 직접 대입하기

     ```java
     Class c = String.Class; 
     ```

  3. Class.forName("클래스 이름") 메서드 사용하기

     ```java
     Class c = Class.forName("java.lang.String");
     ```

- Class 클래스 활용 예제

  ```java
  package classex;
  
  public class Person {
  	private String name;
  	private int age;
  	
  	public Person() {	}
  	public Person(String name) {
  		this.name = name;
  	}
  	
  	public Person(String name, int age) {
  		this.name = name;
  		this.age = age;
  	}
  	public String getName() {
  		return name;
  	}
  	public void setName(String name) {
  		this.name = name;
  	}
  	public int getAge() {
  		return age;
  	}
  	public void setAge(int age) {
  		this.age = age;
  	}
  }
  ```

  ```java
  public class ClassTest {
  
  	public static void main(String[] args) throws ClassNotFoundException {
  		Person person = new Person();
  		Class pClass1 = person.getClass();		//Object의 getClass()메서드 사용
  		System.out.println(pClass1.getName());
  		
  		Class pClass2 = Person.class;			//직접 class 파일 대입
  		System.out.println(pClass2.getName());
  		
  		Class pClass3  = Class.forName("classex.Person");	//클래스 이름으로 가져오기
  		System.out.println(pClass3.getName());
  	}
  }
  /*
  classex.Person
  classex.Person
  classex.Person
  ```

  - `forName()` 메서드에서 발생하는 예외를 처리하기 위해 `ClassNotFoundException` 사용
    - 이름과 일치하는 클래스가 없는 경우 발생
  - `getName()` 메서드를 호출하여 **클래스의 이름**인 `classex.Person`을 출력

### Class 클래스를 활용해 클래스 정보 알아보기

- 프로그래밍 중 사용할 클래스의 자료형을 모르는 경우 발생

  - Class 클래스를 가져올 수 있으면 해당 클래스 정보, 즉 생성자, 메서드, 멤버 변수 정보를 찾을 수 있다
    - Class 클래스를 활용하여 클래스의 정보를 가져오고, 이 정보를 활용하여 **인스턴스를 생성하거나 메서드를 호출하는 방식을 `리플렉션(reflection)`이라고 한다

- String 클래스 정보를 가져오는 예제

  ```java
  package classex;
  
  import java.lang.reflect.Constructor;
  import java.lang.reflect.Field;
  import java.lang.reflect.Method;
  
  public class StringClassTest {
  	public static void main(String[] args) throws ClassNotFoundException {
  		Class strClass = Class.forName("java.lang.String");	//클래스 이름으로 가져오기
  		
  		Constructor[] cons = strClass.getConstructors();	//모든 생성자 가져오기
  		for(Constructor c : cons) {
  			System.out.println(c);
  		}
  
  		System.out.println();
  		Field[] fields = strClass.getFields();	//모든 멤버 변수(필드) 가져오기
  		for(Field f : fields){
  			System.out.println(f);
  		}
  		System.out.println();
  		Method[] methods = strClass.getMethods();	//모든 메서드 가져오기
  		for(Method m : methods){
  			System.out.println(m);
  		}
  	}
  
  }
  /*
  public java.lang.String(byte[])
  public java.lang.String(byte[],int,int)
  public java.lang.String(byte[],java.nio.charset.Charset)
  public java.lang.String(byte[],java.lang.String) throws java.io.UnsupportedEncodingException
  public java.lang.String(byte[],int,int,java.nio.charset.Charset)
  public java.lang.String(java.lang.StringBuilder)
  ...
  ```

  - 클래스의 생성자, 멤버 변수, 메서드 정보를 가져와 출력했다

  - `classex.Person` 클래스 정보를 불러오면

    ```java
    /*
    //생성자
    public classex.Person()
    public classex.Person(java.lang.String,int)
    public classex.Person(java.lang.String)
    
    // ******private이라 멤버 변수 안보임******
    
    //메서드
    public java.lang.String classex.Person.getName()
    public void classex.Person.setName(java.lang.String)
    public int classex.Person.getAge()
    public void classex.Person.setAge(int)
    public final void java.lang.Object.wait(long,int) throws java.lang.InterruptedException
    public final void java.lang.Object.wait() throws java.lang.InterruptedException
    public final native void java.lang.Object.wait(long) throws java.lang.InterruptedException
    public boolean java.lang.Object.equals(java.lang.Object)
    public java.lang.String java.lang.Object.toString()
    public native int java.lang.Object.hashCode()
    public final native java.lang.Class java.lang.Object.getClass()
    public final native void java.lang.Object.notify()
    public final native void java.lang.Object.notifyAll()
    ```

### newInstance()를 사용해 클래스 생성하기

- `newInstance()` 메서드를 통해 인스턴스 생성 가능하다

  - 항상 Object를 반환하므로 **생성된 객체형으로 형 변환해야 된다**

  ```java
  public class NewInstanceTest {
  
  	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
  		Person person1 = new Person();
  		System.out.println(person1);
  		
  		Class pClass = Class.forName("classex.Person");
  		Person person2 = (Person)pClass.newInstance(); 		//Class 클래스의 newInstance()메서드로 생성하기
  		System.out.println(person2);
  	}
  }
  ```

  - `newInstance()`를 사용하여 인스턴스 생성
    - `Person` 클래스의 디폴트 생성자가 호출되어 인스턴스가 생성됨
  - `InstantiationException, IllegalAccessException`은 `Class.forName()과 newInstance()` 사용하는 동안 발생하는 예외처리

- 이미 알고있는 클래스 자료형인 경우 Class 클래스를 사용할 필요 없다

  - 클래스 정보 아는데 피를렉션 프로그래밍은 코드가 복잡해지고 속도도 느려진다
  - 즉, **리플렉션 프로그래밍은 컴파일 시점에서 알 수 없는 클래스**
    - 프로그램 실행 중에 클래스를 메모리에 로딩하거나 객체가 다른곳에 위치해서 원격으로 코딩하고 생성할 때 사용

### Class.forName()을 사용해 동적 로딩하기

- 대부분의 클래스 정보는 프로그램이 로딩될 때 이미 메모리에 있다

  > 회사에서 개발한 시스템이 오라클, MySQL 등등 여러종류의 데이터베이스를 지원한다
  >
  > 그런데 시스템을 컴파일할 때 모든 데이터베이스 라이브러리를 같이 컴파일할 필요는 없다
  >
  > 시스템을 구동할 때 필요 드라이버만 로딩하면 된다
  >
  > 회사가 사용하는 데이터베이스 정보는 환경 파일에서 읽어 올 수도 있고 다른 변수 값으로 받을 수 있다
  >
  > 즉, 프로그램 실행 이후 클래스의 로딩이 필요한 경우 **동적 로딩(dynamic loading)** 방식을 제공한다

- 자바는 `Class.forName()` 메서드를 동적 로딩으로 제공한다

  ```java
  Class pClass = Class.forName("classex.Person");
  ```

  - `forName()`메서드는 문자열을 매개변수로 받으므로, 변수를 변경하여 **여러 데이터베이스 드라이버 중 필요한 드라이버의 값을 설정할 수 있다**

    ```java
    String className = "classex.Person";
    Class pClass = Class.forName(className);
    ```

    - **필요에 따라 로딩되는 클래스를 동적으로 변경할 수 있다**

#### forName() 메서드를 사용할 때 유의할 점

- 매개변수가 **문자열**이라 오류가 있어도 **컴파일할 때에는 모른다**
  - 해당 클래스가 없다면 `ClassNotFoundException`이 발생



## 연습문제

1. 두 개의 인스턴스가 메모리는 다르더라도 논리적으로 동일하다는 것을 구현하는 Object의 메서드는 `equals()`이다

2. String 클래스는 멤버로 가지는 문자열 변수가 final이어서 변하지 않습니다. 다음과 같이 두개의 String변수를 연결할 때 힙 메모리에 생성되는 String 인스턴스를 그려보세요

   ```java
   String a = new String("abc");
   String b = new String("def");
   String a = a+b;
   ```

   > a ----> ["abc"]
   > b ----> ["def"]
   > a ----> ["abcdef"]	//새로운 메모리

3. 기본 자료형을 멤버 변수로 포함하여 메서드를 제공함으로써 기본 자료형의 객체를 제공하는 클래스를 `Wrapper`라고 한다

4. 다음 코드의 출력 결과가 '진돗개 멍멍이'가 되도록 MyDog 클래스를 수정하세요

   ```java
   package q4;
   
   class MyDog{
   	public String name;
   	public String type;
   	
   	public MyDog(String name, String type) {
   		this.name = name;
   		this.type = type;
   	}
   
   	@Override
   	public String toString() {
   		return type + " " + name;
   	}
   }
   
   public class Q4 {
   
   	public static void main(String[] args) {
   		MyDog dog = new MyDog("멍멍이","진돗개");
   		System.out.println(dog);
   	}
   
   }
   ```

5. 자바에서 클래스의 동적 로딩 방식을 제공하는 메서드는 `Class.forName()`이다