import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class CheckBoxItemEventEx extends JFrame{
	JCheckBox[] chk = new JCheckBox[3];
	JLabel sumla = new JLabel();
	int sum = 0;
	
	CheckBoxItemEventEx(){
		setTitle("CheckBoxItemEventEx");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout());

		String[] name = {"사과", "배", "체리"};
		c.add(new JLabel("사과 100원, 배 500원, 체리 20000원"));
		
		MyItemListener listener = new MyItemListener();
		for(int i=0; i<chk.length; i++){
			chk[i] = new JCheckBox(name[i]);
			chk[i].setBorderPainted(true);
			c.add(chk[i]);
			chk[i].addItemListener(listener);
		}
		sumla.setText("현재 가격은 " + sum + "원 입니다.");
		c.add(sumla);
		setSize(250, 300);
		setVisible(true);
		
	}
	class MyItemListener implements ItemListener{

		@Override
		public void itemStateChanged(ItemEvent e) {
			if(e.getStateChange() == ItemEvent.SELECTED){
				if(e.getItem() == chk[0])
					sum += 100;
				else if(e.getItem() == chk[1])
					sum += 500;
				else
					sum += 20000;
			}
			else{
				if(e.getItem() == chk[0])
					sum -= 100;
				else if(e.getItem() == chk[1])
					sum -= 500;
				else
					sum -= 20000;
			}
			sumla.setText("현재 가격은 " + sum + "원 입니다.");
		}
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new CheckBoxItemEventEx();
	}

}
