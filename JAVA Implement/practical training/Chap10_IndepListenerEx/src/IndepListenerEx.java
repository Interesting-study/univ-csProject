import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
/*
class MyActionListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton)e.getSource();
		if(btn.getText().equals("Action"))
			btn.setText("액션");
		else
			btn.setText("Action");
	}//e는 이벤트 객체
	
}
*///독립리스너

public class IndepListenerEx extends JFrame{
	IndepListenerEx(){
		setTitle("Indep Listener Ex");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		
		JButton btn = new JButton("Action");
		c.add(btn);
		btn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				JButton btn = (JButton)e.getSource();
				if(btn.getText().equals("Action"))
					btn.setText("액션");
				else
					btn.setText("Action");
			}
			
		});//익명리스너(소스가 짧으면서 일회용일때 편리하다. -> 재활용이 안 되므로), 이너이면서 이름이 없다.
		
		setSize(300, 300);
		setVisible(true);
	}
	/*
	class MyActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton)e.getSource();
			if(btn.getText().equals("Action"))
				btn.setText("액션");
			else
				btn.setText("Action");
		}//e는 이벤트 객체
		
	}*///이너클래스 리스너(같은 클래스내 전역변수를 참조할 수도 있다.)
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new IndepListenerEx();
	}

}
