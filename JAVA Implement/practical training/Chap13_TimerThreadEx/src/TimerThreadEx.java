import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

class MyThread2 implements Runnable{
	JLabel la;
	MyThread2(JLabel la){
		this.la = la;
	}
	@Override
	public void run() {
		int n=0;
		while(true){
			la.setText(Integer.toString(n));
			n++;
			try{
				Thread.sleep(500);
			}catch(InterruptedException e){
				return;
			}
		}
	}
	
}
class MyThread extends Thread{
	JLabel la;
	boolean flag = false;
	void finish(){
		flag = true;
	}
	MyThread(JLabel la){
		this.la = la;
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
public class TimerThreadEx extends JFrame{
	TimerThreadEx(){
		setTitle("TimerThreadEx");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		
		JButton btn = new JButton("Stop");
		
		JLabel la = new JLabel();
		la.setFont(new Font("Gothic", Font.BOLD, 80));
		c.add(la);
		
		JLabel la2 = new JLabel();
		la2.setFont(new Font("Gothic", Font.BOLD, 80));
		c.add(la2);
		
		MyThread th = new MyThread(la);
		th.start();
		Thread th2 = new Thread(new MyThread2(la2));
		th2.start();
		btn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				//th.interrupt();
				th.finish();
				btn.setEnabled(false);
			}//죽은거는 못 살린다.
			
		});
		c.add(btn);
		setSize(300, 300);
		setVisible(true);
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new TimerThreadEx();
	}

}
