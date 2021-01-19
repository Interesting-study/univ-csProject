class Shape{
	float length, area, circum;
	
	Shape(float length){
		this.length = length;
	}
	void calArea(){
		System.out.println(length+"->"+area);
	}
	void calCircum(){
		System.out.println(length+"->"+circum);
	}
}
class Rect extends Shape{
	Rect(float length){
		super(length);
	}

	@Override
	void calArea() {
		area = length * length;
		super.calArea();
	}

	@Override
	void calCircum() {
		circum = length * 4;
		super.calCircum();
	}
}
class Circle extends Shape{
	Circle(float length){
		super(length);
	}

	@Override
	void calArea() {
		area = 3.14f * length * length;
		super.calArea();
	}

	@Override
	void calCircum() {
		circum = 2.0f * 3.14f * length;
		super.calCircum();
	}
}
public class OverridingSuperEx {

	public static void main(String[] args) {
		Rect r = new Rect(2.0f);
		Circle c = new Circle(3.0f);
		
		r.calArea();
		r.calCircum();
		c.calArea();
		c.calCircum();
	}
}
