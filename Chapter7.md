# 7장 - 배열과 ArrayList

## 7-1 배열이란?

### 자료를 순차적으로 관리하는 구조

- 배열을 사용하면 **자료형이 같은** 자료 여러개를 한 번에 관리할 수 있다

### 배열 선언과 초기화

- 배열 선언 문법

  > (자료형)[ ] (배열 이름) = new (자료형)[개수];
  >
  > (자료형) (배열 이름)[ ] = new (자료형)[개수];

  ```java
  int[] studentIDs = new int[10];	//int형 요소가 10개인 배열 선언
  ```

  - int(4byte) 10개이니 총 40byte의 메모리가 할당됐다

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

### 배열 사용하기

- 배열의 요소에 값을 넣거나 가져올 때 `[ ]`를 사용한다

  ```java
  studentIDs[0] = 10;	//배열의 첫 번째 요소에 값 10을 저장
  ```

- `[ ]`: **인덱스 연산자**

  - 배열 요소가 저장된 메모리 위치를 찾아 주는 역할

- 배열 순서는 **0부터**이다

  - 배열 길이가 n이라 하면, 배열 순서는 **0번 부터 n-1번까지 이다**

    ```java
    package array;
    
    public class ArrayTest {
    
    	public static void main(String[] args) {
    		int[] num = new int[] {1,2,3,4,5,6,7,8};
    		
    		for(int i=0; i<num.length; i++) {
    			System.out.println(num[i]);
    		}
    	}
    }
    ```

    - for문에서 `<= (배열길이 - 1)` 보단 `< (배열길이)`가 더 직관적이다

- ```double[ ] data = new double[5];```의 길이는 5이다
  - 그리고 **초기화하지 않으면 배열의 요소값은 0으로 초기화된다**

### 문자 저장 배열 만들기

```java
public class CharArray {

	public static void main(String[] args) {
		char[] alphabets = new char[26];
		char ch = 'A';
		
		for(int i=0; i<alphabets.length; i++, ch++) {
			alphabets[i] = ch;
		}
		
		for(int i=0;i<alphabets.length;i++) {
			System.out.println(alphabets[i] + ", " + (int)alphabets[i]);
		}
	}
}
/*
A, 65
B, 66
C, 67
D, 68
...
*/
```

### 객체 배열 사용하기

- 참조 자료형 변수도 여러개를 배열로 사용할 수 있다

  ```java
  public class Book {
  	private String bookName;
  	private String author;
  	
  	public Book() {	}
  	public Book(String bookName, String author) {
  		this.bookName = bookName;
  		this.author = author;
  	}
  	public String getBookName() {
  		return bookName;
  	}
  	public void setBookName(String bookName) {
  		this.bookName = bookName;
  	}
  	public String getAuthor() {
  		return author;
  	}
  	public void setAuthor(String author) {
  		this.author = author;
  	}
  	
  	public void showBookInfo() {
  		System.out.println(bookName +", "+author);
  	}
  }
  ```

  ```java
  public class BookArray {
  
  	public static void main(String[] args) {
  		Book[] library = new Book[5];	//Book클래스형으로 객체 배열 생성
  		
  		for(int i=0;i<library.length;i++) {
  			System.out.println(library[i]);
  		}
  	}
  }
  /*
  null
  null
  null
  null
  null
  */
  ```

  - **Book 인스턴스 5개가 생성된게 아니다**
  - `Book[] library = new Book[5];`는 **인스턴스 주소 값을 담을 공간** 5개를 만든거다

  ```java
  public class BookArray2 {
  
  	public static void main(String[] args) {
  		Book[] library = new Book[2];	//Book클래스형으로 객체 배열 생성
  		
  		library[0] = new Book("태백산맥", "조정래");
  		library[1] = new Book("데미안", "헤르만 헤세");
  		
  		for(int i=0;i<library.length;i++) {
  			library[i].showBookInfo();
  			System.out.println(library[i]);
  		}	
  	}
  }
  /*
  태백산맥, 조정래
  array.Book@54bedef2
  데미안, 헤르만 헤세
  array.Book@5caf905d
  */
  ```

  - 배열 각 요소에 **Book 인스턴스 주소 값**이 저장되어 있다

