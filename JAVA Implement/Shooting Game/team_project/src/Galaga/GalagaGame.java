package Galaga;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import MainScreen.MainScreen;

public class GalagaGame extends JFrame {
	// JPanel panel = new JPanel();
	private boolean running = true; // true일 땐 게임을 진행, false일 땐 게임을정지한다

	private ArrayList alienSprites = new ArrayList(); // 외계인들의 객체를 담을 어레이리스트
	private ArrayList shotSprites = new ArrayList(); // 불꽃의 객체를 담을 어레이리스트
	private Sprite starship; // 우주선객체
	// 이미지를 저장하기 위한 공간
	private BufferedImage alienImage[] = new BufferedImage[3]; // 외계인이미지
	private BufferedImage shotImage[] = new BufferedImage[3]; // 불꽃이미지
	private Image lifeImage; // 라이프 이미지
	private BufferedImage shipImage; // 우주선이미지

	// 각각의 이미지를 불러옴
	Image startImg = Toolkit.getDefaultToolkit().getImage("GalagaImg/start.png"); // 메인화면의
																					// 배경이미지
	Image spaceImg = Toolkit.getDefaultToolkit().getImage("GalagaImg/space.png"); // 게임화면의
																					// 배경이미지
	Image gameoverImg = Toolkit.getDefaultToolkit().getImage("GalagaImg/gameover.png");// 게임오버화면
	// 이미지
	static final int MAX_SHOT = 15; // 한번에 최대 쏠 수 있는 불꽃의수
	static final int MAX_ENEMY_SHOT = 5;

	// 화면전환을 위한 모드설정
	static final int MAIN_MODE = 0; // 맨처음화면
	static final int PLAY_MODE = 1; // 게임진행중
	static final int GAME_OVER = 2; // 게임오버

	int currentMode = MAIN_MODE; // 현재의 모드를 나타냄. 시작은 메인모드로 설정

	static int ALIEN_TYPE = 0; // 외계인 종류 설정
	static int ALIEN_WIDTH = 4; // 가로축에 있는 총 외계인의 개수
	static int ALIEN_HEIGHT = 4; // 세로축에 있는 총 외계인의 개수
									// 총 외계인의 개수는 ALIEN_WIDTH x ALIEN_HEIGHT 이다
	static int ALIEN_SPEED_LEVEL1 = -3; // 외계인이 옆으로 움직이는 속도
	static int ALIEN_SPEED_LEVEL2 = -6;
	static int ALIEN_SPEED_LEVEL3 = -10;

	static int UFO_Level3_Divisor = 10;
	static int UFO_Level2_Divisor = 7;

	static AlienSprite3 alienLv3;
	static AlienSprite2 alienLv2;
	static AlienSprite alienLv1;

	static int UFO_Level1 = 0;// stage1에서의 UFO_level1의 갯수
	static int UFO_Level2 = 0;// stage1에서의 UFO_level2의 갯수
	static int UFO_Level3 = 0;// stage1에서의 UFO_level3의 갯수
	// 점수와 레벨과 생명
	int score = 0;
	int level = 1;
	static int remainedLife = 3;
	// 메인화면의 반짝이는 글자를 위해 토글시킬 변수들
	byte flag = 1;
	byte toggle = 1;

