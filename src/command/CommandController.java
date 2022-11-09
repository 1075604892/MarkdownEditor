package command;

import element.RootGlyph;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class CommandController {
    private static CommandController instance;
    private final ArrayList<RootGlyph> rootGlyphs;
    private RootGlyph currentGlyph;

    private CommandController() {
        rootGlyphs = new ArrayList<>();
        currentGlyph = new RootGlyph();
    }

    public static CommandController getInstance() {
        if (instance == null) {
            instance = new CommandController();
        }
        return instance;
    }

    public void analyzeCommand(String command) {
        String[] commands = command.split(" ");

        Order order = new ErrorOrder();
        if (commands[0].startsWith("bookmark") && commands.length == 2
                && commands[1].startsWith("\"") && commands[1].endsWith("\"")) {
            //创建新的文件
            order = new BookmarkOrder(rootGlyphs, currentGlyph, commands[1].replace("\"", ""));
        } else if (commands[0].startsWith("add-title") && commands.length == 2
                && commands[1].startsWith("\"") && commands[1].endsWith("\"")) {
            //创建标题
            order = new AddTitleOrder(currentGlyph, commands[1].replace("\"", ""));
        } else if (commands[0].equals("show-tree")) {
            //展示树形结构
            order = new TreeOrder(currentGlyph, commands[0]);
        } else if (commands[0].startsWith("add-title") && commands.length == 4
                && commands[1].startsWith("\"") && commands[1].endsWith("\"") && commands[2].equals("at")
                && commands[3].startsWith("\"") && commands[1].endsWith("\"")
        ) {
            //创建次级标题
            order = new AddTitleAtOrder(currentGlyph, commands[1].replace("\"", ""),
                    commands[3].replace("\"", ""));
        } else if (commands[0].startsWith("add-bookmark") && commands.length == 4
                && commands[1].startsWith("\"") && commands[1].endsWith("\"") && commands[2].equals("at")
                && commands[3].startsWith("\"") && commands[1].endsWith("\"")
        ) {
            //todo
        }
        order.execute();
    }
}
