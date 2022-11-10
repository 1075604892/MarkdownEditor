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
    public String draw(int index) {
        StringBuilder prefix = new StringBuilder();
        for (int i = 1; i < index; i++) {
            prefix.append("\t");
        }
        return prefix + title;
    }

    @Override
    public String text(int index) {
        StringBuilder prefix = new StringBuilder();
        for (int i = 0; i < index; i++) {
            prefix.append("#");
        }
        return prefix + title + "\n";
    }
}
