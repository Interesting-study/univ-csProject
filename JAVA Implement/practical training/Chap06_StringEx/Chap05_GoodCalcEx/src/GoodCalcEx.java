abstract class Calculator{
	abstract int add(int a, int b);
	abstract int sub(int a, int b);
	abstract float avg(int[] a);
	int mul(int a, int b){
		return a*b;
	}
}
class GoodCalc extends Calculator{

	@Override
	int add(int a, int b) {
		return a+b;
	}

	@Override
	int sub(int a, int b) {
		return a-b;
	}

	@Override
	float avg(int[] a) {
		int sum =0;
		for(int i=0; i<a.length; i++)
			sum += a[i];
		return sum/(float)a.length;
	}
	
}
public class GoodCalcEx {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GoodCalc c = new GoodCalc();
		int[] array = {1,2,3,4,5};
		System.out.println(c.add(10, 20));
		System.out.println(c.sub(10, 20));
		System.out.println(c.avg(array));
		
	}

}
