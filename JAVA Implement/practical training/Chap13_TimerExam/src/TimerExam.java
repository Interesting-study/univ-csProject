import java.awt.Container;//2
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
class MyThread extends Thread{
	JLabel la;
	boolean flag = false;
	MyThread(JLabel la){
		this.la = la;
	}
	void finish(){
		flag = true;
	}
	@Override
	public void run() {
		int n=0;
		while(true){
			la.setText(Integer.toString(n));
			n++;
			try{
				sleep(1000);
				if(flag == true)
					return;
			}catch(InterruptedException e){
				return;
			}
		}
	}	
}
public class TimerExam extends JFrame{
	TimerExam(){
		setTitle("Timer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout());

		JLabel la = new JLabel();
		la.setFont(new Font("Gothic", Font.BOLD, 80));
		c.add(la);
		
		JButton btn = new JButton("Kill");
		c.add(btn);
		MyThread th = new MyThread(la);
		th.start();
		btn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				//th.interrupt();
				th.finish();
				btn.setEnabled(false);
			}			
		});		
		setSize(500, 500);
		setVisible(true);		
	}
	public static void main(String[] args) {
		new TimerExam();
	}
}