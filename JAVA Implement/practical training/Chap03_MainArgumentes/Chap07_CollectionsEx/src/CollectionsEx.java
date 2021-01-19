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
		list.add("�͹̳�����");
		list.add("��Ÿ����");
		list.add("���̾��");
		list.add("�丣");
		
		printList(list);
		Collections.reverse(list);
		printList(list);
		Collections.sort(list);
		printList(list);
		System.out.println(Collections.binarySearch(list, "��Ÿ����"));
	}

}
