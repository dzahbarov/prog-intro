import java.io.IOException;
import java.util.ArrayList;

public class Reverse {
    public static void main(String[] args) throws IOException {

        AnalogScanner scanner = new AnalogScanner(System.in, "UTF-8");
        ArrayList<String> ar = new ArrayList<>();

        while (scanner.notEmptyInput()) {

            ar.add("\n");
            while (!scanner.isEndOfLine() && scanner.hasNextInt()) {
                ar.add(Integer.toString(scanner.nextInt()).concat(" "));
            }

            if (scanner.isEndOfLine()) {
                scanner.toNextLine();
            }
        }

        for (int i = ar.size() - 1; i >= 0; i--) {
            System.out.print(ar.get(i));
        }
    }
}
