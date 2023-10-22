package nemo;

import java.util.List;

public abstract class Direction {
	public abstract Direction left ();
	public abstract Direction right ();
	public abstract List<Integer> howMoveForward ();
	public abstract String getDirection ();
}