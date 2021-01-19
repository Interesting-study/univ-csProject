package FindMine;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
//현기 팀과제 화이팅
public class MineGUI extends JPanel implements ActionListener {
	
	public int lv = Level.mid; 
	public int game = Game.ready;
	
	//사이즈 지뢰등등 변수
	int rows = 16;
	int cols = 16;
	int cell_size = 20;
	int mine = 40;
	int checkMine = 40;
	int flagCount = 0;
	
	
	//GUI 변수
	JLabel[] mineLabel;	//지뢰갯수 라벨
	JButton newButton;	//새게임 시작버튼
	JLabel[] timeLabel;	//게임시간 라벨
	Timer timer = new Timer(1000, this);	//시간
	int time = 0;
	Tiled[][] tile ;
	File file = null;	//전적기록파일
	JPanel northP ;
	JPanel gameP;
	
	
	//이미지 변수
	//이미지는 인터넷 검색 및 window xp 에 있는 지뢰찾기 그림을 가져다씀.
	ImageIcon[] numImgList;
	ImageIcon[] mineImgList;
	ImageIcon[] tileNumImgList;
	
	ImageIcon basicImage;
	ImageIcon clearImage;
	ImageIcon clickImage;
	ImageIcon failImage;
	ImageIcon defaultImage;
	ImageIcon closeImage;
	ImageIcon pressedImage;
	ImageIcon wildcardImage;
	ImageIcon flagImage;
	
	//이너클래스 변수 
	//각각 변수에 리스너 기능을 넣기
	TileActionListener[][] tileActionListener = null;	
	TileMouseListener[][] tileMouseListener = null;	
	
	//생성자
	MineGUI(int lv){
		//레벨지정
		if(lv == Level.row){
			rows = 9;
			cols = 9;
			mine = 9;
			checkMine = 9;
		}else if(lv == Level.mid){
			rows = 16;
			cols = 16;
			mine = 40;
			checkMine = 40;
		}else if(lv == Level.high){
			rows = 16;
			cols = 30;
			mine = 99;
			checkMine = 99;
		}
		
		this.lv = lv;
		setLayout(new BorderLayout());
		setImageLoading();
		setNorthPane();	//northP
		setGamePane();		//gameP
				
	}
	
	//이미지 메소드
	void setImageLoading(){
		numImgList = new ImageIcon[10];
		for(int i=0 ; i< numImgList.length ; i++)
			numImgList[i] = new ImageIcon("MineImg/" + i + "n.gif");
		
		basicImage = new ImageIcon("MineImg/basic.gif");
		clearImage = new ImageIcon("MineImg/clear.gif");
		clickImage = new ImageIcon("MineImg/click.gif");
		failImage = new ImageIcon("MineImg/fail.gif");
		
		mineImgList = new ImageIcon[3];
		for(int i=0 ; i<mineImgList.length ; i++)
			mineImgList[i] = new ImageIcon("MineImg/mine" + (i+1) + ".gif");
		
		tileNumImgList = new ImageIcon[7];
		for(int i=0 ; i<tileNumImgList.length ; i++)
			tileNumImgList[i] = new ImageIcon("MineImg/" + (i+1) + "s.gif");
		
		defaultImage = new ImageIcon("MineImg/default.gif");
		closeImage = new ImageIcon("MineImg/close.gif");
		pressedImage = new ImageIcon("MineImg/pressed.gif");
		wildcardImage = new ImageIcon("MineImg/wildcard.gif");
		flagImage = new ImageIcon("MineImg/flag.gif");
	}
	
