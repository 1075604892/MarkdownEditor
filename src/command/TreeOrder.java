package command;

import element.Glyph;
import element.RootGlyph;
import element.TittleGlyph;

public class TreeOrder implements Order {
    private RootGlyph currentGlyph;
    private String type;

    public static final String TYPE_LS = "ls-tree";
    public static final String TYPE_SHOW = "show-tree";

    public TreeOrder(RootGlyph currentGlyph, String type) {
        this.currentGlyph = currentGlyph;
        this.type = type;
    }

    /*void show(Glyph glyph, int index) {
        StringBuilder prefix = new StringBuilder();
        for (int i = 0; i < index; i++) {
            prefix.append("#");
        }
        if (glyph instanceof TittleGlyph) {
            System.out.println(prefix.append(glyph.show()));
        } else {
            System.out.println(glyph.show());
        }
        if (glyph.getSubGlyphs() != null && glyph.getSubGlyphs().size() > 0) {
            for (Glyph item : glyph.getSubGlyphs()) {
                show(item, index + 1);
            }
        }
    }*/

    @Override
    public void execute() {
        //show(currentGlyph, 0);
        currentGlyph.tree(currentGlyph, 0, RootGlyph.TYPE_SHOW);
    }
}
