import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class NullContainerEx extends JFrame{
	NullContainerEx(){
		setTitle("Null Container");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		
		c.setLayout(null);
		JLabel l = new JLabel("Hello!!!");
		l.setLocation(200, 100);
		l.setSize(50, 30);
		c.add(l);
		
		for(int i=1; i<10; i++){
			JButton b = new JButton(Integer.toString(i));
			b.setSize(50, 30);
			b.setLocation(i*15, i*15);
			c.add(b);
		}
		setSize(300, 500);
		setVisible(true);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new NullContainerEx();
	}

}
