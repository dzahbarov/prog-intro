package game;

import java.io.PrintStream;
import java.util.Scanner;

public class Readers {
    // Валидной считается строка состоящая только из цифр. Число должно быть положительным
    public boolean isNumber(String r) {
        for (int i = 0; i < r.length(); i++) {
            if (!Character.isDigit(r.charAt(i))) {
                return false;
            }
        }
        return !r.isEmpty();
    }

    public int getInt(Scanner in, PrintStream out, String caller) {
        out.print("Enter " + caller + " without extra symbols: ");
        while (true) {

            String r = in.nextLine();

            if (isNumber(r)) {
                int num = Integer.parseInt(r);
                if (num > 0) return num;
            }

            out.print("Enter " + caller + " again: ");
        }

    }

}
