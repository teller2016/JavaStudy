# 2장

## 2-1 컴퓨터의 데이터 표현 방법

#### 컴퓨터에서 수를 표현하는 방법

- 컴퓨터는 0, 1로 표현할 수 있는 최소단위  ```bit```를 사용한다

  - 숫자, 문자도 2진수로 표현한다
  - ```ASCII``` => 영문자, 숫자, 특수 문자를 8비트 값의 수로 정의한 것

- 자바에서는 2, 8, 16진수를 사용할 수 있다

  > 2진수는 ```0B```, 8진수는 ```0```, 16진수는 ```0X```를 붙여서 사용한다

#### 부호있는 수 표현

- 부호 비트```MSB```(Most Significant Bit)는 맨 앞에 붙는다

  > 0이면 양수, 1이면음수
  >
  > 5 (8비트 표현) => 00000101
  >
  > 그러면 10000101은 -5? 아니다

- 2진수를 부호를 바꿔주기 위해서는 ```2의 보수```를 해줘야 된다

  > ```2의 보수```란 보충해 주는 수이다.
  >
  > ex. (10진수) 3에 대한 N의 보수 => N-3
  >
  > 즉 2진수에서 2의 보수는, 더해서 2 -> 10이 되는 수를 뜻한다
  >   0011
  > +1101
  > 10000 인데 4bit 컴퓨터이면 맨 앞의 1이 사라진다.
  > 그래서 0 값이 된다.

  - 1의 보수를 구하여 제일 낮은 자리에 1을 더해주면 2의 보수가 된다



## 2-2 변수란

- 변수란 변하는 수

#### 변수 선언 및 대입

- 변수를 사용하기 위해 ```자료형```을 정해야된다
- 변수 이름을 정하는 것을 ```변수 선언```이라 한다

```java
package chapter2;
public class Variable1 {
	public static void main(String[] args) {
		int level;	//정수형 변수 level 선언
		level = 10;	//level 변수에 10 대입
		System.out.println(level);
	}
}
```

#### 변수 초기화

- 변수에 처음 값을 대입하는 것 => ```초기화```

```java
int level = 10; //level 변수 선언과 동시에 값을 대입(초기화)
```

#### 변수 이름 정하기

> - 변수 이름은 영문자, 숫자, $, _ 만 사용가능
> - 숫자로 시작 못한다
> - 자바에서 사용중인 ```예약어``` 사용 못한다
> - numberOfStudent와 같은 ```camel notation``` 사용하면 좋음



## 2-3 변수가 저장되는 공간의 특성, 자료형

#### 변수와 메모리

- 변수는 컴퓨터 메모리에 저장된다.
- ```메모리```: 프로그램이 실행되는 작업 공간

- 변수가 연산에 사용될때 메모리에서 가져와 사용한다
- ```변수 선언``` => 선언한 이름으로 어떤 위치에 있는 메모리를 얼마만큼의 크기로 사용할지 정의한 것

#### 기본 자료형의 종류

> 1바이트: byte(정수형), boolean(논리형)
> 2바이트: short, char(문자형)
> 4바이트: int, float(실수형)
> 8바이트: long, double

#### 정수 자료형

- 양수, 음수, 0을 나타내는데 사용

- byte, short, int, long형 4가지로 존재

  > 10이란 값을 int형으로 저장하면
  >
  > 4byte -> 32bit
  > 0000 0000 0000 0000 0000 0000 0000 1010 으로 저장된다

- 표현 유효 범위

  > int: -2^31 ~ 2^31-1
  > (부호비트를 제외하여 31bit 사용 + 0 1개 제외)

- 정수형 특징

  > byte: 통신할 때 주로 이용 ex. 동영상, 음악 파일 재생, 네트워크로 데이터 전송할 때 사용
  > short: 2바이트임. 
  > int: 가장 많이 사용. 컴퓨터에서 정수로 연산할때 4바이트 단위 처리가 가장 효율적이기 때문이다.
  > long: int형 범위를 넘어서는 정수 사용할때 이용
  >
  > ```java
  > long num = 12345678900; //에러 발생
  > // 자바는 12345678900을 int형으로 처리한다.
  > long num = 12345678900L; // L혹은 l을 붙여 long으로 처리하라고 컴파일러에게 알려주어야 한다
  > ```

#### 문자 자료형

- 문자 인코딩, 디코딩으로 코드에서 문자로 변환한다

