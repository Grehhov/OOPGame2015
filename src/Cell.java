import java.awt.Point;
import java.util.HashMap;
import java.util.Queue;

public interface Cell {
	public String getImage();
	public Cell getClone();
	public void setCoordinates(int x, int y);
	public Point getCoordinates();
	public Point getCoordinatesOfRandomPlain();
	public void addIfPassableCell(Cell cell, HashMap<Cell, Cell> track, Queue<Cell> queue);
	public void effectOnHero(Hero hero);
	public void coolDown();
	public void updateInfoAboutTrap(Hero hero);
}
