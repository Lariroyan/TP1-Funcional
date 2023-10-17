package nemo;

import java.util.List;

public class ProfundidadCero extends Profundidad{

	public Profundidad subirNemo() {
		return new ProfundidadCero();
	}

	public Profundidad bajarNemo() {
		return new ProfundidadUno();
	}

	public boolean liberarCapsula() {
		return true;
	}
	
}
