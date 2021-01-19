class Calc{
	static int add(int n1, int n2){
		return n1+n2;
	}
	static int sub(int n1, int n2){
		return n1-n2;
	}
	static int mul(int n1, int n2){
		return n1*n2;
	}
	static float div(int n1, int n2){
		return n1/(float)n2;
	}
}
public class CalcEx {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*Calc c= new Calc();
		
		System.out.println(c.add(5, 7));
		System.out.println(c.sub(40, 15));
		System.out.println(c.mul(4,3));
		System.out.println(c.div(20,6));*/
		
		System.out.println(Calc.add(7, 8));
		System.out.println(Calc.sub(40, 15));
		System.out.println(Calc.mul(4,3));
		System.out.println(Calc.div(20,6));
	}

}