	ShotEx shotEx = new ShotEx();
	ShotThread shotTH;
	public JFrame frame = new JFrame();
	// 생성자
	public GalagaGame() {
		System.out.println("팀프의 갤러거");
		setTitle("Galaga Game");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		super.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
				//dispose();
				new MainScreen();
			}

		});
		Container contentPane = getContentPane();
		MyPanel panel = new MyPanel();

		contentPane.add(panel);

		setSize(800, 600);
		add(panel);
		//setResizable(false);
		setVisible(true);
		// 각각의 객체에 이미지를 불러옴
		try {
			shipImage = ImageIO.read(new File("GalagaImg/starship.png"));
			lifeImage = ImageIO.read(new File("GalagaImg/life.png"));
			for (int i = 0; i < shotImage.length; i++) {
				shotImage[i] = ImageIO.read(new File("GalagaImg/fire_flame_level" + (i + 1) + ".png"));
				alienImage[i] = ImageIO.read(new File("GalagaImg/ufo_level" + (i + 1) + ".png"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		requestFocus(); // 키입력을 받기위해 포커스를 맞춰줌 this.
		initSprites(); // 게임에 필요한 객체들을 초기화함 this.
		addKeyListener(new MyKeyAdapter()); // 키이벤트리스너 추가
		Sound("GalagaImg/cheerup.wav", true);
		gameLoop(); // 게임루프를 진행
	}

	// 화면에 나올 객체(우주선, 외계인, 라이프, 적 총알)들을 초기화
	private void initSprites() {
		int totAlien = ALIEN_HEIGHT * ALIEN_WIDTH;
		starship = new StarShipSprite(this, shipImage, 370, 500); // 우주선초기화
		int count = 1;

		UFO_Level1 = totAlien;
		UFO_Level3 = (int) Math.rint((double) totAlien / (double) UFO_Level3_Divisor);
		totAlien -= UFO_Level3;
		UFO_Level2 = (int) Math.rint((double) totAlien / (double) UFO_Level2_Divisor) + UFO_Level3;
		totAlien -= UFO_Level2;

		// 총 외계인의 개수만큼 외계인 생성및초기화
		for (int y = 0; y < ALIEN_HEIGHT; y++) {
			for (int x = 0; x < ALIEN_WIDTH; x++) {
				ALIEN_SPEED_LEVEL1 = 3 - (int) (Math.random() * 7);
				// -3부터 3까지의속도를 랜덤으로
				if (ALIEN_SPEED_LEVEL1 == 0)
					ALIEN_SPEED_LEVEL1 = -3; // 속도가0이되어 멈춰있는 것을 방지

				ALIEN_SPEED_LEVEL2 = 6 - (int) (Math.random() * 13);
				// -6부터 6까지의 속도를 랜덤으로 얻는다
				if (ALIEN_SPEED_LEVEL2 == 0)
					ALIEN_SPEED_LEVEL2 = -10;

				ALIEN_SPEED_LEVEL3 = 10 - (int) (Math.random() * 21);
				// -10부터 10까지의 속도를 랜덤으로
				if (ALIEN_SPEED_LEVEL3 == 0)
					ALIEN_SPEED_LEVEL3 = -50;

				// 외계인 객체를 총 개수만큼 생성한다
				if (count <= UFO_Level3) {
					alienLv3 = new AlienSprite3(this, alienImage[2], 100 + (x * 50), 50 + (y * 30));
					alienLv3.setDx(ALIEN_SPEED_LEVEL3); // 랜덤으로 얻은 속도값을 설정
					alienSprites.add(alienLv3); // 어레이리스트에 객체를 추가
					count++;
				} else if (count <= UFO_Level2 && count > UFO_Level3) {
					alienLv2 = new AlienSprite2(this, alienImage[1], 100 + (x * 50), 50 + (y * 30));
					alienLv2.setDx(ALIEN_SPEED_LEVEL2); // 랜덤으로 얻은 속도값을 설정
					alienSprites.add(alienLv2); // 어레이리스트에 객체를 추가
					count++;
				} else if (count > UFO_Level2 && count <= UFO_Level1) {
					alienLv1 = new AlienSprite(this, alienImage[0], 100 + (x * 50), 50 + (y * 30));
					alienLv1.setDx(ALIEN_SPEED_LEVEL1); // 랜덤으로 얻은 속도값을 설정
					alienSprites.add(alienLv1); // 어레이리스트에 객체를 추가
					count++;
				}
			} // end for
		} // end for
	}

	private void startGame() { // 게임시작
		alienSprites.clear();
		shotSprites.clear();// 객체가 들어있는 어레이리스트를 비워줌
		initSprites(); // 객체를 초기화
	}

	public void endGame() { // 게임종료
		// System.exit(0);
	}

	// 해당 외계인객체를 어레이리스트에서 삭제
	public void removeAlienSprite(Sprite sprite) {
		alienSprites.remove(sprite);
	}

	// 해당 불꽃객체를 어레이리스트에서 삭제
	public void removeShotSprite(Sprite sprite) {
		shotSprites.remove(sprite);
	}

	// 불꽃을 쏠 때 호출되는 메소드
	public void fire() {
		// 불꽃의 최대 개수(MAX_SHOT)만큼만 쏠 수 있다
		if (shotSprites.size() < MAX_SHOT) {
			// 불꽃 객체를 생성한다
			ShotSprite[] shot = new ShotSprite[3];
			for (int i = 0; i < shot.length; i++) {
				shot[i] = new ShotSprite(this, shotImage[i], starship.getX() + 15, starship.getY() - 30);
			}
			shotSprites.add(shot[0]); // 객체를 생성한 뒤 어레이리스트에 저장 -> 아이템 먹으면 바뀔부분
		} else
			return; // 최대개수 이상일경우 생성하지 않는다
	}

	// 적을 모두 죽였을 경우 다음 레벨로 올라간다
	// 레벨이 올라갈 때 호출되는 메소드
	public void nextLevel() {
		level++; // 레벨을올려줌
		ALIEN_HEIGHT++; // 적들을 늘려줌
		initSprites(); // 객체 초기화
		UFO_Level3_Divisor--;
		UFO_Level2_Divisor--;

		if (UFO_Level3_Divisor < 2)
			UFO_Level3_Divisor = 2;
		if (UFO_Level2_Divisor < 2)
			UFO_Level2_Divisor = 2;
	}

	// 새로시작할 때마다 레벨을 1로 초기화해주는 메소드
	public void initLevel() {
		ALIEN_HEIGHT = 6; // 적들의 개수를 초기값으로 설정
		ALIEN_SPEED_LEVEL1 = -3;
		ALIEN_SPEED_LEVEL2 = -10;
		ALIEN_SPEED_LEVEL1 = -50;
		// 적들의 속도를 초기값으로 설정
		ALIEN_TYPE = 0;

		score = 0;
		level = 1; // 점수와레벨초기화
		remainedLife = 3;
		// shotEx = new ShotEx();
	}

	// 게임이 진행되는 루프
	public void gameLoop() {
		// System.out.println("팀프로젝트 갤러거 들어옴");
		// 시작화면
		while(true){
			while (currentMode == MAIN_MODE) {
				// System.out.println("팀프로젝트 갤러거 들어옴(메인)");
				// 플래그를 계속 토글시켜줌으로써 글자가 깜빡이도록 함
				flag = (byte) (flag ^ toggle);

				repaint(); // 다시그린다
				try {
					Thread.sleep(100); // 0.1초마다 글자가 깜빡이도록
				} catch (Exception e) {
				}
			}
			if (currentMode == PLAY_MODE && running) {
				shotTH = new ShotThread(shotEx.bullet, shotEx.bulletLa);
				shotTH.start();
			}
			// 게임진행화면
			while (currentMode == PLAY_MODE && running) {
				// System.out.println("팀프로젝트 갤러거 들어옴(진행)");
				for (int i = 0; i < alienSprites.size(); i++) { // 외계인들을 움직여줌
					if (alienSprites.get(i) instanceof AlienSprite3) {
						AlienSprite3 sprite3 = (AlienSprite3) alienSprites.get(i);
						// Thread alienTh3 = new Thread(sprite3);
						// alienTh3.start();
						sprite3.move();
					} else if (alienSprites.get(i) instanceof AlienSprite2) {
						AlienSprite2 sprite2 = (AlienSprite2) alienSprites.get(i);
						// Thread alienTh2 = new Thread(sprite2);
						// alienTh2.start();
						sprite2.move();
					} else if (alienSprites.get(i) instanceof AlienSprite) {
						AlienSprite sprite1 = (AlienSprite) alienSprites.get(i);
						// Thread alienTh1 = new Thread(sprite1);
						// alienTh1.start();
						sprite1.move();
					}

				}
				for (int j = 0; j < shotSprites.size(); j++) { // 불꽃을 움직여줌
					Sprite shot = (Sprite) shotSprites.get(j);
					shot.move();
				}
				starship.move(); // 우주선을 움직여줌

				// 발사한 불꽃과 외계인의 충돌 검사
				for (int p = 0; p < alienSprites.size(); p++) {
					// 어레이리스트로부터 외계인 객체를 하나씩 꺼냄
					Sprite alien = (Sprite) alienSprites.get(p);

					if (starship.checkCollision(alien) || shotEx.checkEnShotCollision(starship)) {
						remainedLife--;
						starship.x = 370;
						starship.y = 550;
						repaint();
						if (remainedLife == 0)
							currentMode = GAME_OVER; // 우주선과 외계인이 충돌하면 게임오버모드가
														// 된다.
						break;
					}
					for (int s = 0; s < shotSprites.size(); s++) {
						// 어레이리스트로부터 불꽃 객체를 하나씩 꺼냄
						Sprite shot = (Sprite) shotSprites.get(s);

						// 외계인과 불꽃의 충돌검사
						if (shot.checkCollision(alien)) {
							shot.handleCollision(alien);
							alien.handleCollision(shot);
							score += 100; // 적을 맞추면 점수 100점 증가
						} // end if // end if
					} // end for
				} // end for

				// 적을 모두 죽이면 다음레벨로
				if (alienSprites.size() == 0) {
					nextLevel();
				}

				repaint(); // 다시그린다

				try {
					Thread.sleep(10);
				} catch (Exception e) {
				}
			} // end while

			// 게임오버화면
			while (currentMode == GAME_OVER) {
				repaint();
			}
		}
	}// end gameLoop

	class MyPanel extends JPanel {
		// 모드에 따라 화면을 바꿔서 그려주는 메소드
		// JFrame frame = new JFrame();
		@Override
		public void paint(Graphics g) {

			// currentMode 에 따른 스위치문
			switch (currentMode) {
			case MAIN_MODE: // 시작화면
				// 배경이 될 그림을 그려줌
				g.drawImage(startImg, 0, 0, this.getWidth(), this.getHeight(), this);
				g.setColor(Color.WHITE); // 글씨 색을 흰색으로 설정
				g.setFont(new Font("Consolas", Font.BOLD, 30)); // 폰트및 크기설정
				if (flag == 1) // 플래그값이 1일 때 글씨를 보여줌(깜빡이는 효과를 위해)
					g.drawString("Press Space Key To Start", 190, 400);
				break;

			case PLAY_MODE: // 게임을 플레이중일 때
				// 배경 그려줌
				g.drawImage(spaceImg, 0, 0, this.getWidth(), this.getHeight(), this);
				for (int i = 0; i < remainedLife; i++)
					g.drawImage(lifeImage, 5 + 30 * i, 20, 20, 20, this);
				for (int i = 0; i < alienSprites.size(); i++) {
					Sprite sprite = (Sprite) alienSprites.get(i);
					sprite.draw(g); // 외계인 객체를 하나씩 꺼내서 그린다
				}
				for (int j = 0; j < shotSprites.size(); j++) {
					Sprite shot = (Sprite) shotSprites.get(j);
					shot.draw(g); // 불꽃 객체를 하나씩 꺼내서 그린다
				}
				starship.draw(g); // 우주선을 그린다

				for (int i = 0; i < shotEx.bullet.length; i++) {
					g.drawImage(shotEx.bullet[i].getImage(), shotEx.bulletLa[i].getX(), shotEx.bulletLa[i].getY(), 30,
							30, this);
				}

				g.setColor(Color.WHITE);
				g.setFont(new Font("Consolas", Font.PLAIN, 20));
				// 점수와 레벨과 life를 표시해준다
				g.drawString("SCORE : " + score, 5, 17); // score 변수의 점수값을 출력
				g.drawString("LEVEL " + level, 350, 17); // level 변수의 레벨값을 출력
				g.drawString("ENTER: pause/restart ", 570, 17);

				// ShotThread shotTH = new ShotThread(shotEx.bullet,
				// shotEx.bulletLa);
				// shotTH.start();
				break;

			case GAME_OVER: // 게임오버
				g.drawImage(gameoverImg, 0, 0, this.getWidth(), this.getHeight(), this);
				g.setColor(Color.WHITE);
				g.setFont(new Font("Consolas", Font.PLAIN, 20));
				g.drawString("SCORE : " + score, 300, 340); // 총 점수를 알려준다
				g.setFont(new Font("Consolas", Font.BOLD, 25));
				g.drawString("Press Space Key To Restart", 210, 380);
				break;

			}// end switch
		}
	}

	// 키 이벤트 처리부분
	class MyKeyAdapter extends KeyAdapter {
		// 키를 눌렀을 때
		@Override
		public void keyPressed(KeyEvent e) {
			switch (currentMode) {
			case MAIN_MODE:
				if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					currentMode = PLAY_MODE; // 메인모드에서 스페이스바를 누르면 플레이모드로 바뀜
				}
				break;
			case PLAY_MODE:
				// 키보드값에 따라 객체들이 움직임
				int count = 0;
				if (e.getKeyCode() == KeyEvent.VK_LEFT)
					starship.setDx(-3);
				if (e.getKeyCode() == KeyEvent.VK_RIGHT)
					starship.setDx(+3);
				if (e.getKeyCode() == KeyEvent.VK_UP)
					starship.setDy(-3);
				if (e.getKeyCode() == KeyEvent.VK_DOWN)
					starship.setDy(+3);
				if (e.getKeyCode() == KeyEvent.VK_SPACE)
					fire();

				// 엔터키를 누르면 게임을 정지하거나 다시시작할수있다
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (running)
						running = false;
					else
						running = true;
				}
				break;
			case GAME_OVER:
				
				if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					currentMode = MAIN_MODE; // 시작화면으로돌아감
					initLevel(); // 레벨을 초기화해줌
					startGame(); // 게임시작
					gameLoop();
   				}
				break;
			}// end switch
		}

		// 키에서 손을 뗐을 때
		@Override
		public void keyReleased(KeyEvent e) {
			// 키보드에서 손을 떼면 우주선이 멈춰있도록 속도를 0 으로 설정
			if (e.getKeyCode() == KeyEvent.VK_LEFT)
				starship.setDx(0);
			if (e.getKeyCode() == KeyEvent.VK_RIGHT)
				starship.setDx(0);
			if (e.getKeyCode() == KeyEvent.VK_UP)
				starship.setDy(0);
			if (e.getKeyCode() == KeyEvent.VK_DOWN)
				starship.setDy(0);
		}
	}

	class ShotThread extends Thread {
		ImageIcon[] bullet = new ImageIcon[MAX_ENEMY_SHOT];
		int x;
		int y;
		JLabel[] bulletImg = new JLabel[MAX_ENEMY_SHOT];

		ShotThread(ImageIcon[] bullet, JLabel[] bulletImg) {
			this.bullet = bullet;
			this.bulletImg = bulletImg;
		}

		@Override
		public void run() {
			int dy = 18;
			int dx = 100;
			while (true) {
				repaint();
				for (int i = 0; i < MAX_ENEMY_SHOT; i++) {
					x = bulletImg[i].getX();
					y = bulletImg[i].getY();
					if (y > 750) {
						// Thread.sleep(1000);
						bulletImg[i].setLocation((x += dx), dy);
						break;
					}
					if (x > 750) {
						// Thread.sleep(1000);
						bulletImg[i].setLocation(5, dy);
					}
					y += dy;
					// Thread.sleep(1000);
					bulletImg[i].setLocation(x, y);
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
					}
				}
			}
		}
	}

	class ShotEx extends JFrame {
		ImageIcon[] bullet = new ImageIcon[MAX_ENEMY_SHOT];
		JLabel[] bulletLa = new JLabel[MAX_ENEMY_SHOT];
		int x = 100;
		int y = 10;

		ShotEx() {
			int randInt = (int) (Math.random() * 10) + 1;
			for (int i = 0; i < MAX_ENEMY_SHOT; i++) {
				bullet[i] = new ImageIcon("GalagaImg/bullet_level1.png");
				bulletLa[i] = new JLabel(bullet[i]);
				bulletLa[i].setLocation((randInt + x * i), (-300 + y * i));
				bulletLa[i].setSize(bullet[i].getIconWidth(), bullet[i].getIconHeight());
			}
		}

		public boolean checkEnShotCollision(Sprite other) {
			Rectangle myRect = new Rectangle();
			Rectangle otherRect = new Rectangle();
			boolean flag = false;
			otherRect.setBounds(other.getX(), other.getY(), other.getWidth(), other.getHeight());
			for (int i = 0; i < MAX_ENEMY_SHOT; i++) {
				myRect.setBounds(bulletLa[i].getX(), bulletLa[i].getY(), bullet[i].getIconWidth(),
						bullet[i].getIconHeight());
				flag = myRect.intersects(otherRect);
				if (flag == true)
					break;
			}
			return flag;
		}
	}

	// 사운드재생용메소드
	public void Sound(String file, boolean Loop) {
		// 사운드파일을 받아들여 해당사운드를 재생시킨다.
		Clip clip;
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(new BufferedInputStream(new FileInputStream(file)));
			clip = AudioSystem.getClip();
			clip.open(ais);
			clip.start();
			if (Loop)
				clip.loop(-1);
			// Loop 값이true면 사운드재생을 무한반복함
			// false면 한번만재생
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// main 메소드 잇엇음

	// 메인메소드

}
