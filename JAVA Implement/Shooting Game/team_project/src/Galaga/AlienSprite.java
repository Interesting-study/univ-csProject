package Galaga;

import java.awt.Image;
import java.awt.Toolkit;

public class AlienSprite extends Sprite implements Runnable{
	public GalagaGame game;
	// 생성자
	public AlienSprite(GalagaGame game, Image image, int x, int y) {
		super(image, x, y);
		this.game = game;
	}
	@Override
	public void move() {
		if (((dx < 0) && (x < 10)) || ((dx > 0) && (x > 780))) {
			dx = -dx; // 방향바꿈
			y += 3; // 아래로내려옴
			if (y > 600) {
				game.endGame(); // 끝까지 내려오면 게임종료
			}
		} // end if
		super.move();
	}
}

class AlienSprite2 extends AlienSprite{
	private GalagaGame game;
	// 생성자
	public AlienSprite2(GalagaGame game, Image image, int x, int y) {
		super(game, image, x, y);
	}
	@Override
	public void move() {
		if (((dx < 0) && (x < 10)) || ((dx > 0) && (x > 780))) {
			dx = -dx; // 방향바꿈
			y += 40; // 아래로내려옴
			if (y > 600) {
				game.endGame(); // 끝까지 내려오면 게임종료
			}
		} // end if
		super.move();
	}
}

class AlienSprite3 extends AlienSprite{
	private GalagaGame game;
	// 생성자
	public AlienSprite3(GalagaGame game, Image image, int x, int y) {
		super(game, image, x, y);
		this.game = game;
	}
	@Override
	public void move() {
		if (((dx < 0) && (x < 10)) || ((dx > 0) && (x > 780))) {
			dx = -dx; // 방향바꿈
			y += 80; // 아래로내려옴
			if (y > 600) {
				game.endGame(); // 끝까지 내려오면 게임종료
			}
		} // end if
		super.move();
	}
}
