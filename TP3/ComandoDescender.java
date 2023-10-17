package nemo;

public class ComandoDescender extends ComandoNemo {
    public ComandoDescender() {
        this.comando = 'd';
    }

    @Override
    public boolean compararComando(char c) {
        return c == this.comando;
    }

    @Override
    public void ejecutar(Nemo nemo) {
        nemo.descender();
    }
    
}
