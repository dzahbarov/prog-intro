package expression;

public class Const implements MathExpression {
    private final int value;

    public Const(int value){
        this.value = value;
    }

    @Override
    public int evaluate(int valueOfVariable){
        return value;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return value;
    }

    @Override
    public String toString(){
        return Integer.toString(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;
        Const aConst = (Const) o;
        return value == aConst.value;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(value)*29;
    }
}
