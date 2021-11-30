# 12장 - 컬렉션 프레임워크

## 12-1 제네릭

### 제네릭이란?

- 변수, 메서드의 매개변수 등 모두 하나의 자료형을 구현된다
  - 변수나 메서드의 자료형을 필요에 따라 여러 자료형으로 바꿀 수 있으면 프로그램이 유연해진다

- `제네릭 프로그래밍`: 어떤 값이 하나의 참조 자료형이 아닌 **여러 참조 자료형**을 사용할 수 있도록 프로그래밍 하는것

### 제네릭의 필요성

- 예제: 3D 프린터

  - 프린터에 쓰이는 재료가 여러가지 이다

  - 파우더 재료 사용 3D 프린터 클래스

    ```java
    public class ThreeDPrinter {
    	private Powder material;	//재료가 파우더인 경우
    
    	public Powder getMaterial() {
    		return material;
    	}
    
    	public void setMaterial(Powder material) {
    		this.material = material;
    	}
    }
    ```

  - 플라스틱 액체 재료 사용 3D 프린터 클래스

    ```java
    public class ThreeDPrinter {
    	private Plastic material;	//재료가 플라스틱인 경우
    
    	public Plastic getMaterial() {
    		return material;
    	}
    
    	public void setMaterial(Plastic material) {
    		this.material = material;
    	}
    }
    ```

    - 재료만 바뀌었는데 기능이 같으면 클래스 2개를 만드는 것은 **비효율적이다**

- **어떤 재료든 쓸 수 있도록 material 변수를 `Object`로 사용**

  ```java
  public class ThreeDPrinter {
  	private Object material;
  
  	public Object getMaterial() {
  		return material;
  	}
  
  	public void setMaterial(Object material) {
  		this.material = material;
  	}
  }
  ```

  - `Object`는 최상위 클래스라 **모든 클래스는 `Object`로 변환할 수 있다**

  - 코드 구현

    ```java
    ThreeDPrinter printer = new ThreeDPrinter();
    
    Powder p1 = new Powder();
    printer.setMaterial(p1);	//자동 형 변환됨
    
    Powder p2 = (Powder)printer.getMaterial();	// 직접 형 변환을 해야됨
    ```

    - `setMaterial()`의 경우 매개변수 자료형이 `Object`라 자동으로 형 변환된다
    - `getMaterial()`의 경우 반환형이 `Object`라 직접 형 변환 해줘야됨
      - **형 변환하는 번거로움을 없애주는 프로그래밍 방식이 `제네릭`이다**
      - 참조 자료형이 쓰일 수 있는 곳에 특정 자료형을 지정하지 않고, 클래스나 메서드를 정의한 후 사용하는 시점에 어떤 자료형을 사용할지 지정하는 방식이다

### 제네릭 클래스 정의하기

- 제네릭에서는 참조 자료형 사용 부분에 `Object`가 아닌 하나의 문자를 표현한다

  ```java
  public class ThreeDPrinter<T> {	//제네릭 클래스  // type의 약자 T사용. 자료형 매개변수
  	private T material;	
  
  	public T getMaterial() {
  		return material;
  	}
  
  	public void setMaterial(T material) {
  		this.material = material;
  	}
  }
  ```

  - 여러 자료형 역할을 할 material 변수의 자료형을 `T`라고 썼다
    - `T`를 `자료형 매개변수(type parameter)`라고 한다

#### 다이아몬드 연산자 <>

- 자바 7부터 제네릭 자료형의 클래스 선언시 생성자에 사용하는 자료형을 명시하지 않을 수 있다

  ```java
  ArrayList<String> list = new ArrayList<>();	//'< >'로 생략가능
  ```

  - `<>`: `다이아몬드 연산자`
  - 선언된 자료형을 보고 컴파일러가 유추한다

#### 자료형 매개변수 T와 static

- `static` 변수, 메서드는 인스턴스 생성 없이 클래스 이름으로 호출 가능
  - `static` 변수는 인스턴스 변수가 생성되기 전에 생성된다
  - `static` 메서드는 인스턴스 변수를 사용할 수 없다
- 그런데 `T`의 자료형이 정해지는 순간은 제네릭 클래스의 인스턴스가 생성되는 순간이다
  - **즉, `static` 변수의 자료형, `static` 메서드 내부 변수의 자료형으로 `T`를 사용할 수 없다**

