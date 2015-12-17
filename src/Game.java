import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Game {
	Cell[][] field;
	ArrayList<Hero> aliveHeros;
	ArrayList<Hero> deadHeros;
	ArrayList<Hero> allHeros;
	
	public Game(String file, int countOfSwamp) throws IOException {		
		HashMap<Character, Cell> cells = new HashMap<Character, Cell>();
		cells.put('#', new Wall(this));
		cells.put('.', new Plain(this));
		cells.put('T', new ActiveTrap(this));
		int height = 0;
		BufferedReader reader = new BufferedReader(new FileReader(file));
		while(reader.readLine() != null) {
			height++;
		}
		reader = new BufferedReader(new FileReader(file));
		field = new Cell[height][];
		String line;
		int y = 0;
		while((line = reader.readLine()) != null) {
			field[y] = new Cell[line.length()];
			for(int x = 0; x < line.length(); x++) {
				field[y][x] = cells.get(line.charAt(x)).getClone();
				field[y][x].setCoordinates(x, y);
			}
			y++;
		}
		
		new Bonus(this);
		new Bonus(this);
		for (int i = 0; i < countOfSwamp; i++) {
			new Swamp(this);
		}
		aliveHeros = new ArrayList<Hero>();
		allHeros = new ArrayList<Hero>();
	}
	
	void addHero(Hero hero) {
		aliveHeros.add(hero);
		allHeros.add(hero);
	}
	
	void start() throws InterruptedException{
		printField();
		
		while(hpOfAllHeros() != 0) {
			deadHeros = new ArrayList<Hero>();
			for(Hero hero: aliveHeros) {
				hero.move();
				System.out.println(hero.name + " - HP: " + hero.hp + "   Score: " + hero.score);
			}
			
			printField();
			this.aliveHeros.removeAll(deadHeros);
			System.out.println();

			for(int y = 0; y < field.length; y++) {
				for(int x = 0; x < field[y].length; x++){
					field[y][x].coolDown();
				}
			}
			Thread.sleep(1000);
		}
		
		System.out.println("Game Over!");
		for(Hero hero: this.allHeros) {
			System.out.println(hero.name + " - HP: " + hero.hp + "   Score: " + hero.score);
		}
	}
	
	public int hpOfAllHeros() {
		int hp = 0;
		for (Hero hero: aliveHeros) {
			hp += hero.hp;
		}
		return hp;
	}
	
	public void printField() {
		String[] map = new String[field.length];
		for(int y = 0; y < field.length; y++) {
			String line = "";
			for (int x = 0; x < field[y].length; x++) {
				line += field[y][x].getImage();
			}
			map[y] = line;
		}

		for(Hero hero: aliveHeros) {
			int y = hero.coordinates.y;
			int x = hero.coordinates.x;
			map[y] = map[y].substring(0, x) + hero.name + map[y].substring(x + 1);
		}
		
		for(String line: map) {
			System.out.println(line);
		}
	}
	
}
