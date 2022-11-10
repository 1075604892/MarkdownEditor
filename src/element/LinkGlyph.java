package element;

public class LinkGlyph extends Glyph {
    private String title;
    private String link;

    public LinkGlyph(String title, String link) {
        this.title = title;
        this.link = link;
    }

    @Override
    public String draw(int index) {
        StringBuilder prefix = new StringBuilder();
        for (int i = 1; i < index; i++) {
            prefix.append("\t");
        }
        return prefix + "\"" + title + "\"";
    }

    @Override
    public String text(int index) {
        return "[" + title + "]" + "(" + link + ")\n";
    }
}
