package element;

import java.util.ArrayList;
import java.util.List;

public abstract class Glyph {
    protected ArrayList<Glyph> subGlyphs;
    protected Glyph parentGlyph;

    Glyph() {
        subGlyphs = new ArrayList<>();
    }

    public ArrayList<Glyph> getSubGlyphs() {
        return subGlyphs;
    }

    public void setParentGlyphs(Glyph parentGlyphs) {
        this.parentGlyph = parentGlyphs;
    }

    public Glyph getParentGlyph() {
        return parentGlyph;
    }

    //定义如何显示在文本页面上
    public abstract String draw(int index);

    public abstract String text(int index);
}
