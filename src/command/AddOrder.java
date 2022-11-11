package command;

import element.Glyph;
import element.RootGlyph;

import java.util.ArrayList;

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

    @Override
    public void undo() {
        rootGlyph.deleteOne(newGlyph);
    }
}
