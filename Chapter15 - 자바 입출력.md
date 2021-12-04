# 15장 - 자바 입출력

## 15-1 자바 입출력과 스트림

- 입출력은 외부 저장 장치나 네트워크와 연동해야 되기 때문에 장치에 따라 다르게 구현해야 된다
- 자바는 장치에 따라 독립적이고 효율적인 입출력 기능을 제공

### 스트림이란?

- 자바의 모든 입출력을 `스트림(stream)`을 통해 이루어진다

- 입출력 장치는 매우 다양하다

  - 장치에 따라 입출력 부분을 일일이 다르게 구현하면 프로그램 호환성이 떨어진다
  - **자바는 입출력 장치와 무관하고 일관성 있게 프로그램을 구현할 수 있도록 가상의 통로인 스트림을 제공**

- 자료를 읽어 들이는 `소스(source)`와 자료를 쓰려는 `대상(target)`에 따라 각각 다른 스트림 클래스를 제공

  > [자바 응용 프로그램] ====(스트림)==== [입출력 자료]
  >
  > - 파일 디스크, 키보드, 모니터, 메모리 입출력, 네트워크 등에 입출력 기능 사용

#### 입력 스트림과 출력 스트림

- `입력 스트림`: 어떤 대상으로 부터 자료를 읽어 들일 때 사용하는 스트림

  > Ex. 동영상 재생을 위해 동영상 파일 읽어들임 -> 입력 스트림 사용
  > Ex. 사용자가 쓴 글을 파일에 저장할 때 -> 출력 스트림 사용

  - 스트림은 단방향으로 자료가 이동
    - 입력과 출력 동시에 안됨

  > **InputStream, Reader로 끝나는 클래스는 입력 스트림**
  >
  > **OutputStream, Writer로 끝나는 클래스는 출력 스트림**

#### 바이트 단위 스트림과 문자 단위 스트림

- 자바의 스트림은 **바이트(byte) 단위**로 자료의 입출력이 이루어진다

  - 한글 같은 2바이트인 문자를 위해 **문자 스트림을 별도로 제공**
  - **자로형에 따라 `바이트 스트림`, `문자 스트림`이 있다**

  > **Stream으로 끝나는 경우 바이트 스트림**
  >
  > **Reader, Writer로 끝나는 클래스의 경우 문자 스트림이다**

#### 기반 스트림과 보조 스트림

- 자료를 직접 읽거나 쓰는 기능을 제공하는 스트림, 자료를 직접 읽거나 쓰는 기능은 없이 다른 스트림에 부가 기능을 제공하는 스트림으로 구분된다
- `기반 스트림`: 읽어 들일 곳(소스)이나 써야 할 곳(대상)에서 직접 읽고 쓸 수 있고, 입출력 대상에 **직접 역령되어 생성되는 스트림**
- `보조 스트림`: 직접 읽고 쓰는 기능이 없다, **항상 다른 스트림을 포함하여 생성된다**



## 15-2 표준 입출력

- 자바는 화면에 출력하고 입력받는 표준 입출력 클래스를 미리 정의해 놨다

  - 따로 필요 없다

- `System.out`은 표준 출력을 위한 객체

  - `표준 출력`: 콘솔 화면에 입출력되어 `콘솔 입출력`이라고도 한다

- `표준 입출력`을 위한 System 클래스

  | 자료형              | 변수 이름 | 설명                  |
  | ------------------- | --------- | --------------------- |
  | static PrintStream  | out       | 표준 출력 스트림      |
  | static InputStream  | in        | 표준 입력 스트림      |
  | static OutputStream | err       | 표준 오류 출력 스트림 |

  > System.out은 표준 출력용, System.in은 표준 입력용, System.err은 빨간색으로 오류 출력

  - `System` 클래스 생성 없이 사용할 수 있었던 이유는 `out`변수가 클래스의 정적 변수이기 때문

### System.in으로 화면에서 문자 입력받기

- 입출력에 관련한 코드를 구현하면 예외 처리를 해야된다

