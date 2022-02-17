# 13장 - 내부 클래스, 람다식, 스트림

## 13-1 내부 클래스

### 내부 클래스 정의와 유형

- `내부 클래스(inner class)`: 클래스 내부에 선언한 클래스

  - 외부 클래스와 밀접한 관련이 있을때 사용
  - 다른 클래스와 협력할 일이 없는 경우 사용

  ```java
  class Out{	//외부 클래스
      class In{	//내부 클래스
          ...
      }
  }
  ```

- 내부 클래스 유형

  - `인스턴스 내부 클래스`: 멤버 변수처럼 클래스 내부에서 정의하는 클래스
  - `정적(static) 내부 클래스`: static 키워드를 사용하는 클래스
  - `지역(local) 내부 클래스`: 메서드 내부에 정의하는 클래스
  - `익명(anonymous) 내부 클래스`: 이름 없이 선언하고 바로 생성하여 사용하는 클래스

  ```java
  class ABC{	//외부 클래스
      class In{	//인스턴스 내부 클래스
          static class Sln{	//정적 내부 클래스
              ...
          }
      }
      
      public void abc(){
          class Local{	//지역 내부 클래스
              ...
          }
      }
  }
  ```

### 인스턴스 내부 클래스

- 인스턴스 변수 선언할 때와 같은 위치에 선언
- 외부 클래스 내부에서만 생성하여 사용하는 객체를 선언할 때 사용
- 외부 클래스 생성 후에 내부 클래스가 생성된다
  - 외부 클래스 생성없이 내부 클래스를 사용할 수 없다
    - `정적 내부 클래스`는 가능

- 예제

  ```java
  package innerclass;
  
  import innerclass.OutClass.InClass;
  
  class OutClass {
  	private int num = 10;
  	private static int sNum = 20;
  	
  	private InClass inClass;
  	
  	public OutClass() {
  		inClass = new InClass();
  	}
  	
  	class InClass{		//인스턴스 내부 클래스
  		int inNum = 100;
  		//static int sInNum = 200;	//인스턴스 내부 클래스에 정적 변수 선언 불가능
  		
  		void inTest() {
  			System.out.println("OutClass num = "+num+"(외부 클래스의 인스턴스 변수");
  			System.out.println("OutClass snum = "+sNum+"(외부 클래스의 정적 변수");
  		}
  		
  		//static void sTest(){} 	// 정적 메서드 또한 정의 불가능
  	}
  	public void usingClass() {
  		inClass.inTest();
  	}
  
  }
  public class InnerTest {
  
  	public static void main(String[] args) {
  		OutClass outClass = new OutClass();
  		System.out.println("외부 클래스 이용하여 내부 클래스 기능 호출");
  		outClass.usingClass();
  	}
  }
  /*
  외부 클래스 이용하여 내부 클래스 기능 호출
  OutClass num = 10(외부 클래스의 인스턴스 변수
  OutClass snum = 20(외부 클래스의 정적 변수
  ```

#### 인스턴스 내부 클래스에서 사용하는 변수와 메서드

- 외부 클래스의 `private` 변수는 **내부 클래스에서 사용할 수 있다**

- 내부 클래스는 외부 클래스 생성 이후에 생성되기에 정적 변수, 메서드를 정의할 수 없다
- 인스턴스 내부 클래스의 메서드는 외부 클래스의 메서드가 호출될 때 사용할 수 있다

#### 다른 클래스에서 인스턴스 내부 클래스 사용하기

- 외부 클래스 외의 다른 클래스에서 private이 아닌 내부 클래스 생성은 문법적으로는 가능

  ```java
  OutClass outClass = new OutClass();
  outClass.InClass inClass = outClass.new InClass();
  ```

### 정적 내부 클래스

- 내부 클래스가 외부 클래스 상관없이 사용 및 정적 변수가 사용 되려면 `정적 내부 클래스(static inner class)`를 사용해야 된다

