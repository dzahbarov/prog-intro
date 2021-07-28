import java.io.IOException;
import java.util.ArrayList;

public class ReverseAbc {
    public static void main(String[] args) {

        ArrayList<String> ar = new ArrayList<>();

        try (AnalogScanner scanner = new AnalogScanner(System.in, "UTF-8")) {

            while (scanner.notEmptyInput()) {

                ar.add(System.lineSeparator());
                while (!scanner.isEndOfLine() && scanner.notEmptyInput()) {
                    ar.add(scanner.next().concat(" "));
                }

                if (scanner.isEndOfLine()) {
                    scanner.toNextLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Error in scanner: " + e.getMessage());
        }

        for (int i = ar.size() - 1; i >= 0; i--) {
            System.out.print(ar.get(i));
        }
    }
}
