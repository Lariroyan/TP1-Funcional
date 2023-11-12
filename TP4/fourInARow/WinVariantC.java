package fourInARow;

public class WinVariantC extends WinVariant {

    public WinVariantC() {
        winVariant = 'C';
    }

    public boolean findWinVariant(char com) {
        return winVariant == com;
    }

    public boolean checkWin(Linea linea, char player) {
        return linea.checkForVertical(player) || linea.checkForHorizontal(player) || linea.ckeckForDiagonal(player);
    }
}
