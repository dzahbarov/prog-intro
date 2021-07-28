package markup;

import java.util.List;

public class Strong extends Marks implements Elements {

    public Strong(List<Elements> elements) {
        super(elements);
        HtmlSymTab = new Pair("<strong>", "</strong>");
        MarkdownSymTab = new Pair("__", "__");
        BBCodeSymTab = new Pair("[b]", "[/b]");
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
