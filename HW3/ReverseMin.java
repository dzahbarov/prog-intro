import java.util.ArrayList;
import java.util.Scanner;

public class ReverseMin {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        ArrayList<Integer> minColumns = new ArrayList<>();

        ArrayList<Integer> minRows = new ArrayList<>();

        ArrayList<Integer> lengthOfLine = new ArrayList<>();

        int row = 0, column = 0;
        while (scanner.hasNextLine()) {

            String line = scanner.nextLine();
            Scanner scannerLine = new Scanner(line);

            minRows.add(Integer.MAX_VALUE);

            while (scannerLine.hasNextInt()) {
                int x = scannerLine.nextInt();

                if (minColumns.size() <= column) {
                    minColumns.add(Integer.MAX_VALUE);
                }

                if (x < minRows.get(row)) {
                    minRows.set(row, x);
                }
                if (x < minColumns.get(column)) {
                    minColumns.set(column, x);
                }
                column++;


            }
            lengthOfLine.add(column);
            row++;
            column = 0;
        }


        for (int r = 0; r < minRows.size(); r++) {
            for (int c = 0; c < minColumns.size(); c++) {

                if (c < lengthOfLine.get(r)) {
                    System.out.print(Integer.min(minRows.get(r), minColumns.get(c)) + " ");
                }

            }
            System.out.println();
        }
    }
}