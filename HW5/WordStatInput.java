import java.io.*;
import java.util.*;

public class WordStatInput {
    public static void main(String[] args) throws IOException {

        String input = args[0];
        String output = args[1];
        Map<String, Integer> words = new LinkedHashMap<>();

        try (AnalogScanner scanner = new AnalogScanner(new File(input), "UTF-8")) {

            StringBuilder wordClean = new StringBuilder();

            while (scanner.notEmptyInput()) {

                char letterChar = Character.toLowerCase(scanner.nextChar());

                if (!Character.isLetter(letterChar) && Character.DASH_PUNCTUATION != Character.getType(letterChar) && letterChar != '\'') {

                    if (!wordClean.toString().isEmpty()) {
                        words.put(wordClean.toString(), words.getOrDefault(wordClean.toString(), 0) + 1);
                    }
                    wordClean.setLength(0);

                } else if (!Character.isWhitespace(letterChar)) {
                    wordClean.append(letterChar);
                }

            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(output))) {

            for (String word : words.keySet()) {
                bw.write(word + " " + words.get(word));
                bw.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
