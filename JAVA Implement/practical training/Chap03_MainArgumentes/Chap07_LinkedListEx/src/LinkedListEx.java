import java.util.Iterator;
import java.util.LinkedList;

class Stack{
	LinkedList<Integer> list;
	int top;
	Stack(){
		list = new LinkedList<Integer>();
		top =0;
	}
	void push(Integer item){
		list.addFirst(item);
		top++;
	}
	Integer pop(){
		top--;
		return list.getFirst();
	}
	void printStack(){
		Iterator<Integer> it = list.iterator();
		while(it.hasNext())
			System.out.println(it.next());
	}
}
public class LinkedListEx {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Stack s = new Stack();
		s.push(10);
		s.push(20);
		s.push(30);
		System.out.println(s.pop());
		s.printStack();
	}

}
