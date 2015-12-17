import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class AIWithBreadthFirstSearch implements AI{

	public Point nextStep(Game game, Hero hero) {
		Cell start = game.field[hero.coordinates.y][hero.coordinates.x];
		HashMap<Cell, Cell> track = new HashMap<Cell, Cell>();
        track.put(start, null);
        Queue<Cell> queue = new LinkedList<Cell>();
        queue.add(start);
        Cell cell = null;
        while (!queue.isEmpty() && !(cell instanceof Bonus)) {
        	cell = queue.poll();
        	Point coords = cell.getCoordinates();
        	HashMap<Cell, Cell> incedentCells = new HashMap<Cell, Cell>();
        	incedentCells.put(game.field[coords.y-1][coords.x], cell);
        	incedentCells.put(game.field[coords.y][coords.x+1], cell);
        	incedentCells.put(game.field[coords.y+1][coords.x], cell);
        	incedentCells.put(game.field[coords.y][coords.x-1], cell);
        	for(Cell key: track.keySet()) {
        		incedentCells.remove(key);
        	}
        	//for(Cell key: hero.visibleTraps.values()) { 	//Раскомментировать,
        	//	incedentCells.remove(key);					//чтобы всегда обходить
        	//}												//видимые трапы
        	for(Cell key: incedentCells.keySet()) {
        		key.addIfPassableCell(cell, track, queue);
        	}
        }

        Cell pathItem = cell;
        ArrayList<Cell> result = new ArrayList<Cell>();
        while (pathItem != start) {
        	result.add(pathItem);
            pathItem = track.get(pathItem);
        }
        
        for(int i = 0; i < result.size(); i++) {
        	return result.get(result.size() - 1).getCoordinates();
        }
        return start.getCoordinates();
	}
}
