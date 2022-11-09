package element;

public class LinkGlyph extends Glyph {
    private String title;
    private String link;

    @Override
    public void draw() {
    }

    @Override
    public String show() {
        return "[" + title + "]" + "(" + link + ")";
    }

    @Override
    public void ls() {

    }
}
