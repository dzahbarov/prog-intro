package expression.exceptions;

import expression.*;
import expression.parser.BaseParser;
import expression.parser.StringSource;

public class ExpressionParser extends BaseParser implements Parser {

    @Override
    public TripleExpression parse(String expression) throws ParseException {
        this.source = new StringSource(expression);
        nextChar();
        return parseExpression();
    }

    private TripleExpression parseExpression() throws ParseException {

        TripleExpression result = parseOR();
        if (ch == ')') {
            throw new InvalidBracketsException(getPos(), "No opening parenthesis");
        } else if (Character.isDigit(ch)) {
            throw new InvalidConstException(getPos(), "Spaces in numbers");
        } else if (!test(EOF)) {
            throw new ParseException(getPos(), "End-of-input expected");
        }
        return result;
    }


    private TripleExpression parseOR() throws ParseException {
        return parseBinary('|');
    }

    private TripleExpression parseXOR() throws ParseException {
        return parseBinary('^');
    }

    private TripleExpression parseAND() throws ParseException {
        return parseBinary('&');
    }

    private TripleExpression parseFirstLevel() throws ParseException {
        return parseBinary('+', '-');
    }

    private TripleExpression parseSecondLevel() throws ParseException {
        return parseBinary('*', '/');
    }

    private TripleExpression parseThirdLevel() throws ParseException {
        skipWhiteSpace();

        if (isDigit()) {
            return parseConst();
        }

        if (test('(')) {
            TripleExpression lowestLevel = parseOR();
            skipWhiteSpace();
            if (ch != ')') {
                throw new InvalidBracketsException(getPos(), "No closing parenthesis");
            }
            nextChar();
            return lowestLevel;
        }
        if (test('-')) {
            if (Character.isDigit(ch)){
                return parseNegativeConst();
            } else {
                return parseUnaryElement();
            }

        }
        if (Character.isLetter(ch)) {
            if (test('a')) {
                if (test('b') && test('s') && !Character.isLetter(ch)) {
                    return parseAbs();
                } else {
                    throw new InvalidCharacterException(getPos(), "invalid character");
                }

            } else if (test('s')) {
                if (test('q') && test('r') && test('t')) {
                    return parseSqrt();
                } else {
                    throw new InvalidCharacterException(getPos(), "invalid character");
                }
            } else {
                return parseVariable();
            }
        }


        if (ch == '+' || ch == '-' || ch == '/' || ch == '*' || ch == '^' || ch == '&' || ch == '|') {
            throw new InvalidOperandException(getPos(), "No argument for operation");
        }
        if (ch != ')') {
            throw new InvalidCharacterException(getPos(), "Unknown symbol");
        } else {
            throw new InvalidBracketsException(getPos(), "Empty braces");
        }


    }

    private TripleExpression parseSqrt() throws ParseException {
        return new Sqrt((MathExpression) parseThirdLevel());
    }

    private TripleExpression parseAbs() throws ParseException {
        return new Abs((MathExpression) parseThirdLevel());
    }

    private Variable parseVariable() throws ParseException {
        final StringBuilder sb = new StringBuilder();
        while (Character.isLetter(ch)) {
            sb.append(ch);
            nextChar();
        }
        String var = sb.toString();
        if (var.equals("x") || var.equals("y") || var.equals("z")) {
            return new Variable(var);
        } else {
            throw new InvalidVariableException(getPos(), "Bare var");
        }
    }

    private TripleExpression parseUnaryElement() throws ParseException {
        return new UnaryMinus((MathExpression) parseThirdLevel());
    }

    private int getPos(){
        return source.getPos();
    }

    private TripleExpression parseBinary(char firstOperation, char secondOperation) throws ParseException {
        TripleExpression lhs = firstOperation == '+' ? parseSecondLevel() : parseThirdLevel();

        while (!test(EOF)) {
            skipWhiteSpace();
            char op = ch;
            if (op != firstOperation && op != secondOperation) {
                break;
            }
            nextChar();
            TripleExpression rhs = firstOperation == '+' ? parseSecondLevel() : parseThirdLevel();
            if (op == '+') {
                lhs = new CheckedAdd((MathExpression) lhs, (MathExpression) rhs);
            } else if (op == '-') {
                lhs = new CheckedSubtract((MathExpression) lhs, (MathExpression) rhs);
            } else if (op == '*') {
                lhs = new CheckedMultiply((MathExpression) lhs, (MathExpression) rhs);
            } else if (op == '/') {
                lhs = new CheckedDivide((MathExpression) lhs, (MathExpression) rhs);
            }
        }
        return lhs;
    }

    private TripleExpression parseBinary(char operation) throws ParseException {
        TripleExpression lhs = getLogicLower(operation);

        while (!test(EOF)) {
            skipWhiteSpace();
            char op = ch;
            if (op != operation) {
                break;
            }
            nextChar();

            TripleExpression rhs = getLogicLower(operation);

            if (op == '|') {
                lhs = new OR((MathExpression) lhs, (MathExpression) rhs);
            } else if (op == '^') {
                lhs = new XOR((MathExpression) lhs, (MathExpression) rhs);
            } else if (op == '&') {
                lhs = new AND((MathExpression) lhs, (MathExpression) rhs);
            }
        }
        return lhs;
    }

    private TripleExpression getLogicLower(char operation) throws ParseException {
        if (operation == '|') {
            return parseXOR();
        } else if (operation == '^') {
            return parseAND();
        } else {
            return parseFirstLevel();
        }
    }

    private TripleExpression parseConst() throws InvalidConstException {
        StringBuilder value = readDigit();
        try {
            return new Const(Integer.parseInt(value.toString()));
        } catch (NumberFormatException e) {
            throw new InvalidConstException(getPos(), "Constant overflow");
        }
    }

    private TripleExpression parseNegativeConst() throws InvalidConstException {
        StringBuilder value = readDigit();
        value.insert(0, '-');
        try {
            return new Const(Integer.parseInt(value.toString()));
        } catch (NumberFormatException e) {
            throw new InvalidConstException(getPos(), "Constant overflow");
        }
    }


    private void skipWhiteSpace() {
        while (Character.isWhitespace(ch)) {
            nextChar();
        }
    }

    private StringBuilder readDigit() {
        final StringBuilder value = new StringBuilder();
        skipWhiteSpace();
        while (isDigit()) {
            value.append(ch);
            nextChar();
        }
        return value;
    }

    private boolean isDigit() {
        return ch >= '0' && ch <= '9';
    }
}