class A{
	A(){
		System.out.println("생성자A");
	}
	A(int x){
		System.out.println(x +" : 매개변수 있는 생성자A");
	}
	A(int x, int y){
		System.out.println(x+ ", " + y + " :  매개변수 2개 있는 생성자A");
	}
}
class B extends A{
	B(){
		System.out.println("생성자B");
	}
	B(int x){
		super(x,10);
		System.out.println(x + " : 매개변수 있는 생성자B");
	}
}
public class SuperMethodEx {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		B objb = new B(5);
		
	}

}
