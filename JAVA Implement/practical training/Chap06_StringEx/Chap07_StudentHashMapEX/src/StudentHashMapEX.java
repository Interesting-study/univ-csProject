import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

class Student{
	int id;
	String tel;
	Student(int id, String tel){
		this.id = id;
		this.tel = tel;
	}
}
public class StudentHashMapEX {
	static void search(HashMap<String, Student> h, String key){
		Student s = h.get(key);
		if(s == null)
			System.out.println("no item");
		else
			System.out.println(key + " : "+s.id + ", "+s.tel);
	}
	static void printMap(HashMap<String, Student> h){
		Set<String> keys = h.keySet();
		Iterator<String> it = keys.iterator();
		while(it.hasNext()){
			Student s = h.get(it.next());
			System.out.println(s.id + ", "+s.tel);
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashMap<String, Student> h = new HashMap<String, Student>();
		h.put("kim", new Student(1, "010-111-2222"));
		h.put("hong", new Student(2, "010-222-3333"));
		h.put("choi", new Student(3, "010-333-4444"));
		h.put("jung", new Student(4, "010-444-5555"));
		
		search(h,"choi");
		printMap(h);
	}

}
