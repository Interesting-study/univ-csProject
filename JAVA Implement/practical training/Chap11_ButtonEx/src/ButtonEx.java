import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingConstants;

public class ButtonEx extends JFrame{
	ButtonEx(){
		setTitle("ButtonEx");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(null);

		ImageIcon normal = new ImageIcon("images/normalIcon.gif");
		ImageIcon pressed = new ImageIcon("images/pressedIcon.gif");
		ImageIcon rollover = new ImageIcon("images/rolloverIcon.gif");
		
		JButton btn = new JButton("hello", normal);
		btn.setSize(300, 300);
		btn.setLocation(30, 30);
		btn.setHorizontalAlignment(SwingConstants.LEFT);
		btn.setVerticalAlignment(SwingConstants.TOP);
		
		btn.setPressedIcon(pressed);
		btn.setRolloverIcon(rollover);
		c.add(btn);
		
		
		setSize(500, 500);
		setVisible(true);
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ButtonEx();
	}

}