	//위쪽 게임정보 패널
	void setNorthPane(){
		northP = new JPanel();
		northP.setLayout(new GridLayout(1, 3));
		JPanel lP = new JPanel();	//왼
		JPanel cP = new JPanel();	//중앙
		JPanel rP = new JPanel();	//오른
		JPanel minePanel = new JPanel();
		JPanel timePanel = new JPanel();
		
		minePanel.setLayout(new GridLayout(1, 3));
		timePanel.setLayout( new GridLayout(1, 3));
		
		//왼쪽에있는 남은 지뢰갯수 초기값 
		mineLabel = new JLabel[3];	
		if( mine == 9){	//초급
			mineLabel[0] = new JLabel( numImgList[0] );	//100자리
			mineLabel[1] = new JLabel( numImgList[0] );	//10자리
			mineLabel[2] = new JLabel( numImgList[9] );	//1자리
		}else if( mine == 40 ) {
			mineLabel[0] = new JLabel( numImgList[0] );	
			mineLabel[1] = new JLabel( numImgList[4] );	
			mineLabel[2] = new JLabel( numImgList[0] );
		}else if( mine == 99 ) {
			mineLabel[0] = new JLabel( numImgList[0] );	
			mineLabel[1] = new JLabel( numImgList[9] );	
			mineLabel[2] = new JLabel( numImgList[9] );
		}
		//왼쪽 패널붙이기
		minePanel.add( mineLabel[0] );
		minePanel.add(mineLabel[1] );
		minePanel.add(mineLabel[2] );
		lP.add(minePanel);
		
		//중앙 새게임 버튼 
		newButton = new JButton(basicImage);
		newButton.addActionListener( new NewButtonListener());
		newButton.setPreferredSize(new Dimension(20, 20));	
		cP.add( newButton );
		
		//오른쪽의 시간 정보 패널
		timeLabel = new JLabel[3];
		for( int i =0 ; i< 3; i++ ) {
			timeLabel[i] = new JLabel( numImgList[0]);
			timePanel.add(timeLabel[i] );
		}
		
		rP.add(timePanel);
		northP.add(lP);
		northP.add(cP);
		northP.add(rP);
		add(northP, BorderLayout.NORTH);
	}
	
	//게임 세팅 패널
	public void setGamePane(){
		gameP = new JPanel();
		gameP.setLayout(new GridLayout(rows, cols));
		gameP.setPreferredSize(new Dimension(cell_size*cols, cell_size*rows));
		setButtonTile();
		add(gameP, BorderLayout.CENTER);
		game = Game.ready;	//게임레디상태
	}
	
	public void setButtonTile(){
		int[][] map = configureMine();
		
		tile = new Tiled[rows][cols];
		tileActionListener = new TileActionListener[rows][cols];
		tileMouseListener = new TileMouseListener[rows][cols];
		
		for(int r = 0; r<rows ; r++){
			for(int c = 0; c<cols ; c++){
				tile[r][c] = new Tiled(map[r][c], r, c, closeImage);
				try{
					tileActionListener[r][c] = new TileActionListener();
					tile[r][c].addActionListener( tileActionListener[r][c] );
					tileMouseListener[r][c] = new TileMouseListener();
					tile[r][c].addMouseListener( tileMouseListener[r][c]);
					
					tile[r][c].setPressedIcon( pressedImage );	
					tile[r][c].setDisabledIcon( defaultImage );
					gameP.add(tile[r][c]);
				}catch(NullPointerException e){
					System.out.println(e.toString());
				}
			}
		}
	}
	
	
	//지뢰 랜덤 세팅 메서드
	public int[][] configureMine(){
		int count = 0;
		int[][] map = new int[rows][cols];
		
		//기본적으로 아무것도없이 세팅
		for(int r = 0; r<rows ; r++	)
			for(int c = 0; c<cols ; c++)
				map[r][c] = 0;
		
		
		/*
		 * 지뢰찾기 알고리즘 참고 부분
		 * http://soulduse.tistory.com/57
		 */
		
		int[] dx = { -1, -1, -1, 0, 0, 0, 1, 1, 1};
		int[] dy = { -1, 0, 1, -1, 0, 1, -1, 0, 1};
		
		while(true){
			int x = (int)(Math.random()* rows);
			int y = (int)(Math.random() * cols);
			if (map[x][y] != 0)	// 지뢰가 있으면 넘어감.
				continue;
			else{
				map[x][y] = -1;	//지뢰 세팅.
				count++;
			}
			
			if ( count == mine)	// 지뢰갯수 최대가 되면  반복문 나감.
				break;
		}
		
		for(int r = 0 ;r<rows ; r++){
			for(int c =0; c<cols ; c++){
				if(map[r][c] == -1 ){
					for(int i = 0; i<9 ; i++){
						try{	// 자기자신 과 외곽 부분 예외처리
							if( !(dx[i] == 0 && dy[i]==0) && map[r+dx[i]][c+dy[i]] != -1)	
								map[r+dx[i]][c+dy[i]]++;
						}catch(IndexOutOfBoundsException e){
							e.toString();
						}
					}
				}
			}
		}
		return map;
		
	}
	
