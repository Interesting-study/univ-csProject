package FindMine;
import javax.swing.Icon;
import javax.swing.JButton;

public class Tiled extends JButton { 
	int state = 0;
	int face = 0;
	int row = 0;
	int col = 0;
	
	public Tiled(int face, int row, int col, Icon icon){
		super(icon);
		this.state = State.close;	//기본으로 닫혀있는상태
		this.face = face;			//지뢰 여부.
		this.row = row;
		this.col = col;
		
	}
	
	public void setFace(int face){
		this.face = face;
	}
	public int getFace(){
		return face;
	}
	public int getRow(){
		return row;
	}
	public int getCol(){
		return col;
	}
	public void setState(int state){
		this.state = state;
	}
	public int getState(){
		return state;
	}
	
}
