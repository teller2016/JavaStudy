# 14장 - 예외 처리

## 14-1 예외 클래스

### 오류란 무엇인가?

- 프로그램에서 오류 발생 상황 2가지
  - `컴파일 오류(compile error)`: 코드 작성 실수
  - `실행 오류(runtime error)`: 실행 중인 프로그램이 의도하지 않은 동작을 하거나 중지되는 오류
- `버그(bug)`: 프로그램을 잘못 구현하여 의도한 바와 다르게 실행되어 생기는 오류

### 오류와 예외

- 실행 오류 크게 2가지

  - `시스템 오류`: 자바 가상 머신에서 발생하는 오류
    - 프로그램에서 제어 불가능
    - Ex. 사용 가능 동적 메모리가 없는 경우, 스택 메모리의 오버플로
  - `예외(exception)`
    - 프로그램에서 제어 가능
    - Ex. 파일을 읽어 사용하려는데 파일이 없는 경우, 네트워크로 데이터 전송하려는데 연결이 안되는 경우, 배열 출력하려는데 요소가 없는 경우

- 자바에서 제공하는 오류에 대한 전체 클래스

  > [`Error`], [`Exception`] ---> [`Throwable`]

  - 오류 클래스 모두 `Throwable`클래스에서 상속 받는다
  - 프로그램에서 제어하는 부분은 `Exception` 클래스와 그 하위에 있는 예외 클래스가 한다

### 예외 클래스의 종류

- 예외 클래스의 최상위 클래스는 `Exception`클래스이다

  > java.lang.Object
  > 	java.lang.Throwable
  > 		java.lang.Exception

- `Exception` 하위 클래스 중 사용 빈도가 높은 클래스

  > [`FileNotFoundException`], [`SocketException`] ---> [`IOException(입출력 예외 처리)`] ---> 									[`Exception`]
  > [`ArithmeticException`], [`IndexOutofBoundsException`] ---> [`RuntimeException(실행 오류 예외 처리)`] --->

  - 이외에도 많은 클래스 있다



## 14-2 예외 처리하기

### try-catch 문

- 예외를 처리하는 기본 문법 `try-catch`문

  ```java
  try{
      예외가 발생할 수 있는 코드 부분
  }catch(처리할 예외 타입 e){
      try 블록 안에서 예외가 발생했을 때 예외처리를 하는 부분
  }
  ```

#### try-catch문 사용하기

- 배열 예외 발생 예제

  ```java
  package exception;
  
  public class ArrayExceptionHandling {
  
  	public static void main(String[] args) {
  		int[] arr = new int[5];
  		
  		try {		//예외 발생 가능 부분 try블록에 작성
  			for(int i=0; i<=5 ; i++) {
  				arr[i] = i;
  				System.out.println(arr[i]);
  			}
  		}catch(ArrayIndexOutOfBoundsException e) {	//예외 발생시 수행
  			System.out.println(e);
  			System.out.println("예외 처리 부분");
  		}
  		
  		System.out.println("프로그램 종료");
  	}
  }
  /*
  0
  1
  2
  3
  4
  java.lang.ArrayIndexOutOfBoundsException: Index 5 out of bounds for length 5
  예외 처리 부분
  프로그램 종료
  ```

  - 배열 인덱스 범위를 넘어서 예외 발생
    - `ArrayIndexOutOfBoundsException`로 처리하였다
  - 예외 처리 안해도 컴파일 오류가 나지 않아, 도중에 프로그램이 멈춘다
    - **프로그램 비정상 종료 방지를 위해 예외 처리를 해줘야 된다**

### 컴파일러에 의해 예외가 체크되는 경우

- 예외 처리 안해도 컴파일 오류가 나지 않는 경우도 있다
  - BUT 자바에서 제공하는 많은 예외 클래스들은 컴파일러에 의해 처리된다

#### 파일 입출력에서 발생하는 예외 처리하기

