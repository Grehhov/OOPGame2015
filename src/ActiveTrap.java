import java.awt.Point;
import java.util.HashMap;
import java.util.Queue;
import java.util.Random;

public class ActiveTrap implements Cell{
	Point coordinates;
	Game game;
	
	ActiveTrap(Game game) {
		this.game = game;
	}
	
	public Cell getClone() {
		return new ActiveTrap(game);
	}

	public void setCoordinates(int x, int y) {
		coordinates = new Point(x, y);
	}

	public Point getCoordinates() {
		return coordinates;
	}
	
	public Point getCoordinatesOfRandomPlain() {
		Random rnd = new Random();
		int y = rnd.nextInt(game.field.length);
		int x = rnd.nextInt(game.field[0].length);
		return game.field[y][x].getCoordinatesOfRandomPlain();
	}

	public String getImage() {
		return "T";
	}

	public void addIfPassableCell(Cell cell, HashMap<Cell, Cell> track, Queue<Cell> queue) {
		track.put(this, cell);
    	queue.add(this);
	}

	public void effectOnHero(Hero hero) {
		hero.dead.pop().run();
		game.field[coordinates.y][coordinates.x] = new CoolDownTrap(game);
		game.field[coordinates.y][coordinates.x].setCoordinates(coordinates.x, coordinates.y);
		hero.visibleTraps.put(coordinates, this);
	}

	public void coolDown() {
		
	}

	public void updateInfoAboutTrap(Hero hero) {
		hero.visibleTraps.put(coordinates, this);
	}
}
