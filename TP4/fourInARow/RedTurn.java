package fourInARow;

public class RedTurn extends Turn {

    public Turn playRed(Linea linea, int column) {
        linea.play('R', column);
        return new BlueTurn();
    }

    public Turn playBlue(Linea linea, int column) {
        throw new RuntimeException(Linea.messageNotBluesTurn);
    }
}
