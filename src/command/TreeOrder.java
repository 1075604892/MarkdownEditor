package command;

import element.Glyph;

public class TreeOrder implements Order {
    private Glyph currentGlyph;
    private String type;

    public static final String TYPE_LS = "ls-tree";
    public static final String TYPE_SHOW = "show-tree";

    public TreeOrder(Glyph currentGlyph, String type) {
        this.currentGlyph = currentGlyph;
        this.type = type;
    }

    void show(Glyph glyph) {
        System.out.println(glyph.show());
        if (glyph.getSubGlyphs() != null && glyph.getSubGlyphs().size() > 0) {
            for (Glyph item : glyph.getSubGlyphs()) {
                show(item);
            }
        }
    }

    @Override
    public void execute() {
        show(currentGlyph);
    }
}