- 예제

  ```java
  class OutClass {
  	private int num = 10;
  	private static int sNum = 20;
  	
  	static class InStaticClass{		//정적 내부 클래스
  		int inNum = 100;			//정적 내부 클래스의 인스턴스 변수
  		static int sInNum = 200;	//정적 내부 클래스의 정적 변수
  		
  		void inTest(){   //정적 클래스의 일반 메서드
  			//num += 10;    // 외부 클래스의 인스턴스 변수는 사용할 수 없음.
  			System.out.println("InStaticClass inNum = " + inNum + "(내부 클래스의 인스턴스 변수 사용)"); 
  			System.out.println("InStaticClass sInNum = " + sInNum + "(내부 클래스의 스태틱 변수 사용)");
  			System.out.println("OutClass sNum = " + sNum + "(외부 클래스의 스태틱 변수 사용)");
  		}
  		
  		static void sTest(){  // 정적 클래스의 static 메서드
  			//num += 10;   // 외부 클래스의 인스턴스 변수는 사용할 수 없음.
  			//inNum += 10; // 내부 클래스의 인스턴스 변수는 사용할 수 없음
  			
  			System.out.println("OutClass sNum = " + sNum + "(외부 클래스의 스태틱 변수 사용)");
  			System.out.println("InStaticClass sInNum = " + sInNum + "(내부 클래스의 스태틱 변수 사용)");
  		}
  	}
  	
  	...
  
  }
  public class InnerTest {
  
  	public static void main(String[] args) {
  		...
  		//외부 클래스 생성하지 않고 바로 정적 내부 클래스 생성
  		OutClass.InStaticClass sInClass = new OutClass.InStaticClass();  
  		System.out.println("정적 내부 클래스 일반 메서드 호출");
  		sInClass.inTest();
  		System.out.println();
  				
  		System.out.println("정적 내부 클래스의 스태틱 메소드 호출");
  		OutClass.InStaticClass.sTest();
  	}
  }
  /*
  정적 내부 클래스 일반 메서드 호출
  InStaticClass inNum = 100(내부 클래스의 인스턴스 변수 사용)
  InStaticClass sInNum = 200(내부 클래스의 스태틱 변수 사용)
  OutClass sNum = 20(외부 클래스의 스태틱 변수 사용)
  
  정적 내부 클래스의 스태틱 메소드 호출
  OutClass sNum = 20(외부 클래스의 스태틱 변수 사용)
  InStaticClass sInNum = 200(내부 클래스의 스태틱 변수 사용)
  ```

| 정적 내부 클래스 메서드           | 변수 유형                               | 사용 가능 여부 |
| --------------------------------- | --------------------------------------- | -------------- |
| 일반 메서드 `void inTest()`       | 외부 클래스의 인스턴스 변수(num)        | X              |
|                                   | 외부 클래스의 정적 변수(sNum)           | O              |
|                                   | 정적 내부 클래스의 인스턴스 변수(inNum) | O              |
|                                   | 정적 내부 클래스의 정적 변수(sInNum)    | O              |
| 정적 메서드 `static void sTest()` | 외부 클래스의 인스턴스 변수(num)        | X              |
|                                   | 외부 클래스의 정적 변수(sNum)           | O              |
|                                   | 정적 내부 클래스의 인스턴스 변수(inNum) | X              |
|                                   | 정적 내부 클래스의 정적 변수(sInNum)    | O              |

#### 다른 클래스에서 정적 내부 클래스 사용하기

- 정적 내부 클래스는 외부 클래스 생성없이 선언 가능

  ```java
  OutClass.InStaticClass sInClass = new OutClass.InStaticClass();
  ```

- 정적 내부 클래스에 선언한 메서드나 변수는 private이 아닌 경우 바로 사용 가능

  ```java
  OutClass.InStaticClass.sTest();
  ```

### 지역 내부 클래스

- `지역 내부 클래스`: 지역 변수처럼 메서드 내부에 클래스를 정의하여 사용하는 것
  - 메서드 안에서만 사용 가능하다

- Runnable 인터페이스 구현하는 클래스를 지역 내부 클래스로 만든 예제

  ```java
  class Outer{
  	
  	int outNum = 100;
  	static int sNum = 200;
  	
  	Runnable getRunnable(int i){
  		int num = 100;
  		
  		class MyRunnable implements Runnable{
  			int localNum = 10;
  				
  			@Override
  			public void run() {
  				//num = 200;   //에러 남. 지역변수는 상수로 바뀜
  				//i = 100;     //에러 남. 매개 변수 역시 지역변수처럼 상수로 바뀜
  				System.out.println("i =" + i); 
  				System.out.println("num = " +num);  
  				System.out.println("localNum = " +localNum);
  					
  				System.out.println("outNum = " + outNum + "(외부 클래스 인스턴스 변수)");
  				System.out.println("Outter.sNum = " + Outer.sNum + "(외부 클래스 정적 변수)");
  				}
  			}
  		 return new MyRunnable();
  	}
  }
  
  public class LocalInnerTest {
  
  	public static void main(String[] args) {
  		Outer out = new Outer();
  		Runnable runner = out.getRunnable(10);
  		runner.run();
  	}
  }
  ```

  - 메서드 내부에 `Runnable` 인터페이스를 구현한 `MyRunnable` 클래스를 정의했다
  - 자바 스레드가 실행될 때 호출되는 `run()` 메서드 구현했다
  - **`Runnable` 객체를 사용하기 위해 메서드를 통해 객체를 반환받았다**

