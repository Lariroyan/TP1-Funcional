package nemo;

import java.util.List;

public class DepthOne extends Depth{
	
	public List<Depth> up(List<Depth> profundidades) {
		profundidades.remove(profundidades.size()-1);
		return profundidades;
	}

	public List<Depth> down(List<Depth> profundidades) {
		profundidades.add(new DepthBelowOne());
		return profundidades;
	}

	public void freeCapsule() {}
}

