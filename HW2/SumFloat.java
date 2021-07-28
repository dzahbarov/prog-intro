public class SumFloat {
    public static void main(String[] args) {
        float sum = 0;
        StringBuilder builder = new StringBuilder();
        for (String arg : args) {
            String st = arg.concat(" ");
            for (int i = 0; i < st.length(); i++) {
                char current = st.charAt(i);
                if (!Character.isWhitespace(current)) {
                    builder.append(current);
                } else if (!builder.toString().isBlank()) {
                    sum += Float.parseFloat(builder.toString());
                    builder.setLength(0);
                }
            }
        }
        System.out.println(sum);
    }
}