### 배열 복사하기

1. 기존 배열 길이가 같거나 더 긴 배열을 만들고 for문을 사용하여 각 요소 값을 반복해서 복사하는 방법

2. `System.arrarycopy()`메서드를 사용하는 방법

   ```java
   System.arraycopy(src, srcPos, dest, destPos, length);
   ```

   > src: 복사할 배열 이름
   > srcPos: 복사할 배열의 첫 번째 위치
   > dest: 복사해서 붙여 넣을 대상 배열 이름
   > destPos: 붙여 넣기를 시작할 첫 번째 위치
   > length: src에서 dest로 자료를 복사할 요소 개수

   ```java
   public class ArrayCopy {
   
   	public static void main(String[] args) {
   		int[] array1 = {10,20,30,40,50};
   		int[] array2 = {1,2,3,4,5};
   		
   		System.arraycopy(array1, 0, array2, 1, 4);
   		for(int i=0;i<array2.length;i++)
   			System.out.println(array2[i]);
   	}
   
   }
   /*
   1
   10
   20
   30
   40
   */
   ```

- 객체 배열 복사

  ```java
  public class ObjectCopy1 {
  
  	public static void main(String[] args) {
  		Book[] bookArray1 = new Book[3];
  		Book[] bookArray2 = new Book[3];
  		
  		bookArray1[0] = new Book("태백산맥", "조정래");
  		bookArray1[1] = new Book("데미안", "헤르만 헤세");
  		bookArray1[2] = new Book("어떻게 살 것인가", "유시민");
   		System.arraycopy(bookArray1, 0, bookArray2, 0, 3);
  		
  		for(int i=0; i<bookArray2.length; i++){
  			bookArray2[i].showBookInfo();
  		}
  	}
  }
  /*
  태백산맥, 조정래
  데미안, 헤르만 헤세
  어떻게 살 것인가, 유시민
  */
  ```

  - 인스턴스를 생성하지 않았는데 요소 값이 잘 출력된다?

  - **얕은 복사**

    ```java
    public class ObjectCopy2 {
    	public static void main(String[] args) {
    		Book[] bookArray1 = new Book[3];
    		Book[] bookArray2 = new Book[3];
    		
    		bookArray1[0] = new Book("태백산맥", "조정래");
    		bookArray1[1] = new Book("데미안", "헤르만 헤세");
    		bookArray1[2] = new Book("어떻게 살 것인가", "유시민");
     		System.arraycopy(bookArray1, 0, bookArray2, 0, 3);		//얕은 복사 실행
    		
    		for(int i=0; i<bookArray2.length; i++){
    			bookArray2[i].showBookInfo();
    		}
    		
    		bookArray1[0].setBookName("나목");
    		bookArray1[0].setAuthor("박완서");
    		
    		System.out.println("=== bookArray1 ===");
    		for(int i=0; i<bookArray1.length; i++){
    			bookArray1[i].showBookInfo();
    		}
    		
    		System.out.println("=== bookArray2 ===");
    		for(int i=0; i<bookArray2.length; i++){
    			bookArray2[i].showBookInfo();
    		}
    	}
    }
    /*
    태백산맥, 조정래
    데미안, 헤르만 헤세
    어떻게 살 것인가, 유시민
    === bookArray1 ===
    나목, 박완서
    데미안, 헤르만 헤세
    어떻게 살 것인가, 유시민
    === bookArray2 ===
    나목, 박완서
    데미안, 헤르만 헤세
    어떻게 살 것인가, 유시민
    */
    ```

    - **bookArray1의 변경 사항이 bookArray2에 반영됨**
    - 객체 배열을 복사할 때 인스턴스를 따로 생성한 것이 아니라 **기존 인스턴스의 주소 값만 복사한다**
    - 주소 값만 복사한다고 해서 **`얕은 복사(shallow copy)`**라고 한다

  - **깊은 복사**

    - 인스턴스를 복사하여 따로 관리하고 싶으면 **`깊은 복사(deep copy)`**를 해야된다

    ```java
    public class ObjectCopy3 {
    	public static void main(String[] args) {
    		Book[] bookArray1 = new Book[3];
    		Book[] bookArray2 = new Book[3];
    		
    		bookArray1[0] = new Book("태백산맥", "조정래");
    		bookArray1[1] = new Book("데미안", "헤르만 헤세");
    		bookArray1[2] = new Book("어떻게 살 것인가", "유시민");
     		
    		bookArray2[0] = new Book(); //객체 직접 생성
    		bookArray2[1] = new Book();
    		bookArray2[2] = new Book();
    		
    		for(int i=0; i<bookArray1.length; i++){   // 각각의 요소를 직접 복사
    			bookArray2[i].setBookName(bookArray1[i].getBookName());
    			bookArray2[i].setAuthor(bookArray1[i].getAuthor());
    		}
    		
    		for(int i=0; i<bookArray2.length; i++){  //복사된 내용 확인
    			bookArray2[i].showBookInfo();
    		}
    		
    		bookArray1[0].setBookName("나목");   //bookArray1 의 내용 수정
    		bookArray1[0].setAuthor("박완서");
    		
    		System.out.println("=== bookArray1 ===");    //bookArray1 출력
    		for(int i=0; i<bookArray1.length; i++){
    			bookArray1[i].showBookInfo();
    		}
    		
    		System.out.println("=== bookArray2 ===");    //bookArray2 출력
    		for(int i=0; i<bookArray2.length; i++){
    			bookArray2[i].showBookInfo();   // bookArray1 과 다른 내용으로 출력됨
    		}
    	}
    }
    ```

