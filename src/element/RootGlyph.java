package element;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;

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
            newGlyph.setParentGlyphs(this);
            getSubGlyphs().add(newGlyph);
            return;
        }

        Glyph glyph = search(parentTitleName);
        if (glyph != null) {
            newGlyph.setParentGlyphs(glyph);
            glyph.getSubGlyphs().add(newGlyph);
        } else {
            System.out.println("Title not found");
        }
    }

    public void delete(ArrayList<Glyph> glyphs) {
        for (Glyph glyph : glyphs) {
            glyph.getParentGlyph().getSubGlyphs().remove(glyph);
        }
    }

    public ArrayList<Glyph> searchAllTitle(String name) {
        ArrayList<Glyph> glyphs = new ArrayList<Glyph>();
        searching(glyphs, name, this, 0);
        return glyphs;
    }

    public ArrayList<Glyph> searchAllLink(String name) {
        ArrayList<Glyph> glyphs = new ArrayList<Glyph>();
        searching(glyphs, name, this, 1);
        return glyphs;
    }

    private void searching(ArrayList<Glyph> glyphs, String name, Glyph glyph, int type) {
        if (type == 0 && glyph instanceof TittleGlyph && ((TittleGlyph) glyph).getTitle().equals(name)) {
            glyphs.add(glyph);
        } else if (type == 1 && glyph instanceof LinkGlyph && ((LinkGlyph) glyph).getTitle().equals(name)) {
            glyphs.add(glyph);
        } else if (glyph.getSubGlyphs() != null && glyph.getSubGlyphs().size() > 0) {
            for (Glyph item : glyph.getSubGlyphs()) {
                searching(glyphs, name, item, type);
            }
        }
    }

    public void deleteOne(Glyph glyph) {
        glyph.getParentGlyph().getSubGlyphs().remove(glyph);
    }

    public void initialization(String filename) {
        subGlyphs = new ArrayList<Glyph>();
        File file = new File(filename);
        if (file.exists()) {
            this.filename = filename;
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(filename);
                BufferedReader br = new BufferedReader(new InputStreamReader(fis));

                String line = null;
                String[] fathers = {null, null, null, null};
                while ((line = br.readLine()) != null) {
                    if (line.startsWith("#")) {
                        //标题
                        String[] params = line.split(" ");
                        if (params[0].length() == 1) {
                            fathers[0] = params[1];
                            for (int i = 1; i < 4; i++) {
                                fathers[i] = null;
                            }
                            this.add(new TittleGlyph(fathers[params[0].length() - 1]), null);
                            //this.getSubGlyphs().add(new TittleGlyph(fathers[params[0].length() - 1]));
                        } else if (params[0].length() > 1) {
                            fathers[params[0].length() - 1] = params[1];
                            this.add(new TittleGlyph(fathers[params[0].length() - 1]), fathers[params[0].length() - 2]);
                        }
                    } else {
                        //链接
                        String[] params = line.split("]");
                        String title = null;
                        for (int j = 3; j > 0; j--) {
                            if (fathers[j] != null) {
                                title = fathers[j];
                                break;
                            }
                        }
                        if (title != null) {
                            this.add(new LinkGlyph(params[0].substring(1), params[1].substring(1, params[1].length() - 1)), title);
                            //this.search(title).getSubGlyphs().add(new LinkGlyph(params[0].substring(1), params[1].substring(1, params[1].length() - 1)));
                        }
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
