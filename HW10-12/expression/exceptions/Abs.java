package expression.exceptions;

import expression.MathExpression;
import expression.UnaryOperation;
import expression.exceptions.OverflowException;

import java.util.Objects;

public class Abs extends UnaryOperation {

    public Abs(MathExpression element) {
        super(element);
    }

    @Override
    public int evaluate(int valueOfVariable) {
        int r = element.evaluate(valueOfVariable);
        return check(r);
    }

    @Override
    public int evaluate(int x, int y, int z) throws OverflowException {
        int r = element.evaluate(x, y, z);
        return check(r);
    }

    public int check(int r) {
        if (r >= 0) {
            return r;
        } else {
            if (r == Integer.MIN_VALUE) {
                throw new OverflowException("overflow");
            } else {
                return -r;
            }
        }
    }

    @Override
    public String toString() {
        return "|" + element.toString() + "|";
    }

}
