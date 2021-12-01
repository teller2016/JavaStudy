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

- 순서와 상관없이 중복을 허용하지 않는 경우에 `Set` 인터페이스를 구현한 클래스를 사용한다
  - `Set 인터페이스`를 구현한 대표 클래스
    - `HashSet`
    - `TreeSet`

### HashSet 클래스

- `HashSet`클래스는 집합 자료 구조를 구현하며 중복을 허용하지 않는다

- 코드

  ```java
  package collection.hashset;
  import java.util.HashSet;
  public class HashSetTest {
  
  	public static void main(String[] args) {
  		HashSet<String> hashSet = new HashSet<String>();
  		hashSet.add(new String("정몰리"));
  		hashSet.add(new String("정보리"));
  		hashSet.add(new String("정말리"));
  		hashSet.add(new String("정몰리"));
  		
  		System.out.println(hashSet);
  	}
  }
  /*
  [정몰리, 정말리, 정보리]
  ```

  - 중복된 `정몰리` 문자열은 중복되어 출력되지 않는다
  - **추가된 순서와 상관없이 출력된다**

#### HashSet를 활용한 회원 관리 프로그램 구현하기

- HashSet 클래스 생성 및 `addMember()`, `removeMember()`, `showAllMember()` 메서드 구현

  ```java
  package collection.hashset;
  import java.util.HashSet;
  import java.util.Iterator;
  import collection.Member;
  public class MemberHashSet {
  	private HashSet<Member> hashSet;
  	
  	public MemberHashSet() {
  		hashSet = new HashSet<Member>();
  	}
  	
  	public void addMember(Member member) {
  		hashSet.add(member);
  	}
  	
  	public boolean removeMember(int memberId) {
  		Iterator<Member> ir = hashSet.iterator();
  		
  		while(ir.hasNext()) {
  			Member member = ir.next();
  			int tempId = member.getMemberId();
  			
  			if(tempId == memberId) {
  				hashSet.remove(member);
  				return true;
  			}
  		}
  		System.out.println(memberId+"가 존재하지 않습니다");
  		return false;
  	}
  	
  	public void showAllMember() {
  		for(Member member : hashSet)
  			System.out.println(member);
  		System.out.println();
  	}
  }
  ```

  ```java
  public class MemberHashSetTest {
  
  	public static void main(String[] args) {
  
  		MemberHashSet memberHashSet = new MemberHashSet();
  		
  		Member memberLee = new Member(1001, "이지원");
  		Member memberSon = new Member(1002, "손민국");
  		Member memberPark = new Member(1003, "박서훤");
  		
  		
  		memberHashSet.addMember(memberLee);
  		memberHashSet.addMember(memberSon);
  		memberHashSet.addMember(memberPark);
  		memberHashSet.showAllMember();
  		
  		Member memberHong = new Member(1003, "홍길동");  //1003 아이디 중복 
  		memberHashSet.addMember(memberHong);
  		memberHashSet.showAllMember();
  	}
  }
  /*
  이지원회원님의 아이디는 1001입니다
  손민국회원님의 아이디는 1002입니다
  박서훤회원님의 아이디는 1003입니다
  
  이지원회원님의 아이디는 1001입니다
  손민국회원님의 아이디는 1002입니다
  홍길동회원님의 아이디는 1003입니다
  박서훤회원님의 아이디는 1003입니다
  ```

  - 아이디가 동일한 회원 '홍길동'이 추가되었다
    - 객체가 동일한 경우 중복처리 해야된다

#### 객체가 동일함을 구현하기

- 인스턴스 주소가 같으면 같은 객체이다

  - BUT 예제 코드는 `회원 아이디`가 같으면 같은 회원이다
    - 논리적으로 같은 객체를 구현하기 위해 `equals()`, `hashCode()` 메서드를 재정의한다

