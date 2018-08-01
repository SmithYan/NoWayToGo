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
		MouseAdapter l = new MouseAdapter() {// �����ڲ���
			@Override
			public void mouseClicked(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// �趨��ĳ�ʼֵΪ��
				globe = null;
				// ѭ���򼯺�
				for (Globe g : globes) {
					// ������������
					if (e.getX() >= g.point.x && e.getY() >= g.point.y && e.getY() <= g.point.x + g.radius
							&& e.getY() <= g.point.y + g.radius) {
						// ���������ֵ�����������
						globe = g;
						// ����Ӫ�ص����Ƴ�
						globe.home.Remove();
						for (Home home : homes) {
							if (home.globe == g) {
								home.globe = null;
							}
						}
						// �洢���������������
						mouseDownGlobeX = e.getX() - g.point.x;
						mouseDownGlobeY = e.getY() - g.point.y;
						break;
					}
				}
				// �洢�������ڽ��������
				mouseDownX = e.getX();
				mouseDownY = e.getY();
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// ����Ϊ�յ�ʱ��
				if (globe != null) {
					// ��Ӫ�ظ�ֵΪ��
					Home cs = null;
					// ����Boolean����isRelation
					Boolean isRelation = false;
					// ѭ������Ӫ��
					for (Home home : homes) {
						// ����굽��Ӫ�ص�ʱ��
						if (e.getX() >= home.point.x - 20 && e.getY() >= home.point.y - 20
								&& e.getX() <= home.point.x + home.radius + 20
								&& e.getY() <= home.point.y + home.radius + 20) {
							// ��Ӫ�ظ�ֵ����ǰ�����Ӫ��
							cs = home;
							break;
						}
					}
					// ��Ӫ�ز�Ϊ�յ�ʱ��
					if (cs != null) {
						// ѭ����֮������Ӫ��
						for (Home cam : globe.home.another) {
							// ���������Ӫ����������Ӫ�ص�ʱ��
							if (cam == cs) {
								// IDRelation��ֵΪtrue������Ӫ���й�ϵ��
								isRelation = true;
							}
						}
					}
					// ������Ӫ��û�й�ϵʱ���˽����Ӫ�ظ�ֵΪ��
					if (!isRelation) {
						cs = null;
					}
					// ��Ӫ�غ��򶼲�Ϊ�յ�ʱ��
					if (cs != null && cs.globe == null) {
						// ����ֵΪ��Ӫ�ص���
						globe.point = cs.point;
						// �����Ӫ�ظ���Ϊ��굱ǰ��Ӫ��
						globe.home = cs;
					} else {
						// ���λ
						globe.point = new Point(e.getX() - mouseDownGlobeX, e.getY() - mouseDownGlobeY);

					}
					// �������Ϊ����Ӫ�ص�����
					globe.point = globe.home.point;
					// Ӫ�ص����ǵ�ǰ��
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
		frame.addMouseListener(l);// �������Ĳ����¼�
		frame.addMouseMotionListener(l);// �������Ļ����¼�
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
		// ���ô����С
		this.setSize(mainWidth, mainHeight);
		// ������ʾ
		this.setLocationRelativeTo(null);
		// ���ñ�����ɫ
		this.setBackground(Color.WHITE);
		// ��Ӵ���رհ�ť�����Լ�����
		this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				// �˳�����
				System.exit(0);
			}

		});
		// ʹ���ڲ��ɵ�����С
		this.setResizable(false);
		// ��ʾ����
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
