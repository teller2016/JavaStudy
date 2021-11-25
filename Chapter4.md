# 4장 - 제어 흐름 이해하기

## 4-1 조건문

### 조건문이란

- ```조건문```: 주어진 조건에 따라 다른 문장을 선택할 수 있도록 프로그래밍하는 것

### if, if-else문

- if문
  - 주어진 조건식이 '참'일 경우 중괄호 안에 있는 문장을 수행

```java
int age = 10;
if(age >= 8){
    System.out.println("학교 다님");
}
```

- if-else문
  - '참'이면 if문 블록 안에 있는 문장 수행
  - '거짓'이면 else문 블록 안에 있는 문장 수행

```java
package ifexample;

public class IfExample1 {
	public static void main(String[] args) {
		int age = 7;
		if(age >= 8) {
			System.out.println("I go to School");
		}
		else {
			System.out.println("I don't go to School");
		}
	}
}
```

### if-else, else-if문

- 조건이 여러 개인 경우에 else-if문 사용
  - 하나의 조건을 만족하면 나머지 조건을 비교하지 않고 다음 수행문으로 넘어간다

```java
public class IfExample2 {

	public static void main(String[] args) {
		int age = 9;
		int charge;
		
		if(age<8) {
			charge = 1000;
			System.out.println("취학 전 아동");
		}
		else if(age < 14) {		//else if문 사용
			charge = 2000;
			System.out.println("초등학생");
		}
		else {
			charge = 3000;
			System.out.println("일반인");
		}
		System.out.println("입장료는 "+ charge + "입니다");
	}
}
```

### 조건문과 조건 연산자

```java
if(a>b)
    max = a;
else
    max = b;
//를 조건 연산자로 변경하면
max = (a>b)? a : b;
```

### switch-case문

- if문으로 구현하기 번거러운 경우 사용
  - 조건식의 결과가 ```정수``` 또는 ```문자열``` 값이고, 값에 따라 수행되는 경우가 각각 다른 경우에 주로 사용 ( 복잡한 식에는 적합하지 않다 )
  - ```break```문은 switch-case문의 수행을 멈추고 빠져나간다

```java
if(rank == 1){
    m = 'G';
}
else if(rank == 2){
    m = 'S';
}
else{
    m = 'A';
}
//를 switch case문으로 변경하면
switch(rank){
    case 1: m = 'G';
        break;
    case 2: m = 'S';
        break;
    default: m = 'A';
}
```

- case문 동시에 사용
  - ```break```문을 사용하지 않으면 동시에 사용 가능

```java
case 1: case 3: case 5: case 7: case 8: case 10: case 12: day = 31;
	break;
case 4: case 6: case 11: day = 30;
	break;
case 2: day = 28;
	break;
```

- case문에 문자열 사용

```java
public class SwitchCase2 {

	public static void main(String[] args) {
		String medal = "Gold";
		
		switch(medal) {
		case "Gold":
			System.out.println("금메달");
			break;
		case "Silver":
			System.out.println("은메달");
			break;
		default:
			System.out.println("동메달");	
		}
	}
}
```



## 4-2 반복문

- while, do-while, for문 3가지 존재

### while문

- 조건식이 '참'인 동안 수행문을 반복 수행

```java
package loopexample;

public class WhileExample1 {
	public static void main(String[] args) {
		int num = 1;
		int sum = 0;
		
		while(num<=10) {	//num 값이 10보다 작거나 같을 동안
			sum += num;		//sum에 num을 더하고
			++num;			//num을 1씩 더해 나간다
		}
		System.out.println("1부터 10까지의 합은 "+sum+"입니다");
	}
}
```

### do-while문

- ```{}```안의 문장을 무조건 한 번 수행한 후에 조건식을 검사한다

```java
public class DoWhileExample {
	public static void main(String[] args) {
		int num = 1;
		int sum=0;
		
		do {			//조건식이 참이 아니더라도 한번 실행한다
			sum += num;
			num ++;
		}while(num<=10);
		
		System.out.println("1부터 10까지의 합은 "+sum);
	}
}
```

