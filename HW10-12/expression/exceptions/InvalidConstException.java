package expression.exceptions;

public class InvalidConstException extends ParseException{
    public InvalidConstException(int pos, String message) {
        super(pos, message);
    }
}
