package nemo;

public class ComandoGirarIzquierda extends ComandoNemo {

    public ComandoGirarIzquierda() {
        this.comando = 'l';
    }

    @Override
    public boolean compararComando(char c) {
        return c == this.comando;
    }

    @Override
    public void ejecutar(Nemo nemo) {
        nemo.girarIzquierda();
    }
}
