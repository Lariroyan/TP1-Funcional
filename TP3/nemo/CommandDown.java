package nemo;

public class CommandDown extends Command {
	
    public CommandDown() {
        this.command = 'd';
    }

    public boolean findCommand(char com) {
        return com == this.command;
    }

    public void execute(Nemo nemo) {
        nemo.goDown();
    }
}
