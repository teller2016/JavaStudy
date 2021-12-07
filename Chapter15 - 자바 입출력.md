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
  - **자료형에 따라 `바이트 스트림`, `문자 스트림`이 있다**

  > **Stream으로 끝나는 경우 바이트 스트림**
  >
  > **Reader, Writer로 끝나는 클래스의 경우 문자 스트림이다**

#### 기반 스트림과 보조 스트림

- 자료를 직접 읽거나 쓰는 기능을 제공하는 스트림, 자료를 직접 읽거나 쓰는 기능은 없이 다른 스트림에 부가 기능을 제공하는 스트림으로 구분된다
- `기반 스트림`: 읽어 들일 곳(소스)이나 써야 할 곳(대상)에서 직접 읽고 쓸 수 있고, 입출력 대상에 **직접 역령되어 생성되는 스트림**
- `보조 스트림`: 직접 읽고 쓰는 기능이 없다, **항상 다른 스트림을 포함하여 생성된다**



## 15-2 표준 입출력

- 자바는 화면에 출력하고 입력받는 표준 입출력 클래스를 미리 정의해 놨다

  - 즉 따로 선언 필요 없이 출력 가능하다

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

### Reader

- **문자 단위**로 읽는 스트림 중 **최상위 스트림**으로 하위 클래스를 주로 사용한다

  | 스트림 클래스     | 설명                                                         |
  | ----------------- | ------------------------------------------------------------ |
  | FileReader        | 파일에서 문자 단위로 읽는 스트림 클래스                      |
  | InputStreamReader | 바이트 단위로 읽은 자료를 문자로 변환해 주는 보조 스트림 클래스 |
  | BufferedReader    | 문자로 읽을 때 배열을 제공하여 한꺼번에 읽을 수 있는 기능을 제공해 주는 보조 스트림 |

- 메서드

  | 메서드                                 | 설명                                                         |
  | -------------------------------------- | ------------------------------------------------------------ |
  | int read()                             | 파일로부터 한 문자를 읽습니다. 읽은 값을 반환한다            |
  | int read(char[] buf)                   | 파일로부터 buf 배열에 문자를 읽습니다                        |
  | int read(char[] buf, int off, int len) | 파일로부터 buf 배열의 off 위치에서부터 len개수만큼 문자를 읽는다 |
  | void close()                           | 스트림과 연결된 파일 리소스를 닫는다                         |

### FileReader

- Reader 중 가장 많이 사용하는 클래스

- 생성자

  | 생성자                  | 설명                                                         |
  | ----------------------- | ------------------------------------------------------------ |
  | FileReader(String name) | 파일 이름 name(경로 포함)을 매개변수로 받아 입력 스트림을 생성 |
  | FileReader(File f)      | File 클래스 정보를 매개변수로 받아 입력 스트림을 생성        |

- Reader 스트림을 사용하지 않고 바이트 단위로 문자를 읽을 때 (예제)

  ```java
  package stream.inputstream;
  import java.io.FileInputStream;
  import java.io.FileNotFoundException;
  import java.io.IOException;
  public class FileInputStreamTest2 {
  
  	public static void main(String[] args) {
  		try(FileInputStream fis = new FileInputStream("reader.txt")){	// 파일 내용: 안녕하세요
  			int i;
  			while((i = fis.read()) != -1) {
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
  ¾
  ?
  ³
  ?
  ?
  ?
  ¼
  ¼
  ¿
  ?
  end
  ```

  - 한글을 **바이트 단위로 읽어 오니 문자가 깨진다**
    - **문자를 입출력할 때는 문자 스트림을 사용해야 된다**

- FileReader로 읽기

  ```java
  package stream.reader;
  
  import java.io.FileReader;
  import java.io.IOException;
  
  public class FileReaderTest {
  
  	public static void main(String[] args) {
  		try(FileReader fr = new FileReader("reader.txt")){
  			int i;
  			while((i = fr.read()) != -1) {
  				System.out.println((char)i);
  			}
  		} catch (IOException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}
  	}
  }
  /*
  안
  녕
  하
  세
  요
  ```

  - `read(char[] buf), read(char[] buf, int off, int len)`도 바이트 단위 스트림 `FileInputStream`과 유사하다

### Writer

- **문자 단위**로 쓰는 스트림 중 **최상위 스트림**으로 하위 클래스를 주로 사용한다

  | 스트림 클래스      | 설명설명                                                     |
  | ------------------ | ------------------------------------------------------------ |
  | FileWriter         | 파일에 문자 단위로 출력하는 스트림 클래스                    |
  | OutputStreamWriter | 바이트 단위로 출력한 자료를 문자로 변환해 주는 보조 스트림 클래스 |
  | BufferedWriter     | 문자로 쓸 때 배열을 제공하여 한꺼번에 쓸 수 있는 기능을 제공해 주는 보조 스트림 |

