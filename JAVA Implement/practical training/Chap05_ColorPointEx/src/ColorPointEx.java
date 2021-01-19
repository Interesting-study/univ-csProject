class Point{
	private int x, y;
	
	public void setPoint(int x, int y){
		this.x = x;
		this.y = y;
	}
	public void showPoint(){
		System.out.println(x+","+y);
	}
}

class ColorPoint extends Point{
	private String color;
	
	public void setColor(String color){
		this.color = color;
	}
	public void showColorPoint(){
		System.out.println(color);
		showPoint();
	}
}

public class ColorPointEx {

	public static void main(String[] args) {
		ColorPoint cp = new ColorPoint();
		
		//cp.x = 10;
		cp.setPoint(10, 20);
		cp.setColor("green");
		cp.showColorPoint();
	}
}