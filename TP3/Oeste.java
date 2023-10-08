package sumergible;

import java.util.ArrayList;
import java.util.List;

public class Oeste extends Direccion {
	
	public Oeste () {}
	
	public Direccion left () {
		return new Sur (); 
	}
	
	public Direccion right () {
		return new Norte (); 
	}
	
	public List<Integer> forward (int coordenadaX, int coordenadaY){
		List<Integer> coordenadas = new ArrayList();
		coordenadas.add(0, coordenadaX - 1);
		coordenadas.add(1, coordenadaY);
		return coordenadas;
	}
	
	public String getDireccion () {
		return "Oeste";
	}
	
}
