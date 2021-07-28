import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class WordStatInputShingles {
    public static void main(String[] args) {

        String input = args[0];
        String output = args[1];
        Map<String, Integer> words = new LinkedHashMap<>();

        try (AnalogScanner scanner = new AnalogScanner(new File(input), "UTF-8")) {

            StringBuilder wordClean = new StringBuilder();

            while (scanner.notEmptyInput()) {

                char letterChar = Character.toLowerCase(scanner.nextChar());

                if (!Character.isLetter(letterChar) && Character.DASH_PUNCTUATION != Character.getType(letterChar) && letterChar != '\'') {

                    for (int i = 0; i + 3 <= wordClean.length(); i++) {
                        String sub = wordClean.substring(i, i + 3);
                        words.put(sub, words.getOrDefault(sub, 0) + 1);
                    }
                    wordClean.setLength(0);
                } else {
                    wordClean.append(letterChar);
                }

            }

        } catch (IOException e) {
            System.out.println("Error in scanner: " + e.getMessage());
        }
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(output, true), StandardCharsets.UTF_8))) {

            for (String word : words.keySet()) {
                bw.write(word + " " + words.get(word));
                bw.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error in writing: " + e.getMessage());
        }
    }
}
