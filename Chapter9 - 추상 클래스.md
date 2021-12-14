# 9장 - 추상 클래스

## 9-1 추상 클래스

### 추상 클래스란?

- 추상적: 구체적이지 않고 막연한 것

  - `추상 클래스(abstract class)`: 구체적이지 않은 클래스
  - `concrete class`: 추상 클래스가 아닌 클래스

- 추상 클래스 문법

  - 추상 클래스는 **항상 추상 메서드를 포함한다**
    - 추상 메서드는 구현코드 즉 함수 몸체(body)가 없다

  ```java
  int add(int x, int y){	//{}안의 내용이 함수 몸체이다
      return x+y;
  }
  ```

  - `구현부(implementation)`: 중괄호 `{}`로 감싼 부분
  - `구현부`가 없는 함수는 `추상 함수(abstract function)`이며 자바에서 `추상 메서드(abstract method)`라고 한다

  ```java
  abstract int add(int x, int y);//추상 메서드
  int add(int x, int y) {}	//추상 메서드 아니다
  ```

  - 추상 메서드는 위와 같이 선언하며 `abstract` 예약어를 사용한다 [ `{}`대신 `;`를 사용 ]

### 추상 클래스 구현하기

> 클래스 다이어그램 이해
>
> 기울임꼴로 표시하면 추상 클래스, 추상 메서드이다
>
> EX
> [*`Computer`*]
> [*`display()`*, *`typing()`*, `turnOn()`, `turnOff()`]
> ^									^
> |									|
> [`DeskTop`]			[*`NoteBook`*]
> 								[`typing()`]
> 									^
> 									|
> 								[`MyNoteBook`]
>
> - `Computer` 클래스는 추상 클래스이며, 이를 상속받은 DeskTop은 일반 클래스, NoteBook은 추상 클래스이다
>   - `NoteBook`클래스를 상속받은 `MyNoteBook`은 일반 클래스
>   - `display()`, `typing()`은 추상 메서드, `turnOn()`,` turnOff()`는 구현코드가 있는 메서드

- 추상 클래스 구현

  ```java
  public class Computer {
  	public void display();	//오류 발생
  	public void typing();	//오류
  	public void turnOn() {
  		System.out.println("전원을 켭니다");
  	}
  	public void turnOff() {
  		System.out.println("전원을 끕니다");
  	}
  }
  ```

  - **몸체를 작성**하거나, **추상 메서드로 바꿔**서 오류 해결 가능하다

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

  - **추상 메서드가 속한 클래스는 추상 클래스여야 한다**
  - Computer 클래스 의미
    - Computer 클래스를 상속받는 클래스 중 `turnOn()`과 `turnOff()` 구현은 공통이다
    - 하지만 `display()`와 `typing()`은 하위 클래스에 따라 구현이 달라질 수 있다
      - 두 메서드의 구현은 추상 클래스를 상속받은 `DeskTop`과 `NoteBook`에서 실제로 구현된다

- 추상 클래스 상속받기

  ```java
  public class DeskTop extends Computer {
  
  	@Override
  	public void display() {
  		System.out.println("DeskTop display()");
  	}
  
  	@Override
  	public void typing() {
  		System.out.println("DeskTop typing()");
  	}
  }
  ```

  ```java
  public abstract class NoteBook extends Computer {	//추상 클래스
  
  	@Override
  	public void display() {
  		System.out.println("NoteBook display()");
  	}
  
  }
  ```

  - 추상 클래스를 상속받으면 **구현되지 않은 추상 메서드를 모두 구현**해야된다
    - **혹은 하위 클래스도 추상 클래스로 만들어야 된다**

  ```java
  public class MyNoteBook extends NoteBook {
  
  	@Override
  	public void typing() {
  		System.out.println("MyNoteBook typing()");
  	}
  }
  ```

  - 상위 추상 클래스에서 구현하지 않은 `typing()` 구현하여 **모든 추상 메서드가 구현되었다**

- **모든 추상 메서드를 구현한 클래스에 abstract 예약어를 사용한다면?**
  - 모든 메서드를 구현했어도 abstract 사용하면 추상 클래스이다
  - 사용할 목적이 아닌 **상속만을 위해 사용한다**
    - **new 예약어로 인스턴스 생성 못한다**

### 추상 클래스를 만드는 이유

