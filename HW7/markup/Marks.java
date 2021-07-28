package markup;

import java.util.List;

abstract class Marks implements Elements {
    protected List<Elements> elements;
    protected Pair HtmlSymTab;
    protected Pair MarkdownSymTab;
    protected Pair BBCodeSymTab;


    protected Marks(List<Elements> elements) {
        this.elements = elements;
    }

    protected void toMarkdown(StringBuilder stringBuilder, Pair symTable) {
        wrapStringBuilder(stringBuilder, TypeFunction.toMarkdown, symTable);
    }

    protected void toHtml(StringBuilder stringBuilder,Pair symTable) {
        wrapStringBuilder(stringBuilder, TypeFunction.toHtml, symTable);
    }

    protected void toBBCode(StringBuilder stringBuilder, Pair symTable) {
        wrapStringBuilder(stringBuilder, TypeFunction.toBBCode, symTable);
    }

    private void wrapStringBuilder(StringBuilder stringBuilder, TypeFunction caller, Pair symTable) {
        stringBuilder.append(symTable.getOpen());
        for (Elements element : elements) {
            switch (caller) {
                case toMarkdown -> element.toMarkdown(stringBuilder);
                case toHtml -> element.toHtml(stringBuilder);
                case toBBCode -> element.toBBCode(stringBuilder);
            }
        }
        stringBuilder.append(symTable.getClose());
    }

    @Override
    public Pair getHtmlSymTab() {
        return HtmlSymTab;
    }

    @Override
    public Pair getMarkdownSymTab() {
        return MarkdownSymTab;
    }

    @Override
    public Pair getBBCodeSymTab() {
        return BBCodeSymTab;
    }

}
