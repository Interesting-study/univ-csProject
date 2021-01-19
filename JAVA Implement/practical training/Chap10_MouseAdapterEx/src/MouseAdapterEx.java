import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class MouseAdapterEx extends JFrame{
	JLabel la;
	MouseAdapterEx(){
		setTitle("Mouse Event Ex");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(null);
		
		la = new JLabel("Hello");
		la.setSize(80, 30);
		la.setLocation(100, 100);
		c.add(la);
		c.addMouseListener(new MouseAdapter(){

			@Override
			public void mousePressed(MouseEvent e) {
				int x = e.getX();
				int y= e.getY();
				
				la.setLocation(x, y);
			}
		});
		setSize(500, 500);
		setVisible(true);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MouseAdapterEx();
	}

}
