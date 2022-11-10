package element;

public class LinkGlyph extends Glyph {
    private String title;
    private String link;

    public LinkGlyph(String title, String link) {
        this.title = title;
        this.link = link;
    }

    @Override
    public String show() {
        return "[" + title + "]" + "(" + link + ")";
    }
}
