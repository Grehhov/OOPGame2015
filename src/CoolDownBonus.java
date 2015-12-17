import java.awt.Point;
import java.util.HashMap;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;

public class CoolDownBonus implements Cell{
	Point coordinates;
	Stack<Runnable> timeout;
	Game game;
	public CoolDownBonus(Game game) {
		this.game = game;
		timeout = new Stack<Runnable>();
		timeout.push(() -> {
			game.field[coordinates.y][coordinates.x] = new Plain(game);
			game.field[coordinates.y][coordinates.x].setCoordinates(coordinates.x, coordinates.y);
			new Bonus(game);
		});
		for(int i = 0; i < 9; i++) {
			timeout.push(() -> {});
		}
	}
	
	public Cell getClone() {
		return new CoolDownBonus(game);
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
		return ".";
	}

	public void addIfPassableCell(Cell cell, HashMap<Cell, Cell> track, Queue<Cell> queue) {
		track.put(this, cell);
    	queue.add(this);
	}

	public void effectOnHero(Hero hero) {
		
	}

	public void coolDown() {
		timeout.pop().run();
	}

	@Override
	public void updateInfoAboutTrap(Hero hero) {
		// TODO Auto-generated method stub
		
	}
}
