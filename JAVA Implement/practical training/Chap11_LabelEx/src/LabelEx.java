import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class LabelEx extends JFrame{
	LabelEx(){
		setTitle("LabelEx ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout());

		JLabel txt_la = new JLabel("사랑합니다");
		ImageIcon beauty = new ImageIcon("images/beauty.jpg");
		JLabel img_la = new JLabel(beauty);
		ImageIcon normal = new ImageIcon("images/normalIcon.gif");
		JLabel normal_la =new JLabel("여기를 눌러주세요", normal, SwingConstants.CENTER);
		c.add(txt_la);
		c.add(img_la);
		c.add(normal_la);
		
		setSize(400, 600);
		setVisible(true);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new LabelEx();
	}

}