- `T` 이외에도 `E, V, A, B`등 아무 문자나 사용해서 정의 가능하다

#### 제네릭에서 자료형 추론하기

- 자바 10부터 지역 변수의 자료형을 추론할 수 있다

  - 제네릭에도 적용된다

    ```java
    ArrayList<String> list = new ArrayList<String>();
    //를
    var list = new ArrayList<String>();
    ```

### 제네릭 클래스 사용하기

- 파우더 재료인 프린터 선언 및 생성

  ```java
  GenericPrinter<Powder> powderPrinter = new GenericPrinter<Powder>();
  powderPrinter.setMaterial(new Powder());
  Powder powder = powderPrinter.getMaterial(); // 명시적 형 변환을 하지 않음
  ```

  - `T`로 정의한 클래스 부분에 `Powder`형으 넣어줬다
  - `GenericPrinter<Powder>`에서 자료형을 명시해서 `getMaterial()` 메서드 반환할 때 형 변환이 필요없다
  - `대입된 자료형`: T 위치에 사용된 자료형
  - `제네릭 자료형`: 자료형을 대입해서 만든 `GenericPrinter<Powder>`

- class 파일을 생성할 때 `T`를 사용한 곳에 지정된 자료형에 따라 컴파일한다
  - 컴파일러가 자료형을 확인해줘서 안정적이며 형 변환 코드가 줄어든다

#### 제네릭 클래스 사용 예제

- Powder 클래스 정의

  ```java
  public class Powder {
  	public void doPrinting() {
  		System.out.println("Powder 재료로 출력합니다");
  	}
  	
  	public String toString() {
  		return "재료는 Powder 입니다";
  	}
  }
  ```

- Plastic 클래스 정의

  ```java
  public class Plastic{
  
  	public void doPrinting() {
  		System.out.println("Plastic 재료로 출력합니다");
  	}
  	
  	public String toString() {
  		return "재료는 Plastic 입니다";
  	}
  }
  ```

- 프린터를 제네릭 클래스로 정의

  ```java
  public class GenericPrinter<T> {
  	private T material;
  
  	public T getMaterial() {
  		return material;
  	}
  
  	public void setMaterial(T material) {	//T 자료형 변수 material을 반환하는 제네릭 메서드
  		this.material = material;
  	}
  	
  	public String toString() {
  		return material.toString();
  	}
  }
  ```

- 프로그램 실행

  ```java
  public class GenericPrinterTest {
  
  	public static void main(String[] args) {
  		GenericPrinter<Powder> powderPrinter = new GenericPrinter<Powder>();
  		
  		powderPrinter.setMaterial(new Powder());
  		Powder powder = powderPrinter.getMaterial();
  		System.out.println(powderPrinter);
  		
  		GenericPrinter<Plastic> plasticPrinter = new GenericPrinter<Plastic>();
  		
  		plasticPrinter.setMaterial(new Plastic());
  		Plastic plastic = plasticPrinter.getMaterial();
  		System.out.println(plasticPrinter);
  	}
  
  }
  /*
  재료는 Powder 입니다
  재료는 Plastic 입니다
  ```

### T 자료형에 사용할 자료형을 제한하는 <T extends 클래스>

- 제네릭 클래스에서 `T` 자료형에 사용할 자료형에 제한을 둘 수 있다
  - EX. 재료로 물을 사용하면 안된다
  - 방지하기 위해 **`extends` 예약어를 사용한다**

- 사용할 재료 클래스를 추상 클래스에서 상속 받도록 한다

  > [`Powder`], [`Plastic`] ------> [*`Material`*]

  - `Material` 클래스는 추상 클래스로 정의한다

  ```java
  public abstract class Material {
  	public abstract void doPrinting();
  }
  ```

  ```java
  public class Powder extends Material {
  	public void doPrinting() {
  		System.out.println("Powder 재료로 출력합니다");
  	}
  	
  	public String toString() {
  		return "재료는 Powder 입니다";
  	}
  }
  ```

  ```java
  public class Plastic extends Material{
  
  	public void doPrinting() {
  		System.out.println("Plastic 재료로 출력합니다");
  	}
  	
  	public String toString() {
  		return "재료는 Plastic 입니다";
  	}
  }
  ```

  - `<T extends Material>` 사용 코드

    ```java
    public class GenericPrinter<T extends Material> {
    	private T material;
    
    	...
    }
    ```

    - `extends` 예약어를 통해 **사용할 수 있는 자료형에 제한을 뒀다**

