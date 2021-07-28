import java.io.*;
import java.util.*;

public class WordStatSortedLineIndex {
    public static void main(String[] args) throws IOException {

        String input = args[0];
        String output = args[1];
        Map<String, ArrayList<String>> words = new TreeMap<>();
        int numberInLine = 1;
        int numberOfLine = 1;

        try (AnalogScanner scanner = new AnalogScanner(new File(input), "UTF-8")) {

            StringBuilder wordClean = new StringBuilder();

            while (scanner.notEmptyInput()) {

                boolean endOfLine = scanner.isEndOfLine();

                char letterChar = Character.toLowerCase(scanner.nextChar());

                if (!Character.isLetter(letterChar) && Character.DASH_PUNCTUATION != Character.getType(letterChar) && letterChar != '\'') {

                    if (!wordClean.toString().isEmpty()) {

                        String str = numberOfLine + ":" + numberInLine;

                        words.computeIfAbsent(wordClean.toString(), k -> new ArrayList<>()).add(str);
                        numberInLine++;

                    }
                    wordClean.setLength(0);

                } else if (!Character.isWhitespace(letterChar)) {
                    wordClean.append(letterChar);
                }

                if (endOfLine) {
                    numberOfLine++;
                    numberInLine = 1;
                }


            }

        } catch (FileNotFoundException e) {
            System.out.println("Error in scanner: " + e.getMessage());
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(output))) {


            for (String word : words.keySet()) {

                bw.write(word + " " + words.get(word).size());

                for (String i : words.get(word)) {
                    bw.write(" " + i);
                }
                bw.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error in writer: " + e.getMessage());
        }
    }
}