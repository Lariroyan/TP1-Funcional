package sumergible;

//cual es la posicion inicial? 
//cual es la profundidad?
//cual es la dirección (ángulo)?

public class Sumergible {
	
	private int coordenadaX;
	private int coordenadaY;
	private int profundidad;
	private int angulo;
	private boolean capsulaLiberada;
	public static String errorMessage_Explota = "No se puede continuar descendiendo";
	
	public Sumergible () {
		coordenadaX = 0;
		coordenadaY = 0;
		profundidad = 0;
		angulo = 0;
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
		
		if (comando == 'l') { //////////////////////NORTE SUR ESTE OESTE?
			if (angulo == 270) {
				angulo = 0;
			} else {
				angulo = angulo + 90;
			}
		}
		
		if (comando == 'r') {
			if (angulo == 0) {
				angulo = 270;
			} else {
				angulo = angulo - 90;
			}
		}
		
		if (comando == 'f') {
			if (angulo == 0) {
				coordenadaX = coordenadaX + 1;
			}
			
			if (angulo == 90) {
				coordenadaY = coordenadaY + 1;
			}
			
			if (angulo == 180) {
				coordenadaX = coordenadaX - 1;
			}
			
			if (angulo == 270) {
				coordenadaY = coordenadaY - 1;
			}
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
	public int getCoordenadaX () {return coordenadaX;}
	public int getCoordenadaY () {return coordenadaY;}
	public int getAngulo () {return angulo;}
	public boolean getEstadoCapsulaLiberada () {return capsulaLiberada;}
}
