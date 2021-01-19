import java.util.Iterator;
import java.util.LinkedList;

class Stack{
	LinkedList<Integer> list;
	int top;
	Stack(){
		list = new LinkedList<Integer>();
		top = -1;
	}
	void push(Integer item){
		top++;
		list.addFirst(item);
	}
	Integer pop(){
		if(top < 0)
			return null;
		Integer item = list.getFirst();
		list.removeFirst();
		return item;//.get(top--);
	}
}
public class LinkedEx {

	public static void main(String[] args) {
		Stack s = new Stack();
		
		s.push(10);
		s.push(20);
		s.push(30);
		int n = s.top;
		for(int i=n; i >= 0; i--)
			System.out.println(s.pop());
		
	}
}