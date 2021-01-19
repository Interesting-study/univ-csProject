class Shape{
	double length, area, circum;
	Shape(double length){
		this.length = length;
	}
	void calArea(){
		System.out.println(length + " ->" +area);
	}
	void calCircum(){
		System.out.println(length + " -> " + circum);
	}
}
class Rect extends Shape{
	Rect(double length){
		super(length);
	}

	@Override
	void calArea() {
		area = length*length;
		super.calArea();
	}

	@Override
	void calCircum() {
		circum = length*4;
		super.calCircum();
	}
	
}
class Circle extends Shape{
	Circle(double length){
		super(length);
	}

	@Override
	void calArea() {
		area = 3.14*length*length;
		super.calArea();
	}

	@Override
	void calCircum() {
		circum = 2*3.14*length;
		super.calCircum();
	}
}
public class CalShapeEx {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Rect r = new Rect(2.0);
		Circle c = new Circle(3.0);
		
		r.calArea();
		r.calCircum();
		c.calArea();
		c.calCircum();
		//업캐스팅할때 아닐때 호출상황 따져보기!
	}

}
