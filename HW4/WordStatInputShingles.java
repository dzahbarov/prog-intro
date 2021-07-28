import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class WordStatInputShingles {
    public static void main(String[] args) {

        String input = args[0];
        String output = args[1];
        Map<String, Integer> words = new LinkedHashMap<>();

        try (Reader reader = new FileReader(new File(input), StandardCharsets.UTF_8)) {

            StringBuilder wordClean = new StringBuilder();

            int letter = reader.read();

            while (letter != -1) {

                char letterChar = (char) Character.toLowerCase(letter);

                if (!Character.isLetter(letterChar) && Character.DASH_PUNCTUATION != Character.getType(letterChar) && letterChar != '\'') {

                    for (int i = 0; i + 3 <= wordClean.length(); i++) {
                        String sub = wordClean.substring(i, i + 3);
                        words.put(sub, words.getOrDefault(sub, 0) + 1);
                    }
                    wordClean.setLength(0);
                } else {
                    wordClean.append(letterChar);
                }
                letter = Character.toLowerCase(reader.read());
            }
        } catch (IOException e) {
            System.out.println("Error in reader: " + e.getMessage());
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