- 문자 입력 받는 프로그램

  ```java
  package stream.inputstream;
  import java.io.IOException;
  public class SystemInTest1 {
  
  	public static void main(String[] args) throws IOException {
  		System.out.println("알파벳 쓰고 엔터 눌러라");
  		
  		int i;
  		try {
  			i = System.in.read(); //read 메서드로 한 바이트 읽음
  			System.out.println(i);
  			System.out.println((char)i);
  		}catch(IOException e) {
  			e.printStackTrace();
  		}
  
  	}
  }
  /*
  알파벳 쓰고 엔터 눌러라
  A(입력)
  65
  A
  ```

  - `System.in`은 바이트 단위로 읽어 들이는 `InputStream`이므로 1바이트만 읽는다
  - `InputStream`의 `read()`메서드는 1바이트만 읽어 들인다

- 문자 여러개 입력받기

  ```java
  public class SystemInTest2 {
  
  	public static void main(String[] args) throws IOException {
  		System.out.println("알파벳 여러개 쓰고 엔터 눌러라");
  		
  		int i;
  		try {
  			while((i = System.in.read()) != '\n') {
  				System.out.println((char)i);
  			}
  		}catch(IOException e) {
  			e.printStackTrace();
  		}
  	}
  }
  /*
  알파벳 여러개 쓰고 엔터 눌러라
  asdf(엔터)
  asdf
  ```

### 그 외 입력 클래스

#### Scanner 클래스

- `Scanner`클래스는 문자, 정수, 실수 등 다른 자료형도 읽을 수 있다
  - 콘솔 화면뿐 아니라 파일이나 문자열을 생성자의 매개변수로 받아 자료를 읽어 올 수 있다

- `Scanner` 대표 생성자

  | 생성자                      | 설명                                               |
  | --------------------------- | -------------------------------------------------- |
  | Scanner(File source)        | 파일을 매개변수로 받아 Scanner를 생성한다          |
  | Scanner(InputStream source) | 바이트 스트림을 매개변수로 받아 Scanner을 생성한다 |
  | Scanner(String source)      | String을 배개변수로 받아 Scanner를 생성한다        |

  ```java
  Scanner scanner = new Scanner(System.in)
  ```

  - **사용하면 표준 입력으로 부터 자료를 읽어들이는 기능 사용 가능**

- `Scanner` 클래스 메서드

  | 메서드                | 설명                      |
  | --------------------- | ------------------------- |
  | boolean nextBoolean() | boolean 자료를 읽습니다   |
  | byte nextByte()       | 한 바이트 자료를 읽습니다 |
  | short nextShort()     | short 자료형을 읽습니다   |
  | int nextInt()         | int 자료형을 읽습니다     |
  | long nextLong()       | long 자료형을 읽습니다    |
  | float nextFloat()     | float 자료형을 읽습니다   |
  | double nextDouble()   | double 자료형을 읽습니다  |
  | String nextLine()     | 문자열 String을 읽습니다  |

- 예제

  ```java
  package stream.others;
  import java.util.Scanner;
  public class ScannerTest {
  
  	public static void main(String[] args) {
  		Scanner scanner = new Scanner(System.in);
  		
  		System.out.println("이름");
  		String name = scanner.nextLine();
  		
  		System.out.println("번호:");
  		int num = scanner.nextInt();
  		
  		System.out.println(name);
  		System.out.println(num);
  	}
  }
  /*
  이름
  몰리\n
  번호:
  10\n
  몰리
  10
  ```

  - 바이트 단위로 처리 가능한 `System.in`과 달리 **`Scanner`은 다양한 자료형을 입력할 수 있다**

#### Console 클래스

- `System.in`을 사용하지 않고 간단히 콘솔 내용 읽을 수 있는 `Console` 클래스가 있다

  - **직접 콘솔 창에서 자료를 입력받을 때 사용**

- 메서드

  | 메서드                | 설명                                      |
  | --------------------- | ----------------------------------------- |
  | String readLine()     | 문자열을 읽는다                           |
  | char[] readPassword() | 사용자에게 문자열을 보여 주지 않고 읽는다 |
  | Reader reader()       | Reader 클래스를 반환한다                  |
  | PrintWriter writer()  | PrintWriter 클래스를 반환한다             |

- 예제

  ```java
  package stream.others;
  import java.io.Console;
  public class ConsoleTest {
  
  	public static void main(String[] args) {
  		Console console = System.console();
  		
  		System.out.println("이름: ");
  		String name = console.readLine();
  		
  		System.out.println("비밀번호: ");
  		char[] pass = console.readPassword();
  		String strPass = new String(pass);
  		
  		System.out.println(name);
  		System.out.println(strPass);
  	}
  }
  /*
  PS C:\ONEDRIVE\OneDrive - 아주대학교\Java Study\eclipse-workspace\Chapter15\bin> java stream.others.ConsoleTest
  이름:
  몰리\n
  비밀번호:
  ****\n
  몰리
  ㅁㄴㅇㄹ
  ```