- `Member`클래스 수정

  ```java
  public class Member {
  	private int memberId;
  	private String memberName;
  	
  	...
  
  	@Override
  	public int hashCode() {
  		return memberId;
  	}
  
  	@Override
  	public boolean equals(Object obj) {
  		if(obj instanceof Member) {
  			Member member = (Member)obj;
  			if(this.memberId == member.memberId)
  				return true;
  			else
  				return false;
  		}
  		return false;
  	}
  
  	...
  }
  /*
  이지원회원님의 아이디는 1001입니다
  손민국회원님의 아이디는 1002입니다
  박서훤회원님의 아이디는 1003입니다
  
  이지원회원님의 아이디는 1001입니다
  손민국회원님의 아이디는 1002입니다
  박서훤회원님의 아이디는 1003입니다
  ```

  - 아이디가 같은 회원은 추가되지 않는다

### TreeSet 클래스

- `Tree`로 시작하는 클래스는 데이터를 추가한 후 결과를 출력하면 결과 값이 **정렬된다**

- `TreeSet`은 자**료의 중복을 허용하지 않으며, 출력 결과 값을 정렬하는 클래스**

- 예제 코드

  ```java
  package collection.treeset;
  import java.util.TreeSet;
  public class TreeSetTest {
  
  	public static void main(String[] args) {
  		TreeSet<String> treeSet = new TreeSet<>();
  		treeSet.add("홍길동");
  		treeSet.add("강감찬");
  		treeSet.add("이순신");
  		
  		for(String str : treeSet) {
  			System.out.println(str);
  		}
  	}
  }
  /*
  강감찬
  이순신
  홍길동
  ```

  - 결과가 정렬되어 출력되었다
    - **자바는 정렬을 구현하기 위해 `이진트리(binary tree)`를 사용한다**

#### 이진 검색 트리

- 트리는 자료 사이의 계층 구조를 나타내는 자료 구조이다
- `노드`: 트리 자료 구조에서 각 자료가 들어가는 공간
  - `부모-자식 노드(parent-childe node)`: 위아래로 연결된 노드의 관계
- 특징
  - 이진 검색 트리는 노드에 저장되는 자료의 중복을 허용하지 않는다
  - **왼쪽 자식 노드는 부모 노드보다 항상 작은 값을 가진다**
  - **오른쪽 자식 노드는 부모보다 항상 큰 값을 가진다**
    - 비교 범위가 평균 1/2만큼씩 줄어들어 효과적으로 자료검색 가능

#### TreeSet을 활용해 회원 관리 프로그램 구현하기

- 코드

  ```java
  package collection.treeset;
  
  import java.util.Iterator;
  import java.util.TreeSet;
  
  import collection.Member;
  
  public class MemberTreeSet {
  
  	private TreeSet<Member> treeSet;
  
  	public MemberTreeSet(){
  		treeSet = new TreeSet<Member>();
  	}
  	
  	public void addMember(Member member){
  		treeSet.add(member);
  	}
  	
  	public boolean removeMember(int memberId){
  		
  		Iterator<Member> ir = treeSet.iterator();
  		
  		while(ir.hasNext()){
  			Member member = ir.next();
  			int tempId = member.getMemberId();
  			if( tempId == memberId){
  				treeSet.remove(member);
  				return true;
  			}
  		}
  		
  		System.out.println(memberId + "가 존재하지 않는다");
  		return false;
  	}
  	
  	public void showAllMember(){
  		for(Member member : treeSet){
  			System.out.println(member);
  		}
  		System.out.println();
  	}
  }
  ```

  ```java
  package collection.treeset;
  
  import collection.Member;
  
  public class MemberTreeSetTest {
  
  	public static void main(String[] args) {
  
  		MemberTreeSet memberTreeSet = new MemberTreeSet();
  		
  		Member memberPark = new Member(1003, "박서훤");
  		Member memberLee = new Member(1001, "이지원");
  		Member memberSon = new Member(1002, "손민국");
  		
  		
  		memberTreeSet.addMember(memberLee);
  		memberTreeSet.addMember(memberSon);
  		memberTreeSet.addMember(memberPark);
  		memberTreeSet.showAllMember();
  		
  		Member memberHong = new Member(1003, "홍길동");  //1003 아이디 중복 
  		memberTreeSet.addMember(memberHong);
  		memberTreeSet.showAllMember();
  	}
  }
  /*
  Exception in thread "main" java.lang.ClassCastException: class collection.Member cannot be cast to class java.lang.Comparable (collection.Member is in unnamed module of loader 'app'; java.lang.Comparable is in module java.base of loader 'bootstrap')
  	at java.base/java.util.TreeMap.compare(TreeMap.java:1291)
  	at java.base/java.util.TreeMap.put(TreeMap.java:536)
  	at java.base/java.util.TreeSet.add(TreeSet.java:255)
  	at collection.treeset.MemberTreeSet.addMember(MemberTreeSet.java:17)
  	at collection.treeset.MemberTreeSetTest.main(MemberTreeSetTest.java:16)
  ```

  - Member 클래스가 Comparable 인터페이스를 구현하지 않았다고 오류
    - **`Member`클래스를 추가할 때 `TreeSet`에서 어떤 기준으로 노드를 비교하여 트리를 형성해야 하는지 구현하지 않았다**