- 메서드

  | 메서드                                   | 설명                                                         |
  | ---------------------------------------- | ------------------------------------------------------------ |
  | void write(int c)                        | 한 문자를 파일에 출력합니다                                  |
  | void write(char[] buf)                   | 문자 배열 buf의 내용을 파일에 출력합니다                     |
  | void write(char[] buf, int off, int len) | 문자 배열 buf의 off위치에서부터 len개수의 문자를 파일에 출력합니다 |
  | void write(String str)                   | 문자열 str를 파일에 출력합니다                               |
  | void write(String str, int off, int len) | 문자열 str의 off번째 문자부터 len 개수만큼 파일에 출력합니다 |
  | void flush()                             | 파일에 출력하기 전에 자료가 있는 공간(출력 버퍼)을 비워 출력합니다 |
  | void close()                             | 파일과 연결된 스트림을 닫습니다. 출력 버퍼도 비워집니다      |

### FileWriter

- Writer 스트림 중 가장 많이 사용하는 스트림 클래스

- 출력 파일이 존재하지 않으면 파일을 생성한다

- 생성자

  | 생성자                                       | 설명                                                         |
  | -------------------------------------------- | ------------------------------------------------------------ |
  | FileWriter(String name)                      | 파일 이름 name(경로 포함)을 매개변수로 받아 출력 스트림 생성 |
  | FileWriter(String name,<br />boolean append) | 파일 이름 name(경로 포함)을 매개변수로 받아 스트림 생성.<br />append 값이 true이면 파일 스트림을 닫고 다시 생성할 때 파일의 끝에 이어서 쓴다. 디폴트 값을 false다 |
  | FileWriter(File f, )                         | File 클래스 정보를 매개변수로 받아 출력 스트림을 생성        |
  | FileWriter(File f,<br />boolean append)      | File 클래스 정보를 매개변수로 받아 출력 스트림 생성.<br />append 값이 true이면 파일 스트림을 닫고 다시 생성할 때 파일의 끝에 이어서 쓴다. 디폴트 값을 false다 |

- FileWriter 사용 예제

  ```java
  package stream.writer;
  
  import java.io.FileWriter;
  import java.io.IOException;
  
  public class FileWriterTest {
  
  	public static void main(String[] args) {
  		try(FileWriter fw = new FileWriter("writer.txt")){
  			fw.write('A');	//문자 하나 출력
  			
  			char buf[] = {'B', 'C', 'D', 'E', 'F', 'G'};	//문자 배열 출력
  			fw.write(buf);
  			
  			fw.write("안녕하세요. 하이하이");		//문자열 출력
  			
  			fw.write(buf, 1, 2);	//문자 배열의 일부 출력
  			
  			fw.write("65");		//숫자를 그대로 출력
  		} catch (IOException e) {
  			e.printStackTrace();
  		}
  	}
  }
  /*
  [writer.txt]
  ABCDEFG안녕하세요. 하이하이CD65
  ```

  - 65라는 바이트가 아닌 문자를 출력하여 "65" 출력됨



## 15-5 보조 스트림

### 보조 스트림이란?

- 보조 스트림은 입출력 대상이 되는 파일이나 네트워크에 직접 쓰거나 읽는 기능은 없다
  - 보조 기능을 추가해주는 스트림이다
  - 보조 기능은 여러 스트림에 적용할 수 있다
  - 보조 스트림 == `Wrapper 스트림`
    - 다른 스트림을 감싸고 있다는 의미
- 생성자의 매개변수로 다른 스트림을 받게 되면 자신이 감싸고 있는 스트림이 읽거나 쓰는 기능을 수행할 때 보조한다

### FilterInputStream과 FilterOutputStream

- 둘은 보조 스트림의 상위 클래스이다

  - 모든 보조 스트림은 FilterInputStream이나 FilterOutputStream을 상속받는다

- 생성자

  | 생성자                                      | 설명                                        |
  | ------------------------------------------- | ------------------------------------------- |
  | protected FilterInputStream(InputStream in) | 생성자의 매개변수로 InputStream을 받습니다  |
  | public FilterOutputStream(OutputStream out) | 생성자의 매개변수로 OutputStream을 받습니다 |

  - 두 클래스 모두 다른 생성자는 제공하지 않는다
    - 이들 클래스를 상속받은 보조 클래스도 다른 스트림을 배개변수로 받아 상위 클래스를 호출해야된다

- 보조 스트림의 생성자에 다른 보조 스트림을 매개 변수로 전달받을 수 있다

  > 바이트 단위 파일 입력 스트림 [ 기반 스트림 ]
  >
  > \+ 문자로 변환 기능 추가 [ 보조 스트림 ]
  >
  > \+ 버퍼링 기능 추가 [ 보조 스트림 ]

### InputStreamReader와 OutputStreamWriter

