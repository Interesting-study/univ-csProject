package Galaga;

import java.awt.Image;

public class StarShipSprite extends Sprite{
	public GalagaGame game;
	
	public StarShipSprite(GalagaGame game, Image image, int x, int y){//생성자
		super(image, x, y);
		this.game = game;
		dx = 0;
		dy = 0;
	}
	@Override
	public void move(){
		//경계선에 닿았을 때 더이상 움직이지 못하게함
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
		//우주선이 적과 충돌하면 게임종료
		if(other instanceof AlienSprite){
			game.endGame();
		}
	}
}

