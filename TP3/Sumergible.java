package sumergible;

import java.util.ArrayList;
import java.util.List;

//cual es la posicion inicial? 
//cual es la profundidad?
//cual es la dirección (ángulo)?

public class Sumergible {
	
	private int coordenadaX;
	private int coordenadaY;
	List<Integer> coordenadas = new ArrayList();
	private int profundidad;
	private Direccion direccion;
	private boolean capsulaLiberada;
	public static String errorMessage_Explota = "No se puede continuar descendiendo";
	
	public Sumergible () {
		coordenadaX = 0;
		coordenadaY = 0;
		coordenadas.add(0, coordenadaX);
		coordenadas.add(1, coordenadaY);
		profundidad = 0;
		direccion = new Norte ();
		capsulaLiberada = false;
	}
	
	public Sumergible doThis (String comandos) {
		comandos.chars().mapToObj(c -> (char) c).forEach(this::hacer);
		return this;
	}
	
	public void hacer(Character comando) {
		
		if (comando == 'd') {
			profundidad = profundidad - 1;
		}
		
		if (comando == 'u') {
			if (profundidad < 0) {
				profundidad = profundidad + 1;
			}
		}
		
		if (comando == 'l') {
			direccion = direccion.left();
		}
		
		if (comando == 'r') {
			direccion = direccion.right();
		}
		
		if (comando == 'f') {
			coordenadas = direccion.forward(coordenadas.get(0), coordenadas.get(1));
		}
		
		if (comando == 'm') {
			if (profundidad >= -1 && !capsulaLiberada) {
				capsulaLiberada = true;
			} 
			
			if (profundidad < -1) {
				throw new Error (errorMessage_Explota);
			}
		}
	}
	
	//getters
	public int getProfundidad () {return profundidad;}
	public int getCoordenadaX () {return coordenadas.get(0);}
	public int getCoordenadaY () {return coordenadas.get(1);}
	public String getDireccion () {return direccion.getDireccion();}
	public boolean getEstadoCapsulaLiberada () {return capsulaLiberada;}
}
