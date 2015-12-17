import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Stack;

public class Hero {
	Point coordinates = null;
	Point oldCoordinates = null;
	ArrayList<Runnable> moveOnSwamp;
	String name;
	Stack<Runnable> dead;
	int hp = 5;
	int score = 0;
	Game game;
	AI ai;
	HashMap<Point, Cell> visibleTraps;
	
	Hero(String name, Game game, AI ai) {
		this.name = name;
		
		dead = new Stack<Runnable>();
		dead.push(() -> {
			hp--;
			game.deadHeros.add(this);
		});
		for(int i = 0; i < 4; i++) {
			dead.push(() -> {
				hp--;
			});
		}
		
		moveOnSwamp = new ArrayList<Runnable>();
		
		Random rnd = new Random();
		int y = rnd.nextInt(game.field.length);
		int x = rnd.nextInt(game.field[0].length);
		coordinates = game.field[y][x].getCoordinatesOfRandomPlain();
		
		this.game = game;
		this.ai = ai;
		visibleTraps = new HashMap<Point, Cell>();
	}
	
	public void move() {
		oldCoordinates = coordinates;
		coordinates = ai.nextStep(game, this);
		for(int i = 0; i < moveOnSwamp.size(); i++){
			moveOnSwamp.get(i).run();
		}
		game.field[coordinates.y][coordinates.x].effectOnHero(this);
		for(Point p: visibleTraps.keySet()) {
			game.field[p.y][p.x].updateInfoAboutTrap(this);
		}
	}
}
