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
		return (T)s[--top];
	}
}
public class GenericStackEx {
	static <T> GStack<T> reverse(GStack<T> s){
		GStack<T> d = new GStack<T>();
		int num = s.top;
		for(int i=0; i<num; i++)
			d.push(s.pop());
		return d;
	}
	static <T> T searchMax(GStack<T> s, Object obj){
		T max = null;
		int num = s.top;
		
		if(obj instanceof Integer){
			Integer item;
			Integer i_max = -1;
			for(int i=0; i<num; i++){
				item = (Integer)s.pop();
				if(item > i_max)
					i_max = item;
			}
			max = (T)i_max;
		}
		if(obj instanceof String){
			String item;
			String s_max = null;
			int length = -1;
			for(int i=0; i<num; i++){
				item = (String)s.pop();
				if(item.length() > length){
					s_max = item;
					length = item.length();
				}	
			}
			max = (T)s_max;
		}
		return max;
	}//실행 예 : searchMax(s1, s1.s[0])
	/*
	 * static <T> T searchMax(GStack<T>)일 경우에는, if(s.s[0] instanceof ~)로 사용하면 된다.*/
	public static void main(String[] args){
		GStack<Integer> s1 = new GStack<Integer>();
		GStack<String> s2 = new GStack<String>();
		
		s1.push(10);
		s1.push(20);
		s1.push(30);
		s1 = reverse(s1);
		System.out.println(searchMax(s1, s1.s[0]));
		/*int num = s1.top;
		for(int i=0; i<num; i++)
			System.out.println(s1.pop());*/
		
		s2.push("C++");
		s2.push("java");
		s2.push("net");
		System.out.println(searchMax(s2, s2.s[0]));
	}
}
