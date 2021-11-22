# 5장

## 5-1 객체 지향 프로그래밍과 클래스

### 객체와 객체 지향 프로그래밍

- 눈에 보이는 사물을 모두 ```객체```라 할 수 있다

- 눈에 안 보이는 것도 ```객체```가 될 수 있다

  > ex. 주문, 생산, 관리 등 어떤 행동도 객체가 될 수 있다

- ```객체 지향 프로그래밍```: ```객체```를 기반으로 하는 프로그래밍

### 생활 속에서의 객체

- ```절차 지향 프로그래밍```: 순서대로 일어나는 일을 시간순으로 프로그래밍하는 것

  > ex. 아침에 일어난다 => 씻는다 => 밥을 먹는다 => 버스를 탄다 => 요금을 지불한다 => 학교에 도착한다

- 반면 ```객체 지향 프로그래밍```은 객체를 정의하고 객체 간 협력을 프로그래밍하는 것

  > ex. 객체: ```학생```, ```밥```, ```버스```, ```학교``` 등
  >
  > ```밥 먹는다```는 (```학생``` <=> ```밥```)의 협력으로 이루어 진다
  > ```버스를 탄다```는 (```학생``` <=> ```버스```)

  - **객체 지향 프로그램**은 **객체**를 만들고 객체 사이에 일어나는 일을 구현하는 것이다
  - **객체 지향 프로그래밍**을 할 때는 **객체**를 먼저 정의하고 각 객체가 어떤 기능을 제공하고 객체 간 협력을 어떻게 구현할지 고민해야된다

### 클래스란?

- ```객체 지향 프로그래밍```은 ```클래스```기반으로 프로그래밍한다

- ```클래스```: 객체의 속성과 기능을 코드로 구현한 것

  - 객체를 클래스로 구현하는 것 => ```클래스 정의```
  - 클래스 정의를 위해 클래스 **이름**, **속성 또는 특성**이 필요
    - 속성 또는 특성은 클래스 내부에 **변수**로 선언 => 이를 ```멤버 변수```라고 한다

  > EX.
  > 학생 객체
  >
  > **클래스 이름** => Student
  >
  > **속성** => 학번, 이름, 학년, 사는 곳
  >
  > ```java
  > package classpart;
  > 
  > public class Student {	//클래스 이름
  > 	int studentID;		//멤버 변수들
  > 	String studentName;
  > 	int grade;
  > 	String address;
  > }
  > ```

- 클래스 이름 짓는 규칙

  - _클래스 이름은 대문자로 시작한다_(개발자들 사이의 규칙)

    > 이런 규칙을 ```코딩 컨벤션```이라 한다



## 5-2 클래스 살펴보기

### 클래스 속성을 구현하는 ''멤버 변수''

- ```멤버 변수```: 클래스 내부에 선언하여 객체 속성을 나타내는 변수
  - 멤버 변수는 다른말로 ```속성(property)``` 또는 ```특성(attribute)```라고 표현
  - 속성에 알맞는 **자료형**을 선언해 주어야 한다

> EX
> 사람 객체 구현
> **사람 속성** => 이름, 나이, 직업, 주소, 키, 몸무게
>
> ```java
> package classpart;
> 
> public class Person {
> 	String name;
> 	int height;
> 	double weight;
> 	char gender;
> 	boolean married;
> }
> ```
>
> - 기본 자료형으로 선언
> - 클래스형으로 선언 가능하다
>   - ```클래스형```이란?
>     ```객체 자료형``` 혹은 ```참조 자료형```이라 한다
>     참조 자료형으로 사용 하는 클래스는 String, Date 등 JDK에서 제공하는 것
>     Or 개발자가 직접 만든 Student, Person 같은 클래스

### 클래스 기능을 구현하는 ''메서드''

- 객체가 가지는 ```속성```을 사용해 해당 객체와 관련된 기능을 구현할 수 있다

  > ex. 학생에서 이름을 부여한다, 학생이 사는 곳을 출력한다

