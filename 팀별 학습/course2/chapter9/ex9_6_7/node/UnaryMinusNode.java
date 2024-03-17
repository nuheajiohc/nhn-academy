package course2.chapter9.ex9_6_7.node;

public class UnaryMinusNode extends ExpNode {
    ExpNode operand;

    public UnaryMinusNode(ExpNode operand) {
        if (operand == null) {
            throw new IllegalArgumentException("잘못된 연산자입니다.");
        }
        this.operand = operand;
    }

    public double value(double xValue){
        double negative = operand.value(xValue);
        return -negative;
    }

    public void printStackCommands() {
        operand.printStackCommands();
        System.out.println("  Unary minus");
    }

    @Override
    public void printInfix() {
        System.out.print("(-");
        operand.printInfix();
        System.out.print(')');
    }

    @Override
    public ExpNode derivative() {
        return new UnaryMinusNode(operand.derivative());
    }
}
