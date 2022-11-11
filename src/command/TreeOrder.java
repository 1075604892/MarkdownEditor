package command;

import element.Glyph;
import element.RootGlyph;
import element.TittleGlyph;

public class TreeOrder implements Order {
    private RootGlyph currentGlyph;
    private int type;

    public static final String TYPE_LS = "ls-tree";
    public static final String TYPE_SHOW = "show-tree";

    public TreeOrder(RootGlyph currentGlyph, int type) {
        this.currentGlyph = currentGlyph;
        this.type = type;
    }

    @Override
    public void execute() {
        currentGlyph.tree(currentGlyph, 0);
    }

    @Override
    public void undo() {

    }
}
