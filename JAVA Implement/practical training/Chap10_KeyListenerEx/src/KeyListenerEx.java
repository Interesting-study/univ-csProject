import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class KeyListenerEx extends JFrame{
	JLabel[] la;
	KeyListenerEx(){
		setTitle("KeyListenerEx");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		
		la = new JLabel[3];
		String[] name  = {"getKeyCode", "getKeyChar", "getKeyText"};
		for(int i=0; i<la.length; i++){
			la[i] = new JLabel(name[i]);
			la[i].setOpaque(true);
			la[i].setBackground(Color.YELLOW);
			c.add(la[i]);
		}
		c.addKeyListener(new KeyAdapter(){

			@Override
			public void keyPressed(KeyEvent e) {
				int keyCode = e.getKeyCode();
				char keyChar = e.getKeyChar();
				
				la[0].setText(Integer.toString(keyCode));
				la[1].setText(Character.toString(keyChar));
				la[2].setText(e.getKeyText(keyCode));
			}
			
		});
		c.setFocusable(true);
		c.requestFocus();
		
		setSize(500, 500);
		setVisible(true);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new KeyListenerEx();
	}

}
