# 3장 - 자바의 여러 가지 연산자

## 3-1 기본 연산자

### 항과 연산자

- ```연산자(operator)```: 연산에 사용하는 기호

- ```항(operand)```: 연산에 사용하는 값

  > 3+4
  > 3,4: 항
  > +: 연산자

> ```단항 연산자```: 항이 한 개인 연산자 ex. ++num
> ```이항 연산자```: 항이 두 개인 연산자 ex. num1 + num2
> ```삼항 연산자```: 항이 세 개인 연산자 ex. (5 > 3) ? 1 : 0
>
> - 연산 순위
>   - 단항 > 이항 > 삼항

### 대입 연산자

- ```대입 연산자```: 변수에 값을 대입하는 연산자
  - 이항 연산자 중 우선순위가 가장 낮다

```java
int age = 24;
// 왼쪽 변수 = 오른쪽 변수(or 식)
// lValue = rValue

totalScore = mathScore + engScore;
// + 연산은 먼저 하고 대입 연산자이 일어난다
```

### 부호 연산자

- ```+, -``` 두가지 존재
  - 더하기, 빼기 연산에 쓰는 ```이항 연산자```이면서, 부호를 나타내는 ```단항 연산자```로도 사용

```java
int num = 10;
System.out.println(+num);
System.out.println(-num);//num값이 바뀌지는 않음

num = -num; //num값이 변경됨
```

### 산술 연산자

- ```산술 연산자```: 사칙 연산에 사용하는 연산자

- ```+, -, *, /, %``` 5가지 존재

```java
package operator;
public class OperationEx1 {
	public static void main(String[] args) {
		int mathScore= 90;
		int engScore = 70;
		
		int totalScore = mathScore + engScore;
		System.out.println(totalScore);
		
		double avgScore = totalScore / 2.0;
		System.out.println(avgScore);
	}
}
```

### 증가, 감소 연산자

- 값을 1만큼 늘이거나 줄이는 ```단항 연산자```이다

| 연산자 |          기능          | 연산 예                                                      |
| :----: | :--------------------: | ------------------------------------------------------------ |
|   ++   | 항의 값에 1을 더합니다 | val = ++num;  // 1 증가 후 변수에 대입<br />val = num++;  // 변수에 대입 후 1 증가 |
|   --   |  항의 값에 1을 뺍니다  | val = --num;  // 1 감소 후 변수에 대입<br />val = num--;  // 변수에 대입 후 1 감소 |

```java
public class OperationEx2 {

	public static void main(String[] args) {
		int gameScore = 150;
		
		int lastScore1 = ++gameScore;
		System.out.println(lastScore1);
		
		int lastScore2 = --gameScore;
		System.out.println(lastScore2);
	}
}
```

### 관계 연산자

- 항의 코기를 비교하는 항이 두 개인 ```이항 연산자```
  - 두 값을 비교하여 결과 값을 반환한다

```java
int myAge = 27;
boolean value = (myAge > 25);
System.out.println(value);	//true
```

### 논리 연산자

- 논리 연산을 한다

> ```&&```: 두 항이 모두 참인 경우에만 참
>
> ```||```: 두 항 중 하나만 참이면 참
>
> ```!```: 참이면 거짓으로, 거짓이면 참으로 바꿈

- ```단락 회로 평가```, 논리 연산에서 모든 항이 실행되지 않는 경우

```java
public class OperationEx3 {

	public static void main(String[] args) {
		int num1 = 10;
		int i = 2;
		
		boolean value = ((num1 = num1 + 10) < 10) && ((i = i+2)<10);	//논리 곱에서 앞 항의 결과가 거짓이라, 뒷 항은 실행되지 않음
		System.out.println(num1);
		System.out.println(i);
		
		value = ((num1=num1+10)>10) || ((i=i+2)<10);	//논리 합에서 앞의 항의 결과가 참이라, 뒷 문장을 실행되지 않음
		System.out.println(num1);
		System.out.println(i);
	}
}
```

### 복합 대입 연산자

- ```복합 대입 연산자```: 대입 연산자와 다른 연산자를 조합해 하나의 연산자처럼 사용하는 연산자
- 비트 연산자와 함께 사용하여 코드를 간결하게 표현 가능
- 우선 순위가 가장 낮은 연산자이다

>```+=, -=, *=, /=, %=```: 두 항의 값을 연산하여 왼쪽 항에 대입한다
>ex. num1 += 2;  ==> num1 = num1 + 2;
>
>```&=, |=, ^=```: 두 항의 값을 비트 연산하여 왼쪽 항에 대입한다
>ex. num1 &= num2;  ==> num1 = num1 & num2;
>
>```<<=```: 비트를 왼쪽으로 이동하고 그 값을 왼쪽 항에 대입
>
>```>>=```: 비트를 오른쪽으로 이동하고 그 값을 왼쪽항에 대입 (왼쪽에 채워지는 비트는 부호비트와 동일)
>
>```>>>=```: 비트를 오른쪽으로 이동하고 그 값을 왼쪽항에 대입 (왼쪽에 채워지는 비트는 0)
>ex. num >>>= 2;  ==>  num = num >>> 2;

### 조건 연산자

- 연산에 필요한 항이 3개인 ```삼항 연산자```

> 조건식 ? 결과1 : 결과2;
>
> 조건식이 참 => 결과1
> 조건식이 거짓 => 결과2
>
> ex.
> int num = (5 > 3) ? 10: 20;
> 조건식이 참이라 num에 10 대입

```java
public class OperationEx4 {

	public static void main(String[] args) {
		int fatherAge = 45;
		int motherAge = 47;
		
		char ch;
		ch = (fatherAge>motherAge)? 'T' : 'F';
		
		System.out.println(ch);	//F 출력
	}
}
```



## 3-2 비트 연산자

- ```비트 연산자```: 비트 단위로 연산이 이루어지는 연산자

### 비트 논리 연산자

- ```&``` 연산자

  - 두 개의 비트 값이 모두 1인 경우에 결과가 1

  > int num1 = 5;
  > int num2 = 10;
  > int result = num1 & num2;
  >
  > 00000101  num1
  > 00001010  num2
  >
  > 00000000  result = 0

- ```| ```연산자
  - 비트 값이 하나라도 1이면 결과가 1

- ```^``` 연산자

  - XOR 연산자로 같으면 0, 다르면 1

  > 00000101
  > 00001010
  >
  > 00001111

- ```~ ```연산자

  - 0은 1로, 1은 0으로 바꾸는 연산자

  > 00001010
  >
  > 11110101

### 비트 이동 연산자 (Shift 연산자)

- ```<< ```연산자

  - 왼쪽으로 비트 이동하는 연산자

  > int num = 5;
  >
  > num << 2;
  >
  > num:          00000101
  > num << 2: 00010100

- ```>>```연산자

  - 오른쪽으로 비트 이동하는 연산자 (왼쪽 비트는 부호 비트와 동일하게 간다)

  > num:          00000101
  > num >> 2: 00000010         // 왼쪽에 채워지는 비트는 부호 비트와 동일하다

- ```>>>```연산자
  - ```>>```연산과 동일하게 가지만 부호 비트와 무관하게 0으로 채운다

### 연산자 우선순위

- 단항 > 이항 > 삼항 연산자 순서
- 대입 연산자의 우선순위가 가장 낮다
- 산술, 관계, 논리, 대입 연산자 순서로 우선순위를 가진며 ```( )```의 우선순위가 가장 높다

