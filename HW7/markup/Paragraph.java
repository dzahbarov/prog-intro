package markup;

import java.util.List;

public class Paragraph extends Marks implements Elements {

    public Paragraph(List<Elements> elements) {
        super(elements);
        HtmlSymTab = new Pair("", "");
        MarkdownSymTab = new Pair("", "");
        BBCodeSymTab = new Pair("", "");
    }

    @Override
    public void toBBCode(StringBuilder stringBuilder) {
        toBBCode(stringBuilder, getBBCodeSymTab());
    }

    @Override
    public void toMarkdown(StringBuilder stringBuilder) {
        toMarkdown(stringBuilder, getMarkdownSymTab());
    }

    @Override
    public void toHtml(StringBuilder stringBuilder) {
        toHtml(stringBuilder, getHtmlSymTab());
    }

}
