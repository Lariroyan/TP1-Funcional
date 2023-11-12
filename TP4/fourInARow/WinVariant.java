package fourInARow;

import java.util.List;

public abstract class WinVariant {
    protected char winVariant;
    private static List<WinVariant> variants = List.of(new WinVariantA(), new WinVariantB(), new WinVariantC());

    public static WinVariant getVariant(char variant) {
        return variants.stream().
                filter(v -> v.findWinVariant(variant)).
                findFirst().
                orElseThrow(() -> new RuntimeException(Linea.messageNotAValidVariant));
    }

    public abstract boolean findWinVariant (char com);
    public abstract boolean checkWin (Linea linea, char player);

}