- ```멤버 함수(메서드)```: 클래스 내부에서 ```멤버 변수```를 이용하여 클래스 기능을 구현한 것

```java
public class Student {	//클래스 이름
	int studentID;		//멤버 변수들
	String studentName;
	int grade;
	String address;
	
	public void showStudentInfo() {		//메서드 추가
		System.out.println(studentName + ", " + address);	//이름, 주소 출력
	}
}
```

### 패키지란?

- 클래스 파일의 묶음이다

  - 패키지를 만들면 프로젝트 하위 폴더에 물리적으로 디텍터리가 생성된다

- 패키지는 ```계층 구조```를 가질 수 있다

  - 프로젝트 수행 시 패키지의 계층 구조는 프로젝트의 소스 코드 관리와 관련된다

    > EX
    > 학생, 과목, 교실, 담당 교수, 학과 등의 클래스로 이루어진 프로젝트의 경우
    >
    > Student, Course 등의 기본이 되는 클래스를 기준으로
    > 그 하위 클래스를 패키지로 구분하여 구성할 수 있다
    >
    > 즉 패키지를 계층 구조로 구성할 수 있다

    - 계층 구조를 잘 구성해야 **소스 코드 관리**와 **유지 보수**가 편리하다

- 패키지 선언하기

  ```java
  package domain.student.view;	//패키지 선언
  public class StudentView{
      
  }
  ```

  - 클래스의 **전체 이름**은 ```domain.student.view.StudentView```이다
    - 클래스 이름이 같아도 패키지 이름이 다르면 **전체 이름**이 다른 것이므로 **다른 클래스**이다



## 5-3 메서드

- 메서드는 함수의 한 종류이다

### 함수란?

- ```함수```: 하나의 기능을 수행하는 일련의 코드

  > 숫자 두개를 입력받아 **더한다**
  > 가장 좋은 성적 두 개를 **더한다**
  > 두 거리를 **더한다**
  >
  > 세 경우 모두 '두 수를 더해서 결과 값을 보여준다'라는 기능이다
  > 이런 경우 **더하기 기능**을 수행하는 코드를 묶어서 **더하기 함수**를 만들 수 있다

  - 함수는 어떤 기능을 수행하도록 미리 구현해 놓고, 필요할 때마다 **호출**하여 사용할 수 있다

### 함수의 입력과 반환

- 함수는 **이름**이 있고 **입력 값**과 **결과 값**을 가진다

  > 이름: add
  > 입력: num1, num2
  > 결과: result

  - ```매개변수```: num1, num2와 같이 함수의 입력으로 받는 변수
  - ```반환 값```: result와 같이 함수를 수행한 후 결과로 되돌려 주는 값

### 함수 정의하기

- 더하기 함수 정의

  ```java
  int add (int num1, int num2){	//함수 이름: add	//매개변수: num1, num2
      int result;
      result = num1 + num2;
      return result;
  }
  ```

  - 매개 변수가 필요 없는 함수도 있다

  - 반환 값이 없는 함수도 있다
  
    ```java
    int getTenTotal(){	//비워두면 된다
        int i;
        ...
        return;	//반환값 없음
    }
    ```
  

### 함수 호출하고 값 반환하기

```java
public class FunctionTest {
	public static void main(String[] arg) {
		int num1 = 10;
		int num2 = 20;
		
		int sum = add(num1, num2);	//add 함수 호출
		System.out.println(sum);
	}
	
	public static int add(int n1, int n2) {		//add 함수 구현
		int result = n1 + n2;
		return result;
	}
}
```

### 함수 호출과 스택 메모리

- 함수를 호출하면 그 함수만을 위한 메모리 공간이 할당된다

  - 이 메모리 공간을 ```스택```이라 한다

  >스택
  >
  >|n1, n2, result          | => 2. main()에서 add()호출.  3. add()함수가 사용할 메모리 공간이 스택에 생성됨
  >|num1, num2, sum| => 1. main 함수가 사용할 메모리 공간이 스택에 생성됨

  - add() 함수가 끝나면 add() 함수가 사용하던 메모리 공간은 자동으로 사라진다
  - ```지역 변수```: 함수 내부에서만 사용하는 변수 ( 지역 변수는 스택 메모리에 생성된다 )

