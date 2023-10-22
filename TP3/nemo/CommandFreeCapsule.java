package nemo;

public class CommandFreeCapsule extends Command{

    public CommandFreeCapsule() {
        this.command = 'm';
    }

    public boolean findCommand(char com) {
        return com == this.command;
    }

    public void execute(Nemo nemo) {
        nemo.freeCapsule();
    }
}
