package MainScreen;

import java.awt.Color;       
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import FindMine.MineFrame;
import Galaga.GalagaGame;
import Game369.Game369;
import PackMan.Main;

public class MainScreen extends JFrame {
	
	ImageIcon mainImage = new ImageIcon("MainImg/visual_minigame1.gif");
	JButton[] b = new JButton[4];
	String[] gameName = {"369게임", "지뢰찾기", "슈팅게임", "팩맨" };
//	String[] Color_name = {"cyan", "yellow", "green", "pink"};
	JPanel backGroundP;
	JButton btn;
	
	public MainScreen(){
		setTitle("메인 화면");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		backGroundP = new JPanel(){
			public void paintComponent(Graphics g){
				g.drawImage(mainImage.getImage(), 0, 0, getWidth(), getHeight()
						, null);
				
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		
		backGroundP.setLayout(new FlowLayout());
		setBtn();
		this.add(backGroundP);
		
		setSize(850, 600);
		setVisible(true);
	}
	public void setBtn(){
		for(int i = 0 ; i<gameName.length ; i++	){
			b[i] = new JButton(gameName[i]);
			b[i].addActionListener(new myActionListener());
			backGroundP.add(b[i]);
		}
		b[0].setBackground(Color.cyan);
		b[1].setBackground(Color.YELLOW);
		b[2].setBackground(Color.green);
		b[3].setBackground(Color.pink);
	}
		
		
		
	public class myActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			btn = (JButton)e.getSource();
			if(btn == b[1]){
				new MineFrame();
				hide();
//				backGroundP.setVisible(false);
			}
			if(btn == b[0]){
				new Game369();
				hide();
			}
			if(btn == b[2]){
				//new GalagaGame();
			}
			if(btn == b[3] ){
				new Main();
				hide();
			}
		}
	}
	
	
	
	public static void main(String[] args) {
			new MainScreen();
			new GalagaGame();
	}
}