- 테스트 프로그램

  ```java
  public class ComputerTest {
  
  	public static void main(String[] args) {
  		Computer c1 = new Computer();	//오류
  		Computer c2 = new DeskTop();
  		Computer c3 = new NoteBook();	//오류
  		Computer c4 = new MyNoteBook();
  	}
  }
  ```

#### 추상 클래스는 인스턴스로 생성할 수 없다

- 모든 메서드가 구현되지 않아 **인스턴스로 생성 못한다**

#### 추상 클래스에서 구현하는 메서드

- 추상 클래스는 **상속을 하기 위해 만든 클래스**
- **어떤 메서드를 구현하지 않고 추상 메서드로 남겨둘까?**
  - `구현된 메서드`: 하위 클래스에서 **공통으로 사용할 구현 코드**. 하위에서 재정의도 가능
  - `추상 메서드`: 하위 클래스가 어떤 클래스냐에 따라 구현 코드 달라짐



## 9-2 템플릿 메서드

### 추상 클래스와 템플릿 메서드

- `템플릿`: 틀이나 견본

- `템플릿 메서드`: 디자인 패턴의 한 방법. 틀이 있는 메서드

  ```java
  package template;
  
  public abstract class Car {
  	public abstract void drive();
  	public abstract void stop();
  	
  	public void startCar() {
  		System.out.println("시동을 켭니다");
  	}
  	
  	public void turnOff() {
  		System.out.println("시동을 끕니다");
  	}
  	
  	final public void run() {	//템플릿 메서드
  		startCar();
  		drive();
  		stop();
  		turnOff();
  	}
  }
  ```

  - `startCar()`, `turnOff()`는 미리 구현, `drive()`, `stop()`은 차종에 따라 다른 방식으로 구현
  - `run()`
    - 자동차가 달리는 방법을 순서대로 구현했다
    - 상속받은 모든 자동차는 이 순서대로 **동일한 방식으로 달리게 된다**

- `AICar`, `ManualCar` 구현

  ```java
  public class AICar extends Car {
  
  	@Override
  	public void drive() {
  		System.out.println("자율 주행합니다");
  		System.out.println("자동차가 스스로 방향을 전환합니다.");
  	}
  
  	@Override
  	public void stop() {
  		System.out.println("스스로 멈춥니다.");
  	}
  }
  ```

  ```java
  public class ManualCar extends Car{
  
  	@Override
  	public void drive() {
  		System.out.println("사람이 운전합니다");
  		System.out.println("사람이 핸들을 조작합니다");
  	}
  
  	@Override
  	public void stop() {
  		System.out.println("브레이크로 정지합니다");
  	}
  }
  ```

- 테스트 프로그램

  ```java
  public class CarTest {
  
  	public static void main(String[] args) {
  		
  		System.out.println("=== 자율주행 하는 자동차 ===");
  		Car myCar = new AICar();
  		myCar.run();
  		
  		System.out.println("=== 사람이 운전하는 자동차 ===");
  		Car hisCar = new ManualCar();
  		hisCar.run();
  	}
  }
  /*
  === 자율주행 하는 자동차 ===
  시동을 켭니다
  자율 주행합니다
  자동차가 스스로 방향을 전환합니다.
  스스로 멈춥니다.
  시동을 끕니다
  === 사람이 운전하는 자동차 ===
  시동을 켭니다
  사람이 운전합니다
  사람이 핸들을 조작합니다
  브레이크로 정지합니다
  시동을 끕니다
  */
  ```

  - `run()` 템플릿 메서드에 따라 정해진 시나리오대로 실행됐다

### 템플릿 메서드의 역할

- 템플릿 메서드의 역할은 **메서드 실행 순서와 시나리오는 정의하는 것** (**`로직의 흐름을 정의하는 역할`**)
  - 템플릿 메서드에서 호출하는 메서드가 **추상 메서드**이면 클래스에 따라 구현 방식이 다르겠지만
    **시나리오는 변하지 않는다**
- 템플릿 메서드는 실행 순서, 즉 시나리오를 정의한 메서드이므로 바꿀 수 없다 
  - **하위 클래스에서 재정의하면 안된다**
    - **`final`**을 사용하면 **상속받은 하위 클래스가 메서드를 재정의할 수 없다**



## 9-3 템플릿 메서드 응용하기

> Player에 따라 할 수 있는 세 가지 기능
> `run()`, `jump()`, `turn()`
>
> 모든 레벨의 Player가 사용할 수 있는 필살기
> `go(int count)`
>
> - 한번 ` run`하고, count만큼 `jump`하고, 한번 `turn`한다
> - 레벨에서 불가능한 기능일 경우 할 수 없다는 메시지 출력

