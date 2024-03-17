package course2.chapter9.ex9_6_7.node;

public class VariableNode extends ExpNode {

    @Override
    public double value(double xValue) {
        return xValue;
    }

    @Override
    public void printStackCommands() {
        System.out.println("  Push x");
    }

    @Override
    public void printInfix() {
        System.out.print("x");
    }

    @Override
    public ExpNode derivative() {
        return new ConstNode(1);
    }
}
