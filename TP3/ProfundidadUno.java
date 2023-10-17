package nemo;

import java.util.List;

public class ProfundidadUno extends Profundidad{
	
	public Profundidad subirNemo() {
		return null;
	}

	public Profundidad bajarNemo() {
		return new ProfundidadDebajoDeUno();
	}

	public boolean liberarCapsula() {
		return true;
	}
	
}
