package fourInARow;

public abstract class Turn {

    public abstract Turn playRed(Linea linea, int column);
    public abstract Turn playBlue(Linea linea, int column);
}
