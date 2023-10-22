package nemo;

import java.util.ArrayList;
import java.util.List;

public class North extends Direction {
	
	public Direction left () {
		return new West ();
	}
	
	public Direction right () {
		return new East ();
	}
	
	public List<Integer> howMoveForward (){
		List<Integer> coordinates = new ArrayList();
		coordinates.add(0, 0);
		coordinates.add(1, 1);
		return coordinates;
	}
	
	public String getDirection () {
		return "North";
	}
	
}
