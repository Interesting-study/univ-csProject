class Shape{
	Shape next;
	Shape(){
		next = null;
	}
	void draw(){
		System.out.println("Shape");
	}
}
class Line extends Shape{

	@Override
	void draw() {
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

public class MethodOverridingEx {
	static void paint(Shape s){
		s.draw();
	}
	
	public static void main(String[] args) {
		Shape start, last, obj;
		
		start = new Line();
		last = start;
		obj = new Rect();
		last.next = obj;
		last = obj;
		obj = new Circle();
		last.next = obj;
		last = obj;
		
		Shape p = start;
		while(p != null){
			p.draw();
			p = p.next;
		}
	}
}