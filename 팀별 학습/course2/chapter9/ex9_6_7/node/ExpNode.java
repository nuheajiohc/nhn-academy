package course2.chapter9.ex9_6_7.node;

public abstract class ExpNode {
    public abstract double value(double xValue);
    public abstract void printStackCommands();

    public abstract void printInfix();

    public abstract ExpNode derivative();
}