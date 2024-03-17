Q1.알고리즘이 무엇인가?</br>
A1. 어떠한 문제를 풀기 위한 과정으로 볼 수도 있고, 지금까지 알려진 가장 효율적인 방법으로 문제 해결에 접근하는 것으로 볼 수 있다.</br>

Q2. 의사코드의 의미를 설명하고, 의사코드가 어덯게 알고리즘 개발에 유용하게 쓰이는지 설명하시오.</br>
A2. 의사코드는 실제 코드라기 보다는 문제를 풀어가는 순서에 대해 설명한 언어라고 볼 수 있다.</br>
의사코드는 코드를 작성하기 전 코드를 어떤 방향으로 작성해야 할 지 방향성을 제시해준다.</br>

Q3. 블록문이 무엇인가? 자바에서 블록문은 어떻게 쓰이는가?</br>
A3. 블록문은 중괄호"{}"로 묶인 문입니다. 블록문은 for문,if문 등의 제어문에서 많이 쓰이고 여러 코드를 묶을 때 자주 사용됩니다.</br>

Q4. while문과 do-while문의 차이점을 설명하시오.</br>
A4. while문은 조건이 true라면 while문 속 제어문이 계속 반복 실행되고 조건이 false가 되면 반복문을 탈출하게 된다. </br>
do-while문은 do에 있는 블록문이 실행이 먼저 되고나서 while문으로 이동하여 조건문에 따라 반복이 될 지를 결정한다. </br>

Q5.루프문에서 프라임이란 무엇인가?</br>
A5.루프문에 들어가는 조건문이 의미가 있기 위해서는 루프문 이전에 초기화가 올바르게 되어야 한다. 이것을 프라이밍이라고 한다.</br>

Q6. 애니메이션의 의미와 컴퓨터가 애니메이션을 표시하는 방법에 대해서 설명하시오.</br>
A6.애니메이션은 프레임으로 구성된다. 각 프레임은 하나의 이미지를 뜻한다.</br> 컴퓨터는 화면에 하나의 이미지를 표시한 다음 이를 다음 이미지로 바꾸고, 또 다음 이미지로 바꾸는데 이 작업을 빠르게 하면 애니메이션이 된다.</br>

Q7. 3부터 36까지 3의 모든 배수, 즉 3 6 9 12 15 18 21 24 27 30 33 36을 출력하는 for 루프 를 작성하세요 .</br>
A7.
``` java
for(int i=3; i<=36;i+=3){
    System.out.println(i);
}
```
Q8. 다음 main() 루틴을 작성하여 사용자에게 정수를 입력하도록 요청하고, 사용자의 응답을 읽고, 입력한 숫자가 짝수인지 홀수인지 알려줍니다.</br>
A8.
```java
package chapter3.example;

import java.util.Scanner;

public class ex {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        if(number%2==0){
            System.out.println(number + "은 짝수입니다.");
        }else{
            System.out.println(number + "은 홀수입니다.");
        }
    }
}
```
Q9. 1부터 10까지의 범위에서 선택된 두 개의 서로 다른 임의의 정수를 인쇄하는 코드 세그먼트를 작성하세요.</br>
가능한 모든 출력은 동일한 확률을 가져야 합니다.</br>
힌트: 두 개의 임의의 숫자를 쉽게 선택할 수 있지만 선택한 두 숫자가 동일할 수도 있다는 사실을 고려해야 합니다.</br>
A9.
```java
import java.util.ArrayList;
import java.util.Collections;

public class ex {
    public static void main(String[] args) {
        ArrayList<Integer> nums = new ArrayList<>();
        for(int i=1; i<11;i++)
            nums.add(i);
        Collections.shuffle(nums);
        System.out.println(nums.get(0)+", "+nums.get(1));
    }
}
```
Q10. s1 및 s2 가 String 유형의 변수이고 해당 값이 int 유형 값의 문자열 표현일 것으로 예상된다고 가정합니다.</br>
해당 값의 정수 합계를 계산하고 인쇄하거나 값을 정수로 성공적으로 변환할 수 없는 경우 오류 메시지를 인쇄하는 코드 세그먼트를 작성하세요.</br>
( try..catch 문을 사용하세요.)</br>
A10.
```java
try {
    int number1 = Integer.parseInt(s1);
    int number2 = Integer.parseInt(s2);
    int sum = n1 + n2;   
    System.out.println("sum :" + sum);
} catch ( NumberFormatException e ) {
        System.out.println(e.getMessage());
        }
```
Q11. 아래코드의 결과를 출력하시오.</br>
```java
    public static void main(String[] args) {
        int N;
        N = 1;
        while (N <= 32) {
            N = 2 * N;
            System.out.println(N);   
        }
    }
```
A11.</br>
2</br>
4</br>
8</br>
16</br>
32</br>
64</br>

Q12.아래코드의 결과를 출력하시오</br>
```java
public static void main(String[] args) {
   int x,y;
   x = 5;
   y = 1;
   while (x > 0) {
      x = x - 1;
      y = y * x;
      System.out.println(y);
   }
}
```
A12.</br>
4</br>
12</br>
24 </br>
24</br>
0</br>

Q13. 다음 코드의 결과를 출력하시오.
```java
String name;
int i;
boolean startWord;

name = "Richard M. Nixon";
startWord = true;
for (i = 0; i < name.length(); i++) {
    if (startWord)
        System.out.println(name.charAt(i));
    if (name.charAt(i) == ' ')
        startWord = true;
    else
        startWord = false;
}
```
A13.</br>
R</br>
M</br>
N</br>
for문안 첫번째 if문을 통해서 문자가 출력이 되는데 다음 루프부터 if문의 조건문이 공백이 되어야 첫번째 if문이 실행된다.</br>
즉 첫 번째 단어를 제외하고, 그 다음 단어부터 공백 이후의 첫 글자만 출력이 된다는 말이다.</br>
Q14. numbers가 int타입의 배열이라고 가정한다. 배열에서 숫자 42가 나타내는 횟수를 출력하는 코드를 작성하시오.</br>
A14.
```java
int count=0;
for(int number: numbers){
    if(number==42){
        count++;
    }
}
System.out.println(count);
```

Q15. 숫자 배열의 범위를 배열의 최대값에서 최소값을 뺀 값으로 정의합니다.</br>
raceTimes가 double[] 유형의 배열 이라고 가정합니다.</br>
raceTimes 범위를 찾아 인쇄하는 코드 세그먼트를 작성하세요.</br>
A15.
```java
Arrays.sort(raceTimes);
double range = raceTimes[raceTimes.length]-raceTimes[0];
System.out.println(range);
```