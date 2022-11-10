import command.*;
import element.LinkGlyph;
import element.RootGlyph;
import element.TittleGlyph;

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
        //指令预处理
        String[] commands = command.split(" ");

        Order order = new ErrorOrder();
        if (commands[0].startsWith("bookmark") && commands.length == 2 && isParamName(commands[1])) {
            //创建新的文件
            order = new BookmarkOrder(rootGlyphs, currentGlyph, handleParamName(commands[1]));
        } else if (commands[0].startsWith("add-title") && commands.length == 2 && isParamName(commands[1])) {
            //创建标题
            order = new AddOrder(currentGlyph, new TittleGlyph(handleParamName(commands[1])), null);
        } else if (commands[0].equals("show-tree")) {
            //展示树形结构
            order = new TreeOrder(currentGlyph, commands[0]);
        } else if (commands[0].startsWith("add-title") && commands.length == 4
                && isParamName(commands[1]) && commands[2].equals("at") && isParamName(commands[3])
        ) {
            //创建次级标题
            order = new AddOrder(currentGlyph, new TittleGlyph(handleParamName(commands[1])), handleParamName(commands[3]));
        } else if (commands[0].startsWith("add-bookmark") && commands.length == 4
                && isParamLink(commands[1]) && commands[2].equals("at") && isParamName(commands[3])
        ) {
            //创建连接
            String[] params = handleParamLink(commands[1]);
            order = new AddOrder(currentGlyph, new LinkGlyph(params[0], params[1]), commands[3].replace("\"", ""));
        }
        order.execute();
    }

    String handleParamName(String input) {
        return input.replace("\"", "");
    }

    boolean isParamName(String input) {
        return input.startsWith("\"") && input.endsWith("\"");
    }

    String[] handleParamLink(String input) {
        String[] temp = input.split("@");
        temp[0] = temp[0].replace("\"", "");
        temp[1] = temp[1].replace("\"", "");
        return temp;
    }

    boolean isParamLink(String input) {
        return input.matches("\".*\"@\".*\"");
    }
}
