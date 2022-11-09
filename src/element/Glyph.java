package element;

import java.util.ArrayList;
import java.util.List;

public abstract class Glyph {
    private ArrayList<Glyph> subGlyphs;

    Glyph() {
        subGlyphs = new ArrayList<>();
    }

    public ArrayList<Glyph> getSubGlyphs() {
        return subGlyphs;
    }

    //定义如何显示在页面上
    public abstract void draw();

    public abstract String show();

    public abstract void ls();
}
