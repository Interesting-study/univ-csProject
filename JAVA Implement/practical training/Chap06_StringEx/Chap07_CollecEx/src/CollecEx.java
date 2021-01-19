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
		l.add("��Ÿ����");
		l.add("�����¸�");
		l.add("���˵���");
		l.add("�丣");
		l.add(0, "��Ʈ��");
		
		printList(l);
		System.out.println(Collections.binarySearch(l, "�����¸�"));
		Collections.sort(l);
		printList(l);
		System.out.println(Collections.binarySearch(l, "�����¸�"));
		Collections.reverse(l);
		printList(l);
		System.out.println(Collections.binarySearch(l, "�����¸�"));//Q
		
	}
}