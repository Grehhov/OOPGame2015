import java.awt.Point;
import java.util.HashMap;
import java.util.Queue;
import java.util.Random;

public class Wall implements Cell{
	Point coordinates;
	Game game;
	
	Wall(Game game) {
		this.game = game;
	}
	
	public Cell getClone() {
		return new Wall(game);
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
		return "#";
	}

	public void addIfPassableCell(Cell cell, HashMap<Cell, Cell> track, Queue<Cell> queue) {

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
