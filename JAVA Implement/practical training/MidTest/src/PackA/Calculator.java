package PackA;

public abstract class Calculator {
	protected double a;
	protected double b;
	
	protected Calculator(double a, double b){
		this.a = a;
		this.b = b;
	}
	protected void printInfo(){
		System.out.println("a : " + a + ", b  : " + b);
	}
	protected abstract double mul(double a, double b);
	protected abstract double power(double a, double b);
}
