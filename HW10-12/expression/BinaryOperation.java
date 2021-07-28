package expression;

import java.util.Objects;

abstract class BinaryOperation {
    private final MathExpression lhs;
    private final MathExpression rhs;


    public BinaryOperation(MathExpression lhs, MathExpression rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    public MathExpression getLhs() {
        return lhs;
    }

    public MathExpression getRhs() {
        return rhs;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;
        BinaryOperation that = (BinaryOperation) o;
        return Objects.equals(lhs, that.lhs) &&
                Objects.equals(rhs, that.rhs);
    }

    @Override
    public int hashCode() {
        int res = Objects.hashCode(getLhs()) * 31;
        res = (res + Objects.hashCode(getRhs()))*31;
        res = res + Objects.hashCode(getClass()) * 29;
        return res;
    }
}
