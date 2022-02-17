# 11장 - 기본 클래스

### 모든 클래스의 최상위 클래스 Object

- `Object`클래스는 **모든 자바 클래스의 최상위 클래스이다**

모든 클래스는 `Object` 클래스의 메서드를 사용, 재정의할 수 있다

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

- `toString()` 재정의

  ```java
  	@Override
  	public String toString() {
  		return bookTitle + "," + bookNumber;
  	}
  ```

### equals() 메서드

- 기능: **두 인스턴스의 주소 값을 비교하여 boolean 값을 반환**
  - BUT 서로 다른 주소 값을 가져도 같은 인스턴스라고 정의할 수 있다
  - `물리적 동일성(인스턴스의 메모리 주소가 같음)`, `논리적 동일성(논리적으로 두 인스턴스가 같음)`을 구현할 때 `equals()`메서드를 재정의한다

### hashCode() 메서드

- `해시(hash)`는 **정보를 저장하거나 검색할 때 사용하는 자료 구조**

  - 정보를 어디에 저장할 것인지, 어디서 가져올 것인지 **해시 함수**를 사용하여 구현한다
  - `해시 함수`
    - 객체의 **특정 정보(키값)**를 매개변수 값으로 넣으면 객체가 저장되어야 할 위치나 저장된 **해시 테이블 주소**(위치)를 반환한다
  - **자바에서 인스턴스를 힙 메모리에 생성하여 관리할 때 해시 알고리즘을 사용한다**

- 자바에서 두 인스턴스가 같으려면 `hashCode()` 메서드에서 반환하는 해시 코드 값이 같아야 한다

  - **논리적으로 같은 두 객체도 같은 해시 코드 값을 반환하도록 `hashCode()` 메서드를 재정의**해야 된다

    > Ex. 논리적으로 동일한 학생 객체의 경우 `학번` 값을 `hashCode()`메서드에서 리턴하게 한다

### clone() 메서드

- 객체 원본을 유지하며 복사본을 사용할때 사용
- 기본 틀(prototype)의 복사본을 사용해 동일한 인스턴스를 만들어 복잡한 생성 과정을 간단히 할때 사용
- **`clone()`메서드를 사용하려면 객체를 복제해도 된다는 의미로 클래스에 `Cloneable`인터페이스를 구현해야 된다**

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

### StringBuffer와 StringBuilder 클래스 활용하기

- 문자열 변경, 연결시 String 클래스는 내부의 문자열이 변경되지 않으므로 메모리가 낭비된다

  - 이를 해결하는 것이 `StringBuffer`, `StringBuilder` 클래스이다
  - 차이: 여러 작업(스레드)이 동시에 문자열을 변경하려 할 때 안전한 변경을 보장해주는 차이
    - `StringBuffer`: 문자열이 안전하게 변경되도록 보장
    - `StringBuilder`: 보장하지 않음

## 11-3 Wrapper 클래스

### 기본 자료형을 위한 클래스

- 매개변수가 객체거나 반환 값이 객체인 경우 **정수를 객체형으로 사용해야 하는 경우가 발생**

  ```java
  public void setValue(Integer i){...}	//객체를 매개변수로 받는 경우
  public Integer returnValue(){...}		//반환 값이 객체형인 경우
  ```

  - 이를 위해 기본 자료형처럼 사용하는 클래스 제공

Wrapper 클래스 종류

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

## 11-4 클래스

- 자바의 모든 클래스, 인터페이스가 컴파일되면 class파일로 생성된다
  - a.java 파일이 컴파일 되면 a.class파일이 생성
  - a.class 파일에는 클래스나 인스턴스에 대한 변수, 메서드, 생성자 등의 정보가 들어 있다
- **Class 클래스는 컴파일된 class 파일에 저장된 클래스나 인터페이스 정보를 가져오는데 사용**

### Class 클래스란?

- 모르는 클래스의 정보를 사용할 경우에 **Class 클래스**를 사용한다



# 12장 - 컬렉션 프레임워크

## 12-1 제네릭

`제네릭 프로그래밍`: 어떤 값이 하나의 참조 자료형이 아닌 **여러 참조 자료형**을 사용할 수 있도록 프로그래밍 하는것

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

### 제네릭 클래스 사용하기

- 파우더 재료인 프린터 선언 및 생성

  ```java
  GenericPrinter<Powder> powderPrinter = new GenericPrinter<Powder>();
  powderPrinter.setMaterial(new Powder());
  Powder powder = powderPrinter.getMaterial(); // 명시적 형 변환을 하지 않음
  ```

  - `T`로 정의한 클래스 부분에 `Powder`형을 넣어줬다
  - `GenericPrinter<Powder>`에서 자료형을 명시해서 `getMaterial()` 메서드 반환할 때 형 변환이 필요없다
  - `대입된 자료형`: T 위치에 사용된 자료형
  - `제네릭 자료형`: 자료형을 대입해서 만든 `GenericPrinter<Powder>`

- class 파일을 생성할 때 `T`를 사용한 곳에 지정된 자료형에 따라 컴파일한다

  - 컴파일러가 자료형을 확인해줘서 안정적이며 형 변환 코드가 줄어든다

