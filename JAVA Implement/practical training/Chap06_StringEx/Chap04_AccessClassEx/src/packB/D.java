package packB;

import packA.A;

class C extends A{
	void setVal(){
		n1 = 10;
		n2 = 20;
		n3 = 30;
		n4 = 40;
	}
}

public class D {
	public int n1;
	int n2;
	private int n3;
	A objA = new A();
	
	void setVal(){
		objA.n1 = 10;
		objA.n2 = 20;
		objA.n3 = 30;
		objA.n4 = 40;
	}
	
}
