import java.awt.Point;
import java.util.HashMap;
import java.util.Queue;
import java.util.Random;

public class Swamp implements Cell{
	Point coordinates;
	Game game;
	
	Swamp(Game game) {
		this.game = game;
		coordinates = getCoordinatesOfRandomPlain();
		game.field[coordinates.y][coordinates.x] = this;
	}
	
	public Cell getClone() {
		return new Swamp(game);
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
		return "S";
	}

	public void addIfPassableCell(Cell cell, HashMap<Cell, Cell> track, Queue<Cell> queue) {
		track.put(this, cell);
    	queue.add(this);	
	}

	public void effectOnHero(Hero hero) {
		hero.moveOnSwamp.add(() -> {
			hero.coordinates = hero.oldCoordinates;
			hero.moveOnSwamp.set(0, (() -> {
				hero.moveOnSwamp.clear();
			}));
		});
	}

	public void coolDown() {
		
	}

	@Override
	public void updateInfoAboutTrap(Hero hero) {
		// TODO Auto-generated method stub
		
	}
}
