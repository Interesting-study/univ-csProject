import java.util.Vector;

public class VectorEx {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Vector<Integer> v = new Vector<Integer>(7);
		v.add(1);
		v.add(2);
		v.add(3);
		System.out.println(v.size());
		System.out.println(v.capacity());
		for(int i=0; i<v.size(); i++)
			System.out.println(v.get(i));
	}

}
