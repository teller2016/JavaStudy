# 7~10 Review

## 7장 - 배열과 ArrayList

- 배열 초기화하기

  - 배열 자료형에 따라 정수는 0, 실수는 0.0, 객체 배열은 null로 초기화 된다

  - 혹은 배열 선언과 동시에 특정 값으로 초기화 가능하다

    ```java
    int[] studentIDs = new int[] {101,102,103};	//개수는 생략
    int[] studentIDs = new int[3] {101,102,103};// 오류 발생
    ```

    ```java
    int[] studentIDs = {101,102,103};	// new int[] 생략 가능
    ```

    ```java
    int[] studentIDs;
    studentIDs = new int[] {101, 102, 103};	//new int[] 생략 불가능
    ```

    - 자료형을 먼저 선언하고 초기화 가능( `new int[]`는 생략 불가능)

- **얕은 복사는 주소 값만 복사한다**
  - 기존 인스턴스의 주소 값만 복사한다
- **깊은 복사는 새로운 인스턴스에 값을 복사해 넣는다**

### 향상된 for문

```java
public class EnhancedForLoop {

	public static void main(String[] args) {
		String[] strArray = {"Java", "Android", "C", "Javascript"};
		
		for(String lang : strArray)		//lang 변수에 배열의 각 요소가 대입된다
			System.out.println(lang);
	}
}
```

### ArrayList

```java
ArrayList<String> li = new ArrayList<String>();
// .add .size .get(index) .remove(index) .isEmpty()
```



## 8장 - 상속과 다형성

- 상속을 통해 객체 지향 프로그램의 **유지 보수 편리함, 수정, 추가의 유연성을 제공 받는다**
- `extends` 예약어로 상속
- `protected`: 외부 클래스에서는 사용 못함. 하위 클래스에서는 사용 가능
- 하위 클래스가 생성되면 상위 클래스가 먼저 호출된다
- 하위 클래스 인스턴스를 상위 클래스 인스턴스로 형 변환하면 하위 클래스의 변수와 메서드를 사용 못한다
  - 재정의된 메서드의 경우 **인스턴스에 따라 결정된다**
  - **가상 메서드**
    - 각 클래스의 `가상 메서드 테이블`을 만들어 메서드 이름과, 메서드의 주소 위치를 저장하여, 주소값에 해당하는 메서드를 실행한다
    - **메서드는 인스턴스가 여러개 생성되어도 여러개 생성되지 않는다**
- **다형성: 하나의 코드가 여러 자료형으로 구현되어 실행되는 것**
  - Animal 클래스 상속받아 **모든 클래스를 Animal 자료형 하나로 관리 가능**


> ### 다형성 장점
>
> 1. 상위 클래스 자료형 하나로 모든 하위 클래스의 자료형을 관리할 수 있다
> 2. 상위 클래스에서 공통된 메서드를 제공하고, 필요한 경우 하위 클래스로 추가할 수 있다



## 9장 - 추상 클래스

- 추상 클래스는 항상 추상 메서드를 포함한다

  - 추상 메서드는 몸체가 없다

    ```java
    abstract int add(int x, int y);//추상 메서드
    int add(int x, int y) {}	//추상 메서드 아니다
    ```

- 추상 클래스를 상속받아 추상 메서드들을 구현하면 인스턴스 생성가능하다

```java
public abstract class Computer {	//abstract 예약어 추가
	abstract public void display();	//abstract 예약어 추가
	abstract public void typing();	//abstract 예약어 추가
	public void turnOn() {
		System.out.println("전원을 켭니다");
	}
	public void turnOff() {
		System.out.println("전원을 끕니다");
	}
}
```

- 추상 클래스는 **상속을 하기 위해 만든 클래스**
- **어떤 메서드를 구현하지 않고 추상 메서드로 남겨둘까?**
  - `구현된 메서드`: 하위 클래스에서 **공통으로 사용할 구현 코드**. 하위에서 재정의도 가능
  - `추상 메서드`: 하위 클래스가 어떤 클래스냐에 따라 구현 코드 달라짐

#### 템플릿 메서드

- 추상 클래스를 활용해 하위 클래스들이 정해진 시나리오를 실행하도록 한다
  - `final`을 이용한 메서드여서 재정의할 수 없다
  - 메서드의 실행 순서와 시나리오를 정의하는 것이다( **로직의 흐름을 정의하는 역할** )

### final 예약어

- 마지막으로 정한 것이니 더 이상 수정할 수 없다!

| 사용 위치 | 설명                                            |
| --------- | ----------------------------------------------- |
| 변수      | final 변수는 상수를 의미                        |
| 메서드    | final 메서드는 하위 클래스에서 재정의할 수 없다 |
| 클래스    | final 클래스는 상속할 수 없다                   |

## 10장 - 인터페이스

- 인터페이스는 클라이언트 프로그램에 어떤 메서드를 제공하는 미리 알려주는 **명세(specification) 또는 약속의 역할**을 한다

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

- `default` 예약어를 사용해 기본으로 제공하는 메서드 정의 가능

- `static` 예약어를 사용해 인스턴스 생성과 무관하게 사용할 수있는 메서드 정의 가능

- 메서드 2개만 선언하고, **추상 클래스**로 만들어도 된다

  - 이 추상 클래스를 다시 구현해서 사용

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

### 디폴트 메서드

- 기본으로 제공되는 메서드

- `default` 예약어 사용

  ```java
  public interface Calc {
  	...
  	default void description() {
  		System.out.println("정수 계산기를 사용합니다");
  	}
  }
  ```

  - 하위 클래스에서 재정의 가능



- 인터페이스는 **한 클래스가 여러 인터페이스를 구현할 수 있다**
- 인터페이스간에도 상속이 가능
  - **여러 개를 동시에** 상속받을 수 있다