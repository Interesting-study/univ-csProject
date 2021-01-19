import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;

public class ComboActionEx extends JFrame{
	ComboActionEx(){
		setTitle("ComboActionEx");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		String[] name = {"사과", "배", "체리", "바나나", "망고"};
		ImageIcon[] image = new ImageIcon[name.length];
		image[0] = new ImageIcon("images/apple.jpg");
		image[1] = new ImageIcon("images/pear.jpg");
		image[2] = new ImageIcon("images/cherry.jpg");
		image[3] = new ImageIcon("images/banana.jpg");
		image[4] = new ImageIcon("images/mango.jpg");
		
		JComboBox<String> combo = new JComboBox<String>(name);

		setSize(500, 500);
		setVisible(true);
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ComboActionEx();
	}

}