- 자바에서 파일을 읽고 쓰는데 `스트림(stream)` 객체를 사용한다

  - 파일 데이터에서 바이트 단위로 읽는 `FileInputStream`사용

    ```java
    package exception;
    import java.io.FileInputStream;
    public class ExceptionHandling1 {
    
    	public static void main(String[] args) {
    		FileInputStream fis = new FileInputStream("a.txt");
    	}
    }
    // `FileNotFoundException`이 처리되지 않았다는 오류 발생
    ```

    - try-catch문으로 감싼다

    ```java
    package exception;
    import java.io.FileInputStream;
    import java.io.FileNotFoundException;
    public class ExceptionHandling1 {
    
    	public static void main(String[] args) {
    		try {
    			FileInputStream fis = new FileInputStream("a.txt");
    		} catch (FileNotFoundException e) {
    			e.printStackTrace();
    		}
    	}
    }
    /*java.io.FileNotFoundException: a.txt (지정된 파일을 찾을 수 없습니다)
    ```

  - try-catch문 사용하기

    ```java
    public class ExceptionHandling1 {
    
    	public static void main(String[] args) {
    		try {
    			FileInputStream fis = new FileInputStream("a.txt");
    		} catch (FileNotFoundException e) {
    			System.out.println(e);
    		}
    		System.out.println("여기도 수행됨");
    	}
    }
    /*
    java.io.FileNotFoundException: a.txt (지정된 파일을 찾을 수 없습니다)
    여기도 수행됨
    ```

    - `FileNotFoundException` e의 toString()메서드 호출했다
    - 비정상 종류 없이 끝까지 수행되었다

### try-catch-finally문

- 프로그램에서 사용한 리소스는 프로그램이 종료되면 자동으로 해제된다

  - BUT 끝나지 않는 서비스의 경우 **리소스를 반드시 `close()` 메서드로 닫아줘야 된다**

- 예제

  ```java
  try {
  			FileInputStream fis = new FileInputStream("a.txt");
  			
  			if(fis != null) {
  				try {
  					fis.close();
  				}catch(IOException e) {
  					e.printStackTrace();
  				}
  			}
  			
  		} catch (FileNotFoundException e) {
  			System.out.println(e);
  		}
  ```

  - 예외 상황마다 try-catch문을 사용하기에는 번거롭다

- `try-catch-finally`

  ```java
  try{
      예외가 발생할 수 있는 코드 부분
  }catch(처리할 예외 타입 e){
      예외처리를 하는 부분
  }finally{
      항상 수행되는 부분
  }
  ```

  ```java
  public class ExceptionHandling1 {
  
  	public static void main(String[] args) {
  		FileInputStream fis = null;
  		
  		try {
  			fis = new FileInputStream("a.txt");
  		} catch (FileNotFoundException e) {
  			System.out.println(e);
  			return;
  		}finally {
  			if(fis != null) {
  				try {
  					fis.close();
  				}catch(IOException e) {
  					e.printStackTrace();
  				}
  			}
  			System.out.println("finally 항상 수행된다");
  		}
  		System.out.println("여기도 수행됨");
  	}
  }
  /*
  java.io.FileNotFoundException: a.txt (지정된 파일을 찾을 수 없습니다)
  finally 항상 수행된다
  ```

  - "a.txt" 파일이 없어 catch문 수행후 finally 블록이 수행되었다

### try-with-resources문

- 시스템 리소스 사용하고 해제하는 코드가 다소 복잡해보인다
  - 자바 7부터 `try-with-resources`문 제공
    - `close()`메서드를 명시적으로 호출하지 않아도 `try` 블록내에서 리소스를 자동으로 닫도록 할 수 있다
    - **`try-with-resources`문법을 사용하려면 해당 리소스가 `AutoCloseable` 인터페이스를 구현해야 된다**

- `Autocloseable` 인터페이스에는 `close()` 메서드가 있다
  - 구현한 클래스는 `close()` 명시적 호출 없이 메서드가 호출된다

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

- AutoCloseTest

  ```java
  public class AutoCloseTest {
  
  	public static void main(String[] args) {
  		try(AutoCloseObj obj = new AutoCloseObj()){		//사용할 리소스 선언
  			
  		}catch(Exception e) {
  			System.out.println("예외 부분");
  		}
  	}
  }
  /*
  리소스가 close() 되었습니다
  ```

  - **`try-with-resources`문을 사용할 때 try문의 괄호안에 리소스를 선언한다**

  - 리소스 여러개 사용시

    ```java
    try(A a = new A(); B b = new B()){	//세미 콜론으로 리소스 구분
        
    }
    ```

- 예외 발생하여 종료 예제

  ```java
  public class AutoCloseTest {
  
  	public static void main(String[] args) {
  		try(AutoCloseObj obj = new AutoCloseObj()){		//사용할 리소스 선언
  			throw new Exception();		//강제 예외 발생
  		}catch(Exception e) {
  			System.out.println("예외 부분");
  		}
  	}
  }
  /*
  리소스가 close() 되었습니다
  예외 부분
  ```

  - `close()`가 먼저 수행되고 예외 블록이 수행된다
  - **`try-with-resource`문을 사용하면 `close()`메서드를 명시적으로 호출하지 않아도 정상 종료, 예외 발생한 경우 모두 리소스가 해제된다**

