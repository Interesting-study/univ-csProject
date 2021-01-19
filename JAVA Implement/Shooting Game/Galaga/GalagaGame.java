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
	private boolean running = true; // true�� �� ������ ����, false�� �� �����������Ѵ�

	private ArrayList alienSprites = new ArrayList(); // �ܰ��ε��� ��ü�� ���� ��̸���Ʈ
	private ArrayList shotSprites = new ArrayList(); // �Ҳ��� ��ü�� ���� ��̸���Ʈ
	private Sprite starship; // ���ּ���ü
	// �̹����� �����ϱ� ���� ����
	private BufferedImage alienImage[] = new BufferedImage[3]; // �ܰ����̹���
	private BufferedImage shotImage[] = new BufferedImage[3]; // �Ҳ��̹���
	private Image lifeImage; // ������ �̹���
	private BufferedImage shipImage; // ���ּ��̹���

	// ������ �̹����� �ҷ���
	Image startImg = Toolkit.getDefaultToolkit().getImage("GalagaImg/start.png"); // ����ȭ����
																					// ����̹���
	Image spaceImg = Toolkit.getDefaultToolkit().getImage("GalagaImg/space.png"); // ����ȭ����
																					// ����̹���
	Image gameoverImg = Toolkit.getDefaultToolkit().getImage("GalagaImg/gameover.png");// ���ӿ���ȭ��
	// �̹���
	static final int MAX_SHOT = 15; // �ѹ��� �ִ� �� �� �ִ� �Ҳ��Ǽ�
	static final int MAX_ENEMY_SHOT = 5;

	// ȭ����ȯ�� ���� ��弳��
	static final int MAIN_MODE = 0; // ��ó��ȭ��
	static final int PLAY_MODE = 1; // ����������
	static final int GAME_OVER = 2; // ���ӿ���

	int currentMode = MAIN_MODE; // ������ ��带 ��Ÿ��. ������ ���θ��� ����

	static int ALIEN_TYPE = 0; // �ܰ��� ���� ����
	static int ALIEN_WIDTH = 4; // �����࿡ �ִ� �� �ܰ����� ����
	static int ALIEN_HEIGHT = 4; // �����࿡ �ִ� �� �ܰ����� ����
									// �� �ܰ����� ������ ALIEN_WIDTH x ALIEN_HEIGHT �̴�
	static int ALIEN_SPEED_LEVEL1 = -3; // �ܰ����� ������ �����̴� �ӵ�
	static int ALIEN_SPEED_LEVEL2 = -6;
	static int ALIEN_SPEED_LEVEL3 = -10;

	static int UFO_Level3_Divisor = 10;
	static int UFO_Level2_Divisor = 7;

	static AlienSprite3 alienLv3;
	static AlienSprite2 alienLv2;
	static AlienSprite alienLv1;

	static int UFO_Level1 = 0;// stage1������ UFO_level1�� ����
	static int UFO_Level2 = 0;// stage1������ UFO_level2�� ����
	static int UFO_Level3 = 0;// stage1������ UFO_level3�� ����
	// ������ ������ ����
	int score = 0;
	int level = 1;
	static int remainedLife = 3;
	// ����ȭ���� ��¦�̴� ���ڸ� ���� ��۽�ų ������
	byte flag = 1;
	byte toggle = 1;

	ShotEx shotEx = new ShotEx();
	ShotThread shotTH;
	public JFrame frame = new JFrame();
	// ������
	public GalagaGame() {
		System.out.println("������ ������");
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
		// ������ ��ü�� �̹����� �ҷ���
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

		requestFocus(); // Ű�Է��� �ޱ����� ��Ŀ���� ������ this.
		initSprites(); // ���ӿ� �ʿ��� ��ü���� �ʱ�ȭ�� this.
		addKeyListener(new MyKeyAdapter()); // Ű�̺�Ʈ������ �߰�
		Sound("GalagaImg/cheerup.wav", true);
		gameLoop(); // ���ӷ����� ����
	}

	// ȭ�鿡 ���� ��ü(���ּ�, �ܰ���, ������, �� �Ѿ�)���� �ʱ�ȭ
	private void initSprites() {
		int totAlien = ALIEN_HEIGHT * ALIEN_WIDTH;
		starship = new StarShipSprite(this, shipImage, 370, 500); // ���ּ��ʱ�ȭ
		int count = 1;

		UFO_Level1 = totAlien;
		UFO_Level3 = (int) Math.rint((double) totAlien / (double) UFO_Level3_Divisor);
		totAlien -= UFO_Level3;
		UFO_Level2 = (int) Math.rint((double) totAlien / (double) UFO_Level2_Divisor) + UFO_Level3;
		totAlien -= UFO_Level2;

		// �� �ܰ����� ������ŭ �ܰ��� �������ʱ�ȭ
		for (int y = 0; y < ALIEN_HEIGHT; y++) {
			for (int x = 0; x < ALIEN_WIDTH; x++) {
				ALIEN_SPEED_LEVEL1 = 3 - (int) (Math.random() * 7);
				// -3���� 3�����Ǽӵ��� ��������
				if (ALIEN_SPEED_LEVEL1 == 0)
					ALIEN_SPEED_LEVEL1 = -3; // �ӵ���0�̵Ǿ� �����ִ� ���� ����

				ALIEN_SPEED_LEVEL2 = 6 - (int) (Math.random() * 13);
				// -6���� 6������ �ӵ��� �������� ��´�
				if (ALIEN_SPEED_LEVEL2 == 0)
					ALIEN_SPEED_LEVEL2 = -10;

				ALIEN_SPEED_LEVEL3 = 10 - (int) (Math.random() * 21);
				// -10���� 10������ �ӵ��� ��������
				if (ALIEN_SPEED_LEVEL3 == 0)
					ALIEN_SPEED_LEVEL3 = -50;

				// �ܰ��� ��ü�� �� ������ŭ �����Ѵ�
				if (count <= UFO_Level3) {
					alienLv3 = new AlienSprite3(this, alienImage[2], 100 + (x * 50), 50 + (y * 30));
					alienLv3.setDx(ALIEN_SPEED_LEVEL3); // �������� ���� �ӵ����� ����
					alienSprites.add(alienLv3); // ��̸���Ʈ�� ��ü�� �߰�
					count++;
				} else if (count <= UFO_Level2 && count > UFO_Level3) {
					alienLv2 = new AlienSprite2(this, alienImage[1], 100 + (x * 50), 50 + (y * 30));
					alienLv2.setDx(ALIEN_SPEED_LEVEL2); // �������� ���� �ӵ����� ����
					alienSprites.add(alienLv2); // ��̸���Ʈ�� ��ü�� �߰�
					count++;
				} else if (count > UFO_Level2 && count <= UFO_Level1) {
					alienLv1 = new AlienSprite(this, alienImage[0], 100 + (x * 50), 50 + (y * 30));
					alienLv1.setDx(ALIEN_SPEED_LEVEL1); // �������� ���� �ӵ����� ����
					alienSprites.add(alienLv1); // ��̸���Ʈ�� ��ü�� �߰�
					count++;
				}
			} // end for
		} // end for
	}

	private void startGame() { // ���ӽ���
		alienSprites.clear();
		shotSprites.clear();// ��ü�� ����ִ� ��̸���Ʈ�� �����
		initSprites(); // ��ü�� �ʱ�ȭ
	}

	public void endGame() { // ��������
		// System.exit(0);
	}

	// �ش� �ܰ��ΰ�ü�� ��̸���Ʈ���� ����
	public void removeAlienSprite(Sprite sprite) {
		alienSprites.remove(sprite);
	}

	// �ش� �Ҳɰ�ü�� ��̸���Ʈ���� ����
	public void removeShotSprite(Sprite sprite) {
		shotSprites.remove(sprite);
	}

	// �Ҳ��� �� �� ȣ��Ǵ� �޼ҵ�
	public void fire() {
		// �Ҳ��� �ִ� ����(MAX_SHOT)��ŭ�� �� �� �ִ�
		if (shotSprites.size() < MAX_SHOT) {
			// �Ҳ� ��ü�� �����Ѵ�
			ShotSprite[] shot = new ShotSprite[3];
			for (int i = 0; i < shot.length; i++) {
				shot[i] = new ShotSprite(this, shotImage[i], starship.getX() + 15, starship.getY() - 30);
			}
			shotSprites.add(shot[0]); // ��ü�� ������ �� ��̸���Ʈ�� ���� -> ������ ������ �ٲ�κ�
		} else
			return; // �ִ밳�� �̻��ϰ�� �������� �ʴ´�
	}

	// ���� ��� �׿��� ��� ���� ������ �ö󰣴�
	// ������ �ö� �� ȣ��Ǵ� �޼ҵ�
	public void nextLevel() {
		level++; // �������÷���
		ALIEN_HEIGHT++; // ������ �÷���
		initSprites(); // ��ü �ʱ�ȭ
		UFO_Level3_Divisor--;
		UFO_Level2_Divisor--;

		if (UFO_Level3_Divisor < 2)
			UFO_Level3_Divisor = 2;
		if (UFO_Level2_Divisor < 2)
			UFO_Level2_Divisor = 2;
	}

	// ���ν����� ������ ������ 1�� �ʱ�ȭ���ִ� �޼ҵ�
	public void initLevel() {
		ALIEN_HEIGHT = 6; // ������ ������ �ʱⰪ���� ����
		ALIEN_SPEED_LEVEL1 = -3;
		ALIEN_SPEED_LEVEL2 = -10;
		ALIEN_SPEED_LEVEL1 = -50;
		// ������ �ӵ��� �ʱⰪ���� ����
		ALIEN_TYPE = 0;

		score = 0;
		level = 1; // �����ͷ����ʱ�ȭ
		remainedLife = 3;
		// shotEx = new ShotEx();
	}

	// ������ ����Ǵ� ����
	public void gameLoop() {
		// System.out.println("��������Ʈ ������ ����");
		// ����ȭ��
		while(true){
			while (currentMode == MAIN_MODE) {
				// System.out.println("��������Ʈ ������ ����(����)");
				// �÷��׸� ��� ��۽��������ν� ���ڰ� �����̵��� ��
				flag = (byte) (flag ^ toggle);

				repaint(); // �ٽñ׸���
				try {
					Thread.sleep(100); // 0.1�ʸ��� ���ڰ� �����̵���
				} catch (Exception e) {
				}
			}
			if (currentMode == PLAY_MODE && running) {
				shotTH = new ShotThread(shotEx.bullet, shotEx.bulletLa);
				shotTH.start();
			}
			// ��������ȭ��
			while (currentMode == PLAY_MODE && running) {
				// System.out.println("��������Ʈ ������ ����(����)");
				for (int i = 0; i < alienSprites.size(); i++) { // �ܰ��ε��� ��������
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
				for (int j = 0; j < shotSprites.size(); j++) { // �Ҳ��� ��������
					Sprite shot = (Sprite) shotSprites.get(j);
					shot.move();
				}
				starship.move(); // ���ּ��� ��������

				// �߻��� �Ҳɰ� �ܰ����� �浹 �˻�
				for (int p = 0; p < alienSprites.size(); p++) {
					// ��̸���Ʈ�κ��� �ܰ��� ��ü�� �ϳ��� ����
					Sprite alien = (Sprite) alienSprites.get(p);

					if (starship.checkCollision(alien) || shotEx.checkEnShotCollision(starship)) {
						remainedLife--;
						starship.x = 370;
						starship.y = 550;
						repaint();
						if (remainedLife == 0)
							currentMode = GAME_OVER; // ���ּ��� �ܰ����� �浹�ϸ� ���ӿ�����尡
														// �ȴ�.
						break;
					}
					for (int s = 0; s < shotSprites.size(); s++) {
						// ��̸���Ʈ�κ��� �Ҳ� ��ü�� �ϳ��� ����
						Sprite shot = (Sprite) shotSprites.get(s);

						// �ܰ��ΰ� �Ҳ��� �浹�˻�
						if (shot.checkCollision(alien)) {
							shot.handleCollision(alien);
							alien.handleCollision(shot);
							score += 100; // ���� ���߸� ���� 100�� ����
						} // end if // end if
					} // end for
				} // end for

				// ���� ��� ���̸� ����������
				if (alienSprites.size() == 0) {
					nextLevel();
				}

				repaint(); // �ٽñ׸���

				try {
					Thread.sleep(10);
				} catch (Exception e) {
				}
			} // end while

			// ���ӿ���ȭ��
			while (currentMode == GAME_OVER) {
				repaint();
			}
		}
	}// end gameLoop

	class MyPanel extends JPanel {
		// ��忡 ���� ȭ���� �ٲ㼭 �׷��ִ� �޼ҵ�
		// JFrame frame = new JFrame();
		@Override
		public void paint(Graphics g) {

			// currentMode �� ���� ����ġ��
			switch (currentMode) {
			case MAIN_MODE: // ����ȭ��
				// ����� �� �׸��� �׷���
				g.drawImage(startImg, 0, 0, this.getWidth(), this.getHeight(), this);
				g.setColor(Color.WHITE); // �۾� ���� ������� ����
				g.setFont(new Font("Consolas", Font.BOLD, 30)); // ��Ʈ�� ũ�⼳��
				if (flag == 1) // �÷��װ��� 1�� �� �۾��� ������(�����̴� ȿ���� ����)
					g.drawString("Press Space Key To Start", 190, 400);
				break;

			case PLAY_MODE: // ������ �÷������� ��
				// ��� �׷���
				g.drawImage(spaceImg, 0, 0, this.getWidth(), this.getHeight(), this);
				for (int i = 0; i < remainedLife; i++)
					g.drawImage(lifeImage, 5 + 30 * i, 20, 20, 20, this);
				for (int i = 0; i < alienSprites.size(); i++) {
					Sprite sprite = (Sprite) alienSprites.get(i);
					sprite.draw(g); // �ܰ��� ��ü�� �ϳ��� ������ �׸���
				}
				for (int j = 0; j < shotSprites.size(); j++) {
					Sprite shot = (Sprite) shotSprites.get(j);
					shot.draw(g); // �Ҳ� ��ü�� �ϳ��� ������ �׸���
				}
				starship.draw(g); // ���ּ��� �׸���

				for (int i = 0; i < shotEx.bullet.length; i++) {
					g.drawImage(shotEx.bullet[i].getImage(), shotEx.bulletLa[i].getX(), shotEx.bulletLa[i].getY(), 30,
							30, this);
				}

				g.setColor(Color.WHITE);
				g.setFont(new Font("Consolas", Font.PLAIN, 20));
				// ������ ������ life�� ǥ�����ش�
				g.drawString("SCORE : " + score, 5, 17); // score ������ �������� ���
				g.drawString("LEVEL " + level, 350, 17); // level ������ �������� ���
				g.drawString("ENTER: pause/restart ", 570, 17);

				// ShotThread shotTH = new ShotThread(shotEx.bullet,
				// shotEx.bulletLa);
				// shotTH.start();
				break;

			case GAME_OVER: // ���ӿ���
				g.drawImage(gameoverImg, 0, 0, this.getWidth(), this.getHeight(), this);
				g.setColor(Color.WHITE);
				g.setFont(new Font("Consolas", Font.PLAIN, 20));
				g.drawString("SCORE : " + score, 300, 340); // �� ������ �˷��ش�
				g.setFont(new Font("Consolas", Font.BOLD, 25));
				g.drawString("Press Space Key To Restart", 210, 380);
				break;

			}// end switch
		}
	}

	// Ű �̺�Ʈ ó���κ�
	class MyKeyAdapter extends KeyAdapter {
		// Ű�� ������ ��
		@Override
		public void keyPressed(KeyEvent e) {
			switch (currentMode) {
			case MAIN_MODE:
				if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					currentMode = PLAY_MODE; // ���θ�忡�� �����̽��ٸ� ������ �÷��̸��� �ٲ�
				}
				break;
			case PLAY_MODE:
				// Ű���尪�� ���� ��ü���� ������
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

				// ����Ű�� ������ ������ �����ϰų� �ٽý����Ҽ��ִ�
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (running)
						running = false;
					else
						running = true;
				}
				break;
			case GAME_OVER:
				
				if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					currentMode = MAIN_MODE; // ����ȭ�����ε��ư�
					initLevel(); // ������ �ʱ�ȭ����
					startGame(); // ���ӽ���
					gameLoop();
   				}
				break;
			}// end switch
		}

		// Ű���� ���� ���� ��
		@Override
		public void keyReleased(KeyEvent e) {
			// Ű���忡�� ���� ���� ���ּ��� �����ֵ��� �ӵ��� 0 ���� ����
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

	// ���������޼ҵ�
	public void Sound(String file, boolean Loop) {
		// ���������� �޾Ƶ鿩 �ش���带 �����Ų��.
		Clip clip;
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(new BufferedInputStream(new FileInputStream(file)));
			clip = AudioSystem.getClip();
			clip.open(ais);
			clip.start();
			if (Loop)
				clip.loop(-1);
			// Loop ����true�� ��������� ���ѹݺ���
			// false�� �ѹ������
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// main �޼ҵ� �վ���

	// ���θ޼ҵ�

}