#### 지역 내부클래스에서 지역 변수의 유효성

- 지역 변수는 메서드가 호출될 때 스택 메모리에 생성되고 메서드 수행이 끝나면 사라진다

  ```java
  Outer out = new Outer();
  Runnable runner = out.getRunnable(10);
  runner.run();
  ```

  - `getRunnable()` 메서드의 지역 변수 `i`와 `num`은 메서드 호출이 끝나도 `run()`메서드를 통해 사용된다
    - **지역 내부 클래스에서 사용하는 지역변수는 상수로 처리된다**
    - `final` 예약어가 자동으로 추가된다

### 익명 내부 클래스

- `익명 내부 클래스`: 클래스 이름을 사용하지 않는 클래스

- 지역 내부 클래스 코드

  ```java
  class Outer{
  	...
  	Runnable getRunnable(int i){
  		
  		class MyRunnable implements Runnable{
  			...
  			@Override
  			public void run() {
  				...
  				}
  			}
  		 return new MyRunnable();
  	}
  }
  ```

  - 지역 내부 클래스 이름은 클래스를 생성하여 반환할 때만 사용한다

- 익명 내부 클래스

  ```java
  class Outer2{
  	
  	Runnable getRunnable(int i){
  		int num = 100;
  		
  		//MyRunnable 클래스 이름 빼고 클래스 바로 생성
  		return new Runnable() {		//익명 내부 클래스. Runnable 인터페이스 생성
  				
  		@Override
  		public void run() {
  			//num = 200;   //에러 남
  			//i = 10;      //에러 남
  			System.out.println(i);
  			System.out.println(num);
  			}
  		};	//클래스 끝에 ; 씀
  	}
  	
  	//인터페이스나 추상 클래스형 변수를 선언하고 클래스를 생성해 대입하는 방법
  	Runnable runner = new Runnable() {	//익명 내부 클래스를 변수에 대입
  		@Override
  		public void run() {
  			System.out.println("Runnable 이 구현된 익명 클래스 변수");
  		}
  	};//클래스 끝에 ; 씀
  }
  
  public class AnonymousInnerTest {
  
  	public static void main(String[] args) {
  		Outer2 out = new Outer2();
  		Runnable runnerble = out.getRunnable(10);
  		runnerble.run();
  		out.runner.run();
  	}
  
  }
  /*
  10
  100
  Runnable 이 구현된 익명 클래스 변수
  ```

  - 익명 내부 클래스는 하나의 인터페이스, 추상 클래스를 바로 생성할 수 있다
    - 변수를 선언해 대입할 수 있고
    - 메서드 내부에서 인터페이스나 추상 클래스를 구현하여 반환

#### 정리

| 종류                 | 구현 위치                                         | 사용할 수 있는 외부 클래스 변수        | 생성 방법                                                    |
| -------------------- | ------------------------------------------------- | -------------------------------------- | ------------------------------------------------------------ |
| 인스턴스 내부 클래스 | 외부 클래스 멤버 변수와 동일                      | 외부 인스턴스 변수<br />외부 전역 변수 | 외부 클래스를 먼저 만든 후 클래스 생성                       |
| 정적 내부 클래스     | 외부 클래스 멤버 변수와 동일                      | 외부 전역 변수                         | 외부 클래스와 무관하게 생성                                  |
| 지역 내부 클래스     | 메서드 내부에 구현                                | 외부 인스턴스 변수<br />외부 전역 변수 | 메서드를 호출할 때 생성                                      |
| 익명 내부 클래스     | 메서드 내부에 구현<br />변수에 대입하여 직접 구현 | 외부 인스턴스 변수<br />외부 전역 변수 | 메서드를 호출할 때 생성되거나, 인터페이스 타입 변수에 대입할 때<br /> new 예약어를 사용해 생성 |



