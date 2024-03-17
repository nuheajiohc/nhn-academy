package course2.chapter9.ex9_6_7.node;

public class ConstNode extends ExpNode {
    private double number;

    public ConstNode(double value){
        this.number = value;
    }

    public double value(double xValue){
        return this.number;
    }

    public void printStackCommands(){
        System.out.println("  Push " + number);
    }

    @Override
    public void printInfix() {
        System.out.print(number);
    }

    @Override
    public ExpNode derivative() {
        return new ConstNode(0);
    }
}