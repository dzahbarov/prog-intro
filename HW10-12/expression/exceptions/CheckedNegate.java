package expression.exceptions;

import expression.MathExpression;
import expression.UnaryMinus;

public class CheckedNegate extends UnaryMinus {

    public CheckedNegate(MathExpression element) {
        super(element);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        int value = element.evaluate(x, y, z);
        return check(value);
    }

    @Override
    public int evaluate(int valueOfVariable) {
        int value = element.evaluate(valueOfVariable);
        return check(value);
    }

    private int check(int value) {
        if (value == Integer.MIN_VALUE) {
            throw new OverflowException("overflow");
        }
        return -value;
    }

}
