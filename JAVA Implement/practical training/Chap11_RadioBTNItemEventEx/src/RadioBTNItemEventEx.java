import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

public class RadioBTNItemEventEx extends JFrame{
	JRadioButton[] radio = new JRadioButton[3];
	ImageIcon[] image = new ImageIcon[3];
	JLabel imgLa = new JLabel();
	
	RadioBTNItemEventEx(){
		setTitle("RadioBTNItemEventEx");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new BorderLayout());

		JPanel radioPane = new JPanel();
		radioPane.setBackground(Color.GRAY);
		c.add(radioPane, BorderLayout.NORTH);
		
		image[0] = new ImageIcon("images/apple.jpg");
		image[1] = new ImageIcon("images/pear.jpg");
		image[2] = new ImageIcon("images/cherry.jpg");
		
		String[] name = {"사과", "배", "체리"};
		ButtonGroup g = new ButtonGroup();
		MyItemListener listener = new MyItemListener();
		for(int i=0; i<radio.length; i++){
			radio[i] = new JRadioButton(name[i]);
			g.add(radio[i]);
			radioPane.add(radio[i]);
			radio[i].addItemListener(listener);
		}
		radio[2].setSelected(true);
		imgLa.setHorizontalAlignment(SwingConstants.CENTER);
		c.add(imgLa, BorderLayout.CENTER);
		
		
		setSize(500, 500);
		setVisible(true);
		
	}
	class MyItemListener implements ItemListener{

		@Override
		public void itemStateChanged(ItemEvent e) {
			if(e.getStateChange() == ItemEvent.DESELECTED)
				return;
			if(radio[0].isSelected())
				imgLa.setIcon(image[0]);
			else if(radio[1].isSelected())
				imgLa.setIcon(image[1]);
			else
				imgLa.setIcon(image[2]);
		}
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new RadioBTNItemEventEx();
	}

}
