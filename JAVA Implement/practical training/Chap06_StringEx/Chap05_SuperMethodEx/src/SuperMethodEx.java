class A{
	A(){
		System.out.println("������A");
	}
	A(int x){
		System.out.println(x +" : �Ű����� �ִ� ������A");
	}
	A(int x, int y){
		System.out.println(x+ ", " + y + " :  �Ű����� 2�� �ִ� ������A");
	}
}
class B extends A{
	B(){
		System.out.println("������B");
	}
	B(int x){
		super(x,10);
		System.out.println(x + " : �Ű����� �ִ� ������B");
	}
}
public class SuperMethodEx {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		B objb = new B(5);
		
	}

}
