class Point{
	private int x,y;
	Point(){
		this.x = this.y =0;
	}
	Point(int x, int y){
		this.x = x;
		this.y = y;
	}
	void showPoint(){
		System.out.println(x+ ", " + y);
	}
}
class ColorPoint extends Point{
	private String color;
	ColorPoint(int x, int y, String color){
		super(x,y);
		this.color = color;
	}
	void showColorPoint(){
		showPoint();
		System.out.println(color);
	}
}
public class ColorSuperEx {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ColorPoint cp = new ColorPoint(5,10, "green");
		cp.showColorPoint();
	}

}
