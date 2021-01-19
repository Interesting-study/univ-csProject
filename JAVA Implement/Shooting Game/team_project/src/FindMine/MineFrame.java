package FindMine;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import MainScreen.MainScreen;


public class MineFrame extends JFrame {
	public int lv = Level.mid ;	//런타임시 기본시작레벨 중급
	MineGUI mineGUI;

	//메뉴..
	JMenuBar menuBar;
	JMenu fileMenu;
	JMenuItem newItem;
	JMenuItem rowLvItem;
	JMenuItem midLvItem;
	JMenuItem highLvItem;
	JMenuItem bestTimeItem;
	JMenuItem exitItem;
	
	JMenu helpMenu;
	JMenuItem helpItem;
	
	String endString = "게임 끄실?";
	String helpString = "윈도우 기본 지뢰찾기 게임과 게임방식이 동일합니다.";
	
	public MineFrame(){
		setTitle("지뢰 찾기");
		initMenu();
		
		super.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
				dispose();
				new MainScreen();
			}
			
		});
		this.mineGUI = new MineGUI(lv);
		setContentPane(mineGUI);
		pack();

		setResizable(false);
		setVisible(true);
		
	}
	
	class newActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			changeGame();
		}
		
	}
	
	class levelActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource() == rowLvItem)
				changeLevel(Level.row);
			else if(e.getSource() == midLvItem)
				changeLevel(Level.mid);
			else if(e.getSource() == highLvItem)
				changeLevel(Level.high);
		}
	}
	class bestTimeActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			bestTimeRecord();
		}
		public void bestTimeRecord(){
			String score = null;
			File file[]	= new File[3];
			file[0] = new File("Row_Score.txt");
			file[1] = new File("Mid_Score.txt");
			file[2] = new File("High_Score.txt");
			String title[] = {"초급", "중급", "고급"};
			score = bestTimeRead(file[0], title[0]) + bestTimeRead(file[1], title[1]) + bestTimeRead(file[2], title[2]);
			JOptionPane.showMessageDialog(MineFrame.this, score,"지뢰찾기 최고기록", JOptionPane.INFORMATION_MESSAGE);
		}
		
		public String bestTimeRead(File f, String lv){
			String text = null;
			String score = null;
			if(f.exists()){
				try{
					BufferedReader reader = new BufferedReader(new FileReader(f));
					text = reader.readLine();
					reader.close();
					if(text==null)
						score = lv + " " + "999초" + " 알수없음"  ;
					else{
						String[] tmp = text.split("/");
						score = lv + " " + tmp[0] + "초 " + tmp[1];
					}
				}catch(IOException e){
					System.out.println(e.toString());
				}
			}else {
				try{
					FileWriter write = new FileWriter(f);
					
					write.close();
					score = lv + " " + "999초" + " 알수없음" ;;
				}catch(IOException e){
					System.out.println(e.toString());
				}
			
			}
			return score + "\n";
		}	
		
	}
	class exitActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			int result;
			result = JOptionPane.showConfirmDialog(MineFrame.this, endString, "Game End",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE	);
			if(result == JOptionPane.YES_OPTION)
				System.exit(0);
	
		}
		
	}
	class dialogActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource() == helpItem)
				JOptionPane.showMessageDialog(MineFrame.this, helpString, "Help",JOptionPane.INFORMATION_MESSAGE);
			
		}
		
	}
	//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ내부클래스여기까지 절취선 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
	
	public void initMenu(){
		menuBar = new JMenuBar();
		fileMenu = new JMenu("게임");
		newItem = new JMenuItem("새 게임");
		rowLvItem = new JMenuItem("초급");
		midLvItem = new JMenuItem("중급");
		highLvItem = new JMenuItem("고급");
		bestTimeItem = new JMenuItem("최고 기록");
		exitItem = new JMenuItem("끝내기");
		
		newItem.addActionListener(new newActionListener());
		rowLvItem.addActionListener(new levelActionListener());
		midLvItem.addActionListener(new levelActionListener());
		highLvItem.addActionListener(new levelActionListener());
		bestTimeItem.addActionListener(new bestTimeActionListener());
		exitItem.addActionListener(new exitActionListener());
		
		fileMenu.add(newItem);
		fileMenu.addSeparator();
		fileMenu.add(rowLvItem);
		fileMenu.add(midLvItem);
		fileMenu.add(highLvItem);
		fileMenu.addSeparator();
		fileMenu.add(bestTimeItem);
		fileMenu.add(exitItem);
		
		menuBar.add(fileMenu);
		
		helpMenu = new JMenu("도움말");
		helpItem = new JMenuItem("HELP");
		
		helpMenu.add(helpItem);
		
		menuBar.add(helpMenu);
		
		helpItem.addActionListener(new dialogActionListener());
		
		
		setJMenuBar(menuBar);
	}
	
	public void changeLevel(int lv){
		this.lv = lv;
		changeGame();
	}
	public void changeGame(){
		remove(mineGUI);
		mineGUI = new MineGUI(lv);
		setContentPane(mineGUI);
		pack();
		
		setResizable(false);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new MineFrame();
	}
}