### 함수의 장점

1. 기능을 나누어 코드를 효율적으로 구현할 수 있다

   > ex. 계산기 구현을 위해 덧셈, 뺄셈, 곱셈, 나눗셈 기능을 나누어 함수를 여러개 만들면
   > 필요할때마다 함수를 호출 가능
   >
   > => 기능을 분리해 구현하면 프로그램 코드의 가독성이 좋아진다

2. 같은 기능을 반복 사용할때 함수를 호출하면된다

   > 중복되는 코드를 막을 수 있다

3. 디버깅 작업이 편하다

   > 오류난 기능만 찾아서 수정하면 된다

- 함수 하나에 여러 기능이 섞여 있는건 좋지 않다

### 클래스 기능을 구현하는 메서드

- ```메서드```: 클래스 내부에서 사용하는 **멤버 함수**

- 학생 이름을 반환하는 메서드

  ```java
  public class Student {
  	int studentID;		
  	String studentName;
  	int grade;
  	String address;
  	
  	public String getStudentName() {		//학생 이름을 반환하는 메서드
  		return studentName;
  	}
  }
  ```

- 학생 이름을 부여하는 메서드

  ```java
  ...
      public void setStudentName(String name) {
  		studentName = name;
  	}
  ```

- 자바의 이름 짓기 규약

  - **클래스** 이름은 **대문자**로 시작
  - **public 클래스**는 하나이다. public 클래스 이름과 자바 파일 이름은 같아야 한다
  - **패키지** 이름은 모두 **소문자**로 만든다
  - **변수**와 **메서드** 이름은 **소문자**로 시작 + **낙타 표기법** 사용
    - ```낙타 표기법```: 소문자로 시작해 새 단어마다 대문자를 써주는 방식

## 5-4 클래스와 인스턴스

### 클래스 사용과 main()함수

- ```main() 함수```: 자바 가상 머신(JVM)이 프로그램을 시작하기 위해 호출하는 함수이다

  - 클래스 내부에서 만들지만, 클래스의 메서드는 아니다

- main() 함수에서 클래스를 사용하는 방법

  1. 클래스 내부에 main() 함수를 만드는 방법

     - **Student 클래스에 main() 함수 포함하기**

       ```java
       public class Student {	//클래스 이름
       	int studentID;		//멤버 변수들
       	String studentName;
       	int grade;
       	String address;
       	
       	public String getStudentName() {
       		return studentName;
       	}
       	
       	public static void main(String[] arg) {
       		Student studentAhn = new Student();	//Student 클래스 생성
       		studentAhn.studentName = "안연수";
       		
       		System.out.println(studentAhn.studentName);
       		System.out.println(studentAhn.getStudentName());
       	}
       }
       ```

       - 클래스 내부에 main() 함수를 만들면 이 클래스가 프로그램의 **시작 클래스**가 된다

  2. 외부에 테스트용 클래스를 만들어 사용하는 방법

     - **main() 함수를 포함한 실행 클래스 따로 만들기**

       ```java
       package classpart;
       
       public class StudentTest {	//실행 클래스를 따로 만들었다
       
       	public static void main(String[] args) {
       		Student studentAhn = new Student();	//Student 클래스 생성
       		studentAhn.studentName = "안연수";
       		
       		System.out.println(studentAhn.studentName);
       		System.out.println(studentAhn.getStudentName());
       	}
       
       }
       ```

       - 둘다 ```classpart```라는 동일 패키지에 있어서 문제 없으나, **패키지가 다를 경우** ```import```문을 사용하여 원하는 **클래스를 불러와야 한다**

### new 예약어로 클래스 생성하기

