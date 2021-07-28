package expression.exceptions;

import expression.MathExpression;
import expression.Subtract;


public class CheckedSubtract extends Subtract {
    public CheckedSubtract(MathExpression lhs, MathExpression rhs) {
        super(lhs, rhs);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        int lhs = getLhs().evaluate(x, y, z);
        int rhs = getRhs().evaluate(x, y, z);
        return check(lhs, rhs);
    }


    @Override
    public int evaluate(int valueOfVariable) {
        int lhs = getLhs().evaluate(valueOfVariable);
        int rhs = getRhs().evaluate(valueOfVariable);
        return check(lhs, rhs);
    }

    private int check(int lhs, int rhs) {
        int res = lhs - rhs;
        if (lhs >= 0 && rhs <= 0 && res < 0) {
            throw new OverflowException("overflow");
        } else if (lhs < 0 && rhs > 0 && res >= 0) {
            throw new OverflowException("overflow");
        }
        return res;
    }
}
