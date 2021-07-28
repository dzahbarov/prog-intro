package markup;

import java.util.List;

public class Strikeout extends Marks implements Elements {

    public Strikeout(List<Elements> elements) {
        super(elements);
        HtmlSymTab = new Pair("<s>", "</s>");
        MarkdownSymTab = new Pair("~", "~");
        BBCodeSymTab = new Pair("[s]", "[/s]");
    }

    @Override
    public void toMarkdown(StringBuilder stringBuilder) {
        toMarkdown(stringBuilder, getMarkdownSymTab());
    }

    @Override
    public void toHtml(StringBuilder stringBuilder) {
        toHtml(stringBuilder, getHtmlSymTab());
    }

    @Override
    public void toBBCode(StringBuilder stringBuilder) {
        toBBCode(stringBuilder, getBBCodeSymTab());
    }

}