## 15-3 바이트 단위 스트림

### InputStream

- 바이트 단위로 읽는 스트림 중 **최상위 스트림**이다

  - 추상 메서드를 포함하는 **추상 클래스**이다
    - 하위 스트림 클래스가 상속받아 각 클래스에 맞게 추상 메서드 기능을 구현한다

- 주로 사용하는 하위 클래스

  | 스트림 클래스        | 설명                                                         |
  | -------------------- | ------------------------------------------------------------ |
  | FileInputStream      | 파일에서 바이트 단위로 자료를 읽는다                         |
  | ByteArrayInputStream | byte 배열 메모리에서 바이트 단위로 자료를 읽는다             |
  | FilterInputStream    | 기반 스트림에서 자료를 읽을 때 추가 기능을 제공하는 보조 스트림의 상위 클래스 |

- `InputStream`은 바이트 자료를 읽기 위해 다음 메서드 제공

  | 메서드                               | 설명                                                         |
  | ------------------------------------ | ------------------------------------------------------------ |
  | int read()                           | 입력 스트림으로 부터 한 바이트의 자료를 읽는다. 읽은 자료의 바이트 수를 반환 |
  | int read(byte[] b)                   | 입력 스트림으로부터 b[] 크기의 자료를 b[]에 읽는다. 읽은 자료의 바이트 수를 반환 |
  | int read(byte[] b, int off, int len) | b[] 크기의 자료를 b[]의 off 변수 위치부터 저장하며 len만큼 읽는다. 읽은 자료의 바이트 수 반환 |
  | void close()                         | 입력 스트림과 연결된 대상 리소스를 닫는다                    |

  - `read()`는 한 바이트 읽어서 int에 저장한다
    - 더 이상 읽을 자료 없는 경우 -1 반환

### FileInputStream

- 파일에서 바이트 단위로 자료를 읽어 들일 때 사용하는 스트림 클래스

  - 사용을 위해 먼저 스트림 클래스 생성해야 된다

- 생성자

  | 생성자                       | 설명                                                         |
  | ---------------------------- | ------------------------------------------------------------ |
  | FileInputStream(String name) | 파일 이름 name(경로 포함)을 매개변수로 받아 입력 스트림을 생성한다 |
  | FileInputStream(File f)      | File 클래스 정보를 매개변수로 받아 입력 스트림을 생성한다    |

- 예제

  ```java
  package stream.inputstream;
  import java.io.FileInputStream;
  import java.io.IOException;
  public class FileInputStreamTest1 {
  	public static void main(String[] args) {
  		FileInputStream fis = null;
  		
  		try {
  			fis = new FileInputStream("input.txt");	//input.txt 파일 입력 스트림 생성
  			System.out.println(fis.read());
  			System.out.println(fis.read());
  			System.out.println(fis.read());
  		}catch(IOException e) {
  			System.out.println(e);
  		}finally {
  			try {
  				fis.close();
  			}catch(IOException e) {
  				System.out.println(e);
  			}catch(NullPointerException e) {	//스트림이 null인 경우
  				System.out.println(e);
  			}
  		}
  		System.out.println("end");
  	}
  }
  /*
  java.io.FileNotFoundException: input.txt (지정된 파일을 찾을 수 없습니다)
  java.lang.NullPointerException: Cannot invoke "java.io.FileInputStream.close()" because "fis" is null
  end
  ```

  - input.txt가 없어서 FileNotFoundException 발생
  - close() 하려는데 스트림이 생성되지 않았기에 NullPointerException 발생
  - 예외 처리를 함으로써 프로그램이 중단되지 않음

#### 파일에서 자료 읽기

- `input.txt`를 프로젝트 폴더에 추가한다

  > ABC

- 예제 실행 결과

  > 65
  > 66
  > 67
  > end

  - `read()`메서드는 한 바이트씩 읽어 들인다

  ```java
  System.out.println((char)fis.read());		//문자로 변환
  /*
  A
  B
  C
  end
  ```

