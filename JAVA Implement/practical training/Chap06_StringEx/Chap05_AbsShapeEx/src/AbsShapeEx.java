abstract class Shape{
	abstract void draw();
}
class Line extends Shape{

	@Override
	void draw() {
		System.out.println("Line");
	}
}
abstract class Rect extends Shape{
	
}
class Circle extends Shape{

	@Override
	void draw() {
		System.out.println("Circle");
	}
	
}
public class AbsShapeEx {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Shape s;
		//s = new Shape();
		Line l = new Line();
	}

}
