class A{
	A(){
		System.out.println("생성자 A");
	}
	A(int x){
		System.out.println("매개변수가 있는 생성자 A: "+x);
	}
}

class B extends A{
	B(){
		super(10);
		System.out.println("생성자 B");
	}
	B(int x){
		super(x);
		System.out.println("매개변수가 있는 생성자 B: "+x);
	}
}

public class SuperSubMat {

	public static void main(String[] args) {
		B objB = new B();

	}

}
