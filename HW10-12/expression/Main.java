package expression;

import expression.exceptions.*;

public class Main {
    public static void main(String[] args) throws EvaluateException {




        MathExpression exp = new CheckedDivide(new CheckedMultiply(new Const(1000000), new CheckedMultiply(new Variable("x"), new CheckedMultiply(new Variable("x"),  new CheckedMultiply(new Variable("x"),  new CheckedMultiply(new Variable("x"), new Variable("x")))))),
                new CheckedSubtract(new Variable("x"), new Const(1)));



        for (int i = 0; i <= 10; i++) {
            try {
                System.out.println(exp.evaluate(i, 0, 0));
            } catch (EvaluateException e) {
                System.out.println(e.getMessage());
            }
        }






    }
}