- 바이트 단위로 자료를 읽으면 한글 같은 문자는 깨진다

  - 문자는 `Reader`나 `Writer`에서 상속받은 스트림을 사용해서 자료를 읽어야 된다
  - 그런데 바이트 자료만 입력되는 스트림이 있다
    - Ex. `System.in`, `InputStream`, `OutputStream`
    - 이렇게 **생성된 바이트 스트림을 문자료 변환해 주는 보조 스트림이 `InputStreamReader`, `OutputStreamWriter`이다**

- 보조 스트림은 입출력 기능이 없어서 다른 입출력 스트림을 포함한다

  - `InputStreamReader`의 생성자

    | 생성자                                                | 설명                                                         |
    | ----------------------------------------------------- | ------------------------------------------------------------ |
    | InputStreamReader(InputStream in)                     | InputStream 클래스를 생성자의 매개변수로 받아 Reader를 생성한다 |
    | InputStreamReader(InputStream in, Charset cs)         | InputStream과 Charset 클래스를 매개변수로 받아 Reader를 생성한다 |
    | InputStreamReader(InputStream in, CharsetDecoder dec) | InputStream과 CharsetDecoder를 매개변수로 받아 Reader를 생성한다 |
    | InputStreamReader(InputStream in, String charsetName) | InputStream과 String으로 문자 세트 이름을 받아 Reader를 생성한다 |

  - `InputStream` 생성자의 매개변수로 바이트 스트림과 문자 세트를 매개변수로 지정 가능

    - `문자 세트`: 문자를 표현하는 인코딩 방식
    - 바이트 자료가 문자로 변환될 때 지정한 문자 세트가 적용된다 (ex. UTF-16)

  - 생성자에서 매개변수로 받은 `InputStream`이 바이트 자료를 읽으면 문자로 바꿔준다

  ```java
  package stream.decorator;
  
  import java.io.FileInputStream;
  import java.io.FileNotFoundException;
  import java.io.IOException;
  import java.io.InputStreamReader;
  
  public class InputStreamReaderTest {
  
  	public static void main(String[] args) {
  		try(InputStreamReader isr = new InputStreamReader(new FileInputStream("reader.txt"))){
  			//보조 스트림인 InputStreamReader의 매개변수로 기반 스트림인 FileInputStream을 받아 생성함
  			int i;
  			while((i = isr.read()) != -1) {
  				System.out.print((char)i);
  			}
  		} catch (IOException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}
  	}
  
  }
  /*
  안녕하세요
  ```

  - 보조 스트림인 `InputStreamReader`의 매개변수로 기반 스트림인 `FileInputStream`을 받아 생성함
    - `InputStreamReader`은 파일 스트림이 바이트 단위로 읽어들인 내용을 문자로 변환해 주는 역할을 한다
    - 사실 변환 필요없이 `FileReader`로 바로 읽으면 된다

### Buffered 스트림

- 입출력이 한 바이트나 문자 단위로 이루어지면 프로그램 수행 속도가 느려진다

- `Buffered` 스트림은 내부적으로 8,192바이트 크기의 배열을 가지고 있다

  - 스트림에 배열 기능을 추가해 더 빠르게 입출력을 실행할 수 있는 **버퍼링 기능 제공**

- 버퍼링 기능 제공 스트림 클래스

  | 스트림 클래스        | 설명                                                   |
  | -------------------- | ------------------------------------------------------ |
  | BufferedInputStream  | 바이트 단위로 읽는 스트림에 버퍼링 기능을 제공한다     |
  | BufferedOutputStream | 바이트 단위로 출력하는 스트림에 버퍼링 기능을 제공한다 |
  | BufferedReader       | 문자 단위로 읽는 스트림에 버퍼링 기능 제공             |
  | BufferedWriter       | 문자 단위로 출력하는 스트림에 버퍼링 기능 제공         |

- 버퍼링 기능을 제공하는 스트림도 보조 스트림이다

  - 다른 스트림을 포함하여 수행된다

  | 생성자                                        | 설명                                                         |
  | --------------------------------------------- | ------------------------------------------------------------ |
  | BufferedInputStream(InputStream in)           | InputStream 클래스를 생성자의 매개변수로 받아 BufferedInputStream을 생성 |
  | BufferedInputStream(InputStream in, int size) | InputStream 클래스와 **버퍼 크기**를 생성자의 매개변수로 받아 BufferedInputStream을 생성 |

  - `BufferedOutputStream`은 `OutputStream`, `BufferedReader`은 `Reader`, `BufferedWriter`은 `Writer` 클래스를 생성자의 매개변수로 받는다

