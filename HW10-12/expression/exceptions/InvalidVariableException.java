package expression.exceptions;

public class InvalidVariableException extends ParseException{
    public InvalidVariableException(int pos, String message) {
        super(pos, message);
    }
}
