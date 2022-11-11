package command;

public class ErrorOrder implements Order {

    @Override
    public void execute() {
        System.out.println("Error command");
    }

    @Override
    public void undo() {

    }
}
