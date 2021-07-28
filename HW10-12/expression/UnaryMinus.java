package expression;

import expression.exceptions.EvaluateException;


public class UnaryMinus extends UnaryOperation  {

    public UnaryMinus(MathExpression element) {
        super(element);
    }

    @Override
    public int evaluate(int valueOfVariable) {
        return -element.evaluate(valueOfVariable);
    }

    @Override
    public int evaluate(int x, int y, int z) throws EvaluateException {
        return -element.evaluate(x, y, z);
    }

    @Override
    public String toString() {
        return "-" + element.toString();
    }
}
