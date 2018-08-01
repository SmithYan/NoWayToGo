import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Globe implements PaintInterface {

	public final int radius = 48;
	public Color color;
	public Point point;
	public Home home;

	public void paint(Graphics g) {
		g.setColor(color);
		g.drawOval(point.x + 1, point.y + 1, radius, radius);
		g.fillOval(point.x + 1, point.y + 1, radius, radius);
	}
}
