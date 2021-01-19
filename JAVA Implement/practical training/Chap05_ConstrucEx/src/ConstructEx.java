class A{
	A(){
		System.out.println("持失切 A");
	}
}

class B extends A{
	B(){
		System.out.println("持失切 B");
	}
}

class C extends B{
	C(){
		System.out.println("持失切 C");
	}
}

public class ConstructEx {

	public static void main(String[] args) {
		C objC = new C();

	}

}