#### <T extends 클래스>로 상위 클래스 메서드 사용하기

- <T extends 클래스>로 선언하면 제네릭 클래스 사용시 **상위 클래스에서 선언한 메서드를 사용할 수 있다**

- 사용하지 않은 경우

  ```java
  public class GenericPrinter<T>{
      private T material;
  }
  ```

  - T는 컴파일 할때 `Object`클래스로 변환된다
    - `Object`가 제공하는 메서드만 사용 가능

- <T extends 클래스> 사용할 경우

  ```java
  public class GenericPrinter<T extends Material>{
      private T material;
  }
  ```

  - `doPrinting()`메서드를 사용할 수 있다
  - **컴파일시 T 자료형이 `Object`가 아닌 `Material`로 변환된다**

- 예제 코드

  ```java
  public class GenericPrinter<T extends Material> {
  	private T material;
  
  	...
  	
  	public void printing() {
  		material.doPrinting(); 		//상위 클래스 Material의 메서드 호출
  	}
  }
  ```

  ```java
  public class GenericPrinterTest2 {
  
  	public static void main(String[] args) {
  
  		GenericPrinter<Powder> powderPrinter = new GenericPrinter<Powder>();
  		powderPrinter.setMaterial(new Powder());
  		powderPrinter.printing();
  		
  		GenericPrinter<Plastic> plasticPrinter = new GenericPrinter<Plastic>();
  		plasticPrinter.setMaterial(new Plastic());
  		plasticPrinter.printing();
  	}
  }
  /*
  Powder 재료로 출력합니다
  Plastic 재료로 출력합니다
  ```

### 제네릭 메서드 활용하기

- 메서드의 매개변수를 자료형 매개변수로 사용하는 경우

- 자료형 매개변수가 하나 이상인 경우

- 제네릭 메서드의 일반 형식

  > public <자료형 매개변수> (반환형) (메서드이름) (자료형 매개변수 ...) {}

- 자료형 매개변수를 여러 개 사용하는 제네릭 메서드 예제

  ```java
  public class Point<T, V> {
  	T x;
  	V y;
  	
  	Point(T x, V y){
  		this.x = x;
  		this.y = y;
  	}
  	
  	public T getX() {	//제네릭 메서드
  		return x;
  	}
  	
  	public V getY() {	//제네릭 메서드
  		return y;
  	}
  }
  ```

  - 두 좌표 x,y는 정수일 수도 있고 실수일 수도 있다

    - 그래서 `T, V`라는 자료형 매개변수 사용

  - 활용

    ```java
    Point<Integer, Double> p1 = new Point<>(0, 0.0);
    Point<Integer, Double> p2 = new Point<>(10, 10.0);
    ```

  - 사각형 넓이 구하는 예제

    ```java
    public class GenericMethod {
    
    	public static <T, V> double makeRectangle(Point<T, V> p1, Point<T, V> p2) {
    		double left = ((Number)p1.getX()).doubleValue();
    		double right = ((Number)p2.getX()).doubleValue();
    		double top = ((Number)p1.getY()).doubleValue();
    		double bottom = ((Number)p2.getY()).doubleValue();
    		
    		double width = right - left;
    		double height = bottom - top;
    		
    		return width*height;
    	}
    	
    	public static void main(String[] args) {
    		Point<Integer, Double> p1 = new Point<>(0,0.0);
    		Point<Integer, Double> p2 = new Point<>(10, 10.0);
    		
    		double rect = GenericMethod.<Integer, Double>makeRectangle(p1, p2);
    		System.out.println("두 점으로 만든 사각형 넓이: "+rect);
    	}
    
    }
    /*
    두 점으로 만든 사각형 넓이: 100.0
    ```

    - `GenericMethod` 클래스는 제네릭 클래스가 아니지만 내부에서 제네릭 클래스 구현할 수 있다
    - `makeRectangle()` 메서드의 `T, V`는 메서드 내부에서만 유효하다

    ```java
    class Shape<T>{
        public static <T, V> double makeRectangle(Point<T, V> p1, Point<T, V> p2){
            ...
        }
    }
    ```

    - Shape<T> 와 makeRectangle()에서 사용한 T는 전혀 다른 의미다

