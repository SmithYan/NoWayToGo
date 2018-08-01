import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

public class Home implements PaintInterface {

	public Point point;
	public ArrayList<Home> another = new ArrayList<Home>();
	public Globe globe;
	public final int radius = 50;

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.YELLOW);
		g.drawOval(point.x, point.y, radius, radius);
		g.fillOval(point.x, point.y, radius, radius);
		if (globe != null)
			((PaintInterface) globe).paint(g);
	}

	public void Add(Globe g) {
		globe = g;
		g.point = this.point;
		g.home = this;
	}

	public void Remove() {
		globe = null;
	}

}