- 클래스 생성 방법

  - ```(클래스형) (변수) = new (생성자);```

  - 클래스가 생성된다는 것은 클래스를 실제 사용할 수 있도록 **메모리 공간(힙 메모리)**을 할당 받는것이다

  - ```인스턴스```: 실제 사용할 수 있도록 생성된 클래스

  - ```참조 변수```: 인스턴스를 가리키는 클래스형 변수

    ```java
    Student studentAhn = new Student();
    ```

    - new Student();로 Student 클래스를 생성하여 studentAhn에 대입
    - studentAhn은 ```참조 변수```이며, 이 변수가 생성된 ```인스턴스```를 가리킨다

### 인스턴스와 참조 변수

> ```객체```: '의사나 행위가 미치는 대상'
> ```클래스```: 객체를 코드로 구현한 것
> ```인스턴스```: 클래스가 메모리 공간에 생성된 상태
> **```객체```**: 생성된 클래스의 인스턴스
>
> ​																 ==(생성)==> Student1 (인스턴스)
> 학생 ===(정의)===> Student (클래스) ==(생성)==> Student2 (인스턴스)
> ​																 ==(생성)==> Student3 (인스턴스)

- 클래스의 생성자```Student()```를 호출하면 인스턴스가 만들어진다

- 클래스로부터 여러 개의 인스턴스를 생성할 수 있다

  ```java
  public class StudentTest1 {
  	public static void main(String[] args) {
  		Student student1 = new Student();	//첫번째 학생 생성
  		student1.studentName = "안연수";
  		System.out.println(student1.studentName);
  		
  		Student student2 = new Student();	//두번째 학생 생성
  		student2.studentName = "안승연";
  		System.out.println(student2.studentName);
  	}
  
  }
  ```

  - 생성자를 2번 이용하여 서로 다른 변수 이름으로 클래스 2개 생성하였다. 즉 서로 다른 인스턴스 2개가 생성됨

- ```참조 변수```를  사용하면 인스턴스의 **멤버 변수**와 **메서드**를 참조하여 사용할 수 있다

  - ```도트(.) 연산자```를 사용한다

    > ```(참조 변수).(멤버 변수)```
    >
    > ```(참조 변수).(메서드)```
    >
    > EX.```studentAhn.studentName = "Jung";```
    > EX.```System.out.println(studentAhn.getStudentName());```

### 인스턴스와 힙 메모리

- new Student()를 통해 인스턴스를 생성하면 studentID, studentName 등의 멤버 변수를 저장할 공간이 필요하다

  - 클래스 생성자를 하나 호출하면 인스턴스가 ```힙 메모리```에 생성된다

    > Student studentAhn = new Student();
    > 생성된 클래스를 studentAhn 변수에 대입하면, 인스턴스가 저장된 메모리를 studentAhn 변수가 가리킨다.
    >
    > (스택메모리)		(힙 메모리)
    > studentAhn ----->  Student 클래스 생성
    >
    > - studentAhn은 **지역 변수**이며 지역 변수는 **스택 메모리**에 생성된다
    > - 인스턴스는 **힙 메모리**에 생성된다
    > - studentAhn에 생성된 인스턴스를 대입하는 것은 studentAhn에 인스턴스가 생성된 힙 메모리 **주소**를 대입하는 것과 동일

> **힙 메모리란?**
>
> ```힙```: 프로그램에서 사용하는 ```동적 메모리(dynamic memory)``` 공간
>
> - 프로그램은 **스택, 힙, 데이터** 세 영역을 사용
> - 객체가 생성될 때 힙 메모리를 사용
> - 힙은 동적으로 할당되며 사용이 끝나면 메모리를 해제해 주어야 한다.
>   C, C++은 직접 해제, but 자바는 **가비지 컬렉터**가 자동으로 메모리 해제해 준다

- **참조 변수**는 힙 메모리에 생성된 인스턴스를 가리킨다

  ```java
  public class StudentTest2 {
  
  	public static void main(String[] args) {
  		Student student1 = new Student();
  		student1.studentName = "안연수";
  		
  		Student student2 = new Student();
  		student2.studentName = "안승연";
  		
  		System.out.println(student1);
  		System.out.println(student2);
  	}
  }
  /*출력
  	classpart.Student@54bedef2
  	classpart.Student@5caf905d
  */
  ```

  - 힙 메모리에 생성된 인스턴스의 메모리 주소는 **참조 변수**에 저장된다
  - ```(클래스 이름)@(주소 값)```으로 출력된다

