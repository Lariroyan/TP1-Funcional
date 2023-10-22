package nemo;

import java.util.List;

public class DepthZero extends Depth{
	
	public List<Depth> up(List<Depth> Depthes) {
		return Depthes;
	}

	public List<Depth> down(List<Depth> Depthes) {
		Depthes.add(new DepthOne());
		return Depthes;
	}

	public void freeCapsule() {}
	
}
