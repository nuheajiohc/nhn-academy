package course2.chapter10.ex10_7;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Calculator {
    private TextIterator textIterator;

    private HashMap<String, Object> symbolTable;
    private Set<String> defaultSymbols;

    public Calculator() {
        setDefaultSymbolTable();
    }

    private void setDefaultSymbolTable() {
        symbolTable = new HashMap<>();
        symbolTable.put("pi", Math.PI);
        symbolTable.put("e", Math.E);
        for (Maths math : Maths.values()) {
            symbolTable.put(math.name().toLowerCase(), new MathFunction(math));
        }
        defaultSymbols = new HashSet<>(symbolTable.keySet());
    }

    public void selectCalculationType(String input) throws ParserError {
        textIterator = new TextIterator(input);
        textIterator.skipBlanks();
        String command = textIterator.readCommand();
        if (command.equalsIgnoreCase("print")) {
            doPrintCommand();
        } else if (command.equalsIgnoreCase("let")) {
            doLetCommand();
        } else {
            throw new ParserError("입력의 시작은 \"print\"나 \"let\"중에 하나로 작성해주세요.");
        }
    }

    public void doLetCommand() throws ParserError {
        textIterator.skipBlanks();
        String variableName = textIterator.readVariable();
        validateVariableName(variableName);
        textIterator.skipBlanks();

        validateEqualFactor();

        textIterator.skipBlanks();
        double value = expressionValue();
        textIterator.skipBlanks();

        validateExtraValue();

        saveVariable(variableName, value);
    }

    private void validateVariableName(String variableName) throws ParserError {
        if (defaultSymbols.contains(variableName)) {
            throw new ParserError("기본적으로 등록된 symbol의 이름으로 변수를 설정할 수 없습니다.");
        }
    }

    private void validateEqualFactor() throws ParserError {
        if (!textIterator.hasNext() || textIterator.next() != '=') {
            throw new ParserError("변수와 실수 사이에는 \"=\" 하나를 입력해주세요.");
        }
    }

    private void validateExtraValue() throws ParserError {
        if (textIterator.hasNext()) {
            throw new ParserError("뒤에 다른 문자가 들어있습니다.");
        }
    }

    private void saveVariable(String variableName, double value) {
        symbolTable.put(variableName, value);
        System.out.println("변수가 올바르게 저장되었습니다.");
    }


    public void doPrintCommand() throws ParserError {
        textIterator.skipBlanks();
        double value = expressionValue();
        textIterator.skipBlanks();
        validateExtraValue();
        System.out.println("Value is " + value);
    }

    private double expressionValue() throws ParserError {
        textIterator.skipBlanks();
        double value = termValue();

        while (textIterator.hasNext() && (textIterator.peekNext() == '+' || textIterator.peekNext() == '-')) {
            char operation = textIterator.next();
            double nextValue = termValue();
            value = operate(operation, value, nextValue);
            textIterator.skipBlanks();
        }
        return value;
    }

    private double termValue() throws ParserError {
        textIterator.skipBlanks();
        double value = powValue();
        textIterator.skipBlanks();

        while (textIterator.hasNext() && (textIterator.peekNext() == '*' || textIterator.peekNext() == '/')) {
            char operation = textIterator.next();
            double nextValue = powValue();
            value = operate(operation, value, nextValue);
            textIterator.skipBlanks();
        }
        return value;
    }


    private double powValue() throws ParserError {
        textIterator.skipBlanks();
        double value = factorValue();
        textIterator.skipBlanks();

        if (textIterator.hasNext() && textIterator.peekNext() == '^') {
            textIterator.next();
            double nextValue = powValue();
            value = Math.pow(value, nextValue);
            if (Double.isNaN(value)) {
                throw new ParserError("^연산식이 잘못되었습니다.");
            }
            textIterator.skipBlanks();
        }
        return value;
    }

    private double factorValue() throws ParserError {
        textIterator.skipBlanks();
        char ch = textIterator.peekNext();
        if (Character.isDigit(ch)) {
            return Double.parseDouble(textIterator.readNumbers());
        } else if (Character.isLetter(ch)) {
            String name = textIterator.readVariable().toLowerCase();
            Object value = symbolTable.get(name);
            if (value == null) {
                throw new ParserError(name + "은 저장되지 않은 변수입니다.");
            }
            if (value instanceof Double) {
                return (Double) value;
            } else if (value instanceof MathFunction) {
                if (textIterator.peekNext() == '(') {
                    double valueInBracket = factorValue();
                    return ((MathFunction) value).evaluate(valueInBracket);
                } else {
                    throw new ParserError("삼각함수 뒤에는 괄호로 시작되어야합니다.");
                }
            } else {
                throw new ParserError("값은 실수로 입력 해야 합니다.");
            }
        } else if (ch == '(') {
            textIterator.next();
            double value = expressionValue();
            textIterator.skipBlanks();
            if (!textIterator.hasNext() || textIterator.peekNext() != ')') {
                throw new ParserError("괄호가 올바르게 닫히지 않았습니다.");
            }
            textIterator.next();
            return value;
        } else if (ch == '-') {
            textIterator.next();
            return (-1) * factorValue();
        } else {
            throw new ParserError(ch + "는 올바른 표현식 위치에 들어있지 않습니다.");
        }
    }

    private double operate(char operation, double leftValue, double rightValue) {
        switch (operation) {
            case '+':
                return leftValue + rightValue;
            case '-':
                return leftValue - rightValue;
            case '*':
                return leftValue * rightValue;
            case '/':
                if (rightValue == 0) {
                    throw new ArithmeticException("0으로 나눌 수 없습니다.");
                }
                return leftValue / rightValue;
            default:
                return Double.NaN;
        }
    }
}
