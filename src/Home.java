import java.awt.Color;
import java.awt.Graphics;

public class Home implements PaintInterface {

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.BLUE);
		g.fillOval(50, 75, 50, 50);
	}

}
