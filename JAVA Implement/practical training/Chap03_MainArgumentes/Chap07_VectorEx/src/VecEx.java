//7
import java.util.Vector;

public class VecEx {

	public static void main(String[] args) {
		Vector<Integer> v = new Vector<Integer>(2);
		
		v.add(1);
		v.add(2);
		v.add(3);
		System.out.println(v.size());
		System.out.println(v.capacity());
		
		for(int i=0; i<v.size(); i++)
			System.out.println(v.get(i));
	}
}