### 컬렉션 프레임워크에서 사용하는 제네릭

- 컬렉션 프레임워크에서 다양한 자료형을 관리하기 위해 제네릭을 자주 사용한다

- ArrayList 예 - ArrayList 클래스 정의

  ```java
  public class ArrayList<E> extends AbstractList<E> implements List<E>, RandomAccess, Cloneable, java.io.Serialiszable{
      ...
  }
  ```

  - 배열을 요소를 가지므로 `Element`의미하는 `E`사용

  - `E`에 원하는 자료형을 넣어 배열을 사용할 수 있다

    ```java
    ArrayList<String> list = new ArrayList<String>();
    ```

    - `get()`메서드

      ```java
      public E get(int index){
          rangeCheck(index);
          return elementData(index);
      }
      ```

      - `E` 반환형은 ArrayList를 생성할 때 사용한 자료형이다
      - 즉, 여기서는 String이다



## 12-2 컬렉션 프레임워크

### 컬렉션 프레임워크란?

- 프로그램 개발에 사용하는 자료를 관리하기 위해 `자료구조(data structure)`을 사용한다
  - 자료구조는 프로그램 실행 중 메모리에 자료를 유지,관리하기 위해 사용
- 자바에서는 필요한 자료 구조를 미리 구현하여 java.util 패키지에 제공하고 있다
  - 이를 `컬렉션 프레임워크(collection framework)`라 한다
- 자바 컬렉션 프레임워크에는 여러 가지 인터페이스가 정의되어 있다
  - 이 인터페이스들을 구현한 클래스 또한 있다
- 컬렉션 프레임워크의 전체 구조는 `Collection` 인터페이스와 `Map`인터페이스 기반으로 이루어져 있다
  - `Collection`인터페이스는 하나의 자료를 모아서 관리하는 데 필요한 기능 제공
  - `Map` 인터페이스는 **쌍(pair)**으로 된 자료를 관리하는데 유용한 기능 제공

### Collection 인터페이스

> [ArrayList],[Vector],[LinkedList] ----> [List] ----> [Collection]
> [HashSet],[TreeSet]  				    ---->[Set]  ---->

- `Collections`인터페이스 하위에 `List, Set`인터페이스가 있다

  - `List`: 순차적인 자료를 관리하는데 사용하는 클래스를 구현
  - `Set`: 중복되지 않는 객체를 다루는데 사용

- Collection 인터페이스에 선언된 메서드 중 자주 사용하는 메서드

  | 메서드                   | 설명                                                      |
  | ------------------------ | --------------------------------------------------------- |
  | boolean add(E e)         | Collection에 객체를 추가                                  |
  | void clear()             | Collection의 모든 객체를 제거                             |
  | Iterator<E> iterator     | Collection을 순환할 반복자(iterator)를 반환               |
  | boolean remove(Object o) | Collection에 배개변수에 해당하는 인스턴스가 존재하면 제거 |
  | int size()               | Collection에 있는 요소의 개수를 반환                      |

### Map 인터페이스

> [Properties] ---> [HashTable] ---> [Map]
> 							   [HashMap] --->
> 							    [TreeMap] --->

- `Map` 인터페이스는 쌍으로 되어 있는 자료를 관리하는 메서드들이 선언되어 있다

  - `key - value`쌍으로 표현
    - key값은 중복될 수 없다

- `Map`은 기본적으로 **검색용 자료구조 이다**

- 주요 메서드

  | 메서드                                   | 설명                                                         |
  | ---------------------------------------- | ------------------------------------------------------------ |
  | V put(K key, V value)                    | key에 해당하는 value 값을 map에 넣는다                       |
  | V get(K key)                             | key에 해당하는 value 값을 반환                               |
  | boolean isEmpty()                        | Map이 비었는지 여부를 반환                                   |
  | boolean containsKey(Object key)          | Map에 해당 key가 있는지 여부를 반환                          |
  | boolean containsValue(Object value)      | Map에 해당 value가 있는지 여부를 반환                        |
  | Set keyset()                             | key 집합을 Set로 반환(중복 안되므로 Set)                     |
  | Collection values()                      | value를 Collection으로 반환(중복 무관)                       |
  | V remove(key)                            | key가 있는 경우 삭제                                         |
  | boolean remove(Object key, Object value) | key가 있는 경우 key에 해당하는 value가 매개변수와 일치할 때 삭제 |

