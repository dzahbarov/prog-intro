package markup;

public interface Elements {
    void toMarkdown(StringBuilder stringBuilder);

    void toHtml(StringBuilder stringBuilder);

    void toBBCode(StringBuilder stringBuilder);

    Pair getHtmlSymTab();

    Pair getMarkdownSymTab();

    Pair getBBCodeSymTab();

}
