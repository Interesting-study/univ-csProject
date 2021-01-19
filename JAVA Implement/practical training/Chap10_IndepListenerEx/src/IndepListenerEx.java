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
			btn.setText("�׼�");
		else
			btn.setText("Action");
	}//e�� �̺�Ʈ ��ü
	
}
*///����������

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
					btn.setText("�׼�");
				else
					btn.setText("Action");
			}
			
		});//�͸�����(�ҽ��� ª���鼭 ��ȸ���϶� ���ϴ�. -> ��Ȱ���� �� �ǹǷ�), �̳��̸鼭 �̸��� ����.
		
		setSize(300, 300);
		setVisible(true);
	}
	/*
	class MyActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton)e.getSource();
			if(btn.getText().equals("Action"))
				btn.setText("�׼�");
			else
				btn.setText("Action");
		}//e�� �̺�Ʈ ��ü
		
	}*///�̳�Ŭ���� ������(���� Ŭ������ ���������� ������ ���� �ִ�.)
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new IndepListenerEx();
	}

}
