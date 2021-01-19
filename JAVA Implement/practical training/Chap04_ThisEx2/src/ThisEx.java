class A{
	String name;
	
	A(String name){
		this.name = name;
	}
	
	void printInfo(){
		System.out.println(name+":"+this);
	}
	
	A returnMe(){
		return this;
	}
}

public class ThisEx {

	public static void main(String[] args) {
		A obj1 = new A("obj1");
		
		obj1.printInfo();
		System.out.println(obj1.returnMe());

	}

}
