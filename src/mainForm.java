import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class mainForm extends Frame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static int mainWidth = 800;
	public static int mainHeight = 825;
	public static mainForm frame = null;
	Globe globe = new Globe();
	Graphics g;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.print("��Ϸ��ʼ");
		frame = new mainForm();
		frame.launch();
	}

	public void launch() {
		g = this.getGraphics();
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
		
		globe.paint(g);
	}

}
