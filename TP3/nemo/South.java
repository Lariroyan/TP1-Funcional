package nemo;

import java.util.ArrayList;
import java.util.List;

public class South extends Direction {
	
	public Direction left () {
		return new East (); 
	}
	
	public Direction right () {
		return new West (); 
	}
	
	public List<Integer> howMoveForward (){
		List<Integer> coordinates = new ArrayList();
		coordinates.add(0, 0);
		coordinates.add(1, -1);
		return coordinates;
	}
	
	public String getDirection () {
		return "South";
	}
}