	// 타일 열때.
	public void spaceTileOpen(int r, int c){
		if( !(validRange(r, c)) )	// 타일 값이 맞지 않으면
			return;
		if( tile[r][c].getState() == State.open )
			return ;
		if( tile[r][c].getFace() == -1 ) 
			return;
		if( tile[r][c].getState() != State.flag )
			tile[r][c].setState( State.open );
		else
			return;
		
		if(tile[r][c].getFace() != 0 && tile[r][c].getFace() != -1){
			tile[r][c].setState( State.open);
			return;
		}
		
		// 예외처리 아무것도 안하고 리턴한다. 그게아니라면
		
		spaceTileOpen(r-1 ,c);
		spaceTileOpen(r+1 ,c);
		spaceTileOpen(r ,c -1);
		spaceTileOpen(r ,c+1);
		spaceTileOpen(r-1 ,c-1);
		spaceTileOpen(r-1 ,c+1);
		spaceTileOpen(r+1 ,c-1);
		spaceTileOpen(r+1 ,c+1);
		
	}
	
	public boolean validRange(int r, int c){	//위치 값이 0보다크거나  내려가야지 참.
		return ( (r >= 0 && r< rows) && (c >=0 && c < cols) );
	}
	
	
	
	public void replaceTile(){	//각 버튼타일에 값을 지정
		for (int r = 0 ; r< rows; r++){
			for (int c = 0 ; c <cols; c++ ){
				if ( tile[r][c].getState() == State.open ){
					if (tile[r][c].getFace() != 0 && tile[r][c].getFace() != -1)	//지뢰가 없고 페이스가 0이아니면
						tile[r][c].setDisabledIcon(tileNumImgList[tile[r][c].getFace() - 1]);	
					tile[r][c].setEnabled(false);
				}
			}
		}
	}
	
	public void GameOver(){
		for(int r = 0; r<rows; r++){
			for(int c = 0; c<cols ; c++){
				tile[r][c].removeActionListener(tileActionListener[r][c]);
				tile[r][c].removeMouseListener(tileMouseListener[r][c]);
				
				if(tile[r][c].getFace() == -1){
					tile[r][c].setDisabledIcon(mineImgList[0]);
					tile[r][c].setEnabled(false);
					if(tile[r][c].getState() == State.flag )
						tile[r][c].setDisabledIcon(mineImgList[2]);
				}
			}
		}
	game = Game.end;
	}
	
	public void reStartGame(){
		remove(northP);
		remove(gameP);
		setVisible(false);
		setNorthPane();
		setGamePane();
		setVisible(true);
		
	}
	
	public void diplayMineCount(){
		int one = checkMine % 10;
		int ten = checkMine / 10;
		if ( checkMine < 0 ){
			one = Math.abs(checkMine % 10);
			ten = Math.abs(checkMine / 10);
			mineLabel[0].setIcon( new ImageIcon("Img/minus.gif"));
			if( checkMine <= 100 ){
				one = checkMine % 100;
				ten = Math.abs(one / 10 );
				one = Math.abs( one % 10);
			}
		}
		mineLabel[1].setIcon(numImgList[ten]);
		mineLabel[2].setIcon(numImgList[one]);
	}
	
	public boolean allCheckFlagMine(){	//지뢰 전부 검사하기.
		if ( game != Game.ready ){
			for(int r = 0 ; r< rows; r ++){
				for(int c = 0; c< cols ; c++){
					if(tile[r][c].getFace() != -1 && tile[r][c].getState() == State.flag)
						return false;
					if(tile[r][c].getFace() != -1 && tile[r][c].getState() != State.open)
						return false;
				}
			}
			return flagCount == mine;
		}else
			return false;
	}
	
