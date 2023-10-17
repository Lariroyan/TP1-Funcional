package nemo;

import java.util.ArrayList;
import java.util.List;

//cual es la posicion inicial? 
//cual es la profundidad?
//cual es la dirección (ángulo)?

public class Nemo {
	
	private List<Profundidad> profundidades = new ArrayList();
	
	private List<Integer> coordenadas = new ArrayList();	
	private int profundidad;
	private Direccion direccion;
	private ComandoNemo comandoActual;
	private List<ComandoNemo> comandos = new ArrayList<>(); //despues tengo que chequear esto
	//private List<ComandoNemo> comandos = new ArrayList<>();

	public static String errorMessage_Explota = "No se puede continuar descendiendo";
	
	public Nemo () {
		coordenadas.add(0);
		coordenadas.add(0);
		profundidad = profundidades.size() - 1;
		profundidades.add(new ProfundidadCero());
		direccion = new Norte ();
		// Inicializar la lista de comandos
        comandos.add(new ComandoDescender());
        comandos.add(new ComandoAscender());
        comandos.add(new ComandoGirarIzquierda());
        comandos.add(new ComandoGirarDerecha());
        comandos.add(new ComandoAvanzar());
        comandos.add(new ComandoCapsulaLiberada());
		
	}
	

	public Nemo doThis(String comandos) {
		comandos.chars()
				.forEach(c -> this.comandos.stream()
						.filter(comando -> comando.compararComando((char) c))
						.findFirst()
						.ifPresent(comandoEncontrado -> comandoEncontrado.ejecutar(this))
				);
		return this;
	}
    
	
	
	public void hacer(Character comando) { 
		comandoActual.ejecutar(this); 
	}
		
	public void ascender() {
		Profundidad a = profundidades.get(profundidades.size() - 1).subirNemo();
		profundidades.remove(profundidades.size() - 1);
		profundidades.add(a);
	}

	public void descender() {
		Profundidad a = profundidades.get(profundidades.size() - 1).bajarNemo();
		profundidades.remove(profundidades.size() - 1);
		profundidades.add(a);
	}

	public void girarIzquierda() {
		direccion = direccion.left();
	}

	public void girarDerecha() {
		direccion = direccion.right();
	}

	public void avanzar() {
		coordenadas = direccion.forward(coordenadas.get(0), coordenadas.get(1));
	}

	public void liberarCapsula() {
		profundidades.get(profundidades.size() - 1).liberarCapsula();
	}
	
	//getters
	public int getProfundidad () {return profundidades.size() - 1;}
	public int getCoordenadaX () {return coordenadas.get(0);}
	public int getCoordenadaY () {return coordenadas.get(1);}
	public String getDireccion () {return direccion.getDireccion();}

	
}
