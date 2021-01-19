package PackMan;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


 public class pacman_1 extends JFrame implements WindowListener {
	boolean crushed, win;
	static pacman_1 pacmanboard;
	final ImageIcon successIcon = new ImageIcon("PackImg/successIcon.png");
	final ImageIcon smallDot = new ImageIcon("PackImg/smallDot.png");
	final ImageIcon bigDot = new ImageIcon("PackImg/bigDot.png");
	final ImageIcon wall = new ImageIcon("PackImg/wall.png");
	final ImageIcon enemy = new ImageIcon("PackImg/enemy.png");
	final ImageIcon pacman = new ImageIcon("PackImg/pacman.png");
	final ImageIcon empty = new ImageIcon("PackImg/empty.png");
	
	final JButton button = new JButton(successIcon);
	final CardLayout card = new CardLayout();
	
	private static Random random;
	private static int pacmanH, pacmanW, enemyH, enemyW, numOfDot, where, start;
	private static int fieldMin=0, fieldMax=13;

	private static final int FRAME_WIDTH = 690;
	private static final int FRAME_HEIGHT = 640;
	private static int enemy_speed = 500;
	final JLabel[][] f = new JLabel[14][14];
	
	Clip clip;  //.wav ���� ��������� clip
	Thread thread;  //�̵����� �������� ������

	String path = pacman_1.class.getResource("").getPath();
	
	public int chk = 0; //�̵��Ҷ� �̵����� ���� �ִ��� �Ǵ��ϴº���
	public int point = 0;  //���̸� ���������� point���
	int sel = 1; // �Ѹ� �̹����� ��ȯ�ϱ����� ���º���
	
	JLabel win_la = new JLabel(new ImageIcon("PackImg/WIN.png"));
	JLabel defeated_la = new JLabel(new ImageIcon("PackImg/DEFEATED.png"));
	
	//JLabel sc_la = new JLabel("���� ���̼�" + point);
	
	Enemy en;
	ResultEffect de;
	JButton btnTotitle;
	//JButton sc_la;
	
	pacman_1(int enemyspeed, int stage){
		pacmanboard = this;
		
		enemy_speed = enemyspeed;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBackground(Color.WHITE);
		
		
		btnTotitle = new JButton("Ÿ��Ʋ�� ���ư���");
		btnTotitle.setBounds(295,500,0,0);
		btnTotitle.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event) {
				pacmanboard.dispose();//�����ӿ��� �ٸ� ������ �ҷ����� ������ �ٴ����� �ȵǴϱ�
			}
		});
		add(btnTotitle);
		
		/*JButton sc_la = new JButton("���� ���̼�" + point);
		sc_la.setBounds(295, 500, 0, 0);
		sc_la.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				pacmanboard.dispose();	
			}
			
		});
		add(sc_la);
		*/
		win_la.setBounds(0,FRAME_HEIGHT/2-30,0,0);
		add(win_la);
		defeated_la.setBounds(0,FRAME_HEIGHT/2-30,0,0);
		add(defeated_la);
		
	//	sc_la.setBounds(400,500,0,0);
	//	add(sc_la);
		
	//	random = new Random();

		pacmanH=12;  pacmanW=7;  enemyH=5;  enemyW=7;  numOfDot=79;  start=2;

		for (int i=0; i<14; i++) {
			for(int j=0; j<14; j++) {
				f[i][j] = new JLabel();
			}
		}
		KListener listener = new KListener();

		button.addActionListener(new Blistener());

		
		setLayout(null);
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(14,14));
		panel.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
		
		
		
		addKeyListener(new KListener());
		for(int i=0; i<14; i++) {
			for(int j=0; j<14; j++) {
				f[i][j].setIcon(wall);
				f[i][j].addKeyListener(listener);
				panel.add(f[i][j]);
			}
		}
		f[1][1].setIcon(smallDot);	f[2][1].setIcon(smallDot);	f[3][1].setIcon(smallDot);	f[4][1].setIcon(smallDot);	f[5][1].setIcon(smallDot);
		f[5][2].setIcon(smallDot);	f[5][3].setIcon(smallDot);	f[1][3].setIcon(smallDot);	f[2][3].setIcon(smallDot);	f[3][3].setIcon(smallDot);
		f[4][3].setIcon(smallDot);	f[1][4].setIcon(smallDot);	f[1][5].setIcon(smallDot);	f[1][6].setIcon(smallDot);	f[1][7].setIcon(smallDot);
		f[1][8].setIcon(smallDot);	f[1][9].setIcon(smallDot);	f[1][10].setIcon(smallDot);	f[1][11].setIcon(smallDot);	f[1][12].setIcon(smallDot);
		f[2][9].setIcon(smallDot);	f[2][12].setIcon(smallDot);	f[3][12].setIcon(smallDot);	f[4][12].setIcon(smallDot);	f[5][12].setIcon(smallDot);
		f[3][4].setIcon(smallDot);	f[3][5].setIcon(smallDot);	f[3][10].setIcon(smallDot);	f[3][11].setIcon(smallDot);
		f[4][5].setIcon(smallDot);	f[4][6].setIcon(smallDot);	f[4][7].setIcon(smallDot);	f[4][8].setIcon(smallDot);	f[4][9].setIcon(smallDot);
		f[4][10].setIcon(smallDot);	f[2][7].setIcon(smallDot);	f[3][7].setIcon(smallDot);	f[4][10].setIcon(smallDot);	f[4][11].setIcon(smallDot);
		f[5][11].setIcon(smallDot);	f[6][11].setIcon(smallDot);	f[7][11].setIcon(smallDot);	f[7][12].setIcon(smallDot);	f[8][12].setIcon(smallDot);
		f[9][12].setIcon(smallDot);	f[10][12].setIcon(smallDot);f[11][12].setIcon(smallDot);	f[5][7].setIcon(enemy);	f[6][2].setIcon(smallDot);
		f[7][1].setIcon(smallDot);	f[9][11].setIcon(smallDot);	f[11][1].setIcon(smallDot);	f[11][3].setIcon(smallDot);	f[11][11].setIcon(smallDot);
		f[7][2].setIcon(smallDot);	f[7][3].setIcon(smallDot);	f[8][1].setIcon(smallDot);	f[9][1].setIcon(smallDot);	f[9][2].setIcon(smallDot);
		f[9][3].setIcon(smallDot);	f[9][4].setIcon(smallDot);	f[9][5].setIcon(smallDot);	f[9][6].setIcon(smallDot);	f[12][1].setIcon(smallDot);
		f[12][2].setIcon(smallDot);	f[12][3].setIcon(smallDot);	f[12][4].setIcon(smallDot);	f[12][5].setIcon(smallDot);	f[12][9].setIcon(smallDot);
		f[12][10].setIcon(smallDot);f[12][11].setIcon(smallDot); f[10][5].setIcon(smallDot); f[11][5].setIcon(smallDot); f[10][6].setIcon(smallDot);
		f[10][7].setIcon(smallDot);	f[10][8].setIcon(smallDot); f[10][9].setIcon(smallDot); f[11][7].setIcon(smallDot); f[12][7].setIcon(pacman);
		f[9][8].setIcon(smallDot); f[9][9].setIcon(smallDot); f[11][9].setIcon(smallDot); f[6][5].setIcon(empty);f[6][6].setIcon(empty);
		f[6][7].setIcon(wall); f[6][8].setIcon(empty);f[6][9].setIcon(empty); f[7][5].setIcon(empty);f[7][6].setIcon(empty);
		f[7][7].setIcon(empty); f[7][8].setIcon(empty);f[7][9].setIcon(empty);

		add(panel);
		setTitle("�ѸǤ�");
		setVisible(true);
		setSize(FRAME_WIDTH+10, FRAME_HEIGHT+30);
		
		
		m_bgm = new ManageBGM();
		m_bgm.playBGM(0);

		en = new Enemy();	
		en.start();
		de = new ResultEffect();
		de.start();
		
		win = crushed = false;
		
		addWindowListener(this);
		requestFocus();
	}
	
	/*class panel extends JPanel{

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor(Color.PINK);
			g.drawString("���� ���̼�" +point, 100, 200);
			
		}
		
	}
	*/
	
	public void windowActivated(WindowEvent e) {}
	public void windowClosed(WindowEvent e) {
		System.out.println("Onclosing");
		if(en.isAlive())
    		en.interrupt();
    	if(de.isAlive())
    		de.interrupt();
        Main.pmain.setVisible(true);
        m_bgm.stopBGM();
	}
	public void windowClosing(WindowEvent e) {
		
	}
	public void windowDeactivated(WindowEvent e) {}
	public void windowDeiconified(WindowEvent e) {  }
	public void windowIconified(WindowEvent e) {  }
	public void windowOpened(WindowEvent e) {	}

	class Blistener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			System.exit(0);
		}
	}
	
	class KListener extends KeyAdapter{  //Ű�����Է¿� ���� KeyListener
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			if(win || crushed) return;
			switch(key) {
				case KeyEvent.VK_UP:
					if(!f[pacmanH-1][pacmanW].getIcon().equals(wall)) {
						if((pacmanH-1 == enemyH && pacmanW == enemyW)) {
							f[pacmanH][pacmanW].setIcon(empty);
							crushed = true;
						} else {
							if((f[pacmanH-1][pacmanW].getIcon()).equals(smallDot)){
								numOfDot--;
								m_bgm.playBGM(1);
							}
							f[pacmanH-1][pacmanW].setIcon(pacman);
							f[pacmanH][pacmanW].setIcon(empty);
							pacmanH--;
						}
					}
					
					break;
				case KeyEvent.VK_DOWN:
					if(!f[pacmanH+1][pacmanW].getIcon().equals(wall)) {
						if((pacmanH+1 == enemyH && pacmanW == enemyW)) {
							f[pacmanH][pacmanW].setIcon(empty);
							crushed = true;
						} else {
							if((f[pacmanH+1][pacmanW].getIcon()).equals(smallDot)){
								numOfDot--;
								m_bgm.playBGM(1);
							}
							f[pacmanH+1][pacmanW].setIcon(pacman);
							f[pacmanH][pacmanW].setIcon(empty);
							pacmanH++;
						}
					}
					break;
				case KeyEvent.VK_LEFT:
					if(!f[pacmanH][pacmanW-1].getIcon().equals(wall)) {
						if((pacmanH == enemyH && pacmanW-1 == enemyW)) {
							f[pacmanH][pacmanW].setIcon(empty);
							crushed = true;
						} else {
							if((f[pacmanH][pacmanW-1].getIcon()).equals(smallDot)){
								numOfDot--;
								m_bgm.playBGM(1);
							}
							f[pacmanH][pacmanW-1].setIcon(pacman);
							f[pacmanH][pacmanW].setIcon(empty);
							pacmanW--;				
						}
					}
					break;
				case KeyEvent.VK_RIGHT:
					if(!f[pacmanH][pacmanW+1].getIcon().equals(wall)) {
						if((pacmanH == enemyH && pacmanW-1 == enemyW)) {
							f[pacmanH][pacmanW].setIcon(empty);
							crushed = true;							
						} else {
							if((f[pacmanH][pacmanW+1].getIcon()).equals(smallDot)){
								numOfDot--;
								m_bgm.playBGM(1);
							}
							f[pacmanH][pacmanW+1].setIcon(pacman);
							f[pacmanH][pacmanW].setIcon(empty);
							pacmanW++;			
						}
					}
					break;
			}
			System.out.println(numOfDot);			
			if(numOfDot == 0) win = true;
		}
	}
	static ManageBGM m_bgm;
	
	class ResultEffect extends Thread{
		public void run(){
			try{
				while(!crushed && !win){
					sleep(100);
				}
				en.interrupt(); // ���� ����� ����
				pacmanboard.requestFocus(false);
				if(crushed){
					m_bgm.playBGM(2);
					System.out.println("DEFEATED");
					defeated_la.setSize(500, 100);
					for(int i=0;i<FRAME_WIDTH/2-100;i+=10){
						sleep(100);
						defeated_la.setLocation(i,FRAME_HEIGHT/2-30);
					}
				} else {
					m_bgm.playBGM(3); // �¸� bgm �߰�
					System.out.println("WIN");
					win_la.setSize(400, 100);
					for(int i=0;i<FRAME_WIDTH/2-100;i+=10){
						sleep(100);
						win_la.setLocation(i,FRAME_HEIGHT/2-30);
					}
				}
				btnTotitle.setSize(150,30);
			//	sc_la.setSize(150,30);
			}catch(InterruptedException e){ }
		}
	}
	
	class Enemy extends Thread {
		int dy[]={0,0,1,-1},dx[]={1,-1,0,0};//�����¿� ������
		Icon[] tIcon = new Icon[4];
		Icon under = empty;
		Icon t;
		public void run(){
			try{
				while(true){
					Thread.sleep(enemy_speed);
					
					int[][][] trace = new int[14][14][2]; // ��� �Դ��� �˷��ִ� ����, 0�� ��(����), 1�� ��(�ʺ�)
					//Ư�� ��ġi,j�� ���� ��� �Դ��� �˷��� 2�������� ǥ���ؾ���
					
					// BFS start
					Queue<Integer> Qy = new LinkedList<Integer>();
					Queue<Integer> Qx = new LinkedList<Integer>();
					Qy.offer(enemyH);
					Qx.offer(enemyW);
					
					for(int i=0;i<14;i++) 
						for(int j=0;j<14;j++)
							trace[i][j][0] = trace[i][j][1] = -2; // �ܼ� �ʱ�ȭ ����,-2�� �湮���� ������
					trace[enemyH][enemyW][0] = -1; // ù ��ġ�� -1�� �����Ͽ� ���߿� ������
					trace[enemyH][enemyW][1] = -1;
					while(!Qy.isEmpty() && !Qx.isEmpty()){
						int y = Qy.poll(),x = Qx.poll();
						
						for(int i=0;i<4;i++){
							int ty = y+dy[i], tx = x+dx[i];
							if(0<=ty && ty<14 && 0<=tx && tx < 14 && (!(f[ty][tx].getIcon()).equals(wall)) && trace[ty][tx][0] == -2) {
								trace[ty][tx][0] = y;
								trace[ty][tx][1] = x;
								Qy.offer(ty); Qx.offer(tx);
							}
						}
					}
					// BFS end
					
					// ��� ���� ������ ����
					int[][] trace2 = new int[14][14]; 
					for(int i=0;i<14;i++) for(int j=0;j<14;j++)
						trace2[i][j] = 0; // �ܼ� �ʱ�ȭ ����
					
					int tmpy,tmpx;
					trace2[pacmanH][pacmanW] = 1;
					for(int ty = trace[pacmanH][pacmanW][0],tx = trace[pacmanH][pacmanW][1]; ty!=-1 && tx!=-1;ty = trace[tmpy][tmpx][0], tx=trace[tmpy][tmpx][1]){ // �Ѹ����� ���� ������ ���󰣴�.
						trace2[ty][tx] = 1; // ���󰡸鼭 1�� ����, �̷����ϸ� �����¿� ���� �� �� ���⸸ 1�� ������
						tmpy = ty;
						tmpx = tx; // ������ ã�ư��� ���� ����
					}
					/*for(int i=0;i<14;i++) { 
						  for(int j=0;j<14;j++) System.out.print(trace2[i][j] + " ");
						 System.out.println();
						}*/
				/*	for(int i=0;i<14;i++) { 
						  for(int j=0;j<14;j++) System.out.println(trace2[i][j] + " ");
						 System.out.println();
						}*/
					
					for(int i=0;i<4;i++){ // ���� ���� ��ġ���� �����¿츦 �˻��Ͽ� 1�� ������ �̵��Ѵ�.
						int ty = enemyH+dy[i], tx = enemyW+dx[i];
						if(0<=ty && ty<14 && 0<=tx && tx < 14 && trace2[ty][tx] == 1){
							
							t = f[ty][tx].getIcon(); 
							f[ty][tx].setIcon(enemy);
							f[enemyH][enemyW].setIcon(under);
							under = t;
							enemyH = ty;
							enemyW = tx;
							if(ty == pacmanH && tx == pacmanW)
								crushed = true;
							break;
						}
					}
					//System.out.println("!!"+enemyH + ","+enemyW);
				}
			}catch (InterruptedException e){ 
				System.out.println("error or interrupted.");
			}
		}
	}
}

 class ManageBGM {
		static AudioInputStream g_ais, g_bgm_ais;
		static Clip g_clip,g_bgm_clip;
		
		public String musics[]={
			"sori/beginning.wav",
			"sori/eat.wav",
			"sori/gun.wav",
			"sori/win.wav"
		};
		
		public void playBGM(int idx) {
			try {
				g_ais = AudioSystem.getAudioInputStream(new File(musics[idx]));
				g_clip = AudioSystem.getClip();
				g_clip.open(g_ais);
				g_clip.start();
				if(idx == 0){
					g_clip.loop(Clip.LOOP_CONTINUOUSLY); // loop set.
					g_bgm_clip = g_clip;
					g_bgm_ais = g_ais;
				} else if(idx == 3 || idx == 2){
					g_bgm_ais.close(); // ������� ����
					g_bgm_clip.stop();
					g_bgm_clip.close();
				}
			} catch (Exception ex) { return; }
		}
		
		public void stopBGM(){
			try {
				g_ais.close();
				
				g_clip.stop();
				g_clip.close();
				
				g_bgm_ais.close();
				
				g_bgm_clip.stop();
				g_bgm_clip.close();
			} catch (Exception ex) { return; }
		}
		
 }