	public void GameWin(){
		for(int r = 0; r<rows ; r++){
			for(int c=0 ; c<cols ; c++){
				tile[r][c].removeActionListener(tileActionListener[r][c]);
				tile[r][c].removeMouseListener(tileMouseListener[r][c]);
			}
		}
		timer.stop();
		newButton.setIcon(clearImage);
		if(lv == Level.row )
			file = new File("Row_Score.txt");
		if(lv == Level.mid)
			file = new File("Mid_Score.txt");
		if(lv == Level.high)
			file = new File("High_Score.txt");
		
		String text;
		if(file.exists() ){	//파일 존재 여부로 랭킹판단하기.
			try{
				BufferedReader reader = new BufferedReader(new FileReader(file) );
				text = reader.readLine();
				reader.close();
				if(text==null)
					updateScore();
				else{
					String[] score = text.split("/");
					if(Integer.parseInt( score[0] ) > time ) 
						updateScore();
					else{
						text = "기록 갱신 실패. 최고기록은 " + score[0] + "이다." ;
						JOptionPane.showMessageDialog(this, text, "End", JOptionPane.OK_CANCEL_OPTION);
					}
				}
			}catch(IOException e){
				System.out.println(e.toString());
			}
		}else{
			updateScore();
		}
		game = Game.end;
	}
	
