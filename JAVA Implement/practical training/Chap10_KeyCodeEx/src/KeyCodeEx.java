import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;

class MyKeyListener extends KeyAdapter{
	Container c;
	JLabel la;
	MyKeyListener(Container c, JLabel la){
		this.c = c;
		this.la = la;
	}
	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		char keyChar = e.getKeyChar();
		
		la.setText(e.getKeyText(keyCode) + "가 입력되었음");
		if(keyCode == KeyEvent.VK_F1)
			c.setBackground(Color.GREEN);
		if(keyChar == '%')
			c.setBackground(Color.RED);
	}
	
}
public class KeyCodeEx extends JFrame{
	KeyCodeEx(){
		setTitle("KeyCodeEx");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		
		JLabel la = new JLabel();
		c.add(la);
		c.addKeyListener(new MyKeyListener(c, la));
		
		setSize(500, 500);
		setVisible(true);

		c.setFocusable(true);
		c.requestFocus();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new KeyCodeEx();
	}

}
