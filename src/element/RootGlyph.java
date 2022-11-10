package element;

public class RootGlyph extends Glyph {
    private String filename;
    private TittleGlyph targetGlyph;

    public static final int TYPE_TEXT = 0;
    public static final int TYPE_SHOW = 1;

    public RootGlyph() {
    }

    public RootGlyph(String filename) {
        this.filename = filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    @Override
    public String show() {
        return "";
    }

    //独有方法
    public void add(Glyph newGlyph, String parentTitleName) {
        if (parentTitleName == null) {
            getSubGlyphs().add(newGlyph);
            return;
        }

        Glyph glyph = search(parentTitleName);
        if (glyph != null) {
            glyph.getSubGlyphs().add(newGlyph);
        } else {
            System.out.println("Title not found");
        }
    }

    public void tree(Glyph glyph, int index, int type) {
        StringBuilder prefix = new StringBuilder();
        for (int i = 0; i < index; i++) {
            switch (type) {
                case TYPE_TEXT:
                    prefix.append("#");
                    break;
                case TYPE_SHOW:
                    prefix.append("\t");
                    break;
            }
        }
        if (glyph instanceof TittleGlyph || type==TYPE_TEXT) {
            System.out.println(prefix.append(glyph.show()));
        } else {
            System.out.println(glyph.show());
        }
        if (glyph.getSubGlyphs() != null && glyph.getSubGlyphs().size() > 0) {
            for (Glyph item : glyph.getSubGlyphs()) {
                tree(item, index + 1, type);
            }
        }
    }

    //辅助方法
    private TittleGlyph search(String titleName) {
        targetGlyph = null;
        getParentGlyph(titleName, this);
        return targetGlyph;
    }

    private void getParentGlyph(String parentTitleName, Glyph glyph) {
        if (glyph instanceof TittleGlyph && ((TittleGlyph) glyph).getTitle().equals(parentTitleName)) {
            targetGlyph = (TittleGlyph) glyph;
        } else if (glyph.getSubGlyphs() != null && glyph.getSubGlyphs().size() > 0) {
            for (Glyph item : glyph.getSubGlyphs()) {
                getParentGlyph(parentTitleName, item);
            }
        }
    }
}