- Buffered 스트림 사용할 때와 그렇지 않을 때 수행 시간 비교

  - `a.zip`파일을 `copy.zip`파일로 복사

  ```java
  package stream.decorator;
  
  import java.io.FileInputStream;
  import java.io.FileNotFoundException;
  import java.io.FileOutputStream;
  import java.io.IOException;
  
  public class FileCopyTest {
  
  	public static void main(String[] args) {
  		long millisecond = 0;
  		try(
  				FileInputStream fis = new FileInputStream("a.zip");
  				FileOutputStream fos = new FileOutputStream("copy.zip")
  				){
  			millisecond = System.currentTimeMillis();	//파일 복사를 시작하기전 시간
  			int i;
  			while((i = fis.read()) != -1)
  				fos.write(i);
  			
  			millisecond = System.currentTimeMillis() - millisecond;	//파일을 복사하는데 걸리는 시간
  		} catch (IOException e) {
  			e.printStackTrace();
  		}
  		System.out.println("파일 복사하는데 걸린 시간: "+millisecond);
  	}
  
  }
  /*
  파일 복사하는데 걸린 시간: 30812
  ```

  - `a.zip` 파일에서 한 바이트씩 읽어서 `copy.zip` 파일에 쓴다
  - `FileInputStream`은 바이트 단위로 자료를 읽는다
    - 한 바이트씩 읽고 쓰는 과정의 시간이 여러 바이트를 한꺼번에 읽고 쓰는 것보다 느리다

  - 보조 스트림 `BufferedInputStream`과 `BufferedOutputStream`을 사용하여 파일 복사

    ```java
    public class BufferedStreamTest {
    
    	public static void main(String[] args) {
    		long millisecond = 0;
    		try(FileInputStream fis = new FileInputStream("a.zip");
    			FileOutputStream fos = new FileOutputStream("copy.zip");
    			BufferedInputStream bis = new BufferedInputStream(fis);
    			BufferedOutputStream bos = new BufferedOutputStream(fos)){
    			
    			millisecond = System.currentTimeMillis();
    			int i;
    			while((i = bis.read())!= -1)
    				bos.write(i);
    			
    			millisecond = System.currentTimeMillis() - millisecond;
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		
    		System.out.println("파일 복사하는데: "+millisecond+"millisecond");
    	}
    
    }
    /*
    파일 복사하는데: 57millisecond
    ```

    - Buffered 스트림은 멤버 변수로 8,192바이트 배열을 가지고 있다
      - 한번 자료를 읽을 때 8KB 정보를 한꺼번에 읽고 쓸 수 있어서 훨씬 빠르게 수행된다

> **소켓 통신에서 스트림 이용하기**
>
> - 채팅 프로그램을 만들기로 하자
>   - 채팅을 위해 서버와 채팅 클라이언트 프로그램이 통신해야된다
>   - 자바는 통신을 위한 클래스를 제공한다
>     - Ex. 소켓 통신
> - `소켓`: 통신에 사용하는 네트워크 연결 리소스
>
> - `Socket` 클래스에서 스트림 사용 방법
>
>   ```java
>   Socket socket = new Socket();
>   InputStream is = socket.getInputStream();
>   ```
>
>   - 소켓 통신을 하기 위해 스트림을 가져올때 `getInputStream(), getOutputStream()` 사용
>   - `getInputStream()` 메서드 호출 시 `InputStream`이 반환된다
>     - `InputStream`은 바이트 단위 스트림이므로 한글 쓰면 깨짐
>
> - 문자를 쓰고 버퍼링 기능 추가
>
>   ```java
>   Socket socket = new Socket();
>   BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
>   BufferedWriter br = new BufferedWriter(new InputStreamWriter(socket.getOutputStream()));
>   ```

### DataInputStream과 DataOutputStream

> 이전까지 스트림은 텍스트 형식의 자료를 다뤘다

- `DataInputStream`과 `DataOutputStream`은 **메모리에 저장된 0,1 상태를 그대로 읽거나 쓴다**

  - 자료형 크기가 그대로 보존된다

- 생성자

  | 생성자                             | 설명                                                         |
  | ---------------------------------- | ------------------------------------------------------------ |
  | DataInputStream(InputStream in)    | InputStream을 생성자의 매개변수로 받아 DataInputStream을 생성한다 |
  | DataOutputStream(OutputStream out) | OutputStream을 생성자의 매개변수로 받아 DataOutputStream을 생성한다 |

- `DataInputStream`은 자료형별 메서드를 제공한다

  | 메서드                | 설명                                              |
  | --------------------- | ------------------------------------------------- |
  | byte readByte()       | 1바이트를 읽어 반환                               |
  | boolean readBoolean() | 읽은 자료가 0이 아니면 true를, 0이면 false를 반환 |
  | char readChar()       | 한 문자를 읽어 반환                               |
  | short readShort()     | 2바이트를 읽어 정수 값을 반환                     |
  | int readInt()         | 4바이트를 읽어 정수 값을 반환                     |
  | long readLong()       | 8바이트를 읽어 정수 값을 반환                     |
  | float readFloat()     | 4바이트를 읽어 실수값을 반환                      |
  | double readDouble()   | 8바이트를 읽어 실수값을 반환                      |
  | String readUTF()      | 수정된 UTF-8 인코딩 기반으로 문자열을 읽어 반환   |