## 13-2 람다식

### 함수형 프로그래밍과 람다식

- `함수형 프로그래밍(Functional Programming; FP)`: 함수의 구현과 호출만으로 프로그램을 만드는 프로그래밍 방식

- `람다식(Lambda expression)`: 자바에서 제공하는 함수형 프로그래밍 방식

### 람다식 구현하기

- 함수 이름이 없는 함수를 만드는 것이다

  > (매개변수) -> {실행문;}

- ```java
  int add(int x, int y){
      return x+y;
  }
  ```

  ```java
  (int x, int y) -> {return x+y;}
  ```

### 람다식 문법 살펴보기

#### 매개변수 자료형과 괄호 생략하기

- 매개변수 자료형 생략가능

- 매개변수가 하나인 경우에는 괄호 생략 가능

- 예제

  ```java
  str -> {System.out.println(str);}
  ```

  - 매개변수가 두개인 경우 괄호 생략 못한다

#### 중괄호 생략하기

- 구현 부분이 한 문장인 경우 중괄호 생략 가능

  ```java
  str -> System.out.println(str);
  ```

#### return 생략하기

- 구현 부분이 return문 하나라면 중괄호와 return 문 생략 가능

  ```java
  (x,y) -> x+y
  str -> str.length()
  ```

### 람다식 사용하기

- 람다식을 구현하기 위해서는 먼저 인터페이스를 만들고, 인터페이스에 람다식으로 구현할 메서드를 선언한다

  - 이를 `함수형 인터페이스`라고 한다

- 함수형 인터페이스 선언하기

  ```java
  package lambda;
  
  public interface MyNumber {
  	int getMax(int num1, int num2);
  }
  ```

- 람다식 구현과 호출

  ```java
  public class TestMyNumber {
  
  	public static void main(String[] args) {
  		MyNumber max = (x,y)->(x>=y)? x:y;		//람다식을 인터페이스형 max 변수에 대입
  		System.out.println(max.getMax(10, 20));	// 인터페이스형 변수로 메서드 호출
  	}
  }
  /*
  20
  ```



>  ##### 함수형 프로그래밍
>
> - `순수 함수(pure function)`을 구현하고 호출하여 외부 자료에 부수적인 영향을 주지 않도록 구현하는 방식
>   - 순수 함수란 매개변수만을 사용하여 만드는 함수
>     - 함수 외부에 있는 변수를 사용하지 않아 함수가 실행되도 외부에 영향을 주지 않는다
> - 외부 자료에 영향을 주지 않아서
>   - 병렬 처리에 적합
>   - 안정되고 확장성 있는 프로그램 개발 가능

### 함수형 인터페이스

- 람다식을 구현하기 위해 함수형 인터페이스를 만들고, 인터페이스에 람다식으로 구현한 메서드를 선언해야된다
  - 인터페이스는 두 개 이상의 메서드를 가지면 안된다
    - 람다식은 이름이 없어 어떤 메서드를 구현한지 애매해진다

#### @FunctionalInterface 애노테이션

- 함수형 인터페이스를 의미해주며, 메서드를 하나 이상 선언하면 오류 발생

  ```java
  @FunctionalInterface
  public interface MyNumber{ ... }
  ```

### 객체 지향 프로그래밍 방식과 람다식 비교

- 문자열 두개 연결해서 출력하는 예제를 객체 지향 방식과 람다식으로 구현

  - 인터페이스 구현

    ```java
    public interface StringConcat {
    	public void makeString(String s1, String s2);
    }
    ```

#### 클래스에서 인터페이스 구현하기

- 추상 메서드 구현

  ```java
  public class StringConCatImpl implements StringConcat {
  
  	@Override
  	public void makeString(String s1, String s2) {
  		System.out.println(s1 +"," + s2);
  	}
  }
  ```

- 메서드 테스트

  ```java
  public class TestStringConcat {
  
  	public static void main(String[] args) {
  
  		String s1 = "Hello";
  		String s2 = "World";
  		StringConCatImpl concat1 = new StringConCatImpl();
  		concat1.makeString(s1, s2);
  	}
  }
  /*
  Hello,World
  ```

  - 메서드를 사용하기 위해 `StringConCatImpl`클래스를 인스턴스로 생성해야 된다

#### 람다식으로 인터페이스 구현하기