### 클래스 기능과 관계

- Player 클래스를 만들고 레벨에 따라 if 조건문으로 코드 구현하면 복잡하다

### 클래스 설계하기

- 각 플레이어가 가질 수 있는 레벨을 클래스로 분리
  - 각 레벨마다 **공통 기능, 개별 기능**이 있으니 **레벨 클래스를 상속 관계**로 표현

![image-20211126153134503](C:\Users\telle\AppData\Roaming\Typora\typora-user-images\image-20211126153134503.png)

- Player 클래스

  ```java
  package gamelevel;
  
  public class Player {
  	private PlayerLevel level;		//PlayerLevel 자료형을 통해 다형성 구현 가능
  	
  	public Player() {
  		level = new BeginnerLevel();
  		level.showLevelMessage();
  	}
  	
  	public PlayerLevel getLevel() {
  		return level;
  	}
  	
  	public void upgradeLevel(PlayerLevel level) {
  		this.level = level;
  		level.showLevelMessage();
  	}
  	
  	public void play(int count) {
  		level.go(count);
  	}
  }
  ```

  - 초기 레벨을 Beginner로 지정

- PlayerLevel 클래스

  ```java
  public abstract class PlayerLevel {
  	public abstract void run();
  	public abstract void jump();
  	public abstract void turn();
  	public abstract void showLevelMessage();
  	
  	final public void go(int count) {
  		run();
  		for(int i=0;i<count;i++) 
  			jump();
  		turn();
  	}
  }
  ```

  - 각 레벨마다 `run()`, `jump()`, `turn()`, `showLevelMessage()` 메서드는 다르게 구현되기 때문에 추상 메서드로 선언
  - `go()`메서드는 시나리오대로 수행되도록 완전히 구현함

- 초보자, 중급자, 고급자 레벨 클래스

  ```java
  public class BeginnerLevel extends PlayerLevel{
  
  	@Override
  	public void run() {
  		System.out.println("천천히 달립니다.");
  	}
  
  	@Override
  	public void jump() {
  		System.out.println("Jump 할 줄 모름");
  	}
  
  	@Override
  	public void turn() {
  		System.out.println("Turn 할 줄 모름");		
  	}
  
  	@Override
  	public void showLevelMessage() {
  		System.out.println("***** 초보자 레벨 입니다. *****");
  	}
  }
  ```

  - 중급자, 고급자 레벨 클래스도 메서드만 다르고 형태는 같다

#### 테스트 프로그램 작성해서 실행하기

```java
public class MainBoard {

	public static void main(String[] args) {
		Player player = new Player();
		player.play(2);
		
		AdvancedLevel aLevel = new AdvancedLevel();
		player.upgradeLevel(aLevel);
		player.play(2);
		
		SuperLevel sLevel = new SuperLevel();
		player.upgradeLevel(sLevel);
		player.play(3);
	}
}
/*
***** 초보자 레벨 입니다. *****
천천히 달립니다.
Jump 할 줄 모름
Jump 할 줄 모름
Turn 할 줄 모름
***** 중급자 레벨 입니다. *****
빨리 달립니다.
높이 jump 합니다.
높이 jump 합니다.
Turn 할 줄 모름
***** 고급자 레벨 입니다. *****
엄청 빨리 달립니다.
아주 높이 jump 합니다.
아주 높이 jump 합니다.
아주 높이 jump 합니다.
한 바퀴 돕니다.
*/
```

### 추상 클래스와 다형성

- 모든 레벨 클래스는 PlayerLevel 클래스를 상속 받았다
  - Player 클래스에서 레벨을 별도의 자료형으로 선언하지 않고 `PlayerLevel`로 선언
    - **레벨 클래스가 여러 개 존재해도 모든 클래스는 `PlayerLevel` 클래스로 대입 가능**
    - **추상 클래스는 하위 클래스들을 하나의 자료형으로 선언 및 대입 가능**
      - 가상 메서드에 의해 각 클래스에 구현된 기능 호출된다
      - **즉** 하나의 코드가 다양한 자료형을 대상으로 동작하는 **다형성**을 활용했다



## 9-4 final 예약어

- 마지막으로 정한 것이니 더 이상 수정할 수 없다!

| 사용 위치 | 설명                                            |
| --------- | ----------------------------------------------- |
| 변수      | final 변수는 상수를 의미                        |
| 메서드    | final 메서드는 하위 클래스에서 재정의할 수 없다 |
| 클래스    | final 클래스는 상속할 수 없다                   |

