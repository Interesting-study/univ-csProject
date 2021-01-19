import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

public class CollecEx {
	static void printList(LinkedList<String> l){
		Iterator<String> it = l.iterator();
		while(it.hasNext())
			System.out.println(it.next());
		System.out.println("-----------------");
	}
	public static void main(String[] args) {
		LinkedList<String> l = new LinkedList<String>();
		l.add("스타워즈");
		l.add("이프온리");
		l.add("범죄도시");
		l.add("토르");
		l.add(0, "노트북");
		
		printList(l);
		System.out.println(Collections.binarySearch(l, "이프온리"));
		Collections.sort(l);
		printList(l);
		System.out.println(Collections.binarySearch(l, "이프온리"));
		Collections.reverse(l);
		printList(l);
		System.out.println(Collections.binarySearch(l, "이프온리"));//Q
		
	}
}