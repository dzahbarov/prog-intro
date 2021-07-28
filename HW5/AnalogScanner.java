import java.io.*;
import java.nio.charset.Charset;


public class AnalogScanner implements AutoCloseable {
    private final InputStreamReader reader;
    private final char[] buffer = new char[512];
    private int bufferIdx = 0;
    private int itemsInBuffer = 0;
    private final char lineSeparator;

    public AnalogScanner(InputStream src, String charset) throws UnsupportedEncodingException {
        lineSeparator = System.lineSeparator().charAt(0);
        this.reader = new InputStreamReader(src, charset);
    }

    public AnalogScanner(File file, String charset) throws IOException {
        lineSeparator = System.lineSeparator().charAt(0);
        this.reader = new FileReader(file, Charset.forName(charset));
    }


    public String next() throws IOException {

        final StringBuilder word = new StringBuilder();
        toBeginOfWord();
        while (!isWhitespace()) {
            word.append(nextChar());
            if (!notEmptyInput()) {
                break;
            }
        }
        return word.toString();
    }

    private void toBeginOfWord() throws IOException {
        while (isWhitespace()) {
            toNextChar();
        }
    }

    public boolean notEmptyInput() throws IOException {
        checkBuffer();
        return bufferIdx < itemsInBuffer;
    }

    private void updateBuffer() throws IOException {
        bufferIdx = 0;
        itemsInBuffer = reader.read(buffer);
    }

    public char nextChar() throws IOException {
        checkBuffer();
        char tmp = buffer[bufferIdx];
        bufferIdx++;
        return tmp;

    }

    public boolean isEndOfLine() {
        return currentChar() == lineSeparator;
    }

    public int nextInt() throws IOException {
        return Integer.parseInt(next());
    }

    public int nextHexInt() throws IOException {
        return Integer.parseInt(next(), 16);
    }

    public boolean hasNextInt() throws IOException {
        return beginOfInt() < itemsInBuffer;
    }

    private int beginOfWord() throws IOException {
        toBeginOfWord();
        return bufferIdx;
    }

    private int beginOfInt() throws IOException {
        while (!Character.isDigit(currentChar()) && currentChar() != '-') {
            toNextChar();
        }
        return bufferIdx;
    }

    private void checkBuffer() throws IOException {
        if (bufferIdx == itemsInBuffer) {
            updateBuffer();
        }
    }

    public void toNextLine() throws IOException {
        bufferIdx += System.lineSeparator().length();
        checkBuffer();
    }

    private void toNextChar() throws IOException {
        bufferIdx++;
        checkBuffer();
    }

    private boolean isWhitespace() {
        return Character.isWhitespace(currentChar());
    }

    private char currentChar() {
        return buffer[bufferIdx];
    }

    public void close() throws IOException {
        reader.close();
    }

}
