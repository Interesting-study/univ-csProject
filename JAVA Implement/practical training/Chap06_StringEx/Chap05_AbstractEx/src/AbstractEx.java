abstract class Shape{
	abstract void draw();
}
class Rect extends Shape{

	@Override
	void draw() {
		System.out.println("Rect");
	}
}

public class AbstractEx {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
