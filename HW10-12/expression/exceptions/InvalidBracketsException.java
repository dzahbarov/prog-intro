package expression.exceptions;

public class InvalidBracketsException extends ParseException{
    public InvalidBracketsException(int pos, String message) {
        super(pos, message);
    }
}
