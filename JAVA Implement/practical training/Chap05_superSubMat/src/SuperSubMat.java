class A{
	A(){
		System.out.println("������ A");
	}
	A(int x){
		System.out.println("�Ű������� �ִ� ������ A: "+x);
	}
}

class B extends A{
	B(){
		super(10);
		System.out.println("������ B");
	}
	B(int x){
		super(x);
		System.out.println("�Ű������� �ִ� ������ B: "+x);
	}
}

public class SuperSubMat {

	public static void main(String[] args) {
		B objB = new B();

	}

}
