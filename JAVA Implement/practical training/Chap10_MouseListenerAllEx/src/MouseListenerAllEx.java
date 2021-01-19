import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class MouseListenerAllEx extends JFrame{
	JLabel la;
	Container c;
	MouseListenerAllEx(){
		setTitle("MouseListenerAllEx");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		c = getContentPane();
		c.setLayout(new FlowLayout());
		
		la = new JLabel("No Mouse Event");
		c.add(la);
		
		MyMouseListener myListener = new MyMouseListener();
		c.addMouseListener(myListener);
		c.addMouseMotionListener(myListener);
		
		setSize(500, 500);
		setVisible(true);
	}
	class MyMouseListener extends MouseAdapter{

		@Override
		public void mouseDragged(MouseEvent e) {
			int x = e.getX();
			int y = e.getY();
			
			la.setText("mouseDragged (" + x + ", " + y + ")");
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			int x = e.getX();
			int y = e.getY();
			
			la.setText("mouseEntered (" + x + ", " + y + ")");
			c.setBackground(Color.CYAN);
		}

		@Override
		public void mouseExited(MouseEvent e) {
			int x = e.getX();
			int y = e.getY();
			
			la.setText("mouseExited (" + x + ", " + y + ")");
			c.setBackground(Color.gray);
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			int x = e.getX();
			int y = e.getY();
			
			la.setText("mouseMoved (" + x + ", " + y + ")");
		}

		@Override
		public void mousePressed(MouseEvent e) {
			int x = e.getX();
			int y = e.getY();
			
			la.setText("mousePressed (" + x + ", " + y + ")");
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			int x = e.getX();
			int y = e.getY();
			
			la.setText("mouseReleased (" + x + ", " + y + ")");
		}
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MouseListenerAllEx();
	}

}