	public void updateScore(){
		String name = JOptionPane.showInputDialog(this, "최고기록 달성 이름 입력해봐","기록 갱신",JOptionPane.OK_CANCEL_OPTION);
		String text;
		String score;
		try {
			FileWriter write = new FileWriter(file);
			score = time + "/" + name ;	// "/" 인자로 split 구분.
			write.write(score);
			write.close();
			
			String[] tmp = score.split("/");
			score = tmp[0] + "초" + tmp[1] + " 기록을 갱신  축하해요!!";
			JOptionPane.showMessageDialog(this, score, "End", JOptionPane.OK_OPTION, new ImageIcon("clear.gif"));
			
		}catch(IOException e){
			System.out.println(e.toString());
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(time == 1000){	//시간이 999초넘어가면..
			timer.stop();
			return;
		}
		
		time = time +1;
		int one = time %10;
		int ten = time / 10;
		int hund = 0;
		if(ten/10 != 0){
			hund = ten / 10;
			ten = ten % 10;
		}
		timeLabel[0].setIcon(numImgList[hund]);
		timeLabel[1].setIcon(numImgList[ten]);
		timeLabel[2].setIcon(numImgList[one]);
	}
	
	//마우스 양쪽 클릭시.
	public void aroundTilePressed(int r,int c){
		int [] dx = {-1,-1,-1,0,0,1,1,1};
		int [] dy = {-1,0,1,-1,1,-1,0,1};
		
		for(int i =0 ; i<8; i++){
			try{		//i값으로 클릭위치 주변을 다 검색한다.
				if(tile[r+dx[i]][c+dy[i]].getState() != State.flag && tile[r+dx[i]][c+dy[i]].getState() != State.open)
					tile[r+dx[i]][c+dy[i]].setIcon(pressedImage);
			}catch(IndexOutOfBoundsException e){
				System.out.println(e.toString());
			}
		}
	}
	
	//마우스 양쪽 떌시.
	public void aroundTileReleased(int r, int c){
		int flagCnt = 0;	//주변에 지뢰를 검사해서 있으면 값 증가
		int [] dx = {-1,-1,-1,0,0,1,1,1};
		int [] dy = {-1,0,1,-1,1,-1,0,1};
		
		for(int i = 0; i< 8; i++){
			try{	//i값으로 클릭위치 주변을 다검색한다.
				if(tile[r+dx[i]][c+dy[i]].getState() == State.flag )
					flagCnt++;
			}catch(IndexOutOfBoundsException e){
				System.out.println(e.toString());
			}
		}
		
		if(tile[r][c].getFace() == flagCnt){	//예시로 flagCnt가 3이면 face는 3이된다
			for(int i=0; i<8 ;i++){
				try{
					if(tile[r+dx[i]][c+dy[i]].getState() == State.close | tile[r+dx[i]][c+dy[i]].getState() == State.wildcard ){
						Tiled t = tile[r+dx[i]][c+dy[i]];
						if(t.getFace() == -1 ){	
							game = Game.end;
							t.setDisabledIcon(mineImgList[1]);
							timer.stop();
							game = Game.end;
							GameOver();
							return;
						}else if(t.getFace() != 0 && t.getFace() != -1){
							t.setState( State.open );
							t.setDisabledIcon( tileNumImgList[t.getFace() -1]);
							t.setEnabled(false);
						}else{
							spaceTileOpen(t.getRow(), t.getCol());
							replaceTile();
						}
					}
				}catch(IndexOutOfBoundsException e){
					System.out.println(e.toString());
				}
			}
		}else{
			for(int i =0 ; i <8 ; i++){
				try{
					if(tile[r+dx[i]][c+dy[i]].getState() != State.flag && tile[r+dx[i]][c+dy[i]].getState() != State.open)
						tile[r+dx[i]][c+dy[i]].setIcon(closeImage);
				}catch(IndexOutOfBoundsException e){
					System.out.println(e.toString());
				}
			}
		}
		
	}
	
	
	class NewButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			timer.stop();
			time = 0;
			checkMine = mine;
			flagCount = 0;
			reStartGame();
		}
		
	}
	
	class TileActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(game == Game.ready){
				timer.start();
				game = Game.start;
			}
			
			Tiled t = (Tiled)e.getSource();
			if(t.getState() == State.close | t.getState() == State.wildcard ){
				if(t.getFace() == -1){	//지뢰 밟으면.
					game = Game.end;
					t.setDisabledIcon(mineImgList[1]);
					timer.stop();
					game = Game.end;
					GameOver();
					return;
				}else if( t.getFace() != 0 && t.getFace() != -1){	// 지뢰 안밟으면.
					t.setState( State.open);
					t.setDisabledIcon(tileNumImgList[t.getFace() - 1]);	//숫자.
					t.setEnabled(false);
				}else{
					spaceTileOpen(t.getRow(), t.getCol());	//타일열림.
					replaceTile();
				}
			}
			if(allCheckFlagMine()){	//지뢰를 다찾으면이긴다.
				GameWin();
			}
		}
		
	}
	
	class TileMouseListener extends MouseAdapter {
		boolean bothLeft = false;
		boolean bothRight = false;
		//둘다 true 되면 양쪽마우스 클릭
		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			Tiled t = (Tiled)e.getSource();
			if( SwingUtilities.isLeftMouseButton(e) ){
				newButton.setIcon(clickImage);
				if(t.getState() == State.open)
					bothLeft = true;
			}
			if(SwingUtilities.isRightMouseButton(e) ){
				if(t.getState() != State.open){
					if(t.getState() == State.close ){
						t.setIcon(flagImage);
						t.setState(State.flag);
						checkMine -= 1;
						if(t.getFace() == -1 )
							flagCount++;
						newButton.setIcon(clickImage);
						diplayMineCount();
					}else if(t.getState() == State.flag ){
						t.setIcon(wildcardImage);
						t.setState(State.wildcard);
						checkMine += 1;
						if(t.getFace() == -1)
							flagCount--;
						newButton.setIcon(clickImage);
						diplayMineCount();
					}else {
						t.setIcon(closeImage);
						t.setState(State.close);
						newButton.setIcon(clickImage);
					}
				}
				if(allCheckFlagMine())
					GameWin();
				
				if(t.getState() == State.open)
					bothRight = true;
			}
			
			//마우스 동시클릭
			if(t.getState() == State.open ){
				if(bothRight == true && bothLeft == true )
					aroundTilePressed(t.getRow(), t.getCol());
			}
			
			
		}
		
		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			Tiled t = (Tiled)e.getSource();
			if(SwingUtilities.isLeftMouseButton(e) ){
				newButton.setIcon(basicImage);
				if(t.getState() == State.open)
					bothLeft = false;
			}
			
			if(SwingUtilities.isRightMouseButton(e) ){
				if(t.getState() == State.open)
					bothRight = false;
			}
			
			if(t.getState() == State.open){
				if(bothLeft == false && bothRight == false)
					aroundTileReleased(t.getRow(), t.getCol());
			}
			
			if(game == Game.end)
				newButton.setIcon(failImage);
		}
		
		
	}
}