### 상수를 의미하는 final 변수

- `상수`: 변하지 않는 수

```java
public class Constant {
	int num = 10;
	final int NUM = 100;	//상수 선언
	
	public static void main(String[] args) {
		Constant cons = new Constant();
		cons.num = 50;
		cons.NUM = 200;		//상수에 값을 대입해 오류
		
		System.out.println(cons.num);
		System.out.println(cons.NUM);
	}
}
```

#### 여러 자바 파일에서 공유하는 상수 값 정의하기

- 프로젝트 중 여러 파일에서 똑같이 공유해야 하는 상수 값 존재

  - 파일에서 공유해야 하는 **상수 값을 한 파일에 모아 `public static final`로 선언하여 사용**

  ```java
  public class Define {
  
  	public static final int MIN = 1;
  	public static final int MAX = 99999;
  
  	public static final int ENG = 1001;
  	public static final int MATH = 2001;
  	
  	public static final double PI = 3.14;
  	public static final String GOOD_MORNING = "Good Morning!";
  }
  ```

  - `public`으로 선언하여 외부에서도 사용 가능
  - `static`으로 선언하여 **인스턴스를 생성하지 않고** **클래스 이름으로 참조 가능**

```java
public class UsingDefine {

	public static void main(String[] args) {

		System.out.println(Define.GOOD_MORNING);   //static 으로 선언되었으므로 클래스 이름으로 참조 합니다.
		System.out.println("최솟값은 " +  Define.MIN + "입니다.");
		System.out.println("최댓값은 " +  Define.MAX + "입니다.");
		System.out.println("수학 과목 코드 값은 " + Define.MATH + "입니다.");
		System.out.println("영어 과목 코드 값은 " + Define.ENG + "입니다.");
	}
}
```

### 상속할 수 없는 final 클래스

- 클래스를 `final`로 선언하면 상속할 수 없다

  - 상속하면 변수나 메서드 재정의가 가능해진다 => 원래 클래스가 가지는 기능에 오류 발생 가능

  - **보안과 관련되어 있거나, 기반 클래스가 변하면 안되는 경우에 사용**

    ```java
    public class MyString extends String{
        //오류 발생
    }
    ```

    

## 연습문제

1. 클래스를 구현할 때 메서드 몸체를 구현하지 않고 선언만 하는 메서드를 `추상 메서드`라 하고, 이를 포함한 클래스를 `추상 클래스`라고 한다

2. 상수를 선언할 때 상속받은 클래스에서 메서드를 재정의하지 못하도록 사용하는 예약어는 `final`이다

3. 추상 클래스나 추상 메서드를 선언할 때 사용하는 예약어는 `abstract`이다

4. 로직 흐름을 정의한 메서드이며 메서드 내부에서 일반 메서드나 구현되지 않은 추상 메서드를 호출합니다. 흐름이 변하지 않도록 하위 클래스에서 재정의하지 못하게 final로 선언한 메서드를 `템플릿 메서드`라고 한다

5. Car 예제 코드 구현하기

   ```java
   package question5;
   
   public abstract class Car {
   	
   	public abstract void start();
   	public abstract void drive();
   	public abstract void stop();
   	
   	public void run() {
   		start();
   		drive();
   		stop();
   		washCar();
   	}
   	public void washCar() {
   		System.out.println("세차를 합니다");
   	}
   }
   ```

   ```java
   public class Sonata extends Car {
   
   	@Override
   	public void start() {
   		System.out.println("Sonata 시동을 켭니다");
   	}
   
   	@Override
   	public void drive() {
   		System.out.println("Sonata 달린다");
   	}
   
   	@Override
   	public void stop() {
   		System.out.println("Sonata 멈춘다");
   	}
   
   }
   ```

   ```java
   public class CarTest {
   
   	public static void main(String[] args) {
   		ArrayList<Car> carList = new ArrayList<Car>();
   		carList.add(new Sonata());
   		carList.add(new Avante());
   		
   		for(Car car : carList) {
   			car.run();
   			System.out.println("==========");
   		}
   	}
   }
   /*
   Sonata 시동을 켭니다
   Sonata 달린다
   Sonata 멈춘다
   세차를 합니다
   ==========
   Avante 시동을 켭니다
   Avante 달린다
   Avante 멈춘다
   세차를 합니다
   ==========
   */
   ```