- 제네릭 클래스에서 `T` 자료형에 사용할 자료형에 제한을 둘 수 있다

  - `<T extends Material>` 사용 코드

    ```java
    public class GenericPrinter<T extends Material> {
    	private T material;
    
    	...
    }
    ```

    - `extends` 예약어를 통해 **사용할 수 있는 자료형에 제한을 뒀다**

  - <T extends 클래스>로 선언하면 제네릭 클래스 사용시 **상위 클래스에서 선언한 메서드를 사용할 수 있다**

### 제네릭 메서드 활용하기

- 메서드의 매개변수를 자료형 매개변수로 사용하는 경우

- 자료형 매개변수가 하나 이상인 경우

- 제네릭 메서드의 일반 형식

  > public <자료형 매개변수> (반환형) (메서드이름) (자료형 매개변수 ...) {}

## 12-2 컬렉션 프레임워크

### 컬렉션 프레임워크란?

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

## 12-3 List 인터페이스

- `List 인터페이스`에는 객체를 순서에 따라 저장하고 유지하는 데 필요한 메서드가 선언되어 있다
  - 대표적으로 `ArrayList`, `Vector`가 있고, 순차 자료 구조를 구현한 `LinkedList`가 있다

## 12-4 Set 인터페이스

- 순서와 상관없이 중복을 허용하지 않는 경우에 `Set` 인터페이스를 구현한 클래스를 사용한다
  - `Set 인터페이스`를 구현한 대표 클래스
    - `HashSet`
    - `TreeSet`

### Comparable 인터페이스와 Comparator 인터페이스

- `Member` 클래스가 가진 회원 아이디를 기준 오름차순으로 정렬하겠다

  - `Comparable, Comparator` 인터페이스는 이러한 정렬을 구현할 수 있게 해주는 인터페이스이다

  ```java
  public class Member implements Comparable<Member>{
  	...
  }
  ```

#### 자기 자신과 전달받은 매개변수를 비교하는 Comparable 인터페이스

#### 두 매개변수를 비교하는 Comparator 인터페이스

## 12-5 Map 인터페이스

- 자료를 쌍(pair)로 관리하는데 필요한 메서드가 정의되어 있다
  - `key-value` 쌍으로 이루어진 객체의 `key`값은 유일하며 `value`값을 중복될 수 있다
- Map 인터페이스를 구현한 클래스는 내부적으로 해시 알고리즘에 의해 구현되어 있다



# 13장 - 내부 클래스, 람다식, 스트림

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

### 람다식 사용하기

- 람다식을 구현하기 위해서는 먼저 인터페이스를 만들고, 인터페이스에 람다식으로 구현할 메서드를 선언한다

  - 이를 `함수형 인터페이스`라고 한다

- 함수형 인터페이스 선언하기

  - 인터페이스는 두 개 이상의 메서드를 가지면 안된다

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



# 14장 - 예외 처리

## 14-1 예외 클래스

### 오류란 무엇인가?

- 프로그램에서 오류 발생 상황 2가지
  - `컴파일 오류(compile error)`: 코드 작성 실수
  - `실행 오류(runtime error)`: 실행 중인 프로그램이 의도하지 않은 동작을 하거나 중지되는 오류

- 자바에서 제공하는 오류에 대한 전체 클래스

  > [`Error`], [`Exception`] ---> [`Throwable`]

  - 오류 클래스 모두 `Throwable`클래스에서 상속 받는다
  - 프로그램에서 제어하는 부분은 `Exception` 클래스와 그 하위에 있는 예외 클래스가 한다

### try-catch-finally 문

```java
try{
    예외가 발생할 수 있는 코드 부분
}catch(처리할 예외 타입 e){
    예외처리를 하는 부분
}finally{
    항상 수행되는 부분
}
```

### try-with-resources문

- 시스템 리소스 사용하고 해제하는 코드가 다소 복잡해보인다
  - 자바 7부터 `try-with-resources`문 제공
    - `close()`메서드를 명시적으로 호출하지 않아도 `try` 블록내에서 리소스를 자동으로 닫도록 할 수 있다
    - **`try-with-resources`문법을 사용하려면 해당 리소스가 `AutoCloseable` 인터페이스를 구현해야 된다**
- `Autocloseable` 인터페이스에는 `close()` 메서드가 있다
  - 구현한 클래스는 `close()` 명시적 호출 없이 메서드가 호출된다
  - try문의 괄호안에 리소스를 선언한다

- 자바 9에서는 try괄호 외부에서 선언한 변수 쓸 수 있다

  ```
  AutoCloseObj obj = new AutoCloseObj();
  try(obj){   //외부 선언한 변수 사용
      throw new Exception();
  }catch(Exception e) {
      System.out.println("예외 부분");
  }
  ```

### AutoCloseable 인터페이스

- AutoCloseable 인터페이스 구현하기

  ```java
  public class AutoCloseObj implements AutoCloseable {
  
  	@Override
  	public void close() throws Exception {
  		System.out.println("리소스가 close() 되었습니다");	//close()메서드 구현
  	}
  
  }
  ```

  - 시스템 리소스인 경우 파일 스트림을 닫거나, 네트워크 연결을 해제하는 코드를 작성해야된다

## 14-3 예외 처리 미루기

### 예외 처리를 미루는 throws 사용하기

- 예외를 해당 메서드에서 처리하지 않고 미룬 후 메서드를 호출하는 부분에서 예외를 처리하는 방법