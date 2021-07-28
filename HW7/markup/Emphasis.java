package markup;

import java.util.List;

public class Emphasis extends Marks implements Elements {

    public Emphasis(List<Elements> elements) {
        super(elements);
        HtmlSymTab = new Pair("<em>", "</em>");
        MarkdownSymTab = new Pair("*", "*");
        BBCodeSymTab = new Pair("[i]", "[/i]");
    }

    @Override
    public void toMarkdown(StringBuilder stringBuilder) {
        super.toMarkdown(stringBuilder, getMarkdownSymTab());
    }

    @Override
    public void toHtml(StringBuilder stringBuilder) {
        super.toHtml(stringBuilder, getHtmlSymTab());
    }

    @Override
    public void toBBCode(StringBuilder stringBuilder) {
        super.toBBCode(stringBuilder, getBBCodeSymTab());
    }

}
