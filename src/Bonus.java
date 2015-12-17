import java.awt.Point;
import java.util.HashMap;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;

public class Bonus implements Cell{
	Point coordinates = null;
	Stack<Runnable> timeout;
	Game game;
	Bonus(Game game) {
		this.game = game;
		coordinates = getCoordinatesOfRandomPlain();
		game.field[coordinates.y][coordinates.x] = this;
		timeout = new Stack<Runnable>();
	}
	
	public Cell getClone() {
		return new Bonus(game);
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
		return "$";
	}

	public void addIfPassableCell(Cell cell, HashMap<Cell, Cell> track, Queue<Cell> queue) {
		track.put(this, cell);
    	queue.add(this);
	}

	public void effectOnHero(Hero hero) {
		timeout.push(() -> {
			coordinates = getCoordinatesOfRandomPlain();
			game.field[coordinates.y][coordinates.x] = this;
		});
		for(int i = 0; i < 9; i++) {
			timeout.push(() -> {});
		}
		hero.score++;
		int x = coordinates.x;
		int y = coordinates.y;
		game.field[y][x] = new CoolDownBonus(game);
		game.field[y][x].setCoordinates(x, y);
	}

	public void coolDown() {

	}

	@Override
	public void updateInfoAboutTrap(Hero hero) {
		// TODO Auto-generated method stub
		
	}
}
