package nemo;

public class CommandForward extends Command {

   public CommandForward() {
	   this.command = 'f';
   }

   public boolean findCommand(char com) {
	   return com == this.command;
   }

   public void execute(Nemo nemo) {
	   nemo.goForward();
   }
}