- `DataOutputStream`은 자료형별 `write()`메서드 제공

  | 메서드                          | 설명                                       |
  | ------------------------------- | ------------------------------------------ |
  | byte writeByte(int v)           | 1바이트의 자료 출력                        |
  | boolean writeBoolean(boolean v) | 1바이트 값을 출력                          |
  | char writeChar(int v)           | 2바이트를 출력                             |
  | short writeShort(int v)         | 2바이트를 출력                             |
  | int writeInt(int v)             | 4바이트를 출력                             |
  | long writeLong(long v)          | 8바이트를 출력                             |
  | float writeFloat(float v)       | 4바이트를 출력                             |
  | double writeDouble(double v)    | 8바이트를 출력                             |
  | String writeUTF(String str)     | 수정된 UTF-8 인코딩 기반으로 문자열을 출력 |

  - **자료형을 그대로 읽고 쓰는 스트림이라 같은 정수라도 자료형에 따라 다르게 처리한다**

    > writeByte(100)은 1바이트로 쓰인 100을 의미
    > writeInt(100)은 4바이트로 쓰인 100을 의미
    >
    > - 자료를 쓸때 사용한 메서드와 같은 자료형의 메서드로 읽어야 한다
    >
    >   > 정수 100쓰는데 writeInt(100)쓰고 readByte()로 읽으면 서로 사용한 메모리 크기가 달라서 같은 값을 가져올 수 없다
    >
    > - 파일이든 네트워크든 자료를 쓸 때 사용한 메서드 순서대로 읽어야된다

- 파일에 여러 자료형 값을 저장하는 예제

  ```java
  public class DataStreamTest {
  
  	public static void main(String[] args) {
  		try(FileOutputStream fos = new FileOutputStream("data.txt");
  				DataOutputStream dos = new DataOutputStream(fos)){
  			dos.writeByte(100);		//각 자료형에 맞게 자료를 썼다
  			dos.writeChar('A');
  			dos.writeInt(10);
  			dos.writeFloat(3.14F);
  			dos.writeUTF("Test");
  		} catch (IOException e) {
  			e.printStackTrace();
  		}
  		
  		try(FileInputStream fis = new FileInputStream("data.txt");
  				DataInputStream dis = new DataInputStream(fis)){
  			System.out.println(dis.readByte());		//자료형에 맞게 자료를 읽어 출력한다. 
  			System.out.println(dis.readChar());		//파일에 쓴 순서와 같은 순서, 같은 메서드로 읽어야된다
  			System.out.println(dis.readInt());
  			System.out.println(dis.readFloat());
  			System.out.println(dis.readUTF());
  		} catch (IOException e) {
  			e.printStackTrace();
  		}
  	}
  }
  /*
  100
  A
  10
  3.14
  Test
  ```

  - **기반 스트림에서 쓸 수 없던 각 자료형의 자료를 그대로 읽고 쓸 수 있다**
  - **파일에 쓴 동일한 순서, 동일한 메서드로 읽어야 된다**

> **자바 입출력 스트림은 데코레이터 패턴이다**
>
> - 보조 스트림은 자바 입출력 스트림 클래스에 유동적이고 효율적으로 기능을 추가하거나 제거할 수 있는 클래스이다
>
>   - 이러한 구조를 디자인 패턴에서 `데코레이터 패턴`이라고 한다
>
> - `데코레이터 패턴`은 실제 입출력이 가능한 클래스와 그렇지 않은 클래스로 나뉜다
>
>   - 기능이 동적으로 추가되는 클래스를 `데코레이터(장식자)`라고 한다
>   - `데코레이터`는 하나의 클래스에 국한하지 않고 여러 클래스에 적용 가능
>
>   ```java
>   WhippedCreamCoffee whippedCreamCoffee = new WhippedCreamCoffee(new MochaCoffee(new LatteCoffee(new KenyaCoffee())));
>   ```
>
>   - **실제 커피는 `Kenya Coffee`이며 나머지는 클래스 데코레이터이다**



## 15-6 직렬화

### 직렬화와 역직렬화

- 클래스의 인스턴스가 생성되면 변수 값은 계속 변하게 된다

  - `직렬화(serialization)`: 인스턴스의 어느 순간 상태를 그대로 저장하거나 네트워크를 통해 전송할 일
    - 인스턴스 내용을 연속 스트림으로 만드는 것, 인스턴스 변수 값을 스트림으로 만드는 것
  - `역직렬화(deserialization)`: 저장된 내용이나 전송받은 내용을 다시 복원하는 것

