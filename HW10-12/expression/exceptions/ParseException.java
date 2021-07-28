package expression.exceptions;

public class ParseException extends Exception {
    public ParseException(int pos, String message) {
        super("Pos " + pos + ": "  + message);
    }
}
