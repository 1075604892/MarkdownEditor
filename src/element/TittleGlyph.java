package element;

public class TittleGlyph extends Glyph {
    private String title;
    private String prefix;

    public TittleGlyph(String title, String prefix) {
        this.title = title;
        this.prefix = prefix;
    }

    public String getTitle() {
        return title;
    }

    public String getPrefix(){
        return prefix;
    }

    @Override
    public void draw() {

    }

    @Override
    public String show() {
        return prefix + title;
    }

    @Override
    public void ls() {

    }
}
