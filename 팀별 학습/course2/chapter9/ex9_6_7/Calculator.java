package course2.chapter9.ex9_6_7;

import course2.chapter9.ex9_6_7.node.BinOpNode;
import course2.chapter9.ex9_6_7.node.ConstNode;
import course2.chapter9.ex9_6_7.node.ExpNode;
import course2.chapter9.ex9_6_7.node.UnaryMinusNode;
import course2.chapter9.ex9_6_7.node.VariableNode;
import java.util.function.Predicate;

public class Calculator {

    private TextIterator textIterator;

    public Calculator(String expression) {
        textIterator = new TextIterator(expression);
    }

    public double calculate(double x) {
        ExpNode node = expressionTree();
        if (this.textIterator.hasNext()) {
            throw new IllegalArgumentException("표현식이 올바르지 않습니다.");
        }
        node.printStackCommands();

        ExpNode derivative = node.derivative();
        System.out.print("\n 미분한 표현식 :");
        derivative.printInfix();
        System.out.print("\n x = "+x + " 결과 : ");
        System.out.println(derivative.value(x));
        derivative.printStackCommands();
        return node.value(x);
    }

    private ExpNode expressionTree() {
        this.textIterator.skipBlanks();
        ExpNode term = termTree();
        this.textIterator.skipBlanks();
        return recursion(term, operation -> operation == '+' || operation == '-');
    }

    private ExpNode termTree() {
        this.textIterator.skipBlanks();
        ExpNode factor = factorTree();
        this.textIterator.skipBlanks();
        return recursion(factor, operation -> operation == '*' || operation == '/');
    }

    private ExpNode recursion(ExpNode node, Predicate<Character> predicate) {
        if (!this.textIterator.hasNext()) {
            return node;
        }
        char operation = this.textIterator.peekNext();
        if (predicate.test(operation)) {
            this.textIterator.next();
            ExpNode nextTerm = termTree();
            node = new BinOpNode(operation, node, nextTerm);
            this.textIterator.skipBlanks();
            node = recursion(node, predicate);
        }
        return node;
    }

    private ExpNode factorTree() {
        this.textIterator.skipBlanks();
        char factor = this.textIterator.peekNext();
        if (factor == '-') {
            this.textIterator.next();
            ExpNode node = factorTree();
            return new UnaryMinusNode(node);
        } else if (Character.isDigit(factor)) {
            this.textIterator.next();
            StringBuilder sb = new StringBuilder();
            sb.append(factor).append(this.textIterator.readNumbers());
            double num = Double.parseDouble(sb.toString());
            return new ConstNode(num);
        } else if (Character.toLowerCase(factor) == 'x') {
            this.textIterator.next();
            return new VariableNode();
        } else if (factor == '(') {
            this.textIterator.next();
            ExpNode node = expressionTree();
            this.textIterator.skipBlanks();
            if (this.textIterator.next() != ')') {
                throw new IllegalArgumentException("닫는 괄호가 존재하지 않습니다");
            }
            return node;
        } else {
            throw new IllegalArgumentException("표현식이 잘못되었습니다.");
        }
    }
}

