package command;

import element.Glyph;
import element.RootGlyph;

import java.util.ArrayList;

public class BookmarkOrder implements Order {
    ArrayList<RootGlyph> rootGlyphs;
    RootGlyph currentRootGlyph;
    String filename;

    BookmarkOrder(ArrayList<RootGlyph> rootGlyphs, RootGlyph currentRootGlyph, String filename) {
        this.rootGlyphs = rootGlyphs;
        this.currentRootGlyph = currentRootGlyph;
        this.filename = filename;
    }

    @Override
    public void execute() {
        currentRootGlyph.setFilename(filename);
        rootGlyphs.add(currentRootGlyph);
    }
}
