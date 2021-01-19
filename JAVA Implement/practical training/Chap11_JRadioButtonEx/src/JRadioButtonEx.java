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

public class JRadioButtonEx extends JFrame{
	JRadioButton[] radio = new JRadioButton[3];
	ImageIcon[] icon = new ImageIcon[3];
	JLabel imgLa = new JLabel();
	JRadioButtonEx(){
		setTitle("");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		
		JPanel pane = new JPanel();
		pane.setBackground(Color.GRAY);
		c.add(pane, BorderLayout.NORTH);
		icon[0] = new ImageIcon("images/apple.jpg");
		icon[1] = new ImageIcon("images/pear.jpg");
		icon[2] = new ImageIcon("images/cherry.jpg");
		ButtonGroup g = new ButtonGroup();
		String[] name = {"사과", "배", "체리"};
		MyItemListener listener = new MyItemListener();
		for(int i=0; i<radio.length; i++){
			radio[i] = new JRadioButton(name[i]);
			g.add(radio[i]);
			pane.add(radio[i]);
			radio[i].addItemListener(listener);	
		}
		radio[1].setSelected(true);
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
				imgLa.setIcon(icon[0]);
			else if(radio[1].isSelected())
				imgLa.setIcon(icon[1]);
			else
				imgLa.setIcon(icon[2]);
		}
	}
	public static void main(String[] args) {
		new JRadioButtonEx();
	}

}
