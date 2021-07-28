package expression;

import expression.exceptions.EvaluateException;

public class AND extends BinaryOperation implements MathExpression {
    public AND(MathExpression lhs, MathExpression rhs) {
        super(lhs, rhs);
    }

    @Override
    public int evaluate(int valueOfVariable) {
        return getLhs().evaluate(valueOfVariable) & getRhs().evaluate(valueOfVariable);
    }

    @Override
    public int evaluate(int x, int y, int z)  throws EvaluateException {
        return getLhs().evaluate(x, y, z) & getRhs().evaluate(x, y, z);
    }
}
