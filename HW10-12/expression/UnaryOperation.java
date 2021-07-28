package expression;

import java.util.Objects;

public abstract class UnaryOperation implements MathExpression{
    protected final MathExpression element;


    public UnaryOperation(MathExpression element) {
        this.element = element;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;
        UnaryOperation that = (UnaryOperation) o;
        return Objects.equals(element, that.element);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(element) * 31 + Objects.hashCode(getClass()) * 29;
    }

}