### for문

- 반복 횟수가 정해진 경우에 사용하기 좋다

- 변수 초기화식, 조건식, 증감식을 사용하여 반복문을 구성한다

  - ```초기화식```: for문 시작시 딱 한 번만 수행 ( 사용할 변수를 초기화 )
  - ```조건식```: 언제까지 반복 수행할지 조건을 건다
  - ```증감식```: 반복 횟수, 변수 값을 늘리거나 줄인다

  ```java
  int num;
  for(num = 1; num <= 5; num++){	//for(초기화식; 조건식; 증감식) { 수행문; }
      System.out.println(num);
  }
  ```

```java
public class ForExample1 {

	public static void main(String[] args) {
		int i;
		int sum;
		for(i=0, sum=0; i<=10; i++) {	//i, sum을 둘다 초기화 했다
			sum += i;
		}
		System.out.println("1부터 10까지 합: "+sum);
	}
}
```

- ```for```문 요소 생략
  - ```초기화식```: ```for(; i < 5; i++) {}```
  - ```조건식```: ```for(i=0; ; i++) {}```
  - ```증감식```: ```for(i=0; i<5; ) {}```
  - ```모두```: ```for( ; ; ) {}```

### 중첩된 반복문

```java
public class NestedLoop {

	public static void main(String[] args) {
		int dan;
		int times;
		for(dan=2; dan<=9; dan++) {
			for(times=1; times<=9; times++) {
				System.out.println(dan+"X"+times+"="+ dan*times);
			}
			System.out.println( );
		}
	}
}
```

### continue문

- 반복문과 함께 쓰이며, 반복문 안에서 ```continue```문을 만나면 반복문의 처음으로 돌아간다

```java
public class ContinueExample {

	public static void main(String[] args) {
		int total = 0;
		int num;
		
		for(num=1; num<=100; num++) {
			if(num%2==0)
				continue;	//짝수일 경우 이후 수행을 생략하고 num++을 수행
			total += num;
		}
		System.out.println("1부터 100까지 홀수의 합:"+total);
	}
}
```

### break문

- ```break```문을 반복문안에서 만날경우 반복문을 빠져나온다
- 중첩 반복문일 경우 ```break```문을 감싸고 있는 반복문만 빠져나온다

```java
public class BreakExample2 {

	public static void main(String[] args) {
		int sum = 0;
		int num = 0;
		
		for(num=0; ; num++) {
			sum += num;
			if(sum>=100)		//sum이 100보다 같거나 클때 for반복문 중단
				break;
		}
		System.out.println("num: "+num);
		System.out.println("sum: "+sum);
	}
}
```



## 연습문제

### Q4

```
   *   
  ***
 *****
*******
를 출력하라
```

```java
public class Q4 {

	public static void main(String[] args) {
		for(int i=1;i<=4;i++) {
			var line = "";
			int blank = (7-(2*i-1))/2;
			for(int a=0;a<blank;a++)
				line += " ";
			for(int b=0;b<2*i-1;b++)
				line += "*";
			for(int a=0;a<blank;a++)
				line += " ";
			System.out.println(line);
		}
	}
}
```

### Q5

```
   *   
  ***
 *****
*******
 *****
  ***
   *
를 출력하라
```

```java
public class Q5 {

	public static void main(String[] args) {
		int lines = 7;
		int star = 1;
		
		for(int i=1; i<=lines; i++) {
			var line = "";
			int blank = (lines-star)/2;
			
			for(int a=0;a<blank;a++)
				line += " ";
			for(int b=0;b<star;b++)
				line += "*";
			for(int a=0;a<blank;a++)
				line += " ";
			
			if(i<=lines/2) {
				star += 2;
			}
			else {
				star -= 2;
			}
			System.out.println(line);
		}
	}
}
```



