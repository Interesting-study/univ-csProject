import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SliderColor extends JFrame{
	JSlider[] s = new JSlider[3];
	JLabel la = new JLabel("Color");
	
	SliderColor(){
		setTitle("SliderColor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout());

		for(int i=0; i<s.length;i++){
			s[i] = new JSlider(JSlider.HORIZONTAL, 0, 255, 128);
			s[i].setPaintTicks(true);
			s[i].setPaintLabels(true);
			s[i].setPaintTrack(true);
			s[i].setMajorTickSpacing(50);
			s[i].setMinorTickSpacing(10);
			s[i].addChangeListener(new MyChangeListener());
			c.add(s[i]);
		}
		s[0].setForeground(Color.red);
		s[1].setForeground(Color.green);
		s[2].setForeground(Color.blue);
		
		int r = s[0].getValue();
		int g = s[1].getValue();
		int b = s[2].getValue();
		
		la.setOpaque(true);
		la.setBackground(new Color(r,g,b));
		c.add(la);
		
		setSize(250, 500);
		setVisible(true);
		
	}
	class MyChangeListener implements ChangeListener{

		@Override
		public void stateChanged(ChangeEvent e) {
			int r = s[0].getValue();
			int g = s[1].getValue();
			int b = s[2].getValue();
			
			la.setBackground(new Color(r,g,b));
		}
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new SliderColor();
	}

}
