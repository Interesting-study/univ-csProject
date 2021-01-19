package Galaga;

import java.awt.Image;

public class StarShipSprite extends Sprite{
	public GalagaGame game;
	
	public StarShipSprite(GalagaGame game, Image image, int x, int y){//������
		super(image, x, y);
		this.game = game;
		dx = 0;
		dy = 0;
	}
	@Override
	public void move(){
		//��輱�� ����� �� ���̻� �������� ���ϰ���
		if((dx < 0) && (x < 10))
			return;	
		if((dx > 0) && (x > 750))
			return;	
		if((dy < 0) && (y < 10))
			return;	
		if((dy > 0) && (y > 550))
			return;	
		super.move();
	}
	@Override
	public void handleCollision(Sprite other){
		//���ּ��� ���� �浹�ϸ� ��������
		if(other instanceof AlienSprite){
			game.endGame();
		}
	}
}

