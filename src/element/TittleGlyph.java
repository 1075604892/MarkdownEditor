package element;

public class TittleGlyph extends Glyph {
    private String title;

    public TittleGlyph(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String show() {
        return title;
    }
}
