import java.util.Iterator;
import java.util.Vector;

class Point{
	int x, y;
	Point(int x, int y){
		this.x =x;
		this.y =y;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "(" + x + ", " + y + ")";
	}
}
public class PointVectorEx {
	static void printVector(Vector<Point> v){
		Iterator<Point> it = v.iterator();
		while(it.hasNext())
			System.out.println(it.next());
		/*for(int i=0; i<v.size(); i++)
			System.out.println(v.get(i));//PointÀÇ toString()È£Ãâ*/
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Vector<Point> v = new Vector<Point>();
		v.add(new Point(2,3));
		v.add(new Point(3,4));
		v.add(new Point(4,5));
		printVector(v);
	}

}
