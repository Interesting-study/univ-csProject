class A{
	A(){
		System.out.println("持失切A");
	}
}
class B extends A{
	B(){
		System.out.println("持失切B");
	}
}
class C extends B{
	C(){
		System.out.println("持失切C");
	}
}
public class ConstructorEx {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		C objc = new C();
		B objb = new B();
		A obja = new A();
	}

}