### 실습 패키지 구조

- 회원 관리 프로그램

  - 회원 추가
  - 회원 삭제
  - 전체 회원 정보 출력

- 회원을 나타내는 Member 클래스 구현

  ```java
  package collection;
  
  public class Member {
  	private int memberId;
  	private String memberName;
  	
  	public Member(int memberId, String memberName) {
  		this.memberId = memberId;
  		this.memberName = memberName;
  	}
  
  	public int getMemberId() {
  		return memberId;
  	}
  
  	public void setMemberId(int memberId) {
  		this.memberId = memberId;
  	}
  
  	public String getMemberName() {
  		return memberName;
  	}
  
  	public void setMemberName(String memberName) {
  		this.memberName = memberName;
  	}
  
  	@Override
  	public String toString() {
  		return memberName + "회원님의 아이디는 "+ memberId+ "입니다";
  	}
  }
  ```

  

## 12-3 List 인터페이스

- `List 인터페이스`에는 객체를 순서에 따라 저장하고 유지하는 데 필요한 메서드가 선언되어 있다
  - 대표적으로 `ArrayList`, `Vector`가 있고, 순차 자료 구조를 구현한 `LinkedList`가 있다

### ArrayList 클래스

- 객체 순서를 기반으로 순차적으로 자료를 관리하는데 사용

> collection 패키지 -- Member.java
> collection.arrayList 패키지 -- MemberArrayList.java
>
> - 공통으로 사용할 Member 클래스는 collections 패키지에 있고
> - MemberArrayList 클래스를 활용할 관리 클래스는 collection.arraylist 패키지에 있다

#### ArrayList를 활용해 회원 관리 프로그램 구현하기

- 회원추가, 회원삭제, 전체출력 메서드 제공

  ```java
  package collection.arraylist;
  
  import java.util.ArrayList;
  import collection.Member;	//collections 패키지에 있는 Member 클래스 import
  
  public class MemberArrayList {
  	private ArrayList<Member> arrayList;
  	
  	public MemberArrayList() {
  		arrayList = new ArrayList<Member>();
  	}
  	
  	public void addMember(Member member) {		//회원 추가
  		arrayList.add(member);
  	}
  	
  	public boolean removeMember(int memberId) {		//회원 삭제
  		for(int i=0; i<arrayList.size(); i++) {
  			Member member = arrayList.get(i);
  			int tempId = member.getMemberId();
  			if(tempId == memberId) {
  				arrayList.remove(i);
  				return true;
  			}
  		}
  		System.out.println(memberId+"가 존재하지 않는다");
  		return false;
  	}
  	
  	public void showAllMember() {		//회원 정보 전체 출력
  		for(Member member : arrayList)
  			System.out.println(member);
  		System.out.println();
  	}
  }
  ```

#### MemberArrayList 테스트 클래스 구현하기

```java
package collection.arraylist;

import collection.Member;

public class MemberArrayListTest {

	public static void main(String[] args) {
		MemberArrayList memberArrayList = new MemberArrayList();
		
		Member memberLee = new Member(1001, "이지원");
		Member memberSon = new Member(1002, "손민국");
		Member memberPark = new Member(1003, "박서훤");
		Member memberHong = new Member(1004, "홍길동");
		
		memberArrayList.addMember(memberLee);
		memberArrayList.addMember(memberSon);
		memberArrayList.addMember(memberPark);
		memberArrayList.addMember(memberHong);
		
		memberArrayList.showAllMember();
		
		memberArrayList.removeMember(memberHong.getMemberId());
		memberArrayList.showAllMember();
	}
}
/*
이지원회원님의 아이디는 1001입니다
손민국회원님의 아이디는 1002입니다
박서훤회원님의 아이디는 1003입니다
홍길동회원님의 아이디는 1004입니다

이지원회원님의 아이디는 1001입니다
손민국회원님의 아이디는 1002입니다
박서훤회원님의 아이디는 1003입니다
```

### ArrayList와 Vector 클래스

- ArrayList처럼 배열을 구현한 클래스
- ArrayList와 Vector의 차이는 **동기화 지원 여부**이다
  - `동기화(synchronization)`: 2개 이상의 스레드가 동시에 Vector을 사용할 때 오류가 나지 않도록 실행 순서를 보장하는것

