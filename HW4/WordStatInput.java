import java.io.*;
import java.util.*;

public class WordStatInput {
    public static void main(String[] args) {

        String input = args[0];
        String output = args[1];
        Map<String, Integer> words = new LinkedHashMap<>();

        try (Reader reader = new FileReader(new File(input))) {

            StringBuilder wordClean = new StringBuilder();

            int letter = reader.read();

            while (letter != -1) {

                char letterChar = (char) Character.toLowerCase(letter);

                if (!Character.isLetter(letterChar) && Character.DASH_PUNCTUATION != Character.getType(letterChar) && letterChar != '\'') {

                    if (!wordClean.toString().isEmpty()) {
                        words.put(wordClean.toString(), words.getOrDefault(wordClean.toString(), 0) + 1);
                    }
                    wordClean.setLength(0);

                } else if (!Character.isWhitespace(letterChar)) {
                    wordClean.append(letterChar);
                }
                letter = reader.read();
            }
        } catch (IOException e) {
            System.out.println("Error in reader: " + e.getMessage());
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(output))) {

            for (String word : words.keySet()) {
                bw.write(word + " " + words.get(word));
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error in writer: " + e.getMessage());
        }
    }
}
