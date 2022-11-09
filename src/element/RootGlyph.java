package element;

public class RootGlyph extends Glyph {
    String filename;

    public RootGlyph() {
    }

    public RootGlyph(String filename) {
        this.filename = filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    @Override
    public void draw() {

    }

    @Override
    public String show() {
        return "";
    }

    @Override
    public void ls() {

    }
}