### Comparable 인터페이스와 Comparator 인터페이스

- `Member` 클래스가 가진 회원 아이디를 기준 오름차순으로 정렬하겠다

  - `Comparable, Comparator` 인터페이스는 이러한 정렬을 구현할 수 있게 해주는 인터페이스이다

  ```java
  public class Member implements Comparable<Member>{
  	...
  }
  ```

#### 자기 자신과 전달받은 매개변수를 비교하는 Comparable 인터페이스

- `Comparable` 인터페이스에서 `compareTo()` 추상 메서드가 있다

  - `Member`클래스에서 `compareTo()`메서드를 구현해줘야 한다

  ```java
  public class Member implements Comparable<Member>{
  	private int memberId;
  	private String memberName;
  	...
  	@Override
  	public int compareTo(Member member) {
  		return (this.memberId - member.memberId);
  	}
  }
  ```

  - **새로 추가된 값이 `this`, 비교되는 값이 `매개변수`이다**
  - `memberId`값을 비교하여 **새로 추가한 아이디가 크면 양수, 작으면 음수, 같으면 0**
    - 이러면 오름차순으로 구현된다

- **`compareTo()`메서드는 프로그래머가 호출하는게 아닌 객체가 TreeSet에 요소를 추가할 때 호출되는 메서드이다**
  - 어떤 매개변수가 전달될지는 TreeSet의 요소에 따라 달라진다

- 결과 출력

  ```java
  이지원회원님의 아이디는 1001입니다
  손민국회원님의 아이디는 1002입니다
  박서훤회원님의 아이디는 1003입니다
  
  이지원회원님의 아이디는 1001입니다
  손민국회원님의 아이디는 1002입니다
  박서훤회원님의 아이디는 1003입니다
  //오름차순으로 구현됨
  ```

- **내림차순으로 구현하려면**

  ```java
  public int compareTo(Member member) {
  		return (this.memberId - member.memberId) * (-1);		//음수를 곱해준다
  	}
  /*
  박서훤회원님의 아이디는 1003입니다
  손민국회원님의 아이디는 1002입니다
  이지원회원님의 아이디는 1001입니다
  
  박서훤회원님의 아이디는 1003입니다
  손민국회원님의 아이디는 1002입니다
  이지원회원님의 아이디는 1001입니다
  ```

#### 두 매개변수를 비교하는 Comparator 인터페이스

- 정렬을 구현하는데 사용하는 인터페이스

