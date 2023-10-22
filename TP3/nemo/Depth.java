package nemo;

import java.util.List;

public abstract class Depth {
	public static String errorMessage_Explodes = "Nemo cannot release a capsule in this depth and explodes";
	
	public abstract List<Depth> up (List<Depth> depths);
	public abstract List<Depth> down (List<Depth> depths);
	public abstract void freeCapsule ();
}