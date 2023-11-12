package fourInARow;

public class WinVariantB extends WinVariant {
    public WinVariantB() {
        winVariant = 'B';
    }

    public boolean findWinVariant(char com) {
        return winVariant == com;
    }

    public boolean checkWin(Linea linea, char player) {
        return linea.ckeckForDiagonal(player) ;
    }

}
