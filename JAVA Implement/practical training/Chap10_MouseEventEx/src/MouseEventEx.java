import java.awt.Container;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JLabel;

class MyMouseListener implements MouseListener{
	JLabel la;
	MyMouseListener(JLabel la){
		this.la = la;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y= e.getY();
		
		la.setLocation(x, y);
	}

	@Override
	public void mouseReleased(MouseEvent e) {}
	
}
public class MouseEventEx extends JFrame{
	JLabel la;
	MouseEventEx(){
		setTitle("Mouse Event Ex");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(null);
		
		la = new JLabel("Hello");
		la.setSize(80, 30);
		la.setLocation(100, 100);
		c.add(la);
		c.addMouseListener(new MyMouseListener(la));
		/*
		c.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {}

			@Override
			public void mouseEntered(MouseEvent arg0) {}

			@Override
			public void mouseExited(MouseEvent arg0) {}

			@Override
			public void mousePressed(MouseEvent e) {
				int x = e.getX();
				int y= e.getY();
				
				la.setLocation(x, y);
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {}
			
		});*///생성자 소속x, 전역으로 선언하면 다 참조할 수 있다.
		
		setSize(500, 500);
		setVisible(true);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MouseEventEx();
	}

}
