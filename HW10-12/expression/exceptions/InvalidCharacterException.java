package expression.exceptions;

public class InvalidCharacterException extends ParseException {
    public InvalidCharacterException(int pos, String message) {
        super(pos, message);
    }
}
