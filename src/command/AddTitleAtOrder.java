package command;

import element.Glyph;
import element.TittleGlyph;

public class AddTitleAtOrder implements Order {
    private Glyph glyph;
    private String titleName;

    private String parentTitleName;


    public AddTitleAtOrder(Glyph glyph, String titleName, String parentTitleName) {
        this.glyph = glyph;
        this.titleName = titleName;
        this.parentTitleName = parentTitleName;
    }

    void getParentGlyph(String parentTitleName, Glyph glyph) {
        if (glyph instanceof TittleGlyph && ((TittleGlyph) glyph).getTitle().equals(parentTitleName)) {
            targetGlyph = (TittleGlyph) glyph;
        } else if (glyph.getSubGlyphs() != null && glyph.getSubGlyphs().size() > 0) {
            for (Glyph item : glyph.getSubGlyphs()) {
                getParentGlyph(parentTitleName, item);
            }
        }
    }

    TittleGlyph targetGlyph;

    @Override
    public void execute() {
        getParentGlyph(parentTitleName, glyph);
        if (targetGlyph != null) {
            targetGlyph.getSubGlyphs().add(new TittleGlyph(titleName, targetGlyph.getPrefix() + "#"));
        }
    }
}
