import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class mainForm extends Frame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static int mainWidth = 800;
	public static int mainHeight = 850;
	public static mainForm frame = null;

	int mouseDownX;
	int mouseDownY;
	int mouseDownGlobeX;
	int mouseDownGlobeY;
	static int backGauge = 20;

	ArrayList<Home> homes = new ArrayList<Home>();
	ArrayList<Globe> globes = new ArrayList<Globe>();
	Globe globe;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.print("游戏开始");
		frame = new mainForm();
		frame.launch();
	}

	public void newMainForm() {
		Home home1 = new Home();
		home1.point = new Point(backGauge, backGauge + 25);

		Home home2 = new Home();
		home2.point = new Point(mainWidth - backGauge - home2.radius, backGauge + 25);

		Home home3 = new Home();
		home3.point = new Point(backGauge, mainHeight - backGauge * 2 - home3.radius + 25);

		Home home4 = new Home();
		home4.point = new Point(mainWidth - backGauge - home4.radius, mainHeight - backGauge * 2 - home4.radius + 25);

		Home home5 = new Home();
		home5.point = new Point((mainWidth - backGauge * 2) / 2, (mainHeight - backGauge * 2) / 2 + 25 - backGauge);

		home1.another.add(home2);
		home1.another.add(home5);
		home1.another.add(home3);

		home2.another.add(home1);
		home2.another.add(home5);
		home2.another.add(home4);

		home3.another.add(home1);
		home3.another.add(home5);

		home4.another.add(home2);
		home4.another.add(home5);

		home5.another.add(home1);
		home5.another.add(home2);
		home5.another.add(home3);
		home5.another.add(home4);

		homes.add(home1);
		homes.add(home2);
		homes.add(home3);
		homes.add(home4);
		homes.add(home5);

		Globe globe1 = new Globe();
		Globe globe2 = new Globe();
		Globe globe3 = new Globe();
		Globe globe4 = new Globe();

		home1.Add(globe1);
		home2.Add(globe2);
		home3.Add(globe3);
		home4.Add(globe4);

		globe1.color = Color.BLUE;
		globe2.color = Color.BLUE;
		globe3.color = Color.RED;
		globe4.color = Color.RED;

		globes.add(globe1);
		globes.add(globe2);
		globes.add(globe3);
		globes.add(globe4);
	}

	public void launch() {
		this.setTitle("NoWayToGo");
		// 设置船体大小
		this.setSize(mainWidth, mainHeight);
		// 居中显示
		this.setLocationRelativeTo(null);
		// 设置背景颜色
		this.setBackground(Color.WHITE);
		// 添加窗体关闭按钮监听以及方法
		this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				// 退出程序
				System.exit(0);
			}

		});
		// 使窗口不可调整大小
		this.setResizable(false);
		// 显示窗口
		this.setVisible(true);
		newMainForm();
		paint(this.getGraphics());
	}

	@Override
	public void paint(Graphics g) {
		g = this.getGraphics();
		g.setColor(Color.BLACK);
		for (Home homeFirst : homes) {
			for (Home homeSecond : homeFirst.another) {
				g.drawLine(homeFirst.point.x + 25, homeFirst.point.y + 25, homeSecond.point.x + 25,
						homeSecond.point.y + 25);
			}
		}
		for (Home home : homes) {
			home.paint(g);
		}
		if (globe != null) {
			((PaintInterface) globe).paint(g);
		}
	}

}
