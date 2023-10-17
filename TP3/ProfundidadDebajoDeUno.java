package nemo;

import java.util.List;

public class ProfundidadDebajoDeUno extends Profundidad{

	public Profundidad subirNemo() {
		return null;
	}

	public Profundidad bajarNemo() {
		return new ProfundidadDebajoDeUno();
	}

	public boolean liberarCapsula() {
		throw new Error (Nemo.errorMessage_Explota);
	}

}