### 향상된 for문과 배열

- 배열의 편리한 반복문을 제공한다

  > for(변수 : 배열){
  >
  > ​	반복 실행문;
  >
  > }

  ```java
  public class EnhancedForLoop {
  
  	public static void main(String[] args) {
  		String[] strArray = {"Java", "Android", "C", "Javascript"};
  		
  		for(String lang : strArray)		//lang 변수에 배열의 각 요소가 대입된다
  			System.out.println(lang);
  	}
  }
  ```

  

## 7-2 다차원 배열

### 다차원 배열이란?

- 이차원 이상으로 구현한 배열을 `다차원 배열`이라고 한다
  - 평면이나 공간 개념을 구현하는데 사용

### 이차원 배열

- 2행 3열의 이차원 배열 선언하는 코드

  ```java
  int[][] arr = new int[2][3];
  자료형 배열이름         행  열
  ```

  - 2차원 배열 초기화

    ```java
    int[][] arr = {{1,2,3}, {4,5,6}};
    ```

  ```java
  public class TwoDimension {
  
  	public static void main(String[] args) {
  		int[][] arr = {{1,2,3}, {4,5,6}};
  		
  		for(int i=0;i<arr.length;i++)
  			for(int j=0;j<arr[i].length;j++)
  				System.out.println(arr[i][j]);
  	}
  
  }
  ```

  - `arr.length`: 행의 길이
  - `arr[i].length`: 열의 길이
  - 초기화 하지 않고 `int[][] arr = new int[2][3];` 선언만 하면 **모두 0으로 자동 초기화 된다**



## 7-3 ArrayList 클래스 사용하기

### 기존 배열의 단점과 ArrayList

- 객체 배열을 좀 더 쉽게 사용할 수 있도록 **객체 배열 클래스** `ArrayList`를 제공한다

### ArrayList 클래스의 주요 메서드

