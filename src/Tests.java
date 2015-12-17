import static org.junit.Assert.*;

import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

public class Tests {

	@Test
	public void testHPandScore() throws IOException, InterruptedException {
		Game game = new Game("2.txt", 0);
		game.addHero(new Hero("H", game, new AIWithBreadthFirstSearch()));

		assertEquals(game.aliveHeros.size(), 1);
		assertEquals(game.aliveHeros.get(0).hp, 5);
		assertEquals(game.aliveHeros.get(0).score, 0);
		assertEquals(game.aliveHeros.get(0).visibleTraps.size(), 0);
		while(game.hpOfAllHeros() != 0) {
			game.deadHeros = new ArrayList<Hero>();
			for(Hero hero: game.aliveHeros) {
				hero.move();
			}
			
			game.aliveHeros.removeAll(game.deadHeros);
			for(int y = 0; y < game.field.length; y++) {
				for(int x = 0; x < game.field[y].length; x++){
					game.field[y][x].coolDown();
				}
			}
		}
		assertEquals(game.aliveHeros.size(), 0);
		assertEquals(game.allHeros.get(0).hp, 0);
		assertEquals(game.allHeros.get(0).score, 1);
		assertEquals(game.allHeros.get(0).visibleTraps.size(), 5);
	}
}
