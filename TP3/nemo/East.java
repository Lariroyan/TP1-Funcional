package nemo;

import java.util.ArrayList;
import java.util.List;

public class East extends Direction {

	public East () {}
	
	public Direction left () {
		return new North (); 
	}
	
	public Direction right () {
		return new South (); 
	}
	
	public List<Integer> howMoveForward (){
		List<Integer> coordinates = new ArrayList();
		coordinates.add(0, 1);
		coordinates.add(1, 0);
		return coordinates;
	}
	
	public String getDirection () {
		return "East";
	}

}