#### 향상된 try-with-resource문(자바9 에서 추가)

- 자바 7에서는 `AutoCloseable` 인터페이스를 구현한 리소스 변수 선언을 try문 안에서 해야됐다

- 자바 9에서는 try괄호 외부에서 선언한 변수 쓸 수 있다

  ```java
  AutoCloseObj obj = new AutoCloseObj();
  try(obj){	//외부 선언한 변수 사용
      throw new Exception();
  }catch(Exception e) {
  	System.out.println("예외 부분");
  }
  ```



## 14-3 예외 처리 미루기

### 예외 처리를 미루는 throws 사용하기

- 예외를 해당 메서드에서 처리하지 않고 미룬 후 메서드를 호출하는 부분에서 예외를 처리하는 방법

  ```java
  package exception;
  import java.io.FileInputStream;
  import java.io.FileNotFoundException;
  public class ThrowsException {
  	
  	public Class loadClass(String fileName, String className) throws FileNotFoundException, ClassNotFoundException{
  		// 두 예외를 메서드가 호출될 때 처리하도록 미룸
  		FileInputStream fis = new FileInputStream(fileName);	//FileNotFoundException 발생 가능
  		Class c = Class.forName(className);	//ClassNotFoundException 발생 가능
  		return c;
  	}
  	
  	public static void main(String[] args) {
  		ThrowsException test = new ThrowsException();
  		test.loadClass("a.txt", "java.lang.String");	//메서드를 호출할 때 예외를 처리함
  	}
  
  }
  // 오류 발생
  ```

  - `loadClass()`에 예외 처리를 미루겠다는 `throws`를 메서드의 선언부에 추가했다

#### throws를 활용하여 예외 처리 미루기

- 미루면, 그 메서드를 호출하여 사용하는 부분에서 예외 처리를 해야 된다

  - `test.loadClass("a.txt", "java.lang.String");`에서 오류 발생, 3가지 방법으로 오류 처리 가능하다

  - 1. main() 함수 선언부에 throws를 다시 사용하여 예외 처리를 미루는 방법

       - 예외 처리가 자바 가상 머신으로 미뤄져 프로그램 비정상 종료된다

    2. try/multi-catch문 처리

       - 예외를 한문장으로 처리

         ```java
         public static void main(String[] args) {
         		ThrowsException test = new ThrowsException();
         		try {
         			test.loadClass("a.txt", "java.lang.String");
         		} catch (FileNotFoundException | ClassNotFoundException e) {
         			e.printStackTrace();
         		}
         	}
         ```

    3. try-catch문 처리

       - 각 상황마다 예외 처리

         ```java
         public static void main(String[] args) {
         		ThrowsException test = new ThrowsException();
         		try {
         			test.loadClass("a.txt", "java.lang.String");
         		} catch (FileNotFoundException e) {
         			e.printStackTrace();
         		} catch (ClassNotFoundException e) {
         			e.printStackTrace();
         		}	
         	}
         ```

### 다중 예외 처리

- 예외 처리를 하는 경우 이외에도 예외 처리를 해야 할 때가 있다

  > ex. 배열 크기 보다 큰 인덱스를 접근하는 경우 ArrayIndexOutOfBoundsException 발생
  >
  > 이 외에는 컴파일러에 의해 체크되지 않는다

- **모든 예외 상황을 처리하기 위해 맨 마지막 부분에 `Exception`클래스를 활용한 catch 블록을 추가한다**

  ```java
  public class ThrowsException {
  	
  	public Class loadClass(String fileName, String className) throws FileNotFoundException, ClassNotFoundException{
  		FileInputStream fis = new FileInputStream(fileName);	
  		Class c = Class.forName(className);	
  		return c;
  	}
  	
  	public static void main(String[] args) {
  		ThrowsException test = new ThrowsException();
  		try {
  			test.loadClass("a.txt", "java.lang.String");
  		} catch (FileNotFoundException e) {
  			e.printStackTrace();
  		} catch (ClassNotFoundException e) {
  			e.printStackTrace();
  		} catch(Exception e) {		//Exception 클래스로 그 외 예외 상황 처리
  			e.printStackTrace();
  		}
  	}
  }
  ```

  - **`Exception`클래스는 모든 예외 클래스의 최상위 클래스이다**
    - catch 블록 이외의 예외는 모두 `Exception`클래스로 자동 형변환 된다

