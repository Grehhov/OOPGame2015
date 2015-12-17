import java.awt.Point;
import java.util.HashMap;
import java.util.Queue;

public class Plain implements Cell{
	Point coordinates;
	Game game;
	
	Plain(Game game) {
		this.game = game;
	}
	
	public Cell getClone() {
		return new Plain(game);
	}

	public void setCoordinates(int x, int y) {
		coordinates = new Point(x, y);
	}

	public Point getCoordinates() {
		return coordinates;
	}

	public Point getCoordinatesOfRandomPlain() {
		return coordinates;
	}

	public String getImage() {
		return ".";
	}

	public void addIfPassableCell(Cell cell, HashMap<Cell, Cell> track, Queue<Cell> queue) {
		track.put(this, cell);
    	queue.add(this);
	}

	public void effectOnHero(Hero hero) {
	
	}

	public void coolDown() {
		
	}

	@Override
	public void updateInfoAboutTrap(Hero hero) {
		// TODO Auto-generated method stub
		
	}
}
