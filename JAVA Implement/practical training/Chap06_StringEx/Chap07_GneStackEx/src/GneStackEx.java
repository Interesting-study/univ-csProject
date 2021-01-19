class GStack<T>{
	Object[] s;
	int top;
	GStack(){
		s = new Object[10];
		top = 0;
	}
	void push(T item){
		if(top == 10)
			return;
		s[top++] = item;
	}
	T pop(){
		if(top == 0)
			return null;
		top--;
		return (T)s[top];
	}
}
public class GneStackEx {
	static <T> GStack<T> reverse(GStack<T> s){
		GStack<T> d = new GStack<T>();
		while(true){
		T item = s.pop();
		if(item == null)
			break;
		d.push(item);
		}
		return d;
	}
	public static void main(String[] args) {
		GStack<Integer> s = new GStack<Integer>();
		s.push(10);
		s.push(20);
		s.push(30);
		
		int num = s.top;
		/*for(int i=0; i<num; i++)
			System.out.println(s.pop()+","+s.top);*///이게 있으면 s가 비어있음
		
		s = reverse(s);
		for(int i=0; i<num; i++)
			System.out.println(s.pop()+","+s.top);
		
		
/*		GStack<String> s2 = new GStack<String>();
		s2.push("hi");
		s2.push("yes");
		s2.push("bye");
		
		int num2 = s2.top;
		for(int i=0; i<num2; i++)
			System.out.println(s2.pop()+","+s2.top);*/
	}
}