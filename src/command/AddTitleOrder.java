package command;

import element.Glyph;
import element.TittleGlyph;

public class AddTitleOrder implements Order {
    private Glyph glyph;
    private String titleName;


    public AddTitleOrder(Glyph glyph, String titleName) {
        this.glyph = glyph;
        this.titleName = titleName;
    }

    @Override

    public void execute() {
        glyph.getSubGlyphs().add(new TittleGlyph(titleName, "#"));
    }
}