- 가장 많이 사용하는 주요 메서드

  | 메서드              | 설명                                                     |
  | ------------------- | -------------------------------------------------------- |
  | boolean add(E e)    | 요소 하나를 배열에 추가. E는 요소의 자료형을 의미        |
  | int size()          | 배열에 추가된 요소 전체 개수를 반환                      |
  | E get(int index)    | 배열의 index 위치에 있는 요소 값을 반환                  |
  | E remove(int index) | 배열의 index 위치에 있는 요소 값을 제거하고 그 값을 반환 |
  | boolean isEmpty()   | 배열이 비어 있는지 확인                                  |

  - add() 메서드를 이용하면 **배열 길이와 상관없이** 객체를 추가할 수 있다

### ArrayList 클래스 활용하기

- ArrayList 사용 형식

  > ArrayList<E> (배열 이름) = new ArrayList<E>();

  - `< >`안에 사용할 객체의 자료형(E)를 넣는다

    ```java
    ArrayList<Book> library = new ArrayList<Book>();
    ```

  - ArrayList는 java.util 패키지에 구현되어 있는 클래스이다

    - 사용을 위해 `import`를 해주어야 한다

      ```java
      import java.util.ArrayList;
      ```

```java
public class ArrayListTest {

	public static void main(String[] args) {
		ArrayList<Book> library = new ArrayList<Book>();
		
		library.add(new Book("책1","몰리"));
		library.add(new Book("책2","보리"));
		
		for(int i=0;i<library.size();i++) {
			Book book = library.get(i);
			book.showBookInfo();
            //libray[i].showBookInfo(); //인덱스 사용 불가능
		}
		System.out.println("=== 향상된 for문 ===");
		for(Book book : library)
			book.showBookInfo();
	}

}
/*
책1, 몰리
책2, 보리
=== 향상된 for문 ===
책1, 몰리
책2, 보리
*/
```



## 7-4 배열 응용 프로그램

- ArrayList를 사용하여 학생 성적 출력 프로그램 구현

### Student 클래스 구현하기

```java
package arrayList;
import java.util.ArrayList;

public class Student {
	int studentID;
	String studentName;
	ArrayList<Subject> subjectList;	//ArrayList 선언
	
	public Student(int studentID, String studentName){
		this.studentID = studentID;
		this.studentName = studentName;
		subjectList = new ArrayList<Subject>();	//ArrayList 생성
	}
	
	public void addSubject(String name, int score) {
		Subject subject = new Subject();
		subject.setName(name);
		subject.setScorePoint(score);
		subjectList.add(subject);
	}
	
	public void showStudentInfo() {
		int total = 0;
		for(Subject s : subjectList) {
			total += s.getScorePoint();
			System.out.println(studentName+"의 "+s.getName()+"과목 성적: "+s.getScorePoint());
		}
		System.out.println("총점: "+total);
	}
}
```

### Subject 클래스 구현하기

```java
public class Subject {
	private String name;
	private int scorePoint;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getScorePoint() {
		return scorePoint;
	}
	public void setScorePoint(int scorePoint) {
		this.scorePoint = scorePoint;
	}
}
```

### 테스트 클래스 구현 후 결과 확인

```java
public class StudentTest {

	public static void main(String[] args) {
		Student studentLee = new Student(1001, "Lee");
		studentLee.addSubject("국어", 100);
		studentLee.addSubject("수학", 50);
		
		Student studentKim = new Student(1002, "Kim");
		studentKim.addSubject("국어", 70);
		studentKim.addSubject("수학", 80);
		studentKim.addSubject("영어", 100);
		
		studentLee.showStudentInfo();
		System.out.println("========================");
		studentKim.showStudentInfo();
	}
}
/*
Lee의 국어과목 성적: 100
Lee의 수학과목 성적: 50
총점: 150
========================
Kim의 국어과목 성적: 70
Kim의 수학과목 성적: 80
Kim의 영어과목 성적: 100
총점: 250
*/
```

