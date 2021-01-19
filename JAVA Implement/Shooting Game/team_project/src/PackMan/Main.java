package PackMan;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

import MainScreen.MainScreen;


public class Main {
	static JFrame pmain = new MainS();
//	public static void main(String[] args) {
//		 pmain = new MainScreen();
//	}
}

class MainS extends JFrame {
	private int height=320, width=430,sx=100,sy=100;//처음 화면 나올떄 모니터 기준(sx,sy)창 띄워지는거
	private ImageIcon bg = new ImageIcon("PackImg/main.png");
	
	private static JLabel jl;
	private int status=0 , x=0 , y = 100;//첫화면 팩맨 위치임
	private static ImageIcon[] pac_effect;
	
	private class Effect extends Thread{		
		public void run(){
			try{
				while(true){
					sleep(100);
					status++;
					status %= 2; //어떤 이미지 가져올지 정하는거
					x+=20; // 팩맨이 20 픽셀씩 이동하는거
					if(x > 530) 
						x = 0;
					jl.setIcon(pac_effect[status]);
					jl.setLocation(x, y);
				}
			} catch(InterruptedException e) { System.out.println("effect end");}
		}
	}
	public static Effect e_thread;
	
	public MainS(){
		setBounds(sx,sy,sx+width,sy+height); //setSize+setLocation
		setTitle("Pac man!!");
		setLayout(null);
		
		// 화면 이펙트
		pac_effect = new ImageIcon[2];
		pac_effect[0] = new ImageIcon("PackImg/pack1.jpg");
		pac_effect[1] = new ImageIcon("PackImg/pack2.jpg");
		jl = new JLabel(pac_effect[status]);
		jl.setBounds(x,y,50,50);
		jl.setIcon(pac_effect[status]);
		add(jl);
		e_thread = new Effect();
		e_thread.start();
		
		addWindowListener(new WindowAdapter(){
			@Override
			public void windowActivated(WindowEvent arg0) {
				super.windowActivated(arg0);
				if(e_thread.isInterrupted() || !e_thread.isAlive()){
					e_thread = new Effect();					
					e_thread.start();
				}
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				super.windowDeactivated(e);
				if(e_thread.isAlive())		
					e_thread.interrupt();//스레드 강제 종료
			}
		});
		JRadioButton jb_easy = new JRadioButton("Easy");
		JRadioButton jb_normal = new JRadioButton("Normal");
		jb_normal.setSelected(true);
		JRadioButton jb_hard = new JRadioButton("Hard");
		JRadioButton jb_nightmare = new JRadioButton("Nightmare");
		ButtonGroup g = new ButtonGroup(); // 버튼 그룹 생성
		g.add(jb_easy);
		g.add(jb_normal);
		g.add(jb_hard);
		g.add(jb_nightmare);
		
		int x = 330, y = 220;
		
		jb_easy.setBounds(x,y,70,30);
		jb_easy.setBackground(Color.BLACK);
		jb_easy.setForeground(Color.WHITE);
		add(jb_easy);
		
		jb_normal.setBounds(x,y+30,70,30);
		jb_normal.setBackground(Color.BLACK);
		jb_normal.setForeground(Color.WHITE);
		add(jb_normal);
		
		jb_hard.setBounds(x,y+60,70,30);
		jb_hard.setBackground(Color.BLACK);
		jb_hard.setForeground(Color.WHITE);
		add(jb_hard);
		
		jb_nightmare.setBounds(x,y+90,150,30);
		jb_nightmare.setBackground(Color.BLACK);
		jb_nightmare.setForeground(Color.WHITE);
		add(jb_nightmare);
		
		LevelSelectionListener lil = new LevelSelectionListener();
		jb_easy.addItemListener(lil);
		jb_normal.addItemListener(lil);
		jb_hard.addItemListener(lil);
		jb_nightmare.addItemListener(lil);
		
		// start 버튼
		JButton btnStart = new JButton("START");
		btnStart.setBounds(180,150+80,100,50);
		
		btnStart.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event) {
				new pacman_1(enemy_speed,1);
				Main.pmain.setVisible(false);
			}
		});
		add(btnStart);
		
		// 배경화면
		JLabel bg_pic = new JLabel(bg);
		bg_pic.setBounds(0,0,530, 384);
		add(bg_pic);
		
		super.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
				dispose();
				new MainScreen();
			}
			
		});
		
		setVisible(true);
	}
	int enemy_speed = 500;
	class LevelSelectionListener implements ItemListener {
          public void itemStateChanged(ItemEvent e) {
        	  AbstractButton sel = (AbstractButton)e.getItemSelectable();
        	  if(e.getStateChange() == ItemEvent.SELECTED) {
        		  if ( sel.getText().equals("Easy") ) {
        			  enemy_speed = 500;
        		  }
        		  else if ( sel.getText().equals("Normal") ) {
        			  enemy_speed = 300;
        		  }
        		  else if ( sel.getText().equals("Hard") ) {
        			  enemy_speed = 150;
        		  }
        		  else if ( sel.getText().equals("Nightmare") ) {
        			  enemy_speed = 100;
        		  }
        	  }
          }
	}
}
