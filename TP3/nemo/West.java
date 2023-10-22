package nemo;

import java.util.ArrayList;
import java.util.List;

public class West extends Direction {
	
	public West () {}
	
	public Direction left () {
		return new South (); 
	}
	
	public Direction right () {
		return new North (); 
	}
	
	public List<Integer> howMoveForward (){
		List<Integer> coordinates = new ArrayList();
		coordinates.add(0, -1);
		coordinates.add(1, 0);
		return coordinates;
	}
	
	public String getDirection () {
		return "West";
	}
	
}