- ```java
  public class TestStringConcat {
  
  	public static void main(String[] args) {
  		StringConcat concat2 = (s,v) -> System.out.println(s+","+v);
  		concat2.makeString(s1, s2);
  	}
  
  }
  /*
  Hello,World
  ```

  - 람다식으로 구현하는 경우 코드가 간결해진다

### 익명 개체를 생성하는 람다식

- 자바는 객체 없이 메서드 호출이 일어날 수 없다

  - 그런데 람다식은 어떻게 메서드를 호출할까?

- `익명 내부 클래스`는 클래스 이름없이 인터페이스 자료형 변수에 바로 메서드 구현부를 생성하여 대입 가능하다

  - **람다식으로 메서드를 구현해서 호출하면 컴퓨터 내부에 익명 클래스가 생성되어 이를 통해 객체가 생성된다**

    ```java
    StringConcat concat3 = new StringConcat() {
    			@Override
    			public void makeString(String s1, String s2) {
    				System.out.println( s1 + "," + s2 );
    			}
    		};
    ```

#### 람다식에서 사용하는 지역 변수

- 외부 메서드의 지역 변수를 수정하면...

  ```java
  public class TestStringConcat {
  
  	public static void main(String[] args) {
  
  		String s1 = "Hello";
  		String s2 = "World";
  		...
  		int i = 100;
  		StringConcat concat2 = (s,v) -> {
  			// i = 200;		//람다식 내부에서 변경하면 오류 발생
  			System.out.println(i);
  			System.out.println(s+","+v);
  		};
  		concat2.makeString(s1, s2);
  		
  		...
  }
  ```

  - `main()` 함수의 지역 변수를 변경하면 오류, 출력은 가능
  - 지역 변수는 메서드 호출이 끝나면 메모리에서 사라진다
    - 익명 내부 클래스에서 사용하는 경우 지역 변수가 상수로 변한다
    - 람다식도 익명 내부 클래스가 생성되므로 외부 메서드의 지역변수는 final 변수, 즉 상수가 된다

### 함수를 변수처럼 사용하는 람다식

- 람다식을 이용하면 구현된 함수를 변수처럼 사용할 수 있다

#### 인터페이스형 변수에 람다식 대입하기

- 인터페이스

  ```java
  interface PrintString{
      void showString(String str);
  }
  ```

- 변수 대입

  ```java
  PrintString lambdaStr = s -> System.out.println(s);
  lambdaStr.showString("hello labmda_1");
  ```

#### 매개변수로 전달하는 람다식

- 람다식을 변수에 대입하면 매개변수로 전달 가능하다

  - 전달되는 매개변수 자료형은 인터페이스형이다

  ```java
  interface PrintString{
  	void showString(String str);
  }
  
  public class TestLambda {
  
  	public static void main(String[] args) {
  
  		PrintString lambdaStr = s->System.out.println(s);  //람다식을 변수에 대입
  		lambdaStr.showString("hello lambda_1");
  		
  		showMyString(lambdaStr);                          //메서드 매개변수로 전달
  
  	}
  	
  	public static void showMyString(PrintString p) {	// 매개변수를 인터페이스형으로 받음
  		p.showString("hello lambda_2");
  	}
  }
  ```

#### 반환 값으로 쓰이는 람다식

- 메서드의 반환형을 인터페이스형으로 선언하면 구현한 람다식을 반환할 수 있다

  ```java
  interface PrintString{
  	void showString(String str);
  }
  
  public class TestLambda {
  
  	public static void main(String[] args) {
  
  		...
  		PrintString reStr = returnString();
  		reStr.showString("hello ");
  	}
  	
  	...
  	
  	public static PrintString returnString() {		// 람다식 반환
  		return s->System.out.println(s+"world");
  	}
  }
  ```

  

## 13-3 스트림

### 스트림이란?

- `스트림(strem)`: 자료 처리에 대한 기능을 구현해 놓은 클래스

  - 정렬, filter 등
  - 배열, 컬렉션 등의 자료를 일관성 있게 처리 가능

- 스트림을 이용한 출력

  ```java
  int[] arr = {1,2,3,4,5};
  Arrays.stream(arr).forEach(n -> System.out.println(n));
  ```

  - for문을 이용해 출력하는 것보다 간단하다

### 스트림과 연산

- 스트림 종료
  - 중간 연산
    - 자료를 거르거나 변경하여 또 다른 자료를 내부적으로 생성
  - 최종 연산
    - 생성된 내부 자료를 소모해 가면서 연산 수행
    - 마지막에 한번만 호출된다
  - 최종 연산이 호출되어야 중간 연산의 결과가 나온다