### 객체 지향 용어 정리

| **용어**  | **설명**                                    |
| :-------: | :------------------------------------------ |
|   객체    | 객체 지향 프로그램의 대상, 생성된 인스턴스  |
|  클래스   | 객체를 프로그래밍하기 위해 코드로 만든 형태 |
| 인스턴스  | 클래스가 메모리에 생성된 상태               |
| 멤버 변수 | 클래스의 속성, 특성                         |
|  메서드   | 멤버 변수를 이용하여 클래스의 기능을 구현   |
| 참조 변수 | 메모리에 생성된 인스턴스를 가리키는 변수    |
|  참조 값  | 생성된 인스턴스의 메모리 주소 값            |

## 5-5 생성자

### 생성자란?

```java
package constructor;

public class Person {
	String name;
	float height;
	float weight;
}
```

```java
public class PersonTest {

	public static void main(String[] args) {
		Person personLee = new Person();
	}
}
```

- ```생성자```: 클래스를 생성할 때 사용하는 함수(ex. ```Person()```)
  - 생성자는 클래스를 처음 만들 때 멤버 변수나 상수를 **초기화**한다
  - 생성자 이름은 클래스 이름과 같고, 반환값이 없다

- **```디폴트 생성자```**: 자동으로 만들어주는 생성자

  - Person 클래스를 살펴보면 Person() 생성자가 따로 없다

  - 생성자가 없는 클래스는 클래스 파일을 컴파일할 때 자바 컴파일러에서 **자동으로 생성자를 만들어 준다**

    ```java
    public class Person {
    	String name;
    	float height;
    	float weight;
        
        public Person() {}	// 자바 컴파일러가 자동으로 제공하는 디폴트 생성자
    }
    ```

### 생성자 만들기

- 생성자는 주로 멤버 변수에 대한 값들을 매개변수로 받아서 인스턴스가 새로 생성될때 멤버 변수 값들을 초기화하는 역할을 한다

```java
public class Person {
	String name;
	float height;
	float weight;
	
    public Person() {}	//필요의 경우 디폴트 생성자 직접 추가
    
	public Person(String pname) {	//이름을 입력받아 Person 클래스를 생성하는 생성자
		name = name;
	}
}
```

- 생성자를 직접 구현하면 **디폴트 생성자**는 없기 때문에 직접 추가해줄 수 있다

### 생성자 오버로드

- ```오버로드```: 객체 지향 프로그램에서 메소드 이름이 같고 매개변수만 다른 경우

- ```생성자 오버로드```: 클래스에서 생성자가 두 개 이상 제공되는 경우

  > 경우에 따라 디폴트 생성자를 제공하지 않기도 한다
  >
  > EX. 학생이 생성될 때 반드시 학번이 있어야 하는 경우
  >
  > ```java
  > public class Student {
  > 	int studentID;
  > 
  > 	public Student(int studentID) {	
  > 		this.studentID = studentID;
  > 	}
  > }
  > ```

```java
//생성자 사용하기
public class Person {
	String name;
	float height;
	float weight;
	
	public Person() {}	//디폴트 생성자
	
	public Person(String pname) {	//이름만 받는 생성자
		name = pname;
	}
	
	public Person(String pname, float pheight, float pweight) {
		name = pname;
		height = pheight;
		weight = pweight;
	}
}
```

```java
public class PersonTest {

	public static void main(String[] args) {
		Person personKim = new Person();
		personKim.name = "김땡땡";	//디폴트 생성자로 클래스 생성 후
		personKim.weight = 85.5F;	//인스턴스 변수 값을 따로 초기화
		personKim.height = 180.0F;
		
		Person personLee = new Person("이몰리", 175, 75);	//인스턴스 변수 초기화 동시에 클래스 생성
	}
}
```

## 5-6 참조 자료형

### 참조 자료형이란?

