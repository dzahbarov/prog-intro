package expression;

public interface MathExpression extends Expression, TripleExpression {
    String toString();
    boolean equals(Object element);
}
