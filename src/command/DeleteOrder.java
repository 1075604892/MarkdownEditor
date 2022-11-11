package command;

import element.Glyph;
import element.RootGlyph;

import java.util.ArrayList;

public class DeleteOrder implements Order {
    RootGlyph rootGlyph;
    ArrayList<Glyph> glyphs;

    public DeleteOrder(RootGlyph rootGlyph, ArrayList<Glyph> glyphs) {
        this.rootGlyph = rootGlyph;
        this.glyphs = glyphs;
    }

    @Override
    public void execute() {
        rootGlyph.delete(glyphs);
    }

    @Override
    public void undo() {
        for (int i = glyphs.size() - 1; i >= 0; i--) {
            glyphs.get(i).getParentGlyph().getSubGlyphs().add(glyphs.get(i));
        }
    }
}
