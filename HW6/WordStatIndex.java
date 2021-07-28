import java.io.*;
import java.util.*;

public class WordStatIndex {
    public static void main(String[] args) throws IOException {

        String input = args[0];
        String output = args[1];
        Map<String, ArrayList<Integer>> words = new LinkedHashMap<>();
        int cnt = 1;
        try (AnalogScanner scanner = new AnalogScanner(new File(input), "UTF-8")) {

            StringBuilder wordClean = new StringBuilder();

            while (scanner.notEmptyInput()) {

                char letterChar = Character.toLowerCase(scanner.nextChar());

                if (!Character.isLetter(letterChar) && Character.DASH_PUNCTUATION != Character.getType(letterChar) && letterChar != '\'') {

                    if (!wordClean.toString().isEmpty()) {

                        words.computeIfAbsent(wordClean.toString(), k -> new ArrayList<>()).add(cnt);
                        cnt++;
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

                bw.write(word + " " + words.get(word).size());

                for (Integer i : words.get(word)) {
                    bw.write(" " + i);
                }
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
