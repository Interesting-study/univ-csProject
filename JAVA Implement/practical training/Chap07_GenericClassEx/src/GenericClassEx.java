class MyClass<T>{
	T val;
	void set(T val){
		this.val = val;
	}
	T get(){
		return val;
	}
}
public class GenericClassEx {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*MyClass<Integer> my = new MyClass<Integer>();
		my.set(7);
		System.out.println(my.get());*/
		MyClass<String> my = new MyClass<String>();
		my.set("java");
		System.out.println(my.get());
	}

}