- `compare()` 메서드를 구현해야 된다

  ```java
  package collection;
  
  import java.util.Comparator;
  
  public class Member2 implements Comparator<Member2>{
  
  	private int memberId;        
  	private String memberName;   
  	...
  	@Override
  	public int compare(Member2 mem1, Member2 mem2) {
  		
  		return mem1.getMemberId() - mem2.getMemberId();	//전달받은 두 매개변수를 비교함
  	}
  }
  ```

  - `this`와 비교하는 `compareTo()`와 달리 두 매개변수를 비교한다
    - 첫번째 매개변수가 클때 양수를 반환해 오름차순으로 정렬된다

- 유의할점

  - **TreeSet 생성자에** **Comparator를 구현한 객체를 매개변수로 전달해야 된다**

    ```java
    TreeSet<Member> treeSet = new TreeSet<Member>(new Member());
    ```

    - 다음과 같이 코드를 구현해야 된다



- 일반적으로 `Comparator`보다 `Comparable`인터페이스를 많이 사용한다

  - **이미 `Comparable`이 구현되어 있는경우 `Comparator`를 사용할 수 있다**

    > String의 `Comparable`은 이미 오름차순으로 `final`로 선언되어 있다
    >
    > 이럴 경우 `Comparator`을 구현하여 내림차순을 만든다
    >
    > ```java
    > class MyCompare implements Comparator<String>{
    > 
    > 	@Override
    > 	public int compare(String s1, String s2) {
    > 		return (s1.compareTo(s2)) * -1;		//내림차순으로 정렬
    > 	}
    > 	
    > }
    > 
    > public class ComparatorTest {
    > 
    > 	public static void main(String[] args) {
    > 		Set<String> set = new TreeSet<String>(new MyCompare());		//생성자 매개변수로 정렬 방식을 지정
    > 		set.add("aaa");
    > 		set.add("ccc");
    > 		set.add("bbb");
    > 		
    > 		System.out.println(set);
    > 	}
    > }
    > /*
    > [ccc, bbb, aaa]
    > ```
    >
    > - **TreeSet 클래스를 생성할 때 생성자에 매개변수를 넣지않으면 String 클래스에 정의된 `Comparable` 인터페이스의 `compareTo()`메서드 구현 내용대로 오름차순으로 정렬된다**
    > - **TreeSet 클래스 생성자에 `Comparator` 인터페이스를 구현한 `MyCompare` 인스턴스를 매개변수로 넣어줘서 `compare()` 메서드대로 정렬되었다**



## 12-5 Map 인터페이스

- 자료를 쌍(pair)로 관리하는데 필요한 메서드가 정의되어 있다
  - `key-value` 쌍으로 이루어진 객체의 `key`값은 유일하며 `value`값을 중복될 수 있다
- Map 인터페이스를 구현한 클래스는 내부적으로 해시 알고리즘에 의해 구현되어 있다

### HashMap 클래스

- 해시 방식으로 자료를 관리한다

  - `해시 테이블`: 해시 방식의 자료를 저장하는 공간

  - `해시 함수`: key 값에 대응하는 해시 테이블 저장 위치를 계산하는 함수

    ```java
    index = hash(key)	//index는 저장 위치
    ```

- 새로운 `key-value` 입력 혹은 key를 통한 value 검색 속도는 매우 빠르다

- 해쉬 함수가 동일한 index을 반환하면 `충동(collision)` 발생

- `key`값을 중복될 수 없으므로 `equals()`, `hashcode()` 메서드를 재정의하여 사용하는게 좋다

#### HashMap을 활용해 회원 관리 프로그램 구현하기