#### 중간 연산 - filter(), map()

- `filter()`은 조건을 넣고 그 조건에 맞는 참인 경우만 추출

  ```java
  sList.stream().filter(s -> s.length() >= 5).forEach(s -> System.out.println(s));
  // 스트림 생성		    중간 연산						최종 연산
  ```

  - 문자열 길이가 5 이상인 경우만 출력한다

- `map()`은 가진 자료 중 이름만 출력하는 경우 사용

  - Ex. 고객 클래스에서 고객 이름만 가져와서 출력

  ```java
  customerList.stream().map(c -> c.getName()).forEach(s -> System.out.println(s));
  ```

#### 최종 연산 - forEach(), count(), sum(), reduce()

- 최종 연산은 결과를 만드는데 주로 사용
- `forEach()`: 요소를 하나씩 꺼내는 기능
- `sum()`: 배열 요소 합계
- `count()`: 배열 요소 개수 출력
- `max(), min(), average()`

### 스트림 생성하고 사용하기

#### 정수 배열에 스트림 생성하고 사용하기

- ```java
  package stream;
  import java.util.Arrays;
  public class IntArrayTest {
  
  	public static void main(String[] args) {
  		int[] arr = {1,2,3,4,5};
  		
  		int sumVal = Arrays.stream(arr).sum();	// 배열에 저장된 값을 모두 더함
  		int count = (int)Arrays.stream(arr).count();//배열의 요소 개수 반환
  					//반환값이 long형이랑 int형으로 변환
  		System.out.println(sumVal);
  		System.out.println(count);
  	}
  }
  /*
  15
  5
  ```

  - `max(), min(), average()`도 있다

#### Collection에서 스트림 생성하고 사용하기

- Collections 인터페이스를 구현한 클래스 중 ArrayList에 스트림 생성

  ```java
  List<String> sList = new ArrayList<String>();
  sList.add("Tomas");
  sList.add("Tomas");
  sList.add("Edward");
  sList.add("Jack");
  ```

- `stream()`메서드를 사용하여 자료형 명시

  ```java
  Stream<String> stream = sList.stream();
  stream.forEach(s -> System.out.println(s));
  ```

  - stream은 ArrayList의 모든 요소를 갖고 있다

- ArrayList에 저장된 이름을 정렬하여 출력

  ```java
  Stream<String> stream = sList.stream();
  stream.sorted().forEach(s -> System.out.println(s));
  ```

  - `sorted()` 중간 연산을 메서드를 통해 정렬했다
  - **`sorted()` 메서드를 사용하려면 해당 자료 클래스가 `Comparable`인터페이스를 구현해야 된다**
    - **혹은 `Comparator` 인터페이스를 구현한 클래스를 매개변수로 지정해야된다**

- 전체 코드

  ```java
  import java.util.ArrayList;
  import java.util.List;
  import java.util.stream.Stream;
  
  public class ArrayListStreamTest {
  
  	public static void main(String[] args) {
  
  		List<String> sList = new ArrayList<String>();
  		sList.add("Tomas");
  		sList.add("Edward");
  		sList.add("Jack");
  		
  		Stream<String> stream = sList.stream();
  		stream.forEach(s->System.out.print(s + " "));
  		System.out.println();
  		
  		sList.stream().sorted().forEach(s->System.out.print(s+ " "));
  
  		sList.stream().map(s->s.length()).forEach(n->System.out.println(n));
  
  		sList.stream().filter(s->s.length() >= 5).forEach(s->System.out.println(s));
  		
  	}
  }
  ```

### 스트림의 특징

#### 자료의 대상과 관계없이 동일한 연산을 수행한다

- 스트림은 컬렉션의 여러 자료 구조에 대해 여러 작업을 일관성 있게 처리할 수 있는 메서드를 제공한다
  - 요소 값 출력, 조건에 따라 자료 추출, 합계, 평균 등

#### 한 번 생성하고 사용한 스트림은 재사용할 수 없다

- 요소들을 순회하며 작업을 했으면, 소모된 요소는 재사용할 수 없다
  - 다른 기능을 호출하기 위해서는 스트림을 새로 생성해야 된다

#### 스트림의 연산은 기존 자료를 변경하지 않는다

- 스트림을 생성하여 정렬, 합 등 여러 연산을 수행해도 기존 배열의 값은 변하지 않는다
  - 스트림 연산을 위한 메모리 공간이 별도로 존재한다

