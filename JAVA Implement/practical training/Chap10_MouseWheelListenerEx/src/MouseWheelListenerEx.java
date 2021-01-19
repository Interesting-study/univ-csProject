import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class MouseWheelListenerEx extends JFrame{
	MouseWheelListenerEx(){
		setTitle("MouseWheelListenerEx");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout());

		JLabel la = new JLabel("I love java");
		Font f = new Font("°íµñ",Font.BOLD, 30);
		la.setFont(f);		
		c.add(la);
		la.addMouseWheelListener(new MouseWheelListener(){

			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				JLabel la = (JLabel)e.getSource();
				Font f = la.getFont();
				int size = f.getSize();
				
				int n = e.getWheelRotation();
				if(n>0)
					la.setFont(new Font("°íµñ", Font.BOLD, size+5));
				else
					la.setFont(new Font("°íµñ", Font.BOLD, size-5));
			}
			
		});
		
		setSize(500, 500);
		setVisible(true);
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MouseWheelListenerEx();
	}

}
