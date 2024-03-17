Q1. syntax와 semantics가 무엇을 의미하는지 설명하고, syntax error 와 semantics error의 차이점을 예를 들어서 설명하시오.</br>
A1. syntax는 문법을 의미하고, semantics는 의미를 뜻한다.</br>
syntax error는 코드 상에서 세미콜론을 쓰지 않는 것 같은 문법상의 에러 때문에 컴파일이 되지 않는 것을 말한다.</br>
semantics error는 컴파일은 됐지만 원하는 결과를 얻지 못하는 에러를 뜻한다.</br>
예를 들면 number = n/10 코드가 있을 때 n에 5을 넣으면 0.5의 결과를 기대하지만 실제로는 0이 나오는 데 이것을 semantics error라고 한다.</br>

Q2. 변수를 선언할 떄, 컴퓨터는 무엇을 하는지 예를 들어 설명하시오.</br>
A2. 값을 담을 수 있는 메모리 상의 공간에 이름을 부여한다. 그리고 그 변수는 담을 수 있는 자료형을 결정하기 위해 타입을 설정한다.</br>

Q3. 프로그래밍에서 뜻하는 타입이란 무엇인가?</br>
A3. 자바는 char, byte, short, int, long, float, double, boolean으로 8개의 기본형 타입과 참조형 타입을 가지고 있다.</br>
각 타입은 변수에 담을 수 있는 공간의 크기와 자료형을 정해준다.</br>
char은 문자형, byte,short,int,long은 정수형, float,double은 실수형, boolean은 논리형이다. 기본형 타입 외의 타입은 모두 참조형 타입이다.</br>

Q4. boolean 타입은 자바의 기본형 타입 중 하나이다. boolean 타입이 무엇이고, boolean 값은 어디에 쓰이는가? 그리고 가능한 값으로는 무엇이 있는가?</br>
A4. boolean형 타입은 1바이트 크기로 이루어져 있으며 논리적으로 참인지 거짓인지 판단하는 자료형이다. 가능한 값으로는 true와 false 두가지로 이루어져 있다.</br>

Q5. 다음 자바 연산자들의 각각의 의미를 설명하시오.</br>
a) ++</br>
b) &&</br>
c) !=</br>
A5. a는 증감연산자에서 증가할 때 사용하는 연산자인데 ++의 위치에 따라 결과가 달라진다.</br>
result = ++x일 경우 x에 1이 더해진 값이 result에 전해지고, result = x++ 일 경우 x에 1이 더해지는 건 같지만 1이 더해지기 이전의 상태가 result에 전해지게 된다.</br>
b는 논리연산자이다. 논리연산자의 피연산자로는 논리형 타입이 온다. true && true 는 true가 되고 , true && false 는 false, false && false는 false가 된다. 즉 두 피연산자가 모두 true가 아니라면 false가 된다.</br>
c는 비교 연산자이다. 두 피연산자의 값이 같지 않으면 true가 된다.</br>

Q6. 할당문이 무엇을 의미하고, 무엇을 할 때 사용되는가?</br>
A6. 할당을 할때는 = 기호를 사용한다. 오른쪽에 있는 값을 왼쪽에 넘겨줄 때 사용한다.</br>

Q7. 연산자 우선순위는 무엇을 의미하는가?</br>
A7. 먼저 연산해야 할 연산자를 정하는 것</br>

Q8. 리터럴이 무엇인가?</br>
A8. 메모리에 할당되어 있는 값</br>

Q9. 자바에서 클래스는 두가지 다른 목적을 갖는다. 그게 무엇인가?</br>
A9. 클래스는 변수와 서브루틴을 가지고 있다. 변수는 상태를 나타내고 서브루틴은 동작을 나타낸다.</br>

Q10. "x = TextIO.getDouble();"와 "x = TextIO.getlnDouble();" 의 차이점은 무엇인가?</br>
A10. 두 코드 모두 실수를 읽고 x에 저장한다. 둘 다 동일한 값을 읽고 반환한다. 차이점은 두 번째 명령문은 다음 캐리지 리턴(\n)까지 문자를 계속 읽는다.</br>

Q11. 2 + 3 + "test" 의 값은 "5test"이고 "test" + 2 + 3 은 "test23"이 되는 이유를 설명하시오.</br>
그리고 "test" + 2 _ 3 은 무엇인가요?</br>
A11. 괄호가 없다면 앞에서부터 차례대로 평가를 하게 된다.</br>
첫 번째 코드는 2부터 평가를 하게 되는데 2와 3은 정수형이기 때문에 2+3으로 5가 된다.</br> 그리고 5와 "test"를 더할 때 "5test"가 되는데 그 이유는 어떤 기본 자료형이든 문자열과 연산이 되면 해당 값은 모두 문자열로 바뀌어 연산이 된다.</br> 그렇기 때문에 첫 번째 코드의 답은 "5test"가 되고 두 번째 코드 역시 같은 이유로 "test2" -> "test2" + 3 -> "test23"이 된다.</br>
"test" + 2 _ 3은 연산자 우선순위에 의해 2\*3이 먼저 연산되어 6이 되고 이 값이 test와 더해져서 "test6"이 된다.</br>

Q12. 이클립스같은 IDE에서는 언어 구문을 반영하기 위해 다양한 색상을 사용한다.</br> 그 중에서 String의 단어 색상은 int나 double 및 boolean 과 다르게 표시된다. 왜 String만 다른 색상인가?</br>
A12.int나 double 및 boolean과는 다르게 String은 참조형 타입의 자료형이다. 다른 타입이기 때문에 색도 다르게 표현된다.</br>

Q13.import textio.TextIO 와 import java.util.Scanner의 앞의 import를 쓰는 이유는 무엇인가?</br>
A13. textio와 scanner클래스는 각각 textio 및 java.util이라는 패키지 내에 정의된다.</br> 프로그램은 해당 클래스가 무엇인지 모르기 때문에 클래스를 사용하기 위해서는 import를 사용하여 가져와야한다.</br>

Q14. 사용자에게 구매하려는 위젯 수와 위젯 당 비용을 입력하도록 요청하는 프로그램을 작성하시오.</br> 프로그램은 모든 위젯의 총 비용을 출력해야한다. System.out.printf를 사용하여 소수점 두자리까지 출력하시오.</br>
A14.

```java
import java.util.Scanner;
public class Question14{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.print("위젯 개수를 입력 : ");
        int num = sc.nextInt();
        System.out.print("위젯 당 비용을 입력 : ");
        double cost = sc.nextDouble();
        double totalCost = num*cost;
        System.out.printf("위젯의 총 비용 : %.2f\n",totalCost);
    }
}
```