- ```ASCII```는 1byte만 사용
  다른 언어 문자는 2byte 이상을 사용한다
  ```unicode```: 각 언어의 표준 인코딩을 정의한것 (2byte 이다)

- 자바는 ```unicode```에 기반하여 char형은 2바이트이다

  ```java
  char myChar = 'A';
  ```

```java
public class CharacterEx1 {
	public static void main(String[] args) {
		char ch1 = 'A';
		System.out.println(ch1);		//문자 출력
		System.out.println((int)ch1);	//문자에 해당하는 아스키코드값 출력
		
		char ch2 = 66;					
		System.out.println(ch2);		//정수값에 해당하는 문자 출력
		
		int ch3 = 67;
		System.out.println(ch3);
		System.out.println((char)ch3);	//정수값에 해당하는 문자 출력
	}
}
```

- ```''```는 문자에 사용
  ```""```는 문자열에 사용

  >문자열 끝에는 '\0'이 있다.
  >
  >즉 "A"는 "A\0"
  >'A'와 "A"는 다르다

- 문자형에 숫자 저장

  ```java
  char a2 = 65;
  //char a3 = -66;  문자형 변수에 음수를 넣으면 오류 발생
  System.out.println(a2);
  ```

- 자바는 UTF-16 인코딩 사용

  > 유니코드 표현 인코딩 방식은 UTF-8, UTF-16존재한다
  > UTF-16은 2바이트로 문자를 표현하는데, 1바이트 같은 알파벳을 표현하면 메모리 낭비가된다
  >
  > UTF-8은 문자마다 1~4바이트까지 사용하여 문자를 나타낸다. UTF-16에 비해 메모리 낭비가 적고, 전송속도가 빠르다
  > 그래서 UTF-8은 인터넷에 많이 사용된다

#### 실수 자료형

- 실수는 부동 소수점 방식으로 표현한다

  > 0.1 은 1.0 * 10^-1로 
  >
  > 1.0 -> 가수
  > 10 -> 밑수
  > -1 -> 지수

- float형, double형

  >float형: 1부호 1비트, 지수 8비트, 가수 23비트 => 총 32비트 (4바이트) 이용
  >
  >double형: 1부호 1비트, 지수 11비트, 가수 52비트 => 총 64비트(8바이트) 이용

- 자바에서는 double형을 기본으로 사용

  ```java
  package chapter2;
  
  public class DoubleEx1 {
  
  	public static void main(String[] args) {
  		double dnum = 3.14;
  		float fnum = 3.14F; // float의 경우 식별자 F,f를 붙여야 된다
  		
  		System.out.println(dnum);
  		System.out.println(fnum);
  	}
  }
  ```

  - 부동 소수점 방식을 이용하기에 연산을 진행하면 오차가 발생할 수 있다

    ```java
    double dnum = 1;
    for(int i=0; i<10000; i++){
        dnum = dnum + 0.1;
    }
    System.out.println(dnum);	//1001.0000000000159 결과가 나온다
    ```

#### 논리 자료형

- 변수의 참, 거짓을 나타내는 데 사용
- boolean형
  - 1바이트
  - true, false 두 값만 가짐

```java
package chapter2;

public class BooleanEx {

	public static void main(String[] args) {
		boolean isMarried = true;
		System.out.println(isMarried); 	//true 출력
	}
}
```

#### 자료형 없이 변수 선언 (자바 10)

- 지역 변수 자료형 추론(local variable type inference)
  - 변수에 대입되는 자료를 보고 컴파일러가 추측함

```java
var num = 10;		//int
var dNum = 10.0;	//double
var str = "hello";	//string으로 컴파일 
```

- 유의사항

  - 한번 선언한 자료형 변수를 다른 자료형으로 사용 못함

  - ```지역변수```로만 사용가능

    ```java
    public class TypeInference {
    	public static void main(String[] args) {
    		var i = 10;
    		var j = 10.0;
    		var str = "hello";
    		
    		System.out.println(i);
    		System.out.println(j);
    		System.out.println(str);
    		
    		str = "test";	//다른 문자열 대입 가능
    		//str = 3;		//str은 String형으로 사용되었기에 정수값 넣지 못함
    	}
    }
    ```

    

## 2-4 상수와 리터럴

#### 상수 선언

- ```상수(constant)```는 항상 변하지 않는 수

  ```java
  final double PI = 3.14;
  final int MAX_NUM = 100;
  ```

  - 이름은 주로 대문자로 사용 및 _ 기호 사용