#### 파일 끝까지 읽기

- 파일의 내용이 얼마나 있는지 모를때 끝까지 읽는 방법

  - `try-with-resource`문 사용

  ```java
  package stream.inputstream;
  import java.io.FileInputStream;
  import java.io.FileNotFoundException;
  import java.io.IOException;
  public class FileInputStreamTest2 {
  
  	public static void main(String[] args) {
  		try(FileInputStream fis = new FileInputStream("input.txt")){
  			int i;
  			while((i = fis.read()) != -1) {	//-1이 아닌 동안 read()메서드로 계속 1바이트 씩 읽음
  				System.out.println((char)i);
  			}
  			System.out.println("end");
  		}catch(FileNotFoundException e) {
  			e.printStackTrace();
  		} catch (IOException e) {
  			e.printStackTrace();
  		}
  	}
  }
  /*
  A
  B
  C
  end
  ```

#### int read(byte[] b) 메서드로 읽기

- `read(byte[] b)` 메서드는 선언한 바이트 배열 크기만큼 한꺼번에 자료를 읽는다

  - 읽어들인 자료의 수를 반환한다

- 예제

  > input2.txt : ABCDEDFHIJKLMNOPQRSTUVWXYZ

  ```java
  package stream.inputstream;
  
  import java.io.FileInputStream;
  import java.io.FileNotFoundException;
  import java.io.IOException;
  
  public class FileInputStreamTest3 {
  
  	public static void main(String[] args) {
  		try(FileInputStream fis = new FileInputStream("input2.txt")){
  			byte[] bs = new byte[10];
  			int i;
  			while((i = fis.read(bs)) != -1) {
  				for(byte b : bs)
  					System.out.print((char)b);
  				System.out.println(": " + i +"바이트 읽음");
  			}
  		} catch (FileNotFoundException e) {
  			e.printStackTrace();
  		} catch (IOException e) {
  			e.printStackTrace();
  		}
  		System.out.println("end");
  	}
  }
  /*
  ABCDEDFHIJ: 10바이트 읽음
  KLMNOPQRST: 10바이트 읽음
  UVWXYZQRST: 6바이트 읽음
  end
  ```

  - -1(파일 끝)이 아닌 때 까지 읽어서 `bs` 배열에 있는 자료 출력

    - `i`에는 읽은 자료 바이트 수 반환

  - **UVWXYZ** 다음에 **QRST**가 붙었다

    - `bs`를 전체 출력해서 그렇다, **메서드 반환 값을 이용하여 해결한다**

    ```java
    for(int k=0; k < i; k++){	//읽은 바이트 수 만큼 출력해서 해결
        System.out.print((char)bs[k])
    }
    ```

### OutputStream

- 바이트 단위로 쓰는 스트림 중 **최상위 스트림**

- 자료의 출력 대상에 따라 다른 스트림을 제공

  | 스트림 클래스         | 설명                                                         |
  | --------------------- | ------------------------------------------------------------ |
  | FileOutputStream      | 바이트 단위로 파일에 자료를 씁니다                           |
  | ByteArrayOutputStream | Byte 배열에 바이트 단위로 자료를 씁니다                      |
  | FilterOutputStream    | 기반 스트림에서 자료를 쓸 때 추가 기능을 제공하는 보조 스트림의 상위 클래스 |

- `OutputStream` 메서드

  | 메서드                                 | 설명                                                         |
  | -------------------------------------- | ------------------------------------------------------------ |
  | void write(int b)                      | 한 바이트를 출력한다                                         |
  | void write(byte[] b)                   | b[] 배열에 있는 자료를 출력한다                              |
  | void write(byte[] b, int off, int len) | b[] 배열에 있는 자료의 off 위치부터 len개수 만큼 자료를 출력 |
  | void flush()                           | 출력을 위해 잠시 자료가 머무르는 출력 버퍼를 강제로 비워 자료를 출력 |
  | void close()                           | 출력 스트림과 연결된 대상 리소스를 닫는다. 출력 버퍼가 비워진다 |

### FileOutputStream

- 파일에 바이트 단위 자료를 출력하기 위해 사용하는 스트림

