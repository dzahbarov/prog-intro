package expression.parser;

public abstract class BaseParser  {
    protected CharSource source;

    protected char ch;
    protected static final char EOF = 0;

    protected void nextChar() {
        ch = source.hasNext() ? source.nextChar() : EOF;
    }

    protected boolean test(final char expected) {
        if (ch == expected) {
            nextChar();
            return true;
        }
        return false;
    }

    protected void error(String message) {
        source.error(message);
    }
}
