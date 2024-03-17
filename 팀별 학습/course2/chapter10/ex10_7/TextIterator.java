package course2.chapter10.ex10_7;

import java.util.Iterator;

public class TextIterator implements Iterator<Character> {

    private String input;
    private int cursor;

    public TextIterator(String input) {
        this.input = input;
        this.cursor = 0;
    }

    @Override
    public boolean hasNext() {
        return this.cursor < this.input.length();
    }

    @Override
    public Character next() {
        if (hasNext()) {
            return this.input.charAt(this.cursor++);
        }
        throw new IndexOutOfBoundsException("표현식이 올바르게 완성되지 않았습니다.");
    }

    public void skipBlanks() {
        while (hasNext() && this.input.charAt(this.cursor) == ' ') {
            cursor++;
        }
    }

    public Character peekNext() {
        if (hasNext()) {
            return this.input.charAt(this.cursor);
        }
        throw new IndexOutOfBoundsException("표현식이 올바르게 완성되지 않았습니다.");
    }

    public String readNumbers() {
        StringBuilder sb = new StringBuilder();
        while (hasNext() && Character.isDigit(this.input.charAt(this.cursor))) {
            sb.append(next());
        }
        return sb.toString();
    }

    public String readCommand() {
        StringBuilder sb = new StringBuilder();
        while (hasNext() && peekNext() != ' ') {
            sb.append(next());
        }
        return sb.toString();
    }

    public String readVariable() throws ParserError {
        StringBuilder sb = new StringBuilder();
        if (hasNext() && Character.isLetter(peekNext())) {
            while (hasNext() && (Character.isDigit(peekNext()) || (Character.isLetter(peekNext())))) {
                sb.append(next());
            }
            return sb.toString();
        }
        throw new ParserError("변수는 문자로 시작해야합니다.");
    }
}