- 생성자

  | 생성자                                             | 설명                                                         |
  | -------------------------------------------------- | ------------------------------------------------------------ |
  | FileOutputStream(String name)                      | 파일 이름 name(경로 포함)을 매개변수로 받아 출력 스트림 생성 |
  | FileOutputStream(String name,<br />boolean append) | 파일 이름 name(경로 포함)을 매개변수로 받아 스트림 생성.<br />append 값이 true이면 파일 스트림을 닫고 다시 생성할 때 파일의 끝에 이어서 쓴다. 디폴트 값을 false다 |
  | FileOutputStream(File f, )                         | File 클래스 정보를 매개변수로 받아 출력 스트림을 생성        |
  | FileOutputStream(File f,<br />boolean append)      | File 클래스 정보를 매개변수로 받아 출력 스트림 생성.<br />append 값이 true이면 파일 스트림을 닫고 다시 생성할 때 파일의 끝에 이어서 쓴다. 디폴트 값을 false다 |

  - 생성자 매개변수로 전달한 파일이 경로에 없으면 파일을 새로 생성한다
  - 처음부터 새로 쓸지(overwrite), 기존 내용에 연결해서 쓸지(append) 여부를 배개변수로 전달한다

#### write() 메서드 사용하기

- 예제

  ```java
  package stream.outputstream;
  import java.io.FileOutputStream;
  import java.io.IOException;
  public class FileOutputStreamTest1 {
  	public static void main(String[] args) {
  		try(FileOutputStream fos = new FileOutputStream("output.txt", true)){
  			fos.write(65);
  			fos.write(66);
  			fos.write(67);
  		} catch (IOException e) {
  			e.printStackTrace();
  		}
  	}
  }
  /*
  [output.txt]
  ABC
  ```

- 이어서 출력하려면

  ```java
  fos = new FileOutputStream("output.txt", true);
  ```

### write(byte[] b) 메서드 사용하기

- 바이트 배열을 활용하여 여러 자료를 한꺼번에 출력

  ```java
  package stream.outputstream;
  
  import java.io.FileNotFoundException;
  import java.io.FileOutputStream;
  import java.io.IOException;
  
  public class FileOutputStreamTest2 {
  	public static void main(String[] args) throws FileNotFoundException {
  		FileOutputStream fos = new FileOutputStream("output2.txt", true);
  		try(fos){
  			byte[] bs = new byte[26];
  			byte data = 65;
  			for(int i=0; i<bs.length; i++) {
  				bs[i] = data;
  				data++;
  			}
  			fos.write(bs);
  		} catch (IOException e) {
  			e.printStackTrace();
  		}
  	}
  
  }
  /*
  [output2.txt]
  ABCDEFGHIJKLMNOPQRSTUVWXYZ
  ```

  - `fos.write(bs)`를 호출하여 전체 바이트 배열을 한꺼번에 출력했다

#### write(byte[] b, int off, int len) 메서드 사용하기

- `write(byte[] b, int off, int len)`는 전체 배열 자료를 출력하지 않고 off 위치부터 len 길이 만큼 출력

- 예제

  ```java
  package stream.outputstream;
  
  import java.io.FileNotFoundException;
  import java.io.FileOutputStream;
  import java.io.IOException;
  
  public class FileOutputStreamTest3 {
  	public static void main(String[] args) {
  		try(FileOutputStream fos = new FileOutputStream("output3.txt", true)){
  			byte[] bs = new byte[26];
  			byte data = 65;
  			for(int i=0; i<bs.length; i++) {
  				bs[i] = data;
  				data++;
  			}
  			fos.write(bs, 2, 10);		//배열의 2 인덱스부터 10개 바이트 출력
  		} catch (FileNotFoundException e) {
  			e.printStackTrace();
  		} catch (IOException e) {
  			e.printStackTrace();
  		}
  	}
  }
  /*
  [output3.txt]
  CDEFGHIJKL
  ```

#### flush() 메서드와 close() 메서드

- `flush()`메서드는 강제로 자료를 출력한다
- `write()`메서드로 값을 썼다해도 바로 파일이나 네트워크에 전송되지 않는다
  - 출력 버퍼에 어느정도 자료가 모여야 출력된다
  - 즉, `write()`메서드로 출력했어도 파일에 쓰이지 않거나 전송되지 않을 수 있다
    - **이런 경우 `close()`메서드 안에서 `flush()`메서드 호출하여 남은 자료 출력한다 **
    - 출력되면서 버퍼는 비워진다



## 15-4 문자 단위 스트림

