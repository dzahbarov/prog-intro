package markup;

public class Text implements Elements {

    private final String text;

    public Text(String text) {
        this.text = text;
    }

    @Override
    public void toMarkdown(StringBuilder stringBuilder) {
        stringBuilder.append(text);
    }

    @Override
    public void toHtml(StringBuilder stringBuilder) {
        stringBuilder.append(text);
    }

    @Override
    public void toBBCode(StringBuilder stringBuilder) {
        stringBuilder.append(text);
    }

    @Override
    public Pair getHtmlSymTab() {
        return null;
    }

    @Override
    public Pair getMarkdownSymTab() {
        return null;
    }

    @Override
    public Pair getBBCodeSymTab() {
        return null;
    }
}
