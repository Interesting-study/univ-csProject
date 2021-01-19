class Point{
	private int x,y;
	public void setPoint(int x, int y){
		this.x = x;
		this.y = y;
	}
	public void showPoint(){
		System.out.println(x + ", " + y);
	}
}
class ColorPoint extends Point{
	String color;
	void setColor(String color){
		this.color = color;
	}
	void showColor(){
		System.out.println(color);
		showPoint();
	}
}
public class PointEx {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Point p = new Point();
		ColorPoint cp = new ColorPoint();
		
		p.setPoint(10,20);
		p.showPoint();
		cp.setPoint(30, 40);
		cp.setColor("red");
		cp.showColor();
	}

}