#### 스레드와 멀티스레드 프로그래밍

- `스레드`: 간단히 **작업 단위**라 할 수 있다

- 프로그램이 메모리에서 수행되려면 **스레드 작업이 생성되어야 한다**

  - 하나의 스레드만 수행되면 `단일 스레드(single thread)`
  - 두 개 이상의 스레드가 동시에 실행되면 `멀티스레드(multi-thread)`
    - 같은 메모리 공간(리소스)에 접근하기 때문에 변수 값이나 메모리 상태에 오류 발생 가능
    - 메모리에 동시에 접근하지 못하도록 순서를 맞추는 것이 **동기화**

- 멀티스레드 환경이 아닌 경우 **ArrayList**를 사용 권장

  - **Vector**의 경우 동기화 구현을 위해 동시 작업이 이루어지는 자원에 대해 **잠금(Lock)**이 이루어져 느리다
  - Vector의 모든 메서드는 호출될 때 마다 **잠금과 해제가 일어난다**

- ArrayList를 사용해 구현하다 동기화가 필요하면 해당 코드 쓰면된다

  ```java
  Collections.synchronizedList(new ArrayList<String>());
  ```

### LinkedList 클래스

- 배열의 단점
  - 생성할 때 정적 크기로 선언
  - 물리적 순서와 논리적 순서가 동일하다
  - 자료 삽입, 삭제시 나머지 자료를 이동시켜서 빈 공간을 없애야 된다
  - 처음 선언 배열 크기 이상으로 요소가 추가되면 더 큰 배열을 생성해 복사해야 된다
- 이러한 단점을 개선한 자료 구조를 `링크드 리스트(linked list)`라 한다

#### 링크드 리스트 구조

- 각 요소는 **다음 요소를 가리키는 주소 값을 가진다**
  - 물리적인 메모리는 떨어져 있어도 논리적으로는 순서가 있다
- ArrayList에 비해 **삽입, 제거 속도가 빠르며, 크기를 동적으로 증가시킬 수 있다**

> [data, 다음 요소 주소] --> [data, 다음 요소 주소] --> [data, null]

#### 링크드 리스트에 요소 추가하기

- 배열이라면 요소를 추가하기 위해 뒷 요소를 뒤로 밀어서 넣어야 된다

- 링크드 리스트는 서로 가리키는 **주소 값만 변경하면 된다**

  > A --> B --> D --> null
  >
  > A --> B --> C --> D --> null

#### 링크드 리스트의 요소 제거하기

- A,B,C 중 B를 제거하려면 A가 C를 가리키게 하면 된다
  - 제거된 B의 메모리는 가비지 컬렉터가 수거한다

#### 배열과 링크드 리스트의 다른 점

- 배열
  - 생성할 때 용량 지정
  - 용량 초과시 용량을 늘리고 요소 복사
- 링크드 리스트
  - 요소 추가마다 동적으로 요소의 메모리를 생성
  - 자료 추가, 삭제시 자료의 이동이 배열보다 적다
- 배열이 링크드 리스트보다 효율적인 경우
  - 배열을 물리적으로 연결된 자료 구조라 i번째 요소를 바로 계산할 수 있어 **접근이 빠르다**
  - 링크드 리스트보다 구현하기 쉽다
- 자료의 변동이(삽입,삭제)가 많은 경우 **링크드 리스트**, 자료 변동이 거의 없는 경우 **배열**이 효율적이다

#### LinkedList 클래스 사용하기

```java
package collection;
import java.util.LinkedList;
public class LinkedListTest {

	public static void main(String[] args) {
		LinkedList<String> myList = new LinkedList<String>();
		
		myList.add("A");
		myList.add("B");
		myList.add("C");
		
		System.out.println(myList);		//리스트 전체 출력
		
		myList.add(1, "D");	//링크드 리스트 첫번째 위치에 추가
		System.out.println(myList);
		
		myList.addFirst("O"); //링크드 리스트 맨 앞에 추가
		System.out.println(myList);
		
		System.out.println(myList.removeLast());	//연결 리스트의 맨 뒤 요소 삭제 후 해당 요소 출력
		System.out.println(myList);
	}
}
/*
[A, B, C]
[A, D, B, C]
[O, A, D, B, C]
C
[O, A, D, B]
```

