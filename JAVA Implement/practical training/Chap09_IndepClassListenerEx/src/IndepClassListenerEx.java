import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class IndepClassListenerEx extends JFrame{
	IndepClassListenerEx(){
		setTitle("IndepClassListenerEx");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		
		JButton btn = new JButton("Action");
		c.add(btn);
		btn.addActionListener(new MyActionListener());
		
		setSize(300,300);
		setVisible(true);
	}
	public static void main(String[] args) {
		new IndepClassListenerEx();
	}
}
//���� ������ Ŭ����
class MyActionListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton)e.getSource();
		if(b.getText().equals("Action"))
			b.setText("�׼�");
		else
			b.setText("Action");
		
	}
	
}










