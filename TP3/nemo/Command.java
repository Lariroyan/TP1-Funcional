package nemo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Command {
    protected char command;

    public abstract boolean findCommand(char com);
    public abstract void execute(Nemo nemo);

}