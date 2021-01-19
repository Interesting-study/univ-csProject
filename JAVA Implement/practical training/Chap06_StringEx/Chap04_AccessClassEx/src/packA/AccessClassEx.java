package packA;

import packB.D;

public class AccessClassEx {

	public static void main(String[] args) {
		A objA = new A();
		B objB = new B();
		D objD = new D();
		
		objA.n1 = 10;
		objA.n2 = 20;
		//objA.n3 = 30;
		
		objB.n1 = 10;
		objB.n2 = 20;
		//objB.n3 = 30;
		
		objD.n1 = 10;
		//objD.n2 = 20;
		//objD.n3 = 30;

	}

}
