import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
		frame = new mainForm();
		frame.launch();
	}

	public void actionss() {
		MouseAdapter l = new MouseAdapter() {// 匿名内部类
			@Override
			public void mouseClicked(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// 设定球的初始值为空
				globe = null;
				// 循环球集合
				for (Globe g : globes) {
					// 如果点击到了球
					if (e.getX() >= g.point.x && e.getY() >= g.point.y && e.getY() <= g.point.x + g.radius
							&& e.getY() <= g.point.y + g.radius) {
						// 将点击的球赋值给本界面的球
						globe = g;
						// 将此营地的球移除
						globe.home.Remove();
						for (Home home : homes) {
							if (home.globe == g) {
								home.globe = null;
							}
						}
						// 存储鼠标相对于球的坐标
						mouseDownGlobeX = e.getX() - g.point.x;
						mouseDownGlobeY = e.getY() - g.point.y;
						break;
					}
				}
				// 存储鼠标相对于界面的坐标
				mouseDownX = e.getX();
				mouseDownY = e.getY();
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// 当球不为空的时候
				if (globe != null) {
					// 将营地赋值为空
					Home cs = null;
					// 定义Boolean变量isRelation
					Boolean isRelation = false;
					// 循环所有营地
					for (Home home : homes) {
						// 当鼠标到达营地的时候
						if (e.getX() >= home.point.x - 20 && e.getY() >= home.point.y - 20
								&& e.getX() <= home.point.x + home.radius + 20
								&& e.getY() <= home.point.y + home.radius + 20) {
							// 将营地赋值给当前界面的营地
							cs = home;
							break;
						}
					}
					// 当营地不为空的时候
					if (cs != null) {
						// 循环与之相连的营地
						for (Home cam : globe.home.another) {
							// 当鼠标所在营地是其相连营地的时候
							if (cam == cs) {
								// IDRelation赋值为true（两个营地有关系）
								isRelation = true;
							}
						}
					}
					// 当两个营地没有关系时，此界面的营地赋值为空
					if (!isRelation) {
						cs = null;
					}
					// 当营地和球都不为空的时候
					if (cs != null && cs.globe == null) {
						// 将球赋值为此营地的球
						globe.point = cs.point;
						// 将球的营地更改为鼠标当前的营地
						globe.home = cs;
					} else {
						// 球归位
						globe.point = new Point(e.getX() - mouseDownGlobeX, e.getY() - mouseDownGlobeY);

					}
					// 球的坐标为所在营地的坐标
					globe.point = globe.home.point;
					// 营地的球是当前球
					globe.home.globe = globe;
				}
				globe = null;
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseDragged(MouseEvent e) {
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				if (globe != null) {
					globe.point = new Point(e.getX() - 25, e.getY() - 25);
				}
			}
		};
		frame.addMouseListener(l);// 处理鼠标的操作事件
		frame.addMouseMotionListener(l);// 处理鼠标的滑动事件
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
		actionss();
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
