import java.awt.Container;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;


public class FlyingTextEx extends JFrame{
	JLabel la;
	FlyingTextEx(){
		setTitle("FlyingTextEx");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(null);
		
		la = new JLabel("hello");
		la.setSize(80, 30);
		la.setLocation(50, 50);
		c.add(la);
		c.addKeyListener(new KeyAdapter(){

			@Override
			public void keyPressed(KeyEvent e) {
				int keyCode = e.getKeyCode();
				
				switch(keyCode){
				case KeyEvent.VK_UP :
					la.setLocation(la.getX(), la.getY()-10 );
					break;
				case KeyEvent.VK_DOWN :
					la.setLocation(la.getX(), la.getY()+10 );
					break;
				case KeyEvent.VK_LEFT :
					la.setLocation(la.getX()-10, la.getY() );
					break;
				case KeyEvent.VK_RIGHT : 
					la.setLocation(la.getX()+10, la.getY() );
					break;
				}
			}
			
		});
		
		
		c.setFocusable(true);
		c.requestFocus();
		setSize(500, 500);
		setVisible(true);

		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new FlyingTextEx();
	}

}
