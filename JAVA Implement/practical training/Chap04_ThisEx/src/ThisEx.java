class A{
	String name;
	
	A(String name){
		this.name = name;
	}
	A returnMe(){
		return this;
	}
	void printInfo(){
		System.out.println(name + " -> " + this);
	}
}
public class ThisEx {
	public static void main(String[] args){
		A obj1 = new A("obj1");
		obj1.printInfo();
		System.out.println(obj1.returnMe());
	
		A obj2 = new A("obj2");
		System.out.println(obj2.returnMe());
		obj2.printInfo();
	}
}
