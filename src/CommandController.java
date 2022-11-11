import command.*;
import element.LinkGlyph;
import element.RootGlyph;
import element.TittleGlyph;

import java.util.ArrayList;

public class CommandController {
    private static CommandController instance;
    private static RootGlyph currentGlyph;

    private static ArrayList<Order> orderHistory;
    private int orderPoint;

    private CommandController() {
        currentGlyph = new RootGlyph();
        orderHistory = new ArrayList<>();
        orderPoint = 0;
    }

    public static CommandController getInstance() {
        if (instance == null) {
            instance = new CommandController();
        }
        return instance;
    }

    public static void setCurrentGlyph(RootGlyph currentGlyph) {
        CommandController.currentGlyph = currentGlyph;
    }

    public void analyzeCommand(String command) {
        //指令预处理
        String[] commands = command.split(" ");

        Order order = new ErrorOrder();
        if ((commands[0].startsWith("bookmark") || commands[0].startsWith("open")) && commands.length == 2 && isParamName(commands[1])) {
            //创建新的文件
            order = new BookmarkOrder(currentGlyph, handleParamName(commands[1]));
        } else if (commands[0].startsWith("add-title") && commands.length == 2 && isParamName(commands[1])) {
            //创建标题
            order = new AddOrder(currentGlyph, new TittleGlyph(handleParamName(commands[1])), null);
        } else if (commands[0].equals("show-tree")) {
            //展示树形结构
            order = new TreeOrder(currentGlyph, 1);
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
        } else if (commands[0].equals("save")) {
            //保存文件
            order = new SaveOrder(currentGlyph);
        } else if (commands[0].equals("undo")) {
            //回滚
            //todo
        } else if (commands[0].startsWith("delete-bookmark") && commands.length == 2 && isParamName(commands[1])) {
            order = new DeleteOrder(currentGlyph, currentGlyph.searchAllLink(handleParamName(commands[1])));
        }else if (commands[0].startsWith("delete-title") && commands.length == 2 && isParamName(commands[1])) {
            order = new DeleteOrder(currentGlyph, currentGlyph.searchAllTitle(handleParamName(commands[1])));
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
