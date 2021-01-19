import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class FinalTest extends JFrame{
	Container c;
	public MenuScreen menu;
	public CatList CL;
	public PlayScreen ps;
	Timer th_timer;
	CatMove th_cat;
	int idxImages;
	
	FinalTest(){
		setTitle("201532005 ±èµµÈñ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		c = getContentPane();
		c.setLayout(new BorderLayout());
		
		menu = new MenuScreen();
		CL = new CatList();
		ps = new PlayScreen();
		
		CL.getCatList().addListSelectionListener(new ListSelectionListener(){

			@Override
			public void valueChanged(ListSelectionEvent e) {
				int idxImages;
				idxImages = CL.getCatList().getSelectedIndex();
				CL.setIdxImg(idxImages);
				menu.getStartButton().setEnabled(true);
				menu.getStopButton().setEnabled(true);
				menu.getLaTimer().setText("0");
			}
			
		});
		
		menu.getStartButton().addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				init();
			}
			
		});
		menu.getStopButton().addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				stop();
				menu.getStartButton().setEnabled(false);
				menu.getStopButton().setEnabled(false);
			}
			
		});
		
		ps.getLaCat().addMouseListener(new MouseAdapter(){

			@Override
			public void mousePressed(MouseEvent arg0) {
				int score = 0;
				score = Integer.parseInt(menu.getLaScore().getText());
				switch(menu.getStageLevel()){
				case 1:
					score += 1;
					menu.getLaScore().setText(Integer.toString(score));
					break;
				case 2:
					score +=2;
					menu.getLaScore().setText(Integer.toString(score));
					break;
				case 3:	
					score +=3;
					menu.getLaScore().setText(Integer.toString(score));
					break;
					
				}
				
			}
			
		});
		
		setSize(1000, 400);
		setVisible(true);
	}
	void init(){
		stop();
		
		th_timer = new Timer();
		th_timer.la_timer = menu.getLaTimer();
		th_timer.start();
		
		th_cat = new CatMove();
		th_cat.start();
		
		menu.getLaScore().setText("0");
		
		ps.getLaCat().setIcon(CL.getCatIcon()[CL.getIdxImg()]);
		//la_cat.setLocation(500, 300);
	}
	void stop(){
		if(th_timer != null && th_cat.isAlive())
			th_cat.interrupt();
		if(th_cat != null && th_timer.isAlive())
			th_timer.interrupt();
	}
	class Timer extends Thread{
		private JLabel la_timer;
		@Override
		public void run() {
			int time = 0;
			while(true){
				try {
					sleep(1000);
					time++;
					la_timer.setText(Integer.toString(time));
				} catch (InterruptedException e) {
					return;
				}
			}
		}
		
	}
	class CatMove extends Thread{

		@Override
		public void run() {
			int x = 0;
			int y = 0;
			int speed = 1000;
			int level = menu.getStageLevel();
			
			switch(level){
			case 1:
				speed = 1000;
				break;
			case 2:
				speed = 700;
				break;
			case 3:
				speed = 500;
				break;
			}
			
			while(true){
				try {
					sleep(speed);
					x = (int)(Math.random()*(ps.getCentreWidth() - ps.getImgWidth()));
					y = (int)(Math.random()*(ps.getCentreHeight() - ps.getImgHeight()));
					ps.getLaCat().setLocation(x, y);
				} catch (InterruptedException e) {
					return;
				}
			}
		}
		
	}
	class MenuScreen extends JPanel{
		private ButtonGroup bg;
		private JRadioButton[] level;
		private JLabel la_timer = new JLabel(" ");
		private JButton start;
		private JButton stop;
		private JLabel la_score = new JLabel("0");
		private int stageLevel = 1;
		private int score = 0;
		
		public JButton getStartButton(){
			return start;
		}
		public JButton getStopButton(){
			return stop;
		}
		public JLabel getLaTimer(){
			return la_timer;
		}
		public JLabel getLaScore(){
			return la_score;
		}
		public int getStageLevel(){
			return stageLevel;
		}
		public int getScore(){
			return score;
		}
		MenuScreen(){
			JPanel panel = new JPanel();
			panel.setBackground(Color.black);
			bg = new ButtonGroup();
			level = new JRadioButton[3];
			MyItemListener listener = new MyItemListener();
			for(int i = 0; i<level.length; i++){
				level[i] = new JRadioButton(Integer.toString(i+1));
				level[i].addItemListener(listener);
				bg.add(level[i]);
				panel.add(level[i]);
			}
			level[0].setSelected(true);
			
			la_timer.setFont(new Font("°íµñ", Font.BOLD, 30));
			la_timer.setForeground(Color.WHITE);
			panel.add(la_timer);
			
			start = new JButton("start");
			start.setEnabled(false);
			
			panel.add(start);
			
			stop = new JButton("stop");
			stop.setEnabled(false);
			
			panel.add(stop);
			
			JLabel scoreTxt = new JLabel("score: ");
			scoreTxt.setForeground(Color.WHITE);
			panel.add(scoreTxt);
			
			la_score.setForeground(Color.WHITE);
			panel.add(la_score);
			
			c.add(panel, BorderLayout.NORTH);
			setBackground(Color.black);
		}
	}
	class CatList extends JPanel{
		private JList catList = new JList();
		private ImageIcon[] catIcon = new ImageIcon[3];
		private int idxImg;
		CatList(){
			JPanel panel = new JPanel();
			for(int i = 0; i<catIcon.length; i++){
				catIcon[i] = new ImageIcon("images/cat" + (i+1) + ".jpg");
			}
			catList.setListData(catIcon);
			
			panel.add(catList);
			c.add(panel, BorderLayout.WEST);
		}
		public JList getCatList(){
			return catList;
		}
		public int getIdxImg(){
			return idxImg;
		}
		public ImageIcon[] getCatIcon(){
			return catIcon;
		}
		public void setIdxImg(int idxImg){
			this.idxImg = idxImg;
		}
		
	}
	class PlayScreen extends JPanel{
		private int imgWidth;
		private int imgHeight;
		private int centreWidth = 850;
		private int centreHeight = 350;
		private JLabel la_cat = new JLabel();
		
		PlayScreen(){
			JPanel panel = new JPanel();
			panel.add(la_cat);
			c.add(panel, BorderLayout.CENTER);
		}
		public int getImgWidth(){
			return imgWidth;
		}
		public int getImgHeight(){
			return imgHeight;
		}
		public int getCentreWidth(){
			return centreWidth;
		}
		public int getCentreHeight(){
			return centreHeight;
		}
		public JLabel getLaCat(){
			return la_cat;
		}
	}
	
	class MyItemListener implements ItemListener{

		@Override
		public void itemStateChanged(ItemEvent e) {
			//JRadioButton b = (JRadioButton)e.getSource();
			//menu.stageLevel = Integer.parseInt(b.getText());
		}
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new FinalTest();
	}

}