- ```기본 자료형 변수```: 크기가 정해진 (int, char, float, double 등)기본 자료형으로 선언하는 변수
- ```참조 자료형 변수```: 클래스 자료형으로 선언하는 변수
  1. 프로그래머가 필요에 의해 만든 클래스를 사용할 수 있다
  2. JDK에서 제공하는 클래스를 사용할 수 있다

```java
package reference;

public class Student1 {
	int studentID;
	String studentName;//String도 JDK에서 제공하는 참조 자료형이다.
	int koreaStudent;	//국어 성적
	int mathScore;		//수학 성적
    String koreaSubject;//국어 과목 이름
    String mathSubject;	//수학 과목 이름
}
```

- 학생에 대한 클래스인데 과목에 대한 변수가 늘어나고 있다

  - 해결을 위해 과목의 이름과 성적을 Subject라는 클래스로 분리한다

  ```java
  public class Student1 {
  	int studentID;
  	String studentName;
  	Subject korean;	//직접 만든 참조 자료형
  	Subject math;
  }
  ```

  ```java
  public class Subject {
  	String SubjectName;
  	int scorePoint;
  }
  ```

## 5-7 정보 은닉

### 접근 제어자 살펴보기

- 객체 지향 프로그램에서는 ```예약어```(ex. public)를 사용해 클래스 내부의 변수, 메서드, 생성자에 대한 **접근 권한**을 지정할 수 있다

- 이러한 ```예약어```를 ```접근 제어자```라고 한다

  - ```public```은 선언한 변수나 메서드를 외부 클래스에서 접근 가능해, 사용할 수 있다는 뜻
  - ```private```은 외부 클래스에서 사용할 수 없음

  ```java
  package hiding;
  
  public class Student {
  	int studentID;
  	private String studentName;		//학생 이름을 private을 선언
  	int grade;
  	String address;
  	
  	...
  }
  ```

  ```java
  public class StudentTest {
  
  	public static void main(String[] args) {
  		Student studentLee = new Student();
  		studentLee.studentName = "이상원";		//오류 발생
  		
  		System.out.println(studentLee.studentName());
  	}
  
  }
  ```

  - studentName을 **private**으로 선언하여 외부 클래스인 **StudentTest.java**클래스에서 변수에 대한 접근 권한이 없다

### get(), set() 메서드

- **private**으로 선언한 변수를 외부 코드에서 사용하려면, 변수를 사용할 수 있는 **public 메서드**를 제공해야 된다.

  - **public 메서드**가 제공되지 않으면 변수에 대한 접근 방법이 없다
  - ```set()```, ```get()``` public 메서드를 사용한다

  ```java
  public class Student {
  	int studentID;
  	private String studentName;		//학생 이름을 private을 선언
  	int grade;
  	String address;
  	
  	public String getStudentName() {//private 변수인 studentName에 접근하여 값을 가져오는 public get() 메서드
  		return studentName;
  	}
  	
  	public void setStudentName(String studentName) {//변수에 접근하여 값을 지정하는 public set() 메서드
  		this.studentName = studentName;
  	}
  }
  ```

  - _이클립스에서 클래스 내부에서 우클릭 [Source -> Generate Getters and Setters] 메뉴를 통해 자동으로 get, set 메서드 생성 가능_

  ```java
  public class StudentTest {
  
  	public static void main(String[] args) {
  		Student studentLee = new Student();
  		//studentLee.studentName = "이상원";		//오류 발생
  		studentLee.setStudentName("이상원");	//public 메서드를 통해 private 변수에 접근 가능
          
  		System.out.println(studentLee.getStudentName());
  	}
  
  }
  ```

  - **public 메서드**를 통해 **private** 변수에 접근 가능하다

### 정보 은닉이란?

