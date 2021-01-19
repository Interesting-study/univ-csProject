package Galaga;

import java.awt.Image;
import java.awt.Toolkit;

public class AlienSprite extends Sprite implements Runnable{
	public GalagaGame game;
	// ������
	public AlienSprite(GalagaGame game, Image image, int x, int y) {
		super(image, x, y);
		this.game = game;
	}
	@Override
	public void move() {
		if (((dx < 0) && (x < 10)) || ((dx > 0) && (x > 780))) {
			dx = -dx; // ����ٲ�
			y += 3; // �Ʒ��γ�����
			if (y > 600) {
				game.endGame(); // ������ �������� ��������
			}
		} // end if
		super.move();
	}
}

class AlienSprite2 extends AlienSprite{
	private GalagaGame game;
	// ������
	public AlienSprite2(GalagaGame game, Image image, int x, int y) {
		super(game, image, x, y);
	}
	@Override
	public void move() {
		if (((dx < 0) && (x < 10)) || ((dx > 0) && (x > 780))) {
			dx = -dx; // ����ٲ�
			y += 40; // �Ʒ��γ�����
			if (y > 600) {
				game.endGame(); // ������ �������� ��������
			}
		} // end if
		super.move();
	}
}

class AlienSprite3 extends AlienSprite{
	private GalagaGame game;
	// ������
	public AlienSprite3(GalagaGame game, Image image, int x, int y) {
		super(game, image, x, y);
		this.game = game;
	}
	@Override
	public void move() {
		if (((dx < 0) && (x < 10)) || ((dx > 0) && (x > 780))) {
			dx = -dx; // ����ٲ�
			y += 80; // �Ʒ��γ�����
			if (y > 600) {
				game.endGame(); // ������ �������� ��������
			}
		} // end if
		super.move();
	}
}
