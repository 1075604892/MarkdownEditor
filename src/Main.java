import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("启动程序");
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String command = scanner.nextLine();
            CommandController.getInstance().analyzeCommand(command);
        }
    }
}
