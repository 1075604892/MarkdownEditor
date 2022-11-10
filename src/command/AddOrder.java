package command;

import element.Glyph;
import element.RootGlyph;

public class AddOrder implements Order {
    private final RootGlyph rootGlyph;
    private final Glyph newGlyph;
    private final String parentTitleName;


    public AddOrder(RootGlyph rootGlyph, Glyph newGlyph, String parentTitleName) {
        this.rootGlyph = rootGlyph;
        this.newGlyph = newGlyph;
        this.parentTitleName = parentTitleName;
    }

    @Override
    public void execute() {
        rootGlyph.add(newGlyph, parentTitleName);
    }
}