- `ObjectInputStream`, `ObjectOutputStream`을 통해 구현한다

  | 생성자                               | 설명                                                         |
  | ------------------------------------ | ------------------------------------------------------------ |
  | ObjectInputStream(InputStream in)    | InputStream을 생성자의 매개변수로 받아 ObjectInputStream을 생성한다 |
  | ObjectOutputStream(OutputStream out) | OutputStream을 생성자의 매개변수로 받아 ObjectOutputStream을 생성한다 |

- 저장할 파일이나 전송할 네트워크 등의 기반 스트림을 매개변수로 받아서 인스턴스 변수 값을 저장하거나 전송한다

- Person 클래스를 만들어 인스턴스로 생성한 후 파일에 썼다가 복원하는 예제

  ```java
  package stream.serialization;
  
  import java.io.FileInputStream;
  import java.io.FileOutputStream;
  import java.io.IOException;
  import java.io.ObjectInputStream;
  import java.io.ObjectOutputStream;
  
  class Person{
  	private static final long serialVersionUID = -1503252402544036183L;		//버전 관리를 위한 정보
  	String name;
  	String job;
  	
  	public Person() {}
  
  	public Person(String name, String job) {
  		this.name = name;
  		this.job = job;
  	}
  
  	@Override
  	public String toString() {
  		return name +", "+job;
  	}
  	
  	
  }
  
  public class SerializationTest {
  
  	public static void main(String[] args) {
  		Person personAhn = new Person("안재용", "대표이사");
  		Person personKim = new Person("김철수", "상무이사");
  		
  		try(FileOutputStream fos = new FileOutputStream("serial.out");
  				ObjectOutputStream oos = new ObjectOutputStream(fos)
  				){
  			oos.writeObject(personAhn);	// person 인스턴스를 파일에 씀 (직렬화)
  			oos.writeObject(personKim);
  		} catch (IOException e) {
  			e.printStackTrace();
  		}
  		
  		try(FileInputStream fis = new FileInputStream("serial.out");
  				ObjectInputStream ois = new ObjectInputStream(fis)
  				){
  			Person p1 = (Person)ois.readObject(); // person 인스턴스를 파일에 읽어들임 (역직렬화)
  			Person p2 = (Person)ois.readObject();
  			
  			System.out.println(p1);
  			System.out.println(p2);
  		} catch (ClassNotFoundException e) {
  			e.printStackTrace();
  		} catch (IOException e) {
  			e.printStackTrace();
  		}
  	}
  }
  ```

  - 직렬화에 이용할 `Person` 클래스 생성
  - `personAhn, personKim` 값이 `serial.out`파일에 쓰인다 (직렬화)
  - `readObject()`메서드는 반환 값이 `Object`라 형 변환 해줘야됨
  - **오류가 발생한다**

#### Serializable 인터페이스

- 직렬화는 인스턴스 내용이 외부로 유출되는 것이므로 프로그래머가 직렬화 하겠다는 의도를 표시해야 된다

- `Person`클래스에 `마커 인터페이스(marker interface)`인 `Serializable`인터페이스를 추가해야된다

  ```java
  class Person implements Serializable{	//직렬화 하겠다는 의도 표시
  	...
  	String name;
  	String job;
  	...
  	
  }
  /*
  안재용, 대표이사
  김철수, 상무이사
  ```

  - 그러면 다음과 같이 오류없이 출력된다

#### transient 예약어

- 직렬화 될 수 없는 클래스가 존재하거나 직렬화하고 싶지 않은 변수가 있을 수 있다

  - `transient` 예약어: 변수의 직렬화되고 복원되는 과정에서 제외시킨다
  - 사용한 변수 정보는 그 자료형의 기본 값으로 저장된다

  ```java
  String name;
  transient String job;
  /*
  안재용, null
  김철수, null
  ```

#### serialVersionUID를 사용한 버전 관리

- 객체를 역직렬화할 때, 직렬화할 때의 클래스와 상태가 다르면 오류가 발생한다
  - 사이에 클래스가 수정되거나 변경되면 역직렬화 불가능
  - 그래서 **직렬화할 때 자동으로 `serialVersionUID`를 생성하여 정보를 저장한다**
  - 역직렬화시 `serialVersionUID`를 비교한다
    - 클래스 내용이 변경되면 버전이 맞지않아 오류 발생
- 자바 설치 경로의 `bin\serialver.exe`를 사용하면 `serialVersionUID`가 생성된다
  - 이 정보를 클래스 파일에 적으면 된다
  - 이클립스에서는 자동으로 제공한다
  - **직렬화의 대상이 되는 클래스 정보가 바뀌고 이를 공유해야 하는 경우에 버전 정보를 변경하면 된다**

#### Externalizable 인터페이스

- 직렬화 하는데 사용하는 또 다른 인터페이스 `Externalizable`

- `Serializable`과는 달리 구현해야 할 메서드가 존재

  - 객체의 직렬화와 역직렬화를 세밀하게 제어할때 메서드를 구현한다

