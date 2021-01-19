package Pack01;

import Pack02.C;

public class ClassAccessEx {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		A objA = new A();
		B objB = new B();
		C objC = new C();
		
		objA.n1 = 1;
		objA.n2 = 2;
		//objA.n3 = 3;
		
		objC.n1 = 1;
		//objC.n2 = 2;
		//objC.n3 = 3;
	}

}
