package nemo;

public class ComandoAvanzar extends ComandoNemo {
   // private final char comando;
   public ComandoAvanzar() {
    this.comando = 'f';
}

@Override
public boolean compararComando(char c) {
    return c == this.comando;
}

@Override
public void ejecutar(Nemo nemo) {
    nemo.avanzar();

}
}
