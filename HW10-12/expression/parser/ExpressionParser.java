package expression.parser;

import expression.*;

public class ExpressionParser extends BaseParser implements Parser {

    public TripleExpression parse(String expression) {
        this.source = new StringSource(expression);
        nextChar();
        return parseExpression();
    }

    private TripleExpression parseExpression() {

        TripleExpression result = parseOR();
        if (!test(EOF)) {
            error("End-of-input expected");
        }
        return result;
    }


    private TripleExpression parseOR() {
        return parseBinary('|');
    }

    private TripleExpression parseXOR() {
        return parseBinary('^');
    }

    private TripleExpression parseAND() {
        return parseBinary('&');
    }

    private TripleExpression parseFirstLevel() {
        return parseBinary('+', '-');
    }

    private TripleExpression parseSecondLevel() {
        return parseBinary('*', '/');
    }

    private TripleExpression parseThirdLevel() {
        skipWhiteSpace();
        if (isDigit()) {
            return parseConst();
        }

        if (test('(')) {
            TripleExpression lowestLevel = parseOR();
            while (!test(')')) ;
            return lowestLevel;
        }
        if (ch == '-') {
            return parseUnaryElement();
        }

        Variable variable = new Variable(String.valueOf(ch));
        nextChar();
        return variable;

    }

    private TripleExpression parseUnaryElement() {
        TripleExpression tmp;

        if (test('-')) {
            skipWhiteSpace();
            if (isDigit()) {
                return parseNegativeConst();
            } else if (test('(')) {
                TripleExpression lowestLevel = parseOR();
                while (!test(')')) ;
                return new UnaryMinus((MathExpression) lowestLevel);
            } else if (ch == 'x' || ch == 'y' || ch == 'z') {
                UnaryMinus unaryMinus = new UnaryMinus(new Variable(String.valueOf(ch)));
                nextChar();
                return unaryMinus;
            }
            tmp = parseUnaryElement();
            return new UnaryMinus((MathExpression) tmp);
        }
        return parseConst();

    }

    private TripleExpression parseBinary(char firstOperation, char secondOperation) {
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
                lhs = new Add((MathExpression) lhs, (MathExpression) rhs);
            } else if (op == '-') {
                lhs = new Subtract((MathExpression) lhs, (MathExpression) rhs);
            } else if (op == '*') {
                lhs = new Multiply((MathExpression) lhs, (MathExpression) rhs);
            } else if (op == '/') {
                lhs = new Divide((MathExpression) lhs, (MathExpression) rhs);
            }
        }
        return lhs;
    }

    private TripleExpression parseBinary(char operation) {
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

    private TripleExpression getLogicLower(char operation) {
        if (operation == '|') {
            return parseXOR();
        } else if (operation == '^') {
            return parseAND();
        } else {
            return parseFirstLevel();
        }
    }

    private TripleExpression parseConst() {
        StringBuilder value = readDigit();
        return new Const(Integer.parseInt(value.toString()));
    }

    private TripleExpression parseNegativeConst() {
        StringBuilder value = readDigit();
        value.insert(0, '-');
        return new Const(Integer.parseInt(value.toString()));
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