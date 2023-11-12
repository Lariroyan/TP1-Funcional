package fourInARow;

public class BlueTurn extends Turn {
    public Turn playRed(Linea linea, int column) {
        throw new RuntimeException(Linea.messageNotRedsTurn);
    }

    public Turn playBlue(Linea linea, int column) {
        linea.play('B', column);
        return new RedTurn();
    }
}
