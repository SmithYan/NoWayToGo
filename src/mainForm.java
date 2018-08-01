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
		System.out.print("游戏开始");
		frame = new mainForm();
		frame.launch();
	}

	public void launch() {
		g = this.getGraphics();
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
		
		globe.paint(g);
	}

}
