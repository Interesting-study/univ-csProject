import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

class MyLabel extends JLabel implements Runnable{
	long delay;
	
	MyLabel(String name, long delay){
		super(name);
		this.delay = delay;
		setOpaque(true);
		Thread th = new Thread(this);
		th.start();
	}

	@Override
	public void run() {
		int n =0;
		while(true){
			if(n == 0)
				setBackground(Color.GREEN);
			else
				setBackground(Color.YELLOW);
			if(n == 0)
				n = 1;
			else
				n =0;
			try{
				Thread.sleep(delay);
			}catch(InterruptedException e){
				return ;
			}
		}
		
	}
	
}
public class FlikeringEx extends JFrame{
	FlikeringEx(){
		setTitle("FlikeringEx");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout());

		MyLabel la1 = new MyLabel("±ô¹Ú", 500);
		JLabel la2 = new JLabel("¾È±ô¹Ú");
		MyLabel la3 = new MyLabel("±ô¹Ú", 300);
		
		c.add(la1);
		c.add(la2);
		c.add(la3);
		
		setSize(300, 300);
		setVisible(true);
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(Thread.currentThread().getId());
		System.out.println(Thread.currentThread().getName());
		System.out.println(Thread.currentThread().getPriority());
		System.out.println(Thread.currentThread().getState());
		
		new FlikeringEx();
	}

}
