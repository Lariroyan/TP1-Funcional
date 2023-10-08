package sumergible;

import java.util.ArrayList;
import java.util.List;

public class Este extends Direccion {

	public Este () {}
	
	public Direccion left () {
		return new Norte (); 
	}
	
	public Direccion right () {
		return new Sur (); 
	}
	
	public List<Integer> forward (int coordenadaX, int coordenadaY){
		List<Integer> coordenadas = new ArrayList();
		coordenadas.add(0, coordenadaX + 1);
		coordenadas.add(1, coordenadaY);
		return coordenadas;
	}
	
	public String getDireccion () {
		return "Este";
	}
}
