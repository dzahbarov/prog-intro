import java.util.ArrayList;
import java.util.Scanner;

public class Reverse {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ArrayList<String> ar = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            ar.add("\n");
            Scanner scannerLine = new Scanner(line);

            while (scannerLine.hasNextInt()) {
                ar.add(Integer.toString(scannerLine.nextInt()));
            }
        }

        for (int i = ar.size() - 1; i >= 0; i--) {
            System.out.print(" " + ar.get(i));
        }
    }
}
