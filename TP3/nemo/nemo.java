package nemo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Nemo {
	
	private Position coordinate;
	private Direction direction;
	private List<Depth> depths = new ArrayList();
	private List<Command> commands = new ArrayList(); 

	public Nemo (int initialX, int initialY, Direction initialDirection) {
		coordinate = new Position (initialX, initialY);
		direction = initialDirection;
		depths.add(new DepthZero());
		
		commands.addAll(Arrays.asList(new CommandDown(), 
				new CommandUp(), 
				new CommandTurnLeft(), 
				new CommandTurnRight(), 
				new CommandForward(), 
				new CommandFreeCapsule()));
	}

	public Nemo doThis(String command) {
		command.chars()
				.forEach(c -> this.commands.stream()
						.filter(com -> com.findCommand((char) c))
						.findFirst()
						.ifPresent(commandFound -> commandFound.execute(this)));
		return this;
	}
	
	public void goUp() {
		depths = depths.get(depths.size() - 1).up(depths);
	}

	public void goDown() {
		depths = depths.get(depths.size() - 1).down(depths);
	}

	public void turnLeft() {
		direction = direction.left();
	}

	public void turnRight() {
		direction = direction.right();
	}

	public void goForward() {
		coordinate = coordinate.add(direction.howMoveForward());
	}

	public void freeCapsule() {
		depths.get(depths.size() - 1).freeCapsule();
	}
	
	
	public int getDepth () {return depths.size() - 1;}
	public int getCoordinateX () {return coordinate.coordenadaX();}
	public int getCoordinateY () {return coordinate.coordenadaY();}
	public String getDirection () {return direction.getDirection();}
	
}