- 변수를 **public**으로 선언하는 것과 **private**으로 선언하여 public 메서드를 제공하는 것의 차이는?

  ```java
  public class MyDate{
      public int day;
      public int month;
      public int year;
  }
  ```

  - day, month, year모두 public의 경우 외부 클래스에서 MyDate를 사용할 때 **값을 마음대로 넣을 수 있다**
  - 이럴 경우 **오류가 나더라도 값을 대입할 수 있다**

  ```java
  public class MyDate{
      private int day;
      private int month;
      private int year;
      
      public void setDay(int day){
          if(month==2){
              if(day<1 || day>28)
                  System.out.println("오류 발생!!");
              else
                  this.day = day;
          }
      }
  }
  ```

  - **private**을 사용할 경우 **오류가 발생하더라도 해당 변수에 값을 대입되지 못하도록 제어할 수 있다**

- ```정보 은닉```: 클래스 내부에서 사용할 변수나 메서드를 private으로 선언해 외부에서 접근하지 못하도록 하는 것

### 접근 제어자 정리

| 접근 제어자 | 설명                                                         |
| ----------- | ------------------------------------------------------------ |
| public      | 외부 클래스 어디에서나 접근 가능                             |
| protected   | 같은 패키지 내부와 상속 관계의 클래스에서만 접근할 수 있고<br />그 외 클래스에서는 접근할 수 없다 |
| 없는 경우   | default이며 같은 패키지 내부에서만 접근할 수 있다            |
| private     | 같은 클래스 내부에서만 접근할  수 있다                       |

## 연습문제

1. 클래스를 생성할 때 호출하는 ```생성자```는 멤버 변수를 초기화하는데 사용합니다

2. 클래스를 생성하여 메모리에 있는 상태를 ```인스턴스```라 하고 멤버 변수를 다른 말로 ```인스턴스 변수```라고 합니다

3. ```메서드```는 일반 함수에 객체 지향의 개념을 추가하여, 클래스 내부에 선언하고 클래스 멤버 변수를 사용하여 클래스 기능을 구현합니다

4. ```java
   package date;
   import java.util.Calendar;
   
   public class MyDate {
   	private int day;
   	private int month;
   	private int year;
   	private boolean valid = true;
   	
   	public MyDate(int day, int month, int year) {
   		setYear(year);
   		setMonth(month);
   		setDay(day);
   	}
   	
   	public String isValid() {
   		if(valid)
   			return "유효한 날짜입니다.";
   		else
   			return "유효하지 않은 날짜입니다.";
   	}
   	
   	//DAY
   	public void setDay(int day) {
   		switch(month) {
   			case 1: case 3: case 5: case 7: case 8: case 10: case 12:
   				if (day <0 || day >31) 
   					this.valid = false;
   				else 
   					this.day = day;
   				break;
   			case 4: case 6: case 9: case 11:
   				if (day <0 || day >30)
   					valid = false;
   				else 
   					this.day = day;
   				break;
   			case 2:
   				if (( ( year % 4 ==0 &&  year % 100 !=0 ) || year % 400 ==0)){  //윤년인 경우
   					if (day <0 || day >29) 
   						this.valid = false;
   					else
   						this.day = day;
   				}
   				else {
   					if (day <0 || day >28) 
   						this.valid = false;
   					else
   						this.day = day;
   				}
   				break;
   			default:
   				this.valid = false;
   		}
   	}
   	public int getDay() {
   		return day;
   	}
   	
   	//MONTH
   	public int getMonth() {
   		return month;
   	}
   	public void setMonth(int month) {
   		if(month<1 || month>12) 
   			valid = false;
   		else
   			this.month = month;
   	}
   	
   	//YEAR
   	public int getYear() {
   		return year;
   	}
   	public void setYear(int year) {
   		if (year > Calendar.getInstance().get(Calendar.YEAR)) {
   			this.valid = false;
   		}
   		else {
   			this.year = year;
   		}
   	}
   	
   }
   ```

   ```java
   package date;
   
   public class MyDateTest {
   
   	public static void main(String[] args) {
   		MyDate date1 = new MyDate(30, 2, 2000);
   		System.out.println(date1.isValid());
   		MyDate date2 = new MyDate(2, 10, 2006);
   		System.out.println(date2.isValid());
   	}
   
   }
   ```

   
