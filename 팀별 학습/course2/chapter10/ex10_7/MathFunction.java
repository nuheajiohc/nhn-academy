package course2.chapter10.ex10_7;

public class MathFunction {

    Maths functionCode;

    public MathFunction(Maths code) {
        functionCode = code;
    }

    public double evaluate(double x) {
        switch (functionCode) {
            case SIN:
                return Math.sin(x);
            case COS:
                return Math.cos(x);
            case TAN:
                return Math.tan(x);
            case ABS:
                return Math.abs(x);
            case SQRT:
                return Math.sqrt(x);
            default:
                return Math.log(x);
        }
    }
}
