//1
class Point{
	int x, y;
	
	Point(int x, int y){
		this.x = x; 
		this.y = y;
	}

	@Override
	public String toString() {
		
		return super.toString() + "("+ x + "," + y +")";
	}

	@Override
	public boolean equals(Object obj) {
		Point p = (Point)obj;
		if(x == p.x && y == p.y)
			return true;
		else
			return false;
	}
	
}
public class ObjEx {

	public static void main(String[] args) {
		Point p1 = new Point(2, 3);
		Point p2 = new Point(2, 3);
		Point p3 = p1;
		
		System.out.println(p1.toString());
		System.out.println(p1 == p2);
		System.out.println(p1 == p3);
		
		System.out.println(p1.equals(p2));
		System.out.println(p1.equals(p3));
	}

}
