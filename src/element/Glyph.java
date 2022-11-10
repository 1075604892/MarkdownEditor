package element;

import java.util.ArrayList;
import java.util.List;

public abstract class Glyph {
    protected ArrayList<Glyph> subGlyphs;

    Glyph() {
        subGlyphs = new ArrayList<>();
    }

    public ArrayList<Glyph> getSubGlyphs() {
        return subGlyphs;
    }

    //定义如何显示在页面上
    public abstract String show();
}