- `addFirs(), addLast(), removeFirst(), removeLast()` 등의 메서드 존재
  - 스택, 큐에서 다양하게 활용가능

### ArrayList로 스택과 큐 구현하기

- 스택은 상자를 쌓듯이 자료를 관리하는 방식
  - `LIFO(Last In First Out)`: 맨 나중에 추가된 데이터를 먼저 꺼내는 방식
- 큐는 '선착순' 방식
  - `FIFO(First In First Out)`: 먼저 추가된 데이터부터 꺼내서 사용하는 방식

#### ArrayList로 스택 구현하기

- 스택에 자료추가: `push()`

- 자료 꺼내기: `pop()`

- 스택 구현하기

  ```java
  package collection.arraylist;
  import java.util.ArrayList;
  
  class MyStack{
  	private ArrayList<String> arrayStack = new ArrayList<String>();
  	
  	public void push(String data) {
  		arrayStack.add(data);
  	}
  	
  	public String pop() {
  		int len = arrayStack.size();
  		if(len == 0) {
  			System.out.println("스택이 비었습니다");
  			return null;
  		}
  		
  		return(arrayStack.remove(len-1));		//가장 최근에 추가된 요소 반환
  	}
  }
  
  public class StackTest {
  	public static void main(String[] args) {
  		MyStack stack = new MyStack();
  		stack.push("A");
  		stack.push("B");
  		stack.push("C");
  		
  		System.out.println(stack.pop());
  		System.out.println(stack.pop());
  		System.out.println(stack.pop());
  	}
  }
  /*
  C
  B
  A
  ```

#### ArrayList로 큐 구현하기

- 큐 구현하기

  ```java
  package collection.arraylist;
  
  import java.util.ArrayList;
  
  class MyQueue{
  	
  	private ArrayList<String> arrayQueue = new ArrayList<String>();
  	
  	public void enQueue(String data) {
  		arrayQueue.add(data);
  	}
  	
  	public String deQueue() {
  		int len = arrayQueue.size();
  		if(len == 0 ) { 
  			System.out.println("큐가 비었습니다");
  			return null;
  		}
  		
  		return(arrayQueue.remove(0));		//맨 앞의 자료 반환
  	}
  }
  
  public class QueueTest {
  
  	public static void main(String[] args) {
  
  		MyQueue queue = new MyQueue();
  		queue.enQueue("A");
  		queue.enQueue("B");
  		queue.enQueue("C");
  		
  		System.out.println(queue.deQueue());
  		System.out.println(queue.deQueue());
  		System.out.println(queue.deQueue());
  	}
  }
  /*
  A
  B
  C
  ```

  - 스택과 구현이 비슷하다, 단지 맨 앞 혹은 맨 뒤 자료를 반환하는 차이가 있다

### Collection 요소를 순회하는 Iterator

- for문과 get(i)를 통해 배열을 순회하였다

  - **Set 인터페이스를 구현한 경우 get(i) 메서드를 사용할 수 없다**

- `Iterator`은 `Collection` 인터페이스를 구현한 객체에 정의되어 있는 `iterator()` 메소드를 호출하여 참조한다

  - `iterator()`메서드는 `Iterator`클래스가 반환된다

    ```java
    Iterator ir = memberArrayList.iterator();
    ```

#### Iterator을 사용하여 요소를 순회할 때 사용하는 메서드

- 모든 요소를 순회할 때 사용하는 2가지 메서드

  | 메서드            | 설명                                                         |
  | ----------------- | ------------------------------------------------------------ |
  | boolean hasNext() | 이후에 요소가 더 있는지를 체크하는 메서드이다. 요소가 있다면 true 반환 |
  | E next()          | 다음에 있는 요소를 반환                                      |

- `MemberArrayList` 클래스의 `removeMember()`메서드 수정

  ```java
  public boolean removeMember(int memberId) {
  		Iterator<Member> ir = arrayList.iterator();	//Iterator 반환
      
  		while(ir.hasNext()) {
  			Member member = ir.next();
  			int tempId = member.getMemberId();
  			if(tempId == memberId) {
  				arrayList.remove(member);		//해당 회원 삭제
  				return true;
  			}
  		}
  		System.out.println(memberId+"가 존재하지 않는다");
  		return false;
  	}
  ```

  - 순서가 없는 클래스도 Iterator을 사용하면 요소를 순회할 수 있다



## 12-4 Set 인터페이스