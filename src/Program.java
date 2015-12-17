import java.io.IOException;
import java.util.ArrayList;

public class Program {
	public static void main(String[] args) throws IOException, InterruptedException {
		Game game = new Game("1.txt", 2);
		
		game.addHero(new Hero("1", game, new AIWithBreadthFirstSearch()));
		game.addHero(new Hero("2", game, new AIWithBreadthFirstSearch()));
		game.start();
	}
}
