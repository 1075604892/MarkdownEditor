package command;

import element.RootGlyph;

import java.util.ArrayList;

public class BookmarkOrder implements Order {
    RootGlyph rootGlyph;
    String filename;


    public BookmarkOrder(RootGlyph rootGlyph, String filename) {
        this.rootGlyph = rootGlyph;
        this.filename = filename;
    }

    @Override
    public void execute() {
        rootGlyph.initialization(filename);
    }
}
