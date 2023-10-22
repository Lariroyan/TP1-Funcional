package nemo;

import java.util.List;

public class Position {
	
	public int coordenadaX;
	public int coordenadaY;
	
	public Position (int coordX, int coordY) {
		coordenadaX = coordX;
		coordenadaY = coordY;
	}
	
	public Position add (List<Integer> diferenciales) {
		return new Position (coordenadaX + diferenciales.get(0), coordenadaY + diferenciales.get(1));
	}

	public int coordenadaX() {
		return coordenadaX;
	}

	public int coordenadaY() {
		return coordenadaY;
	}
}