#### 스트림의 연산은 중간 연산과 최종 연산이 있다

- 중간 연산은 여러개가 적용될 수 있다
- 최종 연산은 마지막에 한번 적용된다
- 최종 연산이 호출되어야 중간 연산이 모두 적용된다
  - Ex. 정렬, 검색 중간 연산이 호출되어도 최종 연산 호출안되면 결과를 가져올 수 없다

### 프로그래머가 기능을 지정하는 reduce() 연산

- `reduce()` 연산은 내부적으로 스트림의 요소를 하나씩 소모하며 프로그래머가 직접 지정한 기능을 수행한다

- JDK에서 제공하는 `reduce()`메서드 정의

  ```java
  T reduce(T identify, BinaryOperator<T> accumulator)
  ```

  - `T identify`: 초깃값 의미
  - `BinaryOperator`인터페이스는 두 개매변수로 람다식을 구현하며, 이 람다식이 각 요소가 수행해야 할 기능이된다
    - `BinaryOperator` 인터페이스를 구현한 람다식을 직접 써도되고
    - 인터페이스를 구현한 클래스를 생성하여 대입해도 된다
    - `BinaryOperator`은 함수형 인터페이스로 `apply()`메서드를 반드시 구현해야된다
      - `reduce()`메서드가 호출될 때 `BinaryOperator`의 `apply()` 메서드가 호출된다

- 모든 요소 합 예제

  ```java
  Arrays.stream(arr).reduce(0, (a,b) -> a+b)
  ```

  - 초깃값 0
  - 스트림 요소가 매개변수로 전달되면서 합 구한다

- reduce() 사용하기

  ```java
  package stream;
  import java.util.Arrays;
  import java.util.function.BinaryOperator;
  
  class CompareString implements BinaryOperator<String>{	//BinaryOperator를 구현한 클래스 정의
  
  	@Override
  	public String apply(String s1, String s2) {		//reduce() 메서드가 호출될 때 불리는 메서드
  		if(s1.getBytes().length >= s2.getBytes().length) return s1;	//두 문자열의 길이 비교
  		else return s2;
  	}
  	
  }
  
  public class ReduceTest {
  
  	public static void main(String[] args) {
  		String[] greetings = {"안녕하세요~~~", "hello", "Good morning", "반갑습니다^^"};
  		System.out.println(Arrays.stream(greetings).reduce("", (s1, s2) -> {					//람다식 직접 구현
  			if(s1.getBytes().length >= s2.getBytes().length) return s1;	//두 문자열의 길이 비교
  			else return s2;
  		}));
  		
  		String str = Arrays.stream(greetings).reduce(new CompareString()).get(); //BinaryOperator를 구현한 클래스 이용
  		System.out.println(str);
  	}
  	
  	
  }
  ```

  - **람다식을 직접 구현하는 방식**과 직접 **`BinaryOperator` 인터페이스를 구현한 클래스 인스턴스를 매개변수로 전달하는 방식** 사용

### 스트림을 활용하여 여행객의 여행 비용 계산하기

> 15세 이상은 100만원
>
> 그 미만은 50만원
>
> 비용 계산과 고객 명단 검색 등을 스트림을 활용해 구현

- 고객 클래스 정의

  ```java
  package stream;
  
  public class TravelCustomer {
  	private String name;
  	private int age;
  	private int price;
  	
  	public TravelCustomer(String name, int age, int price) {
  		this.name = name;
  		this.age = age;
  		this.price = price;
  	}
  
  	public String getName() {
  		return name;
  	}
  
  	public int getAge() {
  		return age;
  	}
  
  	public int getPrice() {
  		return price;
  	}
  
  	@Override
  	public String toString() {
  		return "name: " + name + " age: " + age + " price: " + price;
  	}
  }
  ```

> 예제 시나리오
>
> 1. 고객의 명단을 출력한다
> 2. 여행의 총 비용을 계산한다
> 3. 고객 중 20세 이상인 사람의 이름을 정렬하여 출력

