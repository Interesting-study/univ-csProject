import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MyFrame extends JFrame{
	MyFrame(){
		setTitle("MyFrame");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container p = getContentPane();
		p.setLayout(new FlowLayout());
		JButton b = new JButton("Action");
		p.add(b);
		b.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				JButton b = (JButton)e.getSource();
				if(b.getText().equals("Action"))
					b.setText("¾×¼Ç");
				else
					b.setText("Action");	
			}		
		});
		setSize(300, 300);
		setVisible(true);		
	}
	public static void main(String[] args) {
		new MyFrame();
	}
}