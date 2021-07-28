package expression.exceptions;

import expression.MathExpression;
import expression.UnaryOperation;
import expression.exceptions.InvalidSqrtArgument;

public class Sqrt extends UnaryOperation {

    public Sqrt(MathExpression element) {
        super(element);
    }

    @Override
    public int evaluate(int valueOfVariable) {
        int r = element.evaluate(valueOfVariable);
        return check(r);
    }

    @Override
    public int evaluate(int x, int y, int z) throws InvalidSqrtArgument {
        int r = element.evaluate(x, y, z);
        return check(r);
    }

    public int check(int r) {
        if (r >= 0) {
            //return (int) Math.sqrt(r);
            return getSqrt(r);
        }
        throw new InvalidSqrtArgument("invalid sqrt argument");
    }

    private int getSqrt(int r) {
        int i = 0;
        while (i * i <= r) {
            i++;
        }

        return i-1;
    }
    @Override
    public String toString() {
        return "sqrt(" + element.toString() + ")";
    }

}
