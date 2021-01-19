import java.awt.Color;
import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;

class MyThread extends Thread{
	Container c;
	boolean flag = false;
	MyThread(Container c){
		this.c = c;
	}
	void finish(){
		flag = true;
	}
	@Override
	public void run() {
		JLabel la;
		while(true){
			int x = (int)(Math.random()*c.getWidth());
			int y = (int)(Math.random()*c.getHeight());
			la = new JLabel("java");
			la.setSize(80, 30);
			la.setLocation(x, y);
			c.add(la);
			c.repaint();
			if(flag == true){
				c.removeAll();
				la = new JLabel("Finished");
				la.setForeground(Color.RED);
				la.setSize(80, 30);
				la.setLocation(100, 100);
				c.add(la);
				c.repaint();
				return;
			}
			try{
				sleep(300);
				
			}catch(InterruptedException e){
				return;
			}
		}
	}
	
}
public class ThreadFinishEx extends JFrame{
	ThreadFinishEx(){
		setTitle("ThreadFinishEx");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(null);

		MyThread th = new MyThread(c);
		th.start();
		c.addMouseListener(new MouseAdapter(){

			@Override
			public void mousePressed(MouseEvent e) {
				th.finish();
			}
			
		});
		
		setSize(500, 500);
		setVisible(true);
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ThreadFinishEx();
	}

}