```java
public class Constant {
	public static void main(String [] arg) {
		final int MAX_NUM = 100;
		final int MIN_NUM;
		
		MIN_NUM = 0;	//사용하기 전에 초기화는 가능, 초기화 안하면 오류 발생
		
		System.out.println(MAX_NUM);
		System.out.println(MIN_NUM);
		
		//MAX_NU = 1000;	// 오류, 상수값은 변경 불가능
	}
}
```

#### 상수 이용 이유

- 반복적으로 사용, 변하지 않는 값을 선언하면 좋음
- 상수값만 변경하여 프로그램 내부의 값들을 변경할 수 있다

#### 리터럴

- 프로그램에서 사용하는 모든 숫자, 문자, 논리값을 일컫는 말

  ```java
  char ch = 'A';
  int num = 10;
  final double PI =3.14; //모두 리터럴
  ```

- 리터럴은 프로그램 시작시 시스템에 같이 로딩되어 특정 메모리 공간인 ```상수 풀```에 놓인다
  int num = 3;을 하면
  3이 상수 풀에 로딩된 후 변수로 대입된다

- ```float PI = 3.14F;```에서 F를 붙인 것도 3.14 리터럴이 float형으로 상수 풀에 저장되어야 한다는 의미로 사용



## 2-5 형 변환

#### 형 변환이란

- 주로 연산 과정에서 각 변수의 자료형이 다를때 같게 바꾸는 작업

  - 묵시적 형 변환(자동 형 변환)

    1. 바이트 크기가 작은 자료형에서 큰 자료형으로 형 변환이 이루어진다

    2. 덜 정밀한 자료형에서 더 정밀한 자료형으로 변환이 이루어진다

       > byte -> short, char -> int -> long -> float -> double
       >
       > 반대로 형 변환을 위해서는 강제로 변환 필요

  - 명시적 형 변환(강제 형 변환)

#### 묵시적 형 변환

- __바이트 크키가 작은 자료형에서 큰 자료형으로 대입__

  ```java
  byte bNum = 10;	//1byte
  int iNum = bNum;//4byte
  // 자료 손실없이 bNum값이 대입된다, 남은 3byte에는 0이 채워진다
  ```

- __덜 정밀한 자료형에서 더 정밀한 자료형으로 대입하는 경우__

  ```java
  int iNum2 = 20;		//4바이트
  float fNum = iNum2;	//4바이트
  //둘다 4바이트이지만 float형이 더 정밀하여 float형으로 변환된다
  ```

- __연산 중에 자동 변환되는 경우__

  ```java
  int iNum = 20;
  float fNum = iNum;
  double dNum;
  dNum = fNum + iNum; // +연산에서 float형으로 변환 후
  //dNum에 대입되면서 double형으로 변환
  ```

#### 명시적 형 변환

- ___바이트 크기가 큰 자료형에서 작은 자료형으로 대입___

  ```java
  int iNum = 10;
  byte bNum = (byte)iNum; //괄호를 사용하여 형을 명시함, (byte)안 붙이면 오류 발생
  ```

  ```java
  int iNum = 1000;
  byte bNum = (byte)iNum;	//자료 손실 발생
  // 1000이 byte형 범위(-128~127)을 넘겨 자료에 대한 손실 발생
  ```

- ___더 정밀한 자료형에서 덜 정밀한 자료형으로 대입___

  ```java
  double dNum = 3.14;
  int iNum2 = (int)dNum;
  ```

- ___연산 중 병 변환___

  ```java
  public class ExplicitConversion {
  
  	public static void main(String[] args) {
  		double dNum1 = 1.2;
  		float fNum2 = 0.9F;
  		
  		int iNum3 = (int)dNum1 + (int)fNum2;	//각각 변환 후 연산
  		int iNum4 = (int)(dNum1 + fNum2);		//연산 후 변환
  		System.out.println(iNum3);	//1 출력
  		System.out.println(iNum4);	//2 출력
  	}
  }
  ```



## 연습문제

1. 바이트 크기가 작은 자료형을 더 큰 자료형으로 대입하는 형 변환은 자동으로 이루어집니다.  => 예
2. 실수를 정수형 변수에 대입하는 경우에 형 변환이 자동으로 이루어지고, 소수점 이하 부분만 없어집니다. => 아니오
3. 더많은 실수를 표현하기 위해 가수부와 지수부로 비트를 나누어 표현하는 방식을 ```부동 소수점 방식```이라고 합니다.