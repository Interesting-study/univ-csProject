import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFrame;

public class BoaderLayoutEx extends JFrame{
	BoaderLayoutEx(){
		setTitle("Boader Layout");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new BorderLayout(30, 40));
		
		c.add(new JButton("add"), BorderLayout.NORTH);
		c.add(new JButton("sub"), BorderLayout.SOUTH);
		c.add(new JButton("mul"), BorderLayout.WEST);
		c.add(new JButton("div"), BorderLayout.EAST);
		c.add(new JButton("calc"), BorderLayout.CENTER);
		
		setSize(300, 300);
		setVisible(true);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new BoaderLayoutEx();
	}

}
