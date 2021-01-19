class Shape{
	void draw(){
		System.out.println("shape");
	}
}
class Line extends Shape{
	void draw(){
		System.out.println("Line");
	}
}
class Rect extends Shape{
	@Override
	void draw() {
		System.out.println("Rect");
	}
}
class Circle extends Shape{
	@Override
	void draw() {
		System.out.println("Circle");
	}
	
}
public class MethodOverRidingEx {
	static void print(Shape s){
		s.draw();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		print(new Shape());
		print(new Line());
		print(new Rect());
		print(new Circle());
	}

}
