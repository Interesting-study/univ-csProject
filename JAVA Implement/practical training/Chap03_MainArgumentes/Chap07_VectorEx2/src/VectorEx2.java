import java.util.Vector;

class Point{
	int x, y;
	Point(int x, int y){
		this.x= x;
		this.y = y;
	}
	@Override
	public String toString() {
		return "(" + x +", " + y + ")";
	}
}
public class VectorEx2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Vector<Point> v = new Vector<Point>();
		v.add(new Point(2,3));
		v.add(new Point(3,4));
		v.add(new Point(4,5));
		for(int i=0; i<v.size(); i++)
			System.out.println(v.get(i));
	}

}
