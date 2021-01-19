import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

class Student{
	int id;
	String tel;
	
	Student(int id, String tel){
		this.id = id; this.tel = tel;
	}
}
public class HashMEx {
	static void search(HashMap<String, Student> h, String name){
		Student s = h.get(name);
		System.out.println(name + ":" + s.id + "," + s.tel);
	}
	static void printMap(HashMap<String, Student> h){
		Set<String> keys = h.keySet();
		Iterator<String> it = keys.iterator();
		while(it.hasNext()){
			String name = it.next();
			Student s = h.get(name);
			System.out.println(name + ":" + s.id + "," + s.tel);
		}
	}
	public static void main(String[] args) {
		HashMap<String, Student> h = new HashMap<String, Student>();
		h.put("hong", new Student(1, "010-9944-5813"));
		h.put("kim", new Student(2, "010-3344-2926"));
		h.put("park", new Student(3, "010-4958-1234"));
		h.put("moon", new Student(4, "010-7845-9851"));
		
		search(h, "park");
		printMap(h);
	}
}