- name 속성을 가진 `Dog`클래스에 `Externalizable` 구현 예제

  ```java
  package stream.serialization;
  ...
  class Dog implements Externalizable{
  	String name;
  	
  	public Dog() {}
  
  	@Override
  	public void writeExternal(ObjectOutput out) throws IOException {	//Externalizable 인터페이스의 메서드 구현
  		out.writeUTF(name);
  	}
  
  	@Override
  	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
  		name = in.readUTF();
  	}
  
  	@Override
  	public String toString() {
  		return name;
  	}
  }
  
  public class ExternalizableTest {
  
  	public static void main(String[] args) throws IOException, ClassNotFoundException {
  		Dog myDog = new Dog();
  		myDog.name = "몰리";
  		
  		FileOutputStream fos = new FileOutputStream("external.out");
  		ObjectOutputStream oos = new ObjectOutputStream(fos);
  		
  		try(fos; oos){
  			oos.writeObject(myDog);
  		} catch (IOException e) {
  			e.printStackTrace();
  		}
  		
  		FileInputStream fis = new FileInputStream("external.out");
  		ObjectInputStream ois = new ObjectInputStream(fis);
  		
  		Dog dog = (Dog)ois.readObject();
  		System.out.println(dog);
  	}
  
  }
  /*
  몰리
  ```

  

## 15-7 그 외 입출력 클래스

### File 클래스

- 파일이라는 개념을 추상화한 클래스

- 별도의 입출력 기능은 없다

  - 파일 자체의 경로나 정보를 알 수 있고 파일을 생성할 수 있다

- 생성자

  | 생성자                | 설명                                   |
  | --------------------- | -------------------------------------- |
  | File(String pathname) | pathname을 매개변수로 받아 파일을 생성 |

- 예제

  ```java
  package stream.others;
  
  import java.io.File;
  import java.io.IOException;
  
  public class FileTest {
  	public static void main(String[] args) throws IOException {
  		File file = new File("C:\\ONEDRIVE\\OneDrive - 아주대학교\\Java Study\\eclipse-workspace\\Chapter15\\newFile.txt");// 경로에 File 클래스 생성. 실제 파일 생성된거 아님
  		file.createNewFile();	//실제 파일 생성
  		
  		System.out.println(file.isFile());
  		System.out.println(file.isDirectory());
  		System.out.println(file.getName());
  		System.out.println(file.getAbsolutePath());
  		System.out.println(file.getPath());
  		System.out.println(file.canRead());
  		System.out.println(file.canWrite());
  		
  		file.delete();	//파일 삭제
  	}
  }
  /*
  true
  false
  newFile.txt
  C:\ONEDRIVE\OneDrive - 대학교\Java Study\eclipse-workspace\Chapter15\newFile.txt
  C:\ONEDRIVE\OneDrive - 대학교\Java Study\eclipse-workspace\Chapter15\newFile.txt
  true
  true
  ```

  - `createNewFile()`로 실제 파일 생성
  - **생성한 파일은 `FileInputStream`과 같은 파일 입출력 기능을 제공하는 클래스의 생성자 매개변수로 사용 가능**

### RandomAccessFile 클래스

- 입출력 클래스 중 유일하게 파일 입출력을 동시에 할 수 있는 클래스
  - 임의의 위치로 이동하여 자료를 읽을 수 있다
  - **파일의 어느 위치에서 읽고 쓰는지 위치를 가리키는 속성인 `파일 포인터`를 지닌다**
- **스트림을 생성하지 않고 간단하게 파일에 자료를 쓰거나 읽을 때 사용하면 유용**

- 생성자

  | 생성자                                     | 설명                                                         |
  | ------------------------------------------ | ------------------------------------------------------------ |
  | RandomAccessFile(File file, String mode)   | 입출력을 할 File과 입출력 mode를 매개변수로 받습니다.<br />mode에는 읽기 전용 'r'과 읽고 쓰기 기능인 'rw'를 사용할 수 있다 |
  | RandomAccessFile(String file, String mode) | 입출력을 할 파일 이름을 문자열로 받고 입출력 mode를 매개변수로 받습니다.<br />mode에는 읽기 전용 'r'과 읽고 쓰기 기능인 'rw'를 사용할 수 있다 |

- 임의의 위치에 읽거나 쓰는 기능 외에도 다양한 자료형 값을 읽거나 쓸 수 있다

  > **RandomAccessFile JavaDoc 살펴보기**
  >
  > - `DataInput`, `DataOutput` 인터페이스를 구현했다
  >   - `DataInputStream` 및 `DataOutputStream`과 같이 다양한 자료형을 다루는 메서드 사용 가능

