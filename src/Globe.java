import java.awt.Color;
import java.awt.Graphics;

public class Globe implements PaintInterface {
	private static int radius = 50;
	private static Color color = Color.black;
	private static int locationX = 50;
	private static int locationY = 50;

	Graphics g;

	public void paint(Graphics g) {
		this.g = g;
		g.setColor(color);
		g.fillOval(locationX, locationY, 2 * radius, 2 * radius);
	}
}
