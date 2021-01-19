package PackB;

import PackA.Calculator;

interface Operation{
	double PI = 3.14;
	void calc();
}
class Calc extends Calculator{
	Calc(double a, double b){
		super(a,b);
	}

	@Override
	protected double mul(double a, double b) {
		return a*b;
	}

	@Override
	protected double power(double a, double b) {
		double p = 1.0;
		for(int i=0; i<b; i++)
			p*=a;
		return p;
	}
}
class Rect extends Calc implements Operation{
	double area;
	
	Rect(double a){
		super(a,a);
	}
	Rect(double a, double b){
		super(a, b);
	}
	@Override
	public void calc() {
		area = mul(a,b);
	}
	@Override
	protected void printInfo() {
		super.printInfo();
		calc();
		System.out.println("area : " + area);
	}
	
}
class Circle extends Calc implements Operation{
	double area;
	
	Circle(double a){
		super(a,a);
	}
	Circle(double a, double b){
		super(a, b);
	}
	@Override
	public void calc() {
		area = mul(PI,mul(a,b));
	}
	@Override
	protected void printInfo() {
		super.printInfo();
		calc();
		System.out.println("area : " + area);
	}

}
public class MidTest {
	static void isObject(Object obj){
		if(obj instanceof Rect)
			System.out.println("+----- 사각형 ----- +");
		if(obj instanceof Circle)
			System.out.println("+----- 원 ----- +");
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Rect r1 = new Rect(2.0);
		Rect r2 = new Rect(2.0, 3.0);
		Circle c1 = new Circle(2.0);
		Circle c2 = new Circle(2.0, 3.0);
		
		isObject(r1);
		r1.printInfo();
		
		isObject(r2);
		r2.printInfo();
		
		isObject(c1);
		c1.printInfo();
		
		isObject(c2);
		c2.printInfo();
	}

}
