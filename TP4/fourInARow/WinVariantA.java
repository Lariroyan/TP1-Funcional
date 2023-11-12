package fourInARow;

public class WinVariantA extends WinVariant {
    public WinVariantA() {
        winVariant = 'A';
    }

    public boolean findWinVariant(char com) {
        return winVariant == com;
    }

    public boolean checkWin(Linea linea, char player) {
        return linea.checkForVertical(player) || linea.checkForHorizontal(player);
    }
}
