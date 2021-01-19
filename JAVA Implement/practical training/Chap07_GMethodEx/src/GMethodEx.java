class GStack<T>{
	int top;
	Object[] s;
	GStack(){
		top =0;
		s = new Object[10];
	}
	void push(T item){
		if(top >=10)
			return;
		s[top] = item;
		top++;
	}
	T pop(){
		T item;
		if(top <= 0)
			return null;
		item = (T)s[--top];
		return item;
	}
}
public class GMethodEx {
	static <T> GStack<T> reverse(GStack<T> s){
		GStack<T> d = new GStack<T>();
		T item; 
		while(true){
			item = s.pop();
			if(item == null)
				break;
			d.push(item);
		}
		return d;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GStack<Integer> s = new GStack<Integer>();
		s.push(10);
		s.push(20);
		s.push(30);
		s = reverse(s);
		
		int num = s.top;
		System.out.println(num);
		for(int i=0; i<3; i++)
			System.out.println(s.pop());
	}

}
