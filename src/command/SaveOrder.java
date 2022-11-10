package command;

import element.RootGlyph;

public class SaveOrder implements Order {
    private RootGlyph rootGlyph;

    public SaveOrder(RootGlyph rootGlyph) {
        this.rootGlyph = rootGlyph;
    }

    @Override
    public void execute() {
        rootGlyph.save();
    }
}