- HashMap 활용 코드

  ```java
  package map.hashmap;
  import java.util.HashMap;
  import java.util.Iterator;
  
  import collection.Member;
  public class MemberHashMap {
  	private HashMap<Integer, Member> hashMap;
  	
  	public MemberHashMap() {
  		hashMap = new HashMap<Integer, Member>();
  	}
  	
  	public void addMember(Member member) {
  		hashMap.put(member.getMemberId(), member);
  	}
  	
  	public boolean removeMember(int memberId) {
  		if(hashMap.containsKey(memberId)) {
  			hashMap.remove(memberId);
  			return true;
  		}
  		System.out.println(memberId+"가 존재하지 않음");
  		return false;
  	}
  	
  	public void showAllMember() {
  		Iterator<Integer> ir = hashMap.keySet().iterator();
  		while(ir.hasNext()) {
  			int key = ir.next();
  			Member member = hashMap.get(key);
  			System.out.println(member);
  		}
  		System.out.println();
  	}
  }
  ```

  - key: 회원 아이디
  - value: 회원 클래스
  - showAllMember()은 key값을 iterator로 순회하여 key에 해당하는 member 출력했다
  - **key 값으로 쓰인 회원 아이디는 Integer형이다**
    - **Integer 클래스는 `equals(), hashcode()`메서드가 이미 재정의 되어있다**