#### 다중 예외 처리에서 주의사항

- 예외는 catch문 선언한 순서대로 검사한다
  - 맨 위에 `catch(Exception e)` 문장을 쓰면 오류 발생
  - 맨 밑에 둬야 된다



## 14-4 사용자 정의 예외

- 자바 제공 예외 처리 클래스 말고 프로그램에 따라 예외 상황이 발생할 수 있다

  > Ex. 회원가입을 위해 아이디 값이 null이면 안되고 8자 이상 20자 이하 조건

### 사용자 정의 예외 클래스 구현하기

- 사용자 정의 예외 클래스 구현할 때는 JDK에서 제공하는 예외 클래스 중 가장 유사한 클래스를 상속받는게 좋다

  - 유사한 예외를 모르면 `Exception`클래스 상속받으면 된다

- 예제 ( 예외 처리 클래스 만드기 )

  ```java
  package exception;
  
  public class IDFormatException extends Exception {
  	public IDFormatException(String message) {	//생성자 매개변수로 예외 상황 메시지 받음
  		super(message);
  	}
  }
  ```

  - `Exception`클래스 상속받아 구현
    - 클래스에서 이미 메시지 생성자, 멤버 변수와 메서드를 이미 제공하니 `super(message)`를 사용하여 예외 메시지 설정했다

- 테스트

  ```java
  package exception;
  
  public class IDFormatTest {
  	private String userID;
  	
  	
  	public String getUserID() {
  		return userID;
  	}
  
  
  	public void setUserID(String userID) throws IDFormatException {
  		if(userID == null)
  			throw new IDFormatException("아이디는 null일 수 없습니다");
  		else if(userID.length() < 8 || userID.length() > 20)
  			throw new IDFormatException("아이디는 8자 이상 20자 이하로 쓰세요");
  		
  		this.userID = userID;
  	}
  
  
  	public static void main(String[] args) {
  		IDFormatTest test = new IDFormatTest();
  		
  		String userID = null;
  		try {
  			test.setUserID(userID);
  		}catch(IDFormatException e) {
  			System.out.println(e.getMessage());
  		}
  		
  		userID = "1234567";
  		try {
  			test.setUserID(userID);
  		}catch(IDFormatException e) {
  			System.out.println(e.getMessage());
  		}
  	}
  }
  /*
  아이디는 null일 수 없습니다
  아이디는 8자 이상 20자 이하로 쓰세요
  ```

### 예외 처리를 할 때는 로그를 잘 남기자

- 프로그램 개발할 때 `로그(log)`를 남기는 것이 중요하다
  - 오류 발생시 로그를 보고 오류가 발생하는 코드를 순서대로 따라갈 수 있다

- 비밀번호 예외 클래스 만들기

  ```java
  package exception;
  
  public class PasswordException extends Exception {
  	public PasswordException(String message) {
  		super(message);
  	}
  }
  ```

  ```java
  ublic class PasswordExceptionTest {
  	private String password;
  	
  	public String getPassword() {
  		return password;
  	}
  
  	public void setPassword(String password) throws PasswordException {
  		if(password.matches("[a-zA-Z]+"))
  			throw new PasswordException("비번은 문자열로만 이루어질 수없다");
  		else if(password == null)
  			throw new PasswordException("비번은 null일 수 없습니다");
  		else if(password.length() < 5)
  			throw new PasswordException("비번은 5자 이상해야됨");
  		this.password = password;
  	}
  
  
  	public static void main(String[] args) {
  		PasswordExceptionTest test = new PasswordExceptionTest();
  		
  		String userPass = "asdf";
  		try {
  			test.setPassword(userPass);
  		}catch(PasswordException e) {
  			System.out.println(e.getMessage());
  		}
          ...//나머지 try-catch
  	}
  
  }
  ```



## 연습문제

1. 모든 예외 클래스의 최상위 클래스는 `Exception`입니다
2. try{} 블록이 수행되면 항상 수행되는 블록으로서 주로 열린 파일이나 네트워크 리소스의 해제를 수행하는 클록을 구현하는 예약어는 `finally`이다
3. 예외 처리를 위해서 try-catch문장을 사용할 수도 있지만, 예외를 직접 처리하지 않고 미룰 때 사용하는 예약어는 `throws`이다
4. 사용자가 예외를 직접 발생시키기 위해 사용하는 예약어는 `throw`이다