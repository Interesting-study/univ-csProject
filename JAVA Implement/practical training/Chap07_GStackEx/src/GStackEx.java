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
		if(top < 0)
			return null;
		top--;
		return (T)s[top];
	}
}
public class GStackEx {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GStack<Integer> s = new GStack<Integer>();
		s.push(10);
		s.push(20);
		s.push(30);
		
		int num = s.top;
		for(int i=0; i<num; i++)
			System.out.println(s.pop());
	}

}
