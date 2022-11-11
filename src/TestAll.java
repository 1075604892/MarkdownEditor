import command.AddOrder;
import command.Order;
import element.RootGlyph;
import element.TittleGlyph;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestAll {

    @Test
    void testAddTitle() {
        RootGlyph rootGlyph = new RootGlyph();
        rootGlyph.initialization("junit_test.bmk");

        TittleGlyph tittleGlyph = new TittleGlyph("标题1");
        Order order = new AddOrder(rootGlyph, tittleGlyph, null);
        order.execute();
        Assert.assertEquals(rootGlyph.getSubGlyphs().get(0), tittleGlyph);
    }

    @Test
    void testAddTitleAt() {
        RootGlyph rootGlyph = new RootGlyph();
        rootGlyph.initialization("junit_test2.bmk");

        TittleGlyph tittleGlyph = new TittleGlyph("标题1");
        TittleGlyph tittleGlyph1 = new TittleGlyph("标题1.1");

        Order order = new AddOrder(rootGlyph, tittleGlyph, null);
        order.execute();

        Order order1 = new AddOrder(rootGlyph, tittleGlyph1, tittleGlyph.getTitle());
        order1.execute();

        Assert.assertEquals(rootGlyph.getSubGlyphs().get(0).getSubGlyphs().get(0), tittleGlyph1);
    }
}