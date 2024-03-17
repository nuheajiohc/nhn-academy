package course2.chapter9.ex9_6_7.node;

import course2.chapter9.ex9_6_7.SimpleParser;
import java.util.Arrays;
import java.util.List;

public class BinOpNode extends ExpNode {
    private char operation;
    ExpNode left;
    ExpNode right;

    private static final List<Character> operations = Arrays.asList('+','-','*','/');

    public BinOpNode(char operation, ExpNode left, ExpNode right) {
        if(!operations.contains(operation)){
            throw new IllegalArgumentException("잘못된 연산자입니다.");
        }

        if(left == null || right == null ){
            throw new IllegalArgumentException("연산자가 마지막이면 안됩니다.");
        }

        this.operation = operation;
        this.left = left;
        this.right = right;
    }

    @Override
    public double value(double xValue) {
        double x = left.value(xValue);
        double y = right.value(xValue);
        switch (this.operation) {
            case '+':
                return x + y;
            case '-':
                return x - y;
            case '*':
                return x * y;
            case '/':
                return x / y;
            default:
                return Double.NaN;
        }
    }

    @Override
    public void printStackCommands() {
        left.printStackCommands();
        right.printStackCommands();
        System.out.println("  Operator " + this.operation);
    }

    @Override
    public void printInfix() {
        System.out.print("(");
        left.printInfix();
        System.out.print(" " + operation + " ");
        right.printInfix();
        System.out.print(")");
    }

    @Override
    public ExpNode derivative() {
        switch (operation) {
            case '+':
                return new BinOpNode('+', left.derivative(), right.derivative());
            case '-':
                return new BinOpNode('-', left.derivative(), right.derivative());
            case '*':
                return new BinOpNode( '+',
                        new BinOpNode('*', left, right.derivative()),
                        new BinOpNode('*', right, left.derivative()) );
            case '/':
                return new BinOpNode( '/',
                        new BinOpNode('-',
                                new BinOpNode('*', right, left.derivative()),
                                new BinOpNode('*', left, right.derivative())),
                        new BinOpNode('*', right, right) );
            default:
                return null;
        }
    }
}