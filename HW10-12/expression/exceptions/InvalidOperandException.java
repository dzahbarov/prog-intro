package expression.exceptions;

public class InvalidOperandException extends ParseException {
    public InvalidOperandException(int pos, String message) {
        super(pos, message);
    }
}
