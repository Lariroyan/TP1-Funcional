package nemo;

public class ComandoAscender extends ComandoNemo {
    private final char comando;

    public ComandoAscender() {
        this.comando = 'u';
    }

    @Override
    public boolean compararComando(char c) {
        return c == this.comando;
    }

    @Override
    public void ejecutar(Nemo nemo) {
        nemo.ascender();
    
}
}