- 여러 자료형 읽고 쓰는 예제

  ```java
  package stream.others;
  
  import java.io.IOException;
  import java.io.RandomAccessFile;
  
  public class RandomAccessFileTest {
  
  	public static void main(String[] args) throws IOException {
  		RandomAccessFile rf = new RandomAccessFile("random.txt", "rw");
  		rf.writeInt(100);
  		System.out.println("파일 포인터 위치: "+rf.getFilePointer());
  		
  		rf.writeDouble(3.14);
  		System.out.println("파일 포인터 위치: "+rf.getFilePointer());
  		
  		rf.writeUTF("안녕하세요");
  		System.out.println("파일 포인터 위치: "+rf.getFilePointer());
  		
  		int i = rf.readInt();
  		double d = rf.readDouble();
  		String str = rf.readUTF();
  		
  		System.out.println("파일 포인터 위치: "+rf.getFilePointer());
  		System.out.println(i);
  		System.out.println(d);
  		System.out.println(str);
  	}
  }
  /*
  파일 포인터 위치: 4
  파일 포인터 위치: 12
  파일 포인터 위치: 29
  Error.. 솰라솰라
  ```

  - `rw`모드를 사용해 읽고 쓰기 가능

  - 포인터의 위치

    ```java
    rf.writeInt(100);		//int 4바이트
    rf.writeDouble(3.14);	//double 8바이트
    rf.writeUTF("안녕하세요");//17바이트
    // 총 29바이트 => 파일 포인터 위치: 29
    ```

    - **파일 포인터 위치 29에서 `read()`메서드를 호출하면 오류 발생**
    - **파일 포인터 위치 변경해주는 `seek()` 메서드를 활용하여 맨 처음으로 이동해줘야 된다**

> **파일 포인터**
>
> - 파일에 자료를 읽거나 쓰면 파일 포인터가 이동한다
> - `RandomAccessFile` 클래스를 생성하면 파일 포인터의 위치는 맨 앞, 0의 위치를 가리킨다
>   - int 값을 쓰면 4바이트이므로 포인터가 4로 이동

- 수정

  ```java
  public class RandomAccessFileTest {
  
  	public static void main(String[] args) throws IOException {
  		RandomAccessFile rf = new RandomAccessFile("random.txt", "rw");
  		rf.writeInt(100);
  		System.out.println("파일 포인터 위치: "+rf.getFilePointer());
  		
  		rf.writeDouble(3.14);
  		System.out.println("파일 포인터 위치: "+rf.getFilePointer());
  		
  		rf.writeUTF("안녕하세요");
  		System.out.println("파일 포인터 위치: "+rf.getFilePointer());
  		
  		rf.seek(0);													//파일 포인터 위치를 맨 처음으로 옮겼다
  		System.out.println("파일 포인터 위치: "+rf.getFilePointer());
  		
  		int i = rf.readInt();
  		double d = rf.readDouble();
  		String str = rf.readUTF();
  				
  		System.out.println("파일 포인터 위치: "+rf.getFilePointer());
  		System.out.println(i);
  		System.out.println(d);
  		System.out.println(str);
  	}
  }
  /*
  파일 포인터 위치: 4
  파일 포인터 위치: 12
  파일 포인터 위치: 29
  파일 포인터 위치: 0
  파일 포인터 위치: 29
  100
  3.14
  안녕하세요
  ```



## 연습문제

1. 자바에서 입출력 기능을 스트림 클래스에서 제공하는 이유는 무엇인가?

   > 입출력 기능을 추상화하여 클래스로 제공함으로써 장치(하드웨어)에 독립적으로 프로그래밍 가능하다

2. 바이트로 읽어 들인 자료를 문자로 변환해주는 스트림은 `InputStreamReader`이다

3. `FileOutputStream`과 `OutputStreamWriter`를 활용하여 a.txt파일에 "자바 공부 재밌어요" 출력해라

   ```java
   package stream.outputstream;
   
   import java.io.OutputStreamWriter;
   import java.io.FileNotFoundException;
   import java.io.FileOutputStream;
   import java.io.IOException;
   
   public class Question3 {
   
   	public static void main(String[] args) throws FileNotFoundException {
   		FileOutputStream fos = new FileOutputStream("a.txt", true);
   		OutputStreamWriter sw = new OutputStreamWriter(fos);
   		try(fos;sw){
   			sw.write("자바 공부 재밌어요");
   		} catch (IOException e) {
   			// TODO Auto-generated catch block
   			e.printStackTrace();
   		}
   	}
   }
   /*
   [a.txt]
   자바 공부 재밌어요
   ```

4. 다른 스트림을 감싸서 부가 기능을 제공하는 스트림은 `보조 스트림`이다

5. 인스턴스 내용을 그대로 저장하거나 네트워크로 전송할 수 있도록 연속된 바이트로 만들고 이를 복원하는 기술을 `직렬화`입니다

6. `4,5번`의 기술을 구현하기 위해 자바에서 사용하는 두 가지 인터페이스는 `Serializable`, `Externalizable`이다