- 구현

  ```java
  package stream;
  
  public class TravelCustomer {
  	private String name;
  	private int age;
  	private int price;
  	
  	public TravelCustomer(String name, int age, int price) {
  		this.name = name;
  		this.age = age;
  		this.price = price;
  	}
  
  	public String getName() {
  		return name;
  	}
  
  	public int getAge() {
  		return age;
  	}
  
  	public int getPrice() {
  		return price;
  	}
  
  	@Override
  	public String toString() {
  		return "name: " + name + " age: " + age + " price: " + price;
  	}
  }
  ```

  ```java
  package stream;
  
  import java.util.ArrayList;
  import java.util.List;
  
  public class TravelTest {
  
  	public static void main(String[] args) {
  		
  		TravelCustomer customerLee = new TravelCustomer("이순신", 40, 100);
  		TravelCustomer customerKim = new TravelCustomer("김유신", 20, 100);
  		TravelCustomer customerHong = new TravelCustomer("홍길동", 13, 50);
  		
  		List<TravelCustomer> customerList = new ArrayList<>();
  		customerList.add(customerLee);
  		customerList.add(customerKim);
  		customerList.add(customerHong);
  		
  		System.out.println("== 고객 명단 추가된 순서대로 출력 ==");
  		customerList.stream().map(c->c.getName()).forEach(s->System.out.println(s));
  		
  		int total = customerList.stream().mapToInt(c->c.getPrice()).sum();
  		System.out.println("총 여행 비용은 :" + total + "입니다");
  		
  		System.out.println("== 20세 이상 고객 명단 정렬하여 출력 ==");
  		customerList.stream().filter(c->c.getAge() >= 20).map(c->c.getName()).sorted().forEach(s->System.out.println(s));
  	}
  }
  /*
  == 고객 명단 추가된 순서대로 출력 ==
  이순신
  김유신
  홍길동
  총 여행 비용은 :250입니다
  == 20세 이상 고객 명단 정렬하여 출력 ==
  김유신
  이순신
  ```

  - `mapToInt()` 값 가져와 정수로 변환



## 연습문제

1. 지역 내부 클래스에서 외부 클래스 메서드의 지역 변수를 사용할 수 있지만, 그 값을 변경하면 오류가 발생합니다. 이때 사용하는 지역 변수는 `final` 변수가 되기 때문입니다

2. 내부 클래스 중 클래스 이름 없이 인터페이스나 추상 클래스 자료형에 직접 대입하여 생성하는 클래스를 `익명 내부 클래스`라고 한다

3. 자바에서 제공하는 함수형 프로그래밍 방식으로 인터페이스의 메서드를 직접 구현하는 코드를 `람다식`이라고 한다

4. 람다식으로 구현할 수 있는 인터페이스는 메서드를 하나만 가져야 합니다. 이러한 인터페이스를 `함수형 인터페이스`라고 한다

5. 두 정수를 매개변수로 하는 메서드가 인터페이스에 정의되어 있다. 두 정수의 합을 반환하는 람다식을 구현하고 호출하라

   ```java
   package lambda;
   
   public interface Calc{
       public int add(int num1, int num2);
   }
   ```

   ```java
   public class CalcTest {
   
   	public static void main(String[] args) {
   		Calc cal = (x,y) -> x+y;
   		System.out.println(cal.add(2,4));
   	}
   
   }
   ```

6. 자바에서 자료처리를 추상화하여 여러 자료형의 자료를 동일하게 처리할 수 있도록 제공하는 클래스를 `스트림`이라고 한다

7. 다음과 같은 도서관에 책이 있다

   ```java
   package question7;
   import java.util.ArrayList;
   import java.util.List;
   
   class Book{
   	String name;
   	int price;
   	
   	Book(String name, int price){
   		this.name = name;
   		this.price = price;
   	}
   
   	public String getName() {
   		return name;
   	}
   
   	public void setName(String name) {
   		this.name = name;
   	}
   
   	public int getPrice() {
   		return price;
   	}
   
   	public void setPrice(int price) {
   		this.price = price;
   	}
   }
   
   public class LibraryTest {
   
   	public static void main(String[] args) {
   		List<Book> bookList = new ArrayList<>( );
   		
   		bookList.add(new Book("자바", 25000));
   		bookList.add(new Book("파이썬", 15000));
   		bookList.add(new Book("안드로이드", 30000));
   		
   		//스트림 생성하고 출력하기
   		int total = bookList.stream().mapToInt(c -> c.getPrice()).sum();
   		System.out.println("총 가격: "+total);
   		bookList.stream().filter(c -> c.getPrice() >= 20000).map(c -> c.getName()).sorted()
   																		.forEach(s->System.out.println(s));
   		
   	}
   }
   ```

   