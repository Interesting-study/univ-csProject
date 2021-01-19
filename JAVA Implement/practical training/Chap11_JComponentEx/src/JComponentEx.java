import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class JComponentEx extends JFrame{
	JComponentEx(){
		setTitle("JComponentEx");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout());

		JButton b1 = new JButton("Megenta/Yellow Button");
		JButton b2 = new JButton("disabled Button");
		JButton b3 = new JButton("getX(), getY()");
		
		b1.setBackground(Color.yellow);
		b1.setForeground(Color.MAGENTA);
		b1.setFont(new Font("Arial", Font.ITALIC, 20));
		
		b2.setEnabled(false);
		
		b3.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				JButton b = (JButton)e.getSource();
				JComponentEx f = (JComponentEx)b.getTopLevelAncestor();
				f.setTitle(b.getX() + ", " + b.getY());
			}
			
		});
		c.add(b1);
		c.add(b2);
		c.add(b3);
		
		setSize(500, 500);
		setVisible(true);
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new JComponentEx();
	}

}
