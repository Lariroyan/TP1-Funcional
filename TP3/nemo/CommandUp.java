package nemo;

public class CommandUp extends Command {

    public CommandUp() {
        this.command = 'u';
    }

    public boolean findCommand(char com) {
        return com == this.command;
    }

    public void execute(Nemo nemo) {
        nemo.goUp();
    }
}
