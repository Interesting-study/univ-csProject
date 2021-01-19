import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

public class CollectionsEx {
	static void printList(LinkedList<String> l){
		Iterator<String> it = l.iterator();
		while(it.hasNext())
			System.out.println(it.next());
		System.out.println("+-------------+");
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LinkedList<String> list = new LinkedList<String>();
		list.add("터미네이터");
		list.add("스타워즈");
		list.add("아이언맨");
		list.add("토르");
		
		printList(list);
		Collections.reverse(list);
		printList(list);
		Collections.sort(list);
		printList(list);
		System.out.println(Collections.binarySearch(list, "스타워즈"));
	}

}