- 테스트 코드

  ```java
  public class MemberHashMapTest {
  
  	public static void main(String[] args) {
  
  		MemberHashMap memberHashMap = new MemberHashMap();
  		
  		
  		Member memberLee = new Member(1001, "이지원");
  		Member memberSon = new Member(1002, "손민국");
  		Member memberPark = new Member(1003, "박서훤");
  		Member memberHong = new Member(1004, "홍길동");
  		
  		memberHashMap.addMember(memberLee);
  		memberHashMap.addMember(memberSon);
  		memberHashMap.addMember(memberPark);
  		memberHashMap.addMember(memberHong);
  		
  		memberHashMap.showAllMember();
  		
  		memberHashMap.removeMember(1004);
  		memberHashMap.showAllMember();
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

#### HashMap과 Hashtable

- 둘다 쌍으로 이루어진 자료를 관리하는데 사용
- `Hashtable` 클래스는 멀티스레드를 위한 동기화 제공
  - 멀티스레드 환경 아니면 `Hashtable`보다 `HashMap` 사용 권장

### TreeMap 클래스

- `key`값으로 자료를 정렬하려면 `TreeMap`을 사용할 수 있다

  - `key`값에 해당하는 클래스에 `Comparable`이나 `Comparator` 인터페이스를 구현해야 된다
  - 위 예제는 `key : Integer`이어서 `Integer`클래스는 이미 `Comparable` 인터페이스가 구현되어있다

  ```java
  package map.treemap;
  
  import java.util.Iterator;
  import java.util.TreeMap;
  
  import collection.Member;
  
  public class MemberTreeMap {
  
  	private TreeMap<Integer, Member> treeMap;
  	
  	public MemberTreeMap()
  	{
  		treeMap = new TreeMap<Integer, Member>();
  	}
  	
  	public void addMember(Member member){
  		
  		treeMap.put(member.getMemberId(), member);
  	}
  	
  	public boolean removeMember(int memberId){
  		
  		if(treeMap.containsKey(memberId)){
  			treeMap.remove(memberId);
  			return true;
  		}
  		
  		System.out.println(memberId + "가 존재하지 않음");
  		return false;
  	}
  	
  	public void showAllMember(){
  		Iterator<Integer> ir = treeMap.keySet().iterator();
  		while (ir.hasNext()){
  			int key = ir.next();
  			Member member = treeMap.get(key);
  			System.out.println(member);
  		}	
  		System.out.println();
  	}
  }
  ```

  ```java
  public class MemberTreeMapTest {
  
  	public static void main(String[] args) {
  
  		MemberTreeMap memberHashMap = new MemberTreeMap();
  		
  		Member memberPark = new Member(1003, "박서훤");
  		Member memberLee = new Member(1001, "이지원");
  		Member memberHong = new Member(1004, "홍길동");
  		Member memberSon = new Member(1002, "손민국");
  		
  		memberHashMap.addMember(memberPark);
  		memberHashMap.addMember(memberLee);
  		memberHashMap.addMember(memberHong);
  		memberHashMap.addMember(memberSon);
  		
  		memberHashMap.showAllMember();
  		
  		memberHashMap.removeMember(1004);
  		memberHashMap.showAllMember();
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

  - key 값인 `회원 아이디` 기준으로 정렬되었다
    - `key`가 `Integer` 클래스여서 `Comparator, Comparable` 인터페이스 구현이 이미 되어있다



## 연습문제

1. 자료 구조를 사용하기 편리하도록 자바에서 제공하는 라이브러리를 `컬렉션 프레임워크`라고 한다

2. 클래스에서 여러 자료형을 사용할 때 자료형을 명시하지 않고 자료형을 의미하는 문자로 선언한 후 실제 클래스를 생성할 때 자료형을 명시하는 프로그래밍 방식을 `제네릭 프로그래밍`이라고 한다

3. Collection 인터페이스를 구현한 클래스를 순회하기 위해 사용하는 인터페이스는 `Iterator`이다

4. TreeSet을 사용할 때 Comparable 인터페이스를 구현하는 이유는 `TreeSet에 추가되는 객체의 정렬 방식을 구현하기 위함이다`

5. StudentTest의 출력 결과가 다음처럼 나오도록 Student 클래스를 구현해 보세요

   ```java
   package question;
   import java.util.HashSet;
   
   class Student{
   	private int studentId;
   	private String studentName;
   	
   	public Student(int studentId, String studentName) {
   		super();
   		this.studentId = studentId;
   		this.studentName = studentName;
   	}
   
   	@Override
   	public int hashCode() {
   		return studentId;
   	}
   
   	@Override
   	public boolean equals(Object obj) {
   		if(obj instanceof Student) {
   			Student student = (Student)obj;
   			if(this.studentId == student.studentId)
   				return true;
   			else
   				return false;
   		}
   		return false;
   	}
   
   	@Override
   	public String toString() {
   		return this.studentId +":"+this.studentName;
   	}
   	
   }
   
   public class StudentTest {
   	public static void main(String[] args) {
   		HashSet<Student> set = new HashSet<Student>();
   		set.add(new Student(100, "홍길동"));
   		set.add(new Student(200, "가나"));
   		set.add(new Student(300, "다라"));
   		set.add(new Student(400, "마바"));
   		set.add(new Student(100, "사아"));
   		
   		System.out.println(set);
   	}
   
   }
   /*
   [400:마바, 100:홍길동, 200:가나, 300:다라]
   ```

6. 
   ```java
   public class Car{
   	String name;
   	public Car() {	}
   	public Car(String name) {
   		this.name = name;
   	}
   }
   ```

   ```java
   public class CarFactory {
   	private static CarFactory instance = new CarFactory();
   	HashMap<String, Car> carMap = new HashMap<>();
   	
   	private CarFactory() {}
   	public static CarFactory getInstance() {
   		if(instance == null)
   			instance = new CarFactory();
   		return instance;
   	}
   	
   	public Car createCar(String name) {
   		if(carMap.containsKey(name))
   			return carMap.get(name);
   		Car car = new Car(name);
   		carMap.put(name, car);
   		return car;
   	}
   }
   ```

   ```java
   public class CarTest {
   
   	public static void main(String[] args) {
   		CarFactory factory = CarFactory.getInstance();
   		
   		Car sonata1 = factory.createCar("연수차");
   		Car sonata2 = factory.createCar("연수차");
   		System.out.println(sonata1 == sonata2); // true
   	
   		Car avante1 = factory.createCar("승연차");
   		Car avante2 = factory.createCar("승연차");
   		System.out.println(avante1 == avante2); // true
   		System.out.println(sonata1 == avante1); // false
   	}
   
   }
   ```