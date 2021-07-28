public class Sum {
    public static void main(String[] args) {
        int sum = 0;
        int num = 0;
        int mul = 1;
        if (args.length != 0) {
            for (String arg : args) {
                String s = arg.concat(" ");
                for (int i = 0; i < s.length(); i++) {
                    char current = s.charAt(i);
                    if (Character.isWhitespace(current)) {
                        sum += mul * num;
                        mul = 1;
                        num = 0;
                    } else if (Character.isDigit(current)) {
                        num = 10 * num + Character.getNumericValue(current);
                    } else if (current == '-') {
                        mul = -1;
                    }
                }

            }
        }
        System.out.println(sum);
    }
}
