class MyClass<T>{
	T val;
	void set(T val){
		this.val = val;
	}
	T get(){
		return val;
	}
}
public class GClassEx {

	public static void main(String[] args) {
		MyClass<Integer> my = new MyClass<Integer>();
		my.set(10);
		System.out.println(my.get());
		
		MyClass<String> my2 = new MyClass<String>();
		my2.set("hi");
		System.out.println(my2.get());
	}
}