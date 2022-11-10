package element;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class RootGlyph extends Glyph {
    private String filename;
    private TittleGlyph targetGlyph;

    public RootGlyph() {
    }

    @Override
    public String draw(int index) {
        return "";
    }

    @Override
    public String text(int index) {
        return "";
    }

    public RootGlyph(String filename) {
        this.filename = filename;
    }

    /*public void setFilename(String filename) {
        this.filename = filename;
    }*/


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

    public void initialization(String filename) {
        File file = new File(filename);
        if (file.exists()) {
            this.filename = filename;
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(filename);
                BufferedReader br = new BufferedReader(new InputStreamReader(fis));

                String line = null;
                String nowSub = "";
                Glyph currentGlyph = this;
                Glyph parent = null;
                while ((line = br.readLine()) != null) {
                    if (line.startsWith(nowSub + "#")) {
                        //往下一级的标题元素
                        line = line.replace("#", "");
                        Glyph newGlyph = new TittleGlyph(line);
                        currentGlyph.getSubGlyphs().add();
                        nowSub += "#";
                        parent = currentGlyph;
                    } else if (line.startsWith(nowSub + "[")) {
                        String[] temp = line.split("]");
                        temp[0] = temp[0].replace("[", "");
                        temp[1] = temp[1].replace("(", "").replace(")", "");
                        currentGlyph.getSubGlyphs().add(new LinkGlyph(temp[0], temp[1]));
                    } else {

                    }
                }
                br.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            this.filename = filename;
        }
    }

    public void tree(Glyph glyph, int index) {
        System.out.println(glyph.draw(index));
        if (glyph.getSubGlyphs() != null && glyph.getSubGlyphs().size() > 0) {
            for (Glyph item : glyph.getSubGlyphs()) {
                tree(item, index + 1);
            }
        }
    }

    public void save() {
        File file = new File(filename);

        if (file.exists()) {
            file.delete();
        }

        try {
            file.createNewFile();
            BufferedWriter writer = Files.newBufferedWriter(file.toPath());
            saving(this, 0, writer);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    //辅助方法
    public TittleGlyph search(String titleName) {
        targetGlyph = null;
        getParentGlyph(titleName, this);
        return targetGlyph;
    }

    private void getParentGlyph(String parentTitleName, Glyph glyph) {
        if (glyph instanceof TittleGlyph && ((TittleGlyph) glyph).getTitle().equals(parentTitleName) && targetGlyph == null) {
            targetGlyph = (TittleGlyph) glyph;
        } else if (glyph.getSubGlyphs() != null && glyph.getSubGlyphs().size() > 0) {
            for (Glyph item : glyph.getSubGlyphs()) {
                getParentGlyph(parentTitleName, item);
            }
        }
    }

    private void saving(Glyph glyph, int index, BufferedWriter writer) {
        try {
            writer.write(glyph.text(index));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (glyph.getSubGlyphs() != null && glyph.getSubGlyphs().size() > 0) {
            for (Glyph item : glyph.getSubGlyphs()) {
                saving(item, index + 1, writer);
            